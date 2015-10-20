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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
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
 * < <Descri��o da Classe>>
 * 
 * @author Rafael Corr�a
 */
public class AtualizarTarifaSocialAction extends GcomAction {
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("avancar");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarDadosTarifaSocialActionForm atualizarDadosTarifaSocialActionForm = (AtualizarDadosTarifaSocialActionForm) actionForm;
		
		String concluir = httpServletRequest.getParameter("concluir");
		
		if (concluir != null && !concluir.equals("")) {
			retorno = actionMapping
			.findForward("concluir");
		}
		
		Collection colecaoTarifaSocialHelper = (Collection) sessao.getAttribute("colecaoTarifaSocialHelper");
		
		Imovel imovelSessao = (Imovel) sessao.getAttribute("imovelTarifa");
		
		TarifaSocialHelper tarifaSocialHelperAtualizar = null;
		
		if (sessao.getAttribute("tarifaSocialHelperAtualizar") != null) {
			tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelperAtualizar");
		} else {
			tarifaSocialHelperAtualizar = new TarifaSocialHelper();
		}
		
		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = new TarifaSocialDadoEconomia();
		
		TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelper");
		
		// Seta os valores dentro do objeto
		String numeroCartaoProgramaSocial = atualizarDadosTarifaSocialActionForm.getNumeroCartaoProgramaSocial();
		String tipoCartaoProgramaSocial = atualizarDadosTarifaSocialActionForm.getTipoCartao();
		String dataValidadeCartao = atualizarDadosTarifaSocialActionForm.getDataValidadeCartao();
		String dataComparecimentoCarta = atualizarDadosTarifaSocialActionForm.getDataComparecimentoCarta();
		String numeroParcelas = atualizarDadosTarifaSocialActionForm.getNumeroMesesAdesao();
		String numeroContratoCompanhiaEletrica = atualizarDadosTarifaSocialActionForm.getNumeroContratoCelpe();
		Long numeroContratoCompanhiaEletricaFormatado = null;
		String consumoMedio = atualizarDadosTarifaSocialActionForm.getConsumoCelpe();
		Integer consumoMedioFormatado = null;
		String numeroIptu = atualizarDadosTarifaSocialActionForm.getNumeroIPTU();
		BigDecimal numeroIptuFormatado = null;
		String areaConstruida = atualizarDadosTarifaSocialActionForm.getAreaConstruida();
		BigDecimal areaConstruidaFormatada = null;
		String idAreaConstruidaFaixa = atualizarDadosTarifaSocialActionForm.getAreaConstruidaFaixa();
		String valorRenda = atualizarDadosTarifaSocialActionForm.getValorRendaFamiliar();
		BigDecimal valorRendaFormatada = null;
		String tipoRenda = atualizarDadosTarifaSocialActionForm.getRendaTipo();
		String motivoRevisao = atualizarDadosTarifaSocialActionForm.getMotivoRevisao();
		String numeroMoradores = atualizarDadosTarifaSocialActionForm.getNumeroMoradores();
		Short numeroMoradoresFormatado = null;
		Date dataCorrente = new Date();
		
		Integer idImovelTarifaSocial = null;
		
		// Id da Tarifa Social
		tarifaSocialDadoEconomia.setId(tarifaSocialHelper.getTarifaSocialDadoEconomia().getId());
		
		// Data de Implanta��o
		tarifaSocialDadoEconomia.setDataImplantacao(tarifaSocialHelper.getTarifaSocialDadoEconomia().getDataImplantacao());
		
		// N�mero do Cart�o do Programa Social
		if (numeroCartaoProgramaSocial != null && !numeroCartaoProgramaSocial.trim().equals("")) {
			tarifaSocialDadoEconomia.setNumeroCartaoProgramaSocial(new Long(numeroCartaoProgramaSocial));
		}
		
		// Tipo do Cart�o do Programa Social
		if (tipoCartaoProgramaSocial != null && !tipoCartaoProgramaSocial.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			TarifaSocialCartaoTipo tarifaSocialCartaoTipo = new TarifaSocialCartaoTipo();
			tarifaSocialCartaoTipo.setId(new Integer(tipoCartaoProgramaSocial));
			tarifaSocialDadoEconomia.setTarifaSocialCartaoTipo(tarifaSocialCartaoTipo);
			
			if (tarifaSocialHelper.getClienteImovel() != null) {
			
			// [FS0016] - Verificar duplicidade do Cart�o do Programa Social
				fachada.verificarDuplicidadeCartaoProgramaSocial(new Long(
						numeroCartaoProgramaSocial), tarifaSocialCartaoTipo,
						tarifaSocialHelper.getClienteImovel().getImovel().getId());
			
			}
		}
		
