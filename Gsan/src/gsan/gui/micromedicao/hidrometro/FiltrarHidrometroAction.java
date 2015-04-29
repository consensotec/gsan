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
package gsan.gui.micromedicao.hidrometro;

import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltrarHidrometroHelper;
import gsan.micromedicao.hidrometro.FiltroHidrometro;
import gsan.micromedicao.hidrometro.FiltroHidrometroSituacao;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroSituacao;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ParametroSimples;

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
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 * @created 5 de Setembro de 2005
 */
public class FiltrarHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		String tela = (String) sessao.getAttribute("tela");
		
		if (tela != null && !tela.equals("")) {
			if (tela.equals("movimentarHidrometro")) {
				retorno = actionMapping.findForward("movimentarHidrometro");
			}
		} else {
			retorno = actionMapping.findForward("retornarFiltroHidrometro");
		}

		// Recupera os par�metros do form
		String numeroHidrometro = hidrometroActionForm.getNumeroHidrometro();
		String dataAquisicao = hidrometroActionForm.getDataAquisicao();
		String anoFabricacao = hidrometroActionForm.getAnoFabricacao();
		String indicadorOperacional = hidrometroActionForm.getIndicadorOperacional();
		String idHidrometroClasseMetrologica = hidrometroActionForm.getIdHidrometroClasseMetrologica();
		String idHidrometroMarca = hidrometroActionForm.getIdHidrometroMarca();
		String idHidrometroDiametro = hidrometroActionForm.getIdHidrometroDiametro();
		String idHidrometroCapacidade = hidrometroActionForm.getIdHidrometroCapacidade();
		String idHidrometroTipo = hidrometroActionForm.getIdHidrometroTipo();
		
		String idHidrometroSituacao = hidrometroActionForm.getIdHidrometroSituacao();
		String idLocalArmazenagem = hidrometroActionForm.getIdLocalArmazenagem();
		String fixo = hidrometroActionForm.getFixo();
		String faixaInicial = hidrometroActionForm.getFaixaInicial();
		String faixaFinal = hidrometroActionForm.getFaixaFinal();
		
		String idHidrometroRelojoaria = hidrometroActionForm.getIdHidrometroRelojoaria();
		String idLocalidade = hidrometroActionForm.getIdLocalidade();
		
		String idSetorComercial = hidrometroActionForm.getIdSetorComercial();
		String codigoSetorComercial = hidrometroActionForm.getCodigoSetorComercial();
		
		String idQuadra = hidrometroActionForm.getIdQuadra();
		String numeroQuadra = hidrometroActionForm.getNumeroQuadra();
		
		String vazaoTransicao = hidrometroActionForm.getVazaoTransicao();
		String vazaoNominal = hidrometroActionForm.getVazaoNominal();
		String vazaoMinima = hidrometroActionForm.getVazaoMinima();
		String notaFiscal = hidrometroActionForm.getNotaFiscal();
		String tempoGarantiaAnos = hidrometroActionForm.getTempoGarantiaAnos();
		
		String indicadorMacromedidor = hidrometroActionForm.getIndicadorMacromedidor();
		String indicadorFinalidade = "";
		
		if (indicadorMacromedidor != null) {
			if (indicadorMacromedidor.equals(Hidrometro.INDICADOR_REDE_ESGOTO)) {
				indicadorMacromedidor = Hidrometro.INDICADOR_MICROMEDIDOR;
				indicadorFinalidade = Hidrometro.INDICADOR_MICROMEDIDOR;
			} else if (indicadorMacromedidor.equals(Hidrometro.INDICADOR_MACROMEDIDOR) || indicadorMacromedidor.equals(Hidrometro.INDICADOR_MICROMEDIDOR)) {
				indicadorFinalidade = Hidrometro.INDICADOR_MACROMEDIDOR;
			}
		}
		
		String tombamento = hidrometroActionForm.getTombamento();
		String idHidrometroFatorCorrecao = hidrometroActionForm.getIdHidrometroFatorCorrecao();
		String idHidrometroClassePressao = hidrometroActionForm.getIdHidrometroClassePressao();
		
		FiltroHidrometro filtroHidrometro = 
			new FiltroHidrometro(FiltroHidrometro.NUMERO_HIDROMETRO);
		
		boolean peloMenosUmParametroInformado = false;
		
		if (idHidrometroSituacao != null && !idHidrometroSituacao.equals("-1") ) { 
			FiltroHidrometroSituacao filtroHidrometroSituacao = new FiltroHidrometroSituacao();
			filtroHidrometroSituacao.adicionarParametro( new ParametroSimples( FiltroHidrometroSituacao.ID, 
					hidrometroActionForm.getIdHidrometroSituacao() ) );
			Collection colecaoHidrometroSituacao =  this.getFachada().pesquisar(filtroHidrometroSituacao, 
					HidrometroSituacao.class.getName() );
			Object hidrometroSituacao = Util.retonarObjetoDeColecao(colecaoHidrometroSituacao);
			
			HidrometroSituacao situacao = (HidrometroSituacao) hidrometroSituacao;
			if (situacao.getDescricao().equals("INSTALADO") && idLocalidade.equals("")) {
				throw new ActionServletException("atencao.required", null, "Localidade");
			}
		}

		// Caso o fixo, a faixa inicial e faixa final seja diferente de null
		// ent�o ignora os outros parametros e faz a pesquisa do filtro por
		// esses 3 par�metros
		if (fixo != null && !fixo.equalsIgnoreCase("")) {
			
			if (faixaInicial != null && !faixaInicial.equalsIgnoreCase("")) {
				sessao.setAttribute("faixaInicial", faixaInicial);
			}
			
			if (faixaFinal != null && !faixaFinal.equalsIgnoreCase("")) {
				sessao.setAttribute("faixaFinal", faixaFinal);
			}
			
			sessao.setAttribute("fixo", fixo);
			sessao.removeAttribute("tombamento");
			sessao.removeAttribute("instalado");
			
			peloMenosUmParametroInformado = true;
			
		} else if (tombamento != null && !tombamento.equalsIgnoreCase("")) {
			
			sessao.setAttribute("tombamento", tombamento);
			sessao.removeAttribute("fixo");
			sessao.removeAttribute("instalado");

			peloMenosUmParametroInformado = true;
			
		} else if( hidrometroActionForm.getIdLocalidade() != null &&
				!hidrometroActionForm.getIdLocalidade().equals("") ){
			
			
			sessao.setAttribute("instalado", true);
			sessao.removeAttribute("fixo");
			sessao.removeAttribute("tombamento");
			FiltrarHidrometroHelper helper = new FiltrarHidrometroHelper();
			
			// Insere os par�metros informados no filtro
			if (numeroHidrometro != null && 
				!numeroHidrometro.trim().equalsIgnoreCase("")) {
				
				peloMenosUmParametroInformado = true;
				helper.setNumeroHidrometro(numeroHidrometro);
				
			}
			
			Date dataAquisicaoDate = Util.converteStringParaDate(dataAquisicao);
			Calendar dataAtual = new GregorianCalendar();
			
			if (dataAquisicao != null && !dataAquisicao.trim().equalsIgnoreCase("")) {
				
				// caso a data de aquisi��o seja menor que a data atual
				if (dataAquisicaoDate.after(new Date())) {
					throw new ActionServletException("atencao.data.aquisicao.nao.superior.data.corrente");
				}
				
				peloMenosUmParametroInformado = true;
				helper.setDataAquisicao(dataAquisicaoDate);
			}

//			if (anoFabricacao != null && !anoFabricacao.trim().equalsIgnoreCase("")) {
			
			if (anoFabricacao != null && !anoFabricacao.equals("")) {
			
				peloMenosUmParametroInformado = true;
				
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.ANO_FABRICACAO, anoFabricacao));

				helper.setAnoFabricacao(anoFabricacao);
				
				int anoAtual = dataAtual.get(Calendar.YEAR);
				Integer anoFabricacaoInteger = new Integer(anoFabricacao);
				
				// caso o ano de fabrica��o seja maior que o atual
				if (anoFabricacaoInteger > anoAtual) {
					throw new ActionServletException("atencao.ano.fabricacao.nao.superior.data.corrente");
				}
				if(dataAquisicaoDate != null){
					Integer anoDataAquisicao = Util.getAno(dataAquisicaoDate);
					// caso a data de aquisi��o seja menor que o ano fabrica��o
					if (anoDataAquisicao < anoFabricacaoInteger) {
						throw new ActionServletException("atencao.ano.fabricacao.menor.ano.aquisicao");
	
					}
				}
			}

			if (indicadorMacromedidor != null && 
				!indicadorMacromedidor.trim().equalsIgnoreCase("") && 
				!indicadorMacromedidor.trim().equalsIgnoreCase("-1")) {
				
				helper.setIndicadorMacromedidor(indicadorMacromedidor);
			}

			if (indicadorOperacional != null && 
				!indicadorOperacional.trim().equalsIgnoreCase("") && 
				!indicadorOperacional.trim().equalsIgnoreCase("-1")) {
				
				helper.setIndicadorOperacional(indicadorOperacional);
			}

			if (idHidrometroClasseMetrologica != null && 
				Integer.parseInt(idHidrometroClasseMetrologica) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroClasseMetrologica(idHidrometroClasseMetrologica);
			}

			if (idHidrometroMarca != null && 
				Integer.parseInt(idHidrometroMarca) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroMarca(idHidrometroMarca);
			}
			
			if (idHidrometroDiametro != null && 
				Integer.parseInt(idHidrometroDiametro) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroDiametro(idHidrometroDiametro);
			}

			if (idHidrometroCapacidade != null && 
				Integer.parseInt(idHidrometroCapacidade) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroCapacidade(idHidrometroCapacidade);
			}
			
			if (idHidrometroTipo != null && 
				Integer.parseInt(idHidrometroTipo) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
							
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroTipo(idHidrometroTipo);
			}
			
			if (idHidrometroRelojoaria != null && 
				Integer.parseInt(idHidrometroRelojoaria) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroRelojoaria(idHidrometroRelojoaria);
			}

			if (idHidrometroSituacao != null && 
				Integer.parseInt(idHidrometroSituacao) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroSituacao(idHidrometroSituacao);
			}

			if (idLocalArmazenagem != null && !idLocalArmazenagem.equals("")) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdLocalArmazenagem(idLocalArmazenagem);
			}

			if (idHidrometroFatorCorrecao != null
					&& !idHidrometroFatorCorrecao.trim().equals("")
					&& !idHidrometroFatorCorrecao.trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroFatorCorrecao(idHidrometroFatorCorrecao);
			}

			if (idHidrometroClassePressao != null
					&& !idHidrometroClassePressao.trim().equals("")
					&& !idHidrometroClassePressao.trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroClassePressao(idHidrometroClassePressao);
			}

			if (idHidrometroRelojoaria != null && 
				Integer.parseInt(idHidrometroRelojoaria) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdHidrometroRelojoaria(idHidrometroRelojoaria);
			}
			
			if (idLocalidade != null && !idLocalidade.equals("")) {
				
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				
				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.ID, 
						hidrometroActionForm.getIdLocalidade()));

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna localidade
				Collection colecaoPesquisa = 
					this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());
				
				if(colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
					
					Localidade localidade =(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					idLocalidade = localidade.getId().toString();
					peloMenosUmParametroInformado = true;
					helper.setIdLocalidade(idLocalidade);
					helper.setNomeLocalidade(localidade.getDescricao());
					
				}else{
					throw new ActionServletException("atencao.localidade.inexistente");
				}
			}
			
			if (codigoSetorComercial != null && !codigoSetorComercial.equals("")) {
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				
				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, 
						hidrometroActionForm.getIdLocalidade()));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						hidrometroActionForm.getCodigoSetorComercial()));

				filtroSetorComercial.adicionarParametro(
					new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				Collection colecaoPesquisa = 
					this.getFachada().pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());
				
				
				if(colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
					
					SetorComercial setorComercial =(SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
					idSetorComercial = setorComercial.getId().toString();
					
					peloMenosUmParametroInformado = true;
					helper.setIdSetorComercial(idSetorComercial);
					helper.setCodigoSetorComercial(""+setorComercial.getCodigo());
					
				}else{
					throw new ActionServletException("atencao.setor_comercial.inexistente");
				}
			}
			
			if ( vazaoTransicao != null && !vazaoTransicao.equals("") ) {
				helper.setVazaoTransicao( vazaoTransicao );
			}
			
			if ( vazaoNominal != null && !vazaoNominal.equals("") ) {
				helper.setVazaoNominal( vazaoNominal );
			}
			
			if ( vazaoMinima != null && !vazaoMinima.equals("") ) {
				helper.setVazaoMinima( vazaoMinima );
			}
			
			if ( notaFiscal != null && !notaFiscal.equals("") ) {
				helper.setNotaFiscal( notaFiscal );
			}

			if ( tempoGarantiaAnos != null && !tempoGarantiaAnos.equals("") ) {
				helper.setTempoGarantiaAnos( tempoGarantiaAnos );
			}
			
			if (numeroQuadra != null && !numeroQuadra.equals("")) {
				
				FiltroQuadra filtroQuadra = new FiltroQuadra();

				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, 
						hidrometroActionForm.getIdSetorComercial()));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, 
						hidrometroActionForm.getNumeroQuadra()));

				filtroQuadra.adicionarParametro(
					new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				Collection colecaoPesquisa = 
					this.getFachada().pesquisar(filtroQuadra,
						Quadra.class.getName());
					
				if(colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
						
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					idQuadra = quadra.getId().toString();
					
					peloMenosUmParametroInformado = true;
					helper.setIdQuadra(idQuadra);
					helper.setNumeroQuadra(""+quadra.getNumeroQuadra());
					
				}else{
					throw new ActionServletException("atencao.quadra.inexistente");
				}
			}

			// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}

			sessao.setAttribute("helper",helper);
			sessao.setAttribute("voltarFiltrar","1");
			
			sessao.removeAttribute("fixo");
			sessao.removeAttribute("faixaFinal");
			sessao.removeAttribute("faixaInicial");
			sessao.removeAttribute("filtroHidrometro");
			
		}else{

			sessao.removeAttribute("instalado");
			sessao.removeAttribute("fixo");
			sessao.removeAttribute("tombamento");
			
			// Insere os par�metros informados no filtro
			if (numeroHidrometro != null && 
				!numeroHidrometro.trim().equalsIgnoreCase("")) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ComparacaoTexto(
						FiltroHidrometro.NUMERO_HIDROMETRO, 
						numeroHidrometro));
			}
			
			Date dataAquisicaoDate = Util.converteStringParaDate(dataAquisicao);
			Calendar dataAtual = new GregorianCalendar();
			
			if (dataAquisicao != null && 
				!dataAquisicao.trim().equalsIgnoreCase("")) {
				
				// caso a data de aquisi��o seja menor que a data atual
				if (dataAquisicaoDate.after(new Date())) {
					throw new ActionServletException("atencao.data.aquisicao.nao.superior.data.corrente");
				}
				
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.DATA_AQUISICAO, 
						dataAquisicaoDate));
			}

			if (anoFabricacao != null && 
				!anoFabricacao.trim().equalsIgnoreCase("")) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.ANO_FABRICACAO, 
						anoFabricacao));
				
				int anoAtual = dataAtual.get(Calendar.YEAR);
				Integer anoFabricacaoInteger = new Integer(anoFabricacao);
				
				// caso o ano de fabrica��o seja maior que o atual
				if (anoFabricacaoInteger > anoAtual) {
					throw new ActionServletException("atencao.ano.fabricacao.nao.superior.data.corrente");
				}
				if(dataAquisicaoDate != null){
					
					Integer anoDataAquisicao = Util.getAno(dataAquisicaoDate);
					// caso a data de aquisi��o seja menor que o ano fabrica��o
					if (anoDataAquisicao < anoFabricacaoInteger) {
						throw new ActionServletException("atencao.ano.fabricacao.menor.ano.aquisicao");
	
					}
				}
			}

			if (indicadorOperacional != null && 
				!indicadorOperacional.trim().equalsIgnoreCase("") && 
				!indicadorOperacional.trim().equalsIgnoreCase("-1")) {
				
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.INDICADOR_OPERACIONAL,
						indicadorOperacional));
			}

			if (indicadorMacromedidor != null && 
				!indicadorMacromedidor.trim().equalsIgnoreCase("") && 
				!indicadorMacromedidor.trim().equalsIgnoreCase("-1")) {

				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.INDICADOR_MACROMEDIDOR,
						indicadorMacromedidor));
			}

			if (indicadorFinalidade != null && !"".equals(indicadorFinalidade)) {
				filtroHidrometro.adicionarParametro(
						new ParametroSimples(
							FiltroHidrometro.INDICADOR_FINALIDADE,
							indicadorFinalidade));
			}

			if (idHidrometroClasseMetrologica != null && 
				Integer.parseInt(idHidrometroClasseMetrologica) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_CLASSE_METROLOGICA_ID,
						idHidrometroClasseMetrologica));
			}

			if (idHidrometroMarca != null && 
				Integer.parseInt(idHidrometroMarca) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_MARCA_ID,
						idHidrometroMarca));
			}

			if (idHidrometroDiametro != null && 
				Integer.parseInt(idHidrometroDiametro) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_DIAMETRO_ID,
						idHidrometroDiametro));
			}

			if (idHidrometroCapacidade != null && 
				Integer.parseInt(idHidrometroCapacidade) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_CAPACIDADE_ID,
						idHidrometroCapacidade));
			}
			if (idHidrometroTipo != null && 
				Integer.parseInt(idHidrometroTipo) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_TIPO_ID, 
						idHidrometroTipo));
			}
			
			if (idHidrometroRelojoaria != null && 
				Integer.parseInt(idHidrometroRelojoaria) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_RELOJOARIA_ID, 
						idHidrometroRelojoaria));
			}


			if (idHidrometroSituacao != null && 
				Integer.parseInt(idHidrometroSituacao) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_SITUACAO_ID,
						idHidrometroSituacao));
			}

			if (idLocalArmazenagem != null && !idLocalArmazenagem.equals("")) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_ID,
						idLocalArmazenagem));
			}

			if (idHidrometroFatorCorrecao != null
					&& !idHidrometroFatorCorrecao.trim().equals("")
					&& !idHidrometroFatorCorrecao.trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_FATOR_CORRECAO_ID,
						idHidrometroFatorCorrecao));
			}

			if (idHidrometroClassePressao != null
					&& !idHidrometroClassePressao.trim().equals("")
					&& !idHidrometroClassePressao.trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(
					new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_CLASSE_PRESSAO_ID,
						idHidrometroClassePressao));
			}
			
			if ( vazaoTransicao != null && !vazaoTransicao.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.VAZAO_TRANSICAO, 
						vazaoTransicao.replace( "," , "." ) ) );
			}
			
			if ( vazaoNominal != null && !vazaoNominal.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.VAZAO_NOMINAL, 
						vazaoNominal.replace( "," , "." ) ) );
			}

			if ( vazaoMinima != null && !vazaoMinima.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.VAZAO_MINIMA, 
						vazaoMinima.replace( "," , "." ) ) ) ;
			}
			
			if ( notaFiscal != null && !notaFiscal.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.NOTA_FISCAL,  
						notaFiscal));
			}
			
			if ( tempoGarantiaAnos != null && !tempoGarantiaAnos.equals("") ) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro( 
					new ParametroSimples(
						FiltroHidrometro.TEMPO_GARANTIA_ANOS, 
						tempoGarantiaAnos));
			}
			// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
			if (!peloMenosUmParametroInformado) {
					throw new ActionServletException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			if (retorno.getName().equalsIgnoreCase("movimentarHidrometro")) {
				filtroHidrometro.setConsultaSemLimites(true);
			}

			// Manda o filtro pela sess�o para o ExibirManterHidrometroAction
			sessao.setAttribute("filtroHidrometro",filtroHidrometro);
			sessao.setAttribute("voltarFiltrar","1");
			
			sessao.removeAttribute("fixo");
			sessao.removeAttribute("faixaFinal");
			sessao.removeAttribute("faixaInicial");
		}

		sessao.setAttribute("indicadorMacromedidor", indicadorMacromedidor);
		sessao.setAttribute("filtrar_manter", "filtrar_manter");
		
		return retorno;
	}
}
