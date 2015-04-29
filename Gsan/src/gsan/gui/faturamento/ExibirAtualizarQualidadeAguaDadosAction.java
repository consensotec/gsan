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
 * R�mulo Aur�lio de Melo Souza Filho
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
package gsan.gui.faturamento;

import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.FiltroQualidadeAgua;
import gsan.faturamento.FiltroQualidadeAguaPadrao;
import gsan.faturamento.QualidadeAgua;
import gsan.faturamento.QualidadeAguaPadrao;
import gsan.gui.GcomAction;
import gsan.operacional.FiltroFonteCaptacao;
import gsan.operacional.FonteCaptacao;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 16/09/2008
 */

public class ExibirAtualizarQualidadeAguaDadosAction extends GcomAction {

	/**
	 * Description of the Method
	 */
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarQualidadeAguaDadosAction");

		AtualizarQualidadeAguaActionForm form = (AtualizarQualidadeAguaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		if ( httpServletRequest.getParameter("manter") != null && 
				httpServletRequest.getParameter("manter").equals("sim") ) {
			
			sessao.setAttribute("PrimeiraVez",1);
		
			String idQualidadeAgua = null;
	
			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idQualidadeAgua = httpServletRequest
						.getParameter("idRegistroAtualizacao");
				
				sessao.setAttribute("idRegistroAtualizacao", idQualidadeAgua);
			}
	
			if (idQualidadeAgua == null) {
				if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
					idQualidadeAgua = (String) sessao
							.getAttribute("idRegistroAtualizacao");
				} else {
					idQualidadeAgua = (String) httpServletRequest.getAttribute(
							"idRegistroAtualizacao").toString();
				}
	
			} else {
				sessao.setAttribute("i", true);
			}
	
			QualidadeAgua qualidadeAgua = null;
	
			String idLocalidade = null;
	
			String codigoSetor = null;
	
			String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
	
			if (objetoConsulta != null && !objetoConsulta.equals("")) {
	
				// Validamos o cliente
				if (form.getIdLocalidade() != null
						&& !form.getIdLocalidade().trim().equals("")) {
					FiltroLocalidade filtro = new FiltroLocalidade();
	
					filtro.adicionarParametro(new ParametroSimples(
							FiltroLocalidade.ID, form.getIdLocalidade()));
	
					Collection colLocalidade = fachada.pesquisar(filtro,
							Localidade.class.getName());
	
					if (colLocalidade == null
							|| !colLocalidade.iterator().hasNext()) {
						// O cliente n�o existe
						form.setIdLocalidade("");
						form.setLocalidadeDescricao("Localidade inexistente");
						httpServletRequest.setAttribute("localidadeEncontrada",
								"exception");
	
					} else {
						Localidade localidade = (Localidade) Util
								.retonarObjetoDeColecao(colLocalidade);
						form.setIdLocalidade(localidade.getId().toString());
	
						form.setLocalidadeDescricao(localidade.getDescricao());
					}
				}else{
					form.setIdLocalidade("");
					form.setLocalidadeDescricao("");
				}
	
				if (form.getIdSetorComercial() != null
						&& !form.getIdSetorComercial().trim().equals("")) {
					// Validamos o imovel
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
	
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form
									.getIdSetorComercial()));
	
					Collection colSetorComercial = fachada.pesquisar(
							filtroSetorComercial, SetorComercial.class.getName());
	
					if (colSetorComercial == null || colSetorComercial.isEmpty()) {
						// O Imovel n�o existe
						form.setIdSetorComercial("");
						form
								.setSetorComercialDescricao("Setor Comercial inexistente");
	
						httpServletRequest.setAttribute(
								"codigoSetorComercialEncontrado", "exception");
	
					} else {
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colSetorComercial);
	
						form.setIdSetorComercial(setorComercial.getCodigo() + "");
	