		// Data de Validade do Cart�o
		if (dataValidadeCartao != null && !dataValidadeCartao.trim().equals("")) {
			Date dataValidadeCartaoFormatada = Util.converteStringParaDate(dataValidadeCartao);
			
			if (dataCorrente.compareTo(dataValidadeCartaoFormatada) > 0) {
				throw new ActionServletException("atencao.data_validade.menor.data_corrente");
			}
			
			tarifaSocialDadoEconomia.setDataValidadeCartao(dataValidadeCartaoFormatada);
		}
		
		

		if (dataComparecimentoCarta != null && !dataComparecimentoCarta.trim().equals("")) {
			Date dataComparecimentoCartaFormatada = Util.converteStringParaDate(dataComparecimentoCarta);
			
			if (Util.compararData(dataComparecimentoCartaFormatada,dataCorrente) == 1) {
				throw new ActionServletException("atencao.tarifasocial.data_comparecimento_carta_maior_que_hoje");
			}
			
			tarifaSocialHelperAtualizar.setDataComparecimentoCarta(dataComparecimentoCartaFormatada);
		}
		
		
		// N�mero de Parcelas 
		if (numeroParcelas != null && !numeroParcelas.trim().equals("")) {
			tarifaSocialDadoEconomia.setNumeroMesesAdesao(new Short(numeroParcelas));
		}
		
		// Consumo M�dio da Companhia de Energia El�trica
		if (consumoMedio != null && !consumoMedio.trim().equals("")) {
			consumoMedioFormatado = new Integer(consumoMedio);
			tarifaSocialDadoEconomia.setConsumoCelpe(new Integer(consumoMedio));
		}
		
		// Valor da Renda Familiar
		if (valorRenda != null && !valorRenda.trim().equals("")) {
			valorRendaFormatada = Util.formatarMoedaRealparaBigDecimal(valorRenda);
			tarifaSocialDadoEconomia.setValorRendaFamiliar(valorRendaFormatada);
		}
		
		// Tipo da Renda
		if (tipoRenda != null && !tipoRenda.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			RendaTipo rendaTipo = new RendaTipo();
			rendaTipo.setId(new Integer(tipoRenda));
			if (tipoRenda.equals(RendaTipo.COMPROVADA.toString())) {
				rendaTipo.setDescricao("COMPROVADA");
			} else if (tipoRenda.equals(RendaTipo.DECLARADA.toString())) {
				rendaTipo.setDescricao("DECLARADA");
			}
			tarifaSocialDadoEconomia.setRendaTipo(rendaTipo);
		}
		
		// Motivo de Revis�o
		if (motivoRevisao != null && !motivoRevisao.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			TarifaSocialRevisaoMotivo tarifaSocialRevisaoMotivo = new TarifaSocialRevisaoMotivo();
			tarifaSocialRevisaoMotivo.setId(new Integer(motivoRevisao));
			tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(tarifaSocialRevisaoMotivo);
			tarifaSocialDadoEconomia.setDataRevisao(new Date());
		}
		
		Integer idMunicipio = null;
		
		Integer idImovel = null;
		
		// Caso seja de uma economia
		if (tarifaSocialHelper.getClienteImovel() != null) {
			
			ClienteImovel clienteImovel = tarifaSocialHelper.getClienteImovel();
			
			idImovelTarifaSocial = clienteImovel.getImovel().getId();
			
			SetorComercial setorComercial = clienteImovel.getImovel().getSetorComercial();
			
			idMunicipio = setorComercial.getMunicipio().getId();
			
			Imovel imovel = fachada.pesquisarImovelComSituacaoAguaEsgoto(idImovelTarifaSocial);
			
			imovel.setSetorComercial(setorComercial);
			
			// N�mero do IPTU
			if (numeroIptu != null && !numeroIptu.trim().equals("")) {
				numeroIptuFormatado = Util.formatarMoedaRealparaBigDecimal(numeroIptu);
				
				idImovel = fachada.verificarNumeroIptu(numeroIptuFormatado, idImovelTarifaSocial, null, idMunicipio);
				
				if (idImovel != null) {
					throw new ActionServletException("atencao.imovel.iptu_jacadastrado", null, idImovel.toString());
				}
				
				imovel.setNumeroIptu(numeroIptuFormatado);
			} else {
				imovel.setNumeroIptu(null);
			}
			
			// N�mero do Contrato da Companhia El�trica
			if (numeroContratoCompanhiaEletrica != null && !numeroContratoCompanhiaEletrica.trim().equals("")) {
				
				numeroContratoCompanhiaEletricaFormatado = new Long(numeroContratoCompanhiaEletrica);
				
				idImovel = fachada.verificarNumeroCompanhiaEletrica(numeroContratoCompanhiaEletricaFormatado, idImovelTarifaSocial, null);
				
				if (idImovel != null) {
					throw new ActionServletException("atencao.imovel.numero_celpe_jacadastrado", null, idImovel.toString());
				}
				
				imovel.setNumeroCelpe(numeroContratoCompanhiaEletricaFormatado);
			} else {
				imovel.setNumeroCelpe(null);
			}
			
			// �rea Constru�da
			if (areaConstruida != null && !areaConstruida.trim().equals("")) {
				areaConstruidaFormatada = Util.formatarMoedaRealparaBigDecimal(areaConstruida);
				imovel.setAreaConstruida(areaConstruidaFormatada);
			} else {
				imovel.setAreaConstruida(null);
			}
			
			// �rea Constru�da Faixa
			if (idAreaConstruidaFaixa != null && !idAreaConstruidaFaixa.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				AreaConstruidaFaixa areaConstruidaFaixa = new AreaConstruidaFaixa();
				areaConstruidaFaixa.setId(new Integer(idAreaConstruidaFaixa));
				imovel.setAreaConstruidaFaixa(areaConstruidaFaixa);
			} else {
				imovel.setAreaConstruidaFaixa(null);
			}
			
			// N�mero de Moradores
			if (numeroMoradores != null && !numeroMoradores.trim().equals("")) {
				numeroMoradoresFormatado = new Short(numeroMoradores);
				imovel.setNumeroMorador(numeroMoradoresFormatado);
			} else {
				imovel.setNumeroMorador(null);
			}
			
			tarifaSocialDadoEconomia.setImovel(imovel);
			clienteImovel.setImovel(imovel);
			tarifaSocialHelperAtualizar.setClienteImovel(clienteImovel);
			
			// [FS0008] - Verificar Preenchimento dos Campos
			fachada.verificarPreenchimentoManterDadosTarifaSocial(
				numeroContratoCompanhiaEletricaFormatado,
				areaConstruidaFormatada, numeroIptuFormatado,
				idImovelTarifaSocial, numeroCartaoProgramaSocial,
				dataValidadeCartao, numeroParcelas, consumoMedioFormatado,
				valorRendaFormatada, tipoCartaoProgramaSocial, tipoRenda);
		
		} 
		// Caso seja de m�ltiplas economias
		else {
			ClienteImovelEconomia clienteImovelEconomia = new ClienteImovelEconomia();
			
			idImovelTarifaSocial = tarifaSocialHelper.getClienteImovelEconomia().getImovelEconomia().getId();
			idMunicipio = tarifaSocialHelper.getClienteImovelEconomia()
					.getImovelEconomia().getImovelSubcategoria().getComp_id()
					.getImovel().getSetorComercial().getMunicipio().getId();
			
			ImovelEconomia imovelEconomia = fachada.pesquisarImovelEconomiaPeloId(idImovelTarifaSocial);
			imovelEconomia.setImovelSubcategoria(tarifaSocialHelper.getClienteImovelEconomia()
					.getImovelEconomia().getImovelSubcategoria());
		
			// N�mero do IPTU
			if (numeroIptu != null && !numeroIptu.trim().equals("")) {
				numeroIptuFormatado = Util.formatarMoedaRealparaBigDecimal(numeroIptu);
				
				if (colecaoTarifaSocialHelper != null && !colecaoTarifaSocialHelper.isEmpty()) {
					Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
					
					while (colecaoTarifaSocialHelperIterator.hasNext()) {
						
						TarifaSocialHelper tarifaSocialHelperVerificacao = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
								.next();

						// Verifica se o n�mero de contrato da companhia j� foi usado em outra economia do im�vel
						if (tarifaSocialHelperVerificacao
								.getClienteImovelEconomia().getImovelEconomia()
								.getNumeroIptu() != null
								&& tarifaSocialHelperVerificacao
										.getClienteImovelEconomia()
										.getImovelEconomia()
										.getNumeroIptu()
										.equals(
												numeroIptuFormatado)
								&& !tarifaSocialHelperVerificacao
										.getTarifaSocialDadoEconomia()
										.getId()
										.equals(
												tarifaSocialDadoEconomia
														.getId())) {
							throw new ActionServletException("atencao.imovel.iptu_jacadastrado", null, imovelSessao.getId().toString());
						}
					}
				}
				
				idImovel = fachada.verificarNumeroIptu(numeroIptuFormatado, null, idImovelTarifaSocial, idMunicipio);
				
				if (idImovel != null) {
					throw new ActionServletException("atencao.imovel.iptu_jacadastrado", null, idImovel.toString());
				}
				
				imovelEconomia.setNumeroIptu(numeroIptuFormatado);
				
			} else {
				imovelEconomia.setNumeroIptu(null);
			}
			
			// N�mero do Contrato da Companhia El�trica
			if (numeroContratoCompanhiaEletrica != null && !numeroContratoCompanhiaEletrica.trim().equals("")) {
				
				numeroContratoCompanhiaEletricaFormatado = new Long(numeroContratoCompanhiaEletrica);
				
				if (colecaoTarifaSocialHelper != null && !colecaoTarifaSocialHelper.isEmpty()) {
					Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
					
					while (colecaoTarifaSocialHelperIterator.hasNext()) {
						
						TarifaSocialHelper tarifaSocialHelperVerificacao = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
								.next();

						// Verifica se o n�mero de contrato da companhia j� foi usado em outra economia do im�vel
						if (tarifaSocialHelperVerificacao
								.getClienteImovelEconomia().getImovelEconomia()
								.getNumeroCelpe() != null
								&& tarifaSocialHelperVerificacao
										.getClienteImovelEconomia()
										.getImovelEconomia()
										.getNumeroCelpe()
										.equals(
												numeroContratoCompanhiaEletricaFormatado)
								&& !tarifaSocialHelperVerificacao
										.getTarifaSocialDadoEconomia()
										.getId()
										.equals(tarifaSocialDadoEconomia
														.getId())) {
							throw new ActionServletException("atencao.imovel.numero_celpe_jacadastrado", null, imovelSessao.getId().toString());
						}
					}
				}
				
				idImovel = fachada.verificarNumeroCompanhiaEletrica(numeroContratoCompanhiaEletricaFormatado, idImovelTarifaSocial, null);
				
				imovelEconomia.setNumeroCelpe(numeroContratoCompanhiaEletricaFormatado);
				
			} else {
				imovelEconomia.setNumeroCelpe(null);
			}
			
			// �rea Constru�da
			if (areaConstruida != null && !areaConstruida.trim().equals("")) {
				areaConstruidaFormatada = Util.formatarMoedaRealparaBigDecimal(areaConstruida);
				imovelEconomia.setAreaConstruida(areaConstruidaFormatada);
			} else {
				imovelEconomia.setAreaConstruida(null);
			}
			
			// �rea Constru�da Faixa
			if (idAreaConstruidaFaixa != null && !idAreaConstruidaFaixa.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				AreaConstruidaFaixa areaConstruidaFaixa = new AreaConstruidaFaixa();
				areaConstruidaFaixa.setId(new Integer(idAreaConstruidaFaixa));
				imovelEconomia.setAreaConstruidaFaixa(areaConstruidaFaixa);
			} else {
				imovelEconomia.setAreaConstruidaFaixa(null);
			}
			
			// N�mero de Moradores
			if (numeroMoradores != null && !numeroMoradores.trim().equals("")) {
				numeroMoradoresFormatado = new Short(numeroMoradores);
				imovelEconomia.setNumeroMorador(numeroMoradoresFormatado);
			} else {
				imovelEconomia.setNumeroMorador(null);
			}
			
			Imovel imovel = (Imovel) sessao.getAttribute("imovelTarifa");
			
			clienteImovelEconomia.setImovelEconomia(imovelEconomia);
			tarifaSocialDadoEconomia.setImovelEconomia(imovelEconomia);
			tarifaSocialDadoEconomia.setImovel(imovel);
			tarifaSocialHelperAtualizar.setClienteImovelEconomia(clienteImovelEconomia);
			
			// [FS0008] - Verificar Preenchimento dos Campos
			fachada.verificarPreenchimentoManterDadosTarifaSocialMultiplasEconomias(
				numeroContratoCompanhiaEletricaFormatado,
				areaConstruidaFormatada, numeroIptuFormatado,
				idImovelTarifaSocial, numeroCartaoProgramaSocial,
				dataValidadeCartao, numeroParcelas, consumoMedioFormatado,
				valorRendaFormatada, tipoCartaoProgramaSocial, tipoRenda);
			
		}
		
		httpServletRequest.setAttribute("telaLimpa", true);
		
		tarifaSocialHelperAtualizar.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
		
		sessao.setAttribute("tarifaSocialHelperAtualizar", tarifaSocialHelperAtualizar);

		return retorno;

	}
	
}	