						form.setSetorComercialDescricao(setorComercial
								.getDescricao());
					}
				}else{
					form.setIdSetorComercial("");
					form.setSetorComercialDescricao("");
				}
				
				if (form.getFonteCaptacao() != null && !form.getFonteCaptacao().equals("-1")){
					FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
					
					filtroFonteCaptacao.adicionarParametro(new ParametroSimples(
							FiltroFonteCaptacao.DESCRICAO, form
									.getFonteCaptacao()));
	
					Collection colFonteCaptacao = fachada.pesquisar(
							filtroFonteCaptacao, FonteCaptacao.class.getName());
	
					if (colFonteCaptacao != null || !colFonteCaptacao.isEmpty()) {
						
						FonteCaptacao fonteCaptacao = (FonteCaptacao) Util
								.retonarObjetoDeColecao(colFonteCaptacao);
	
						form.setFonteCaptacao(fonteCaptacao.getDescricao());
	
					}
				}else{
					form.setFonteCaptacao("");
				}
				
			} else {
	
				if (idQualidadeAgua != null && !idQualidadeAgua.trim().equals("")
						&& Integer.parseInt(idQualidadeAgua) > 0) {
	
					FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
					filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("localidade");
					filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
					filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
					filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
	
					filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
							FiltroQualidadeAgua.ID, idQualidadeAgua));
					Collection colecaoQualidadeAgua = fachada.pesquisar(
							filtroQualidadeAgua, QualidadeAgua.class.getName());
					if (colecaoQualidadeAgua != null
							&& !colecaoQualidadeAgua.isEmpty()) {
						qualidadeAgua = (QualidadeAgua) Util
								.retonarObjetoDeColecao(colecaoQualidadeAgua);
	
						// sessao.setAttribute("qualidadeAguaBase", qualidadeAgua);
					}
	
					if (qualidadeAgua.getLocalidade() != null
							&& (idLocalidade == null || idLocalidade.trim().equals(
									""))) {
	
						if (qualidadeAgua.getLocalidade() != null
								&& !qualidadeAgua.getLocalidade().getId()
										.toString().trim().equals("")) {
	
							idLocalidade = qualidadeAgua.getLocalidade().getId()
									.toString();
	
							FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	
							filtroLocalidade
									.adicionarParametro(new ParametroSimples(
											FiltroLocalidade.ID, idLocalidade));
	
							Collection colecaoLocalidade = fachada.pesquisar(
									filtroLocalidade, Localidade.class.getName());
	
							Localidade localidade = (Localidade) Util
									.retonarObjetoDeColecao(colecaoLocalidade);
	
							form.setIdLocalidade(localidade.getId().toString());
	
							form.setLocalidadeDescricao(localidade.getDescricao());
						}
					}else{
						form.setIdLocalidade("");
						form.setLocalidadeDescricao("");
					}
	
					if (qualidadeAgua.getSetorComercial() != null
							&& (codigoSetor == null || codigoSetor.trim()
									.equals(""))) {
	
						FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
	
						filtroSetorComercial
								.adicionarParametro(new ParametroSimples(
										FiltroSetorComercial.ID, qualidadeAgua
												.getSetorComercial().getId()
												.toString()));
	
						Collection colSetor = fachada.pesquisar(
								filtroSetorComercial, SetorComercial.class
										.getName());
	
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colSetor);
						form.setIdSetorComercial(setorComercial.getCodigo() + "");
						form.setSetorComercialDescricao(setorComercial
								.getDescricao());
	
					}else{
						form.setIdSetorComercial("");
						form.setSetorComercialDescricao("");
					}
					form.setIdQualidadeAgua(idQualidadeAgua);
					
					if(qualidadeAgua.getFonteCaptacao() != null){
						form.setFonteCaptacao(qualidadeAgua.getFonteCaptacao().getId()+"");	
					}
					
					if (qualidadeAgua.getSistemaAbastecimento() != null){
						
						form.setSistemaAbastecimento(qualidadeAgua.getSistemaAbastecimento().getDescricao());
						
					}else{
						
						form.setSistemaAbastecimento("");
						
					}
					
					form.setReferencia(
						Util.formatarAnoMesParaMesAno(qualidadeAgua.getAnoMesReferencia()));
	
					SistemaParametro sistemaParametro = fachada
							.pesquisarParametrosDoSistema();
	
					if (qualidadeAgua.getAnoMesReferencia().intValue() >= sistemaParametro
							.getAnoMesFaturamento().intValue()) {
						sessao.removeAttribute("desabilitaCampos");
					} else {
						sessao.setAttribute("desabilitaCampos", "S");
					}
					// dados do mes
					form.setIndiceMensalCloroResidual(qualidadeAgua
							.getNumeroCloroResidual() != null ? qualidadeAgua
							.getNumeroCloroResidual().toString() : "0");
					form
							.setIndiceMensalColiformesFecais(qualidadeAgua
									.getNumeroIndiceColiformesFecais() != null ? qualidadeAgua
									.getNumeroIndiceColiformesFecais().toString()
									: "0");
					form
							.setIndiceMensalColiformesTotais(qualidadeAgua
									.getNumeroIndiceColiformesTotais() != null ? qualidadeAgua
									.getNumeroIndiceColiformesTotais().toString()
									: "0");
					form
							.setIndiceMensalCor(qualidadeAgua.getNumeroIndiceCor() != null ? qualidadeAgua
									.getNumeroIndiceCor().toString()
									: "0");
					form
							.setIndiceMensalFerro(qualidadeAgua
									.getNumeroIndiceFerro() != null ? qualidadeAgua
									.getNumeroIndiceFerro().toString() : "0");
					form
							.setIndiceMensalFluor(qualidadeAgua
									.getNumeroIndiceFluor() != null ? qualidadeAgua
									.getNumeroIndiceFluor().toString() : "0");
					form
							.setIndiceMensalNitrato(qualidadeAgua
									.getNumeroNitrato() != null ? qualidadeAgua
									.getNumeroNitrato().toString() : "0");
					form
							.setIndiceMensalPH(qualidadeAgua.getNumeroIndicePh() != null ? qualidadeAgua
									.getNumeroIndicePh().toString()
									: "0");
					form.setIndiceMensalTurbidez(qualidadeAgua
							.getNumeroIndiceTurbidez() != null ? qualidadeAgua
							.getNumeroIndiceTurbidez().toString() : "0");
	
					form
							.setIndiceMensalColiformesTermotolerantes(qualidadeAgua
									.getNumeroIndiceColiformesTermotolerantes() != null ? qualidadeAgua
									.getNumeroIndiceColiformesTermotolerantes()
									.toString()
									: "0");
					
					form
							.setIndiceMensalAlcalinidade(qualidadeAgua
									.getNumeroIndiceAlcalinidade() != null ? qualidadeAgua
									.getNumeroIndiceAlcalinidade()
									.toString()
									: "0");
					
					//Dureza Total
					form.setDurezaTotal("0");
					if(qualidadeAgua.getIndiceDurezaTotal() != null 
							&& !qualidadeAgua.getIndiceDurezaTotal().equals("")){
						form.setDurezaTotal(qualidadeAgua.getIndiceDurezaTotal().toString());
					}
	
					// dados padrao
					FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
					Collection colPadrao = fachada.pesquisar(
							filtroQualidadeAguaPadrao, QualidadeAguaPadrao.class
									.getName());
					if (!colPadrao.isEmpty()) {
						QualidadeAguaPadrao qualidadeAguaPadrao = (QualidadeAguaPadrao) Util
								.retonarObjetoDeColecao(colPadrao);
	
						form.setPadraoCloroResidual(qualidadeAguaPadrao
								.getDescricaoPadraoCloro());
						form.setPadraoColiformesFecais(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesFecais());
						form.setPadraoColiformesTotais(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesTotais());
						form.setPadraoCor(qualidadeAguaPadrao
								.getDescricaoPadraoCor());
						form.setPadraoFerro(qualidadeAguaPadrao
								.getDescricaoPadraoFerro());
						form.setPadraoFluor(qualidadeAguaPadrao
								.getDescricaoPadraoFluor());
						form.setPadraoNitrato(qualidadeAguaPadrao
								.getDescricaoNitrato());
						form
								.setPadraoPH(qualidadeAguaPadrao
										.getDescricaoPadraoPh());
						form.setPadraoTurbidez(qualidadeAguaPadrao
								.getDescricaoPadraoTurbidez());
	
						form.setPadraoColiformesTermotolerantes(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesTermotolerantes());
						
						form.setPadraoAlcalinidade(qualidadeAguaPadrao.getDescricaoPadraoAlcalinidade());
						
						//Dureza Total Padr�o
						form.setDescricaoDurezaTotal("");
						if(qualidadeAguaPadrao.getDescricaoDurezaTotal() != null 
								&& !qualidadeAguaPadrao.getDescricaoDurezaTotal().equals("")){
							form.setDescricaoDurezaTotal(qualidadeAguaPadrao.getDescricaoDurezaTotal());
						}
	
						sessao.setAttribute("qualidadeAguaPadraoId",
								qualidadeAguaPadrao.getId());
	
						sessao.setAttribute("qualidadeAguaPadrao",
								qualidadeAguaPadrao);
					}
				}
	
				if (qualidadeAgua == null
						&& sessao.getAttribute("qualidadeAgua") != null) {
					qualidadeAgua = (QualidadeAgua) sessao
							.getAttribute("qualidadeAgua");
	
					if (qualidadeAgua.getLocalidade() != null
							&& (idLocalidade == null || idLocalidade.trim().equals(
									""))) {
	
						if (qualidadeAgua.getLocalidade() != null
								&& !qualidadeAgua.getLocalidade().getId()
										.toString().trim().equals("")) {
	
							idLocalidade = qualidadeAgua.getLocalidade().getId()
									.toString();
	
							FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	
							filtroLocalidade
									.adicionarParametro(new ParametroSimples(
											FiltroLocalidade.ID, idLocalidade));
	
							Collection colecaoLocalidade = fachada.pesquisar(
									filtroLocalidade, Localidade.class.getName());
	
							Localidade localidade = (Localidade) Util
									.retonarObjetoDeColecao(colecaoLocalidade);
	
							form.setIdLocalidade(localidade.getId().toString());
	
							form.setLocalidadeDescricao(localidade.getDescricao());
						}
					}
	
					if (qualidadeAgua.getSetorComercial() != null
							&& (codigoSetor == null || codigoSetor.trim()
									.equals(""))) {
	
						FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
	
						filtroSetorComercial
								.adicionarParametro(new ParametroSimples(
										FiltroSetorComercial.ID, qualidadeAgua
												.getSetorComercial().getId()
												.toString()));
	
						Collection colSetor = fachada.pesquisar(
								filtroSetorComercial, SetorComercial.class
										.getName());
	
						SetorComercial setorComercial = (SetorComercial) Util
								.retonarObjetoDeColecao(colSetor);
						form.setIdSetorComercial(setorComercial.getCodigo() + "");
						form.setSetorComercialDescricao(setorComercial
								.getDescricao());
	
					}
					form.setIdQualidadeAgua(idQualidadeAgua);
	
					if(qualidadeAgua.getFonteCaptacao() != null){
						form.setFonteCaptacao(qualidadeAgua.getFonteCaptacao().getId()+"");	
					}else{
						form.setFonteCaptacao("");
					}
					
					if(qualidadeAgua.getSistemaAbastecimento() != null){
						form.setSistemaAbastecimento(qualidadeAgua.getSistemaAbastecimento().getDescricao());
					}else{
						form.setSistemaAbastecimento("");
					}
	
					form.setReferencia(Util.formatarAnoMesParaMesAno(qualidadeAgua
							.getAnoMesReferencia()));
	
					SistemaParametro sistemaParametro = fachada
							.pesquisarParametrosDoSistema();
	
					if (qualidadeAgua.getAnoMesReferencia().intValue() >= sistemaParametro
							.getAnoMesFaturamento().intValue()) {
						sessao.removeAttribute("desabilitaCampos");
					} else {
						sessao.setAttribute("desabilitaCampos", "S");
					}
					// dados do mes
					form.setIndiceMensalCloroResidual(qualidadeAgua
							.getNumeroCloroResidual() != null ? qualidadeAgua
							.getNumeroCloroResidual().toString() : "0");
					form
							.setIndiceMensalColiformesFecais(qualidadeAgua
									.getNumeroIndiceColiformesFecais() != null ? qualidadeAgua
									.getNumeroIndiceColiformesFecais().toString()
									: "0");
					form
							.setIndiceMensalColiformesTotais(qualidadeAgua
									.getNumeroIndiceColiformesTotais() != null ? qualidadeAgua
									.getNumeroIndiceColiformesTotais().toString()
									: "0");
					form
							.setIndiceMensalCor(qualidadeAgua.getNumeroIndiceCor() != null ? qualidadeAgua
									.getNumeroIndiceCor().toString()
									: "0");
					form
							.setIndiceMensalFerro(qualidadeAgua
									.getNumeroIndiceFerro() != null ? qualidadeAgua
									.getNumeroIndiceFerro().toString() : "0");
					form
							.setIndiceMensalFluor(qualidadeAgua
									.getNumeroIndiceFluor() != null ? qualidadeAgua
									.getNumeroIndiceFluor().toString() : "0");
					form
							.setIndiceMensalNitrato(qualidadeAgua
									.getNumeroNitrato() != null ? qualidadeAgua
									.getNumeroNitrato().toString() : "0");
					form
							.setIndiceMensalPH(qualidadeAgua.getNumeroIndicePh() != null ? qualidadeAgua
									.getNumeroIndicePh().toString()
									: "0");
					form.setIndiceMensalTurbidez(qualidadeAgua
							.getNumeroIndiceTurbidez() != null ? qualidadeAgua
							.getNumeroIndiceTurbidez().toString() : "0");
	
					form
							.setIndiceMensalColiformesTermotolerantes(qualidadeAgua
									.getNumeroIndiceColiformesTermotolerantes() != null ? qualidadeAgua
									.getNumeroIndiceColiformesTermotolerantes()
									.toString()
									: "0");
					
					form
							.setIndiceMensalAlcalinidade(qualidadeAgua
									.getNumeroIndiceAlcalinidade() != null ? qualidadeAgua
									.getNumeroIndiceAlcalinidade()
									.toString()
									: "0");
	
					if (sessao.getAttribute("qualidadeAguaPadrao") != null) {
						QualidadeAguaPadrao qualidadeAguaPadrao = (QualidadeAguaPadrao) sessao
								.getAttribute("qualidadeAguaPadrao");
	
						form.setPadraoCloroResidual(qualidadeAguaPadrao
								.getDescricaoPadraoCloro());
						form.setPadraoColiformesFecais(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesFecais());
						form.setPadraoColiformesTotais(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesTotais());
						form.setPadraoCor(qualidadeAguaPadrao
								.getDescricaoPadraoCor());
						form.setPadraoFerro(qualidadeAguaPadrao
								.getDescricaoPadraoFerro());
						form.setPadraoFluor(qualidadeAguaPadrao
								.getDescricaoPadraoFluor());
						form.setPadraoNitrato(qualidadeAguaPadrao
								.getDescricaoNitrato());
						form
								.setPadraoPH(qualidadeAguaPadrao
										.getDescricaoPadraoPh());
						form.setPadraoTurbidez(qualidadeAguaPadrao
								.getDescricaoPadraoTurbidez());
	
						form.setPadraoColiformesTermotolerantes(qualidadeAguaPadrao
								.getDescricaoPadraoColiformesTermotolerantes());
						
						form.setPadraoAlcalinidade(qualidadeAguaPadrao
								.getDescricaoPadraoAlcalinidade());
					}
				}
	
				if (qualidadeAgua != null) {
					sessao.setAttribute("qualidadeAgua", qualidadeAgua);
	
				}
				if (sessao.getAttribute("colecaoQualidadeAgua") != null) {
					sessao.setAttribute("caminhoRetornoVoltar",
							"/gsan/filtrarQualidadeAguaAction.do");
				} else {
					sessao.setAttribute("caminhoRetornoVoltar",
							"/gsan/exibirFiltrarQualidadeAguaAction.do");
				}
	
			}
			
			this.montaColecaoFonteCaptacao(form,httpServletRequest);
		}else{
			sessao.setAttribute("PrimeiraVez",2);
		}
		
		return retorno;
	}
	
	/**
	 * Monta a colecao de Fontes de Captacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 */	
	private void montaColecaoFonteCaptacao(
			AtualizarQualidadeAguaActionForm form,HttpServletRequest httpServletRequest){
		
		String localidade = form.getIdLocalidade();
		String setorComercial = form.getIdSetorComercial();
		Collection<FonteCaptacao> colecaoFonteCaptacao = new ArrayList<FonteCaptacao>();
		
		//Pesquisa fontes de captacao por setor comercial
		if(localidade != null && !localidade.equals("") && 
			setorComercial != null && !setorComercial.equals("")){

			Collection colecaoSetoresComerciais = 
				this.pesquisarSetorComercial(
					new Integer(localidade),
					new Integer(setorComercial));
						
			if (colecaoSetoresComerciais != null && !colecaoSetoresComerciais.isEmpty()) {
				
				SetorComercial setor = 
					(SetorComercial) colecaoSetoresComerciais.iterator().next();
				
				Collection colecaoSetor = new ArrayList();
				colecaoSetor.add(setor);
				
				colecaoFonteCaptacao = this.getFachada().pesquisarFonteCaptacao(colecaoSetor);
			}
			
			
		}else if(localidade != null && !localidade.equals("")){

			Collection colecaoSetoresComerciais = 
				this.pesquisarSetorComercial(
					new Integer(localidade),
					null);
						
			colecaoFonteCaptacao = this.getFachada().pesquisarFonteCaptacao(colecaoSetoresComerciais);			
		}else{
			
			FiltroFonteCaptacao filtroFonteCaptacao = new FiltroFonteCaptacao();
			
			filtroFonteCaptacao.limparListaParametros();
			filtroFonteCaptacao.adicionarParametro(
				new ParametroSimples( 
						FiltroFonteCaptacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoFonteCaptacao = 
				this.getFachada().pesquisar(filtroFonteCaptacao, 
					FonteCaptacao.class.getName());
		}
		
		if (colecaoFonteCaptacao != null && !colecaoFonteCaptacao.isEmpty()) {
			httpServletRequest.setAttribute("colecaoFonteCaptacao",colecaoFonteCaptacao);
		} 

	}
	
	/**
	 * Pesquisa o SetorComercial
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 */		
	private Collection<SetorComercial> pesquisarSetorComercial(Integer localidade,Integer setorComercial){
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		filtroSetorComercial.limparListaParametros();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples( 
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples( 
				FiltroSetorComercial.ID_LOCALIDADE, 
				localidade));
		
		if(setorComercial != null){

			filtroSetorComercial.adicionarParametro(
				new ParametroSimples( 
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					setorComercial));
		}
		
		return 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	}
}
