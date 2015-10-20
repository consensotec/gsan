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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obt�m o action form
		PesquisarHidrometroActionForm pesquisarHidrometroActionForm = (PesquisarHidrometroActionForm) actionForm;

		// Cole��o que armazena o resultado da pesquisa
		Collection<Hidrometro> hidrometros = null;

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("listaHidrometro");

		// cria sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Pega a instancia da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltroHidrometro filtroHidrometro = new FiltroHidrometro(
				FiltroHidrometro.NUMERO_HIDROMETRO);

		// Recupera os par�metros do form
		String numeroHidrometro = pesquisarHidrometroActionForm.getNumeroHidrometro();
		String dataAquisicao = pesquisarHidrometroActionForm.getDataAquisicao();
		String anoFabricacao = pesquisarHidrometroActionForm.getAnoFabricacao();
		String indicadorOperacional = pesquisarHidrometroActionForm.getFinalidade();
		String idHidrometroClasseMetrologica = pesquisarHidrometroActionForm.getIdHidrometroClasseMetrologica();
		String idHidrometroMarca = pesquisarHidrometroActionForm.getIdHidrometroMarca();
		String idHidrometroDiametro = pesquisarHidrometroActionForm.getIdHidrometroDiametro();
		String idHidrometroCapacidade = pesquisarHidrometroActionForm.getIdHidrometroCapacidade();
		String idHidrometroTipo = pesquisarHidrometroActionForm.getIdHidrometroTipo();
		String idLocalArmazenagem = pesquisarHidrometroActionForm.getIdLocalArmazenamento();
		String idHidrometroSituacao = pesquisarHidrometroActionForm.getIdHidrometroSituacao();
		String fixo = pesquisarHidrometroActionForm.getFixo();
		String faixaInicial = pesquisarHidrometroActionForm.getFaixaInicial();
		String faixaFinal = pesquisarHidrometroActionForm.getFaixaFinal();

		boolean peloMenosUmParametroInformado = false;

		// Caso o fixo, a faixa inicial e faixa final seja diferente de null
		// ent�o ignora os outros parametros e faz a pesquisa do filtro por
		// esses 3 par�metros
		if ((fixo != null && !fixo.trim().equalsIgnoreCase(""))
				&& (faixaInicial != null
						&& !faixaInicial.trim().equalsIgnoreCase("") && (faixaFinal != null && !faixaFinal
						.trim().equalsIgnoreCase("")))) {

			// Verifica se a faixa inicial e final s�o iguais a zero
			if( faixaInicial.equals("") || faixaInicial == null  ){
				throw new ActionServletException(
				"atencao.faixa.inicial.deve.ser.informada");
			}else{
				if( faixaInicial.equals("0") ){
					throw new ActionServletException(
					"atencao.faixa.inicial.deve.ser.maior.zero");
				}
			}	
			if( faixaFinal.equals("") || faixaFinal == null  ){
				throw new ActionServletException(
					"atencao.faixa.final.deve.ser.informada");
			}else{
				if( faixaFinal.equals("0")){
					throw new ActionServletException(
						"atencao.faixa.final.deve.ser.maior.zero");
				}
			}	
			
			if( faixaInicial != null && faixaFinal != null  ){
				Integer faixaIni = new Integer(faixaInicial);
				Integer faixaFim = new Integer(faixaFinal);
				if( faixaIni > faixaFim ){
					throw new ActionServletException(
						"atencao.faixa.final.deve.ser.maior.faixao.inicial");
				}
			}
			
			hidrometros = fachada.pesquisarNumeroHidrometroFaixa(fixo,
					faixaInicial, faixaFinal);
		} else {
			// Insere os par�metros informados no filtro
			if (numeroHidrometro != null
					&& !numeroHidrometro.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ComparacaoTexto(
						FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
			}
			if (dataAquisicao != null
					&& !dataAquisicao.trim().equalsIgnoreCase("")) {

				// Caso a data de aquisi��o seja maior que a data atual
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				Date dataAquisicaoFormatada = null;
				try {
					dataAquisicaoFormatada = formatoData.parse(dataAquisicao);
				} catch (ParseException ex) {
					// Erro no hibernate
					reportarErros(httpServletRequest, "erro.sistema", ex);
					// Atribui o mapeamento de retorno para a tela de erro
					retorno = actionMapping.findForward("telaErro");
				}
				
				// caso a data de aquisi��o seja menor que a data atual
				if (dataAquisicaoFormatada.after(new Date())) {
					throw new ActionServletException(
							"atencao.data.aquisicao.nao.superior.data.corrente");
				}

				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.DATA_AQUISICAO, Util.converteStringParaDate(dataAquisicao)));
			}

			if (anoFabricacao != null
					&& !anoFabricacao.trim().equalsIgnoreCase("")) {
				// Verifica se o ano de fabrica��o � maior que o ano da data de aquisi��o
				//Integer anoFabricacaoForm = new Integer(anoFabricacao);
				
				Short anoFabricacaoForm = new Short(anoFabricacao);
				
				
				if (dataAquisicao != null
						&& !dataAquisicao.trim().equalsIgnoreCase("")
						&& dataAquisicao.length() == 10) {
					
					Integer anoDataAquisicao = new Integer(dataAquisicao.substring(6));
					if (anoFabricacaoForm > anoDataAquisicao) {
						throw new ActionServletException(
								"atencao.ano.fabricacao.nao.superior.data.fabricacao");
					}	
				}
				
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.ANO_FABRICACAO, anoFabricacao));
			}

			if (indicadorOperacional != null
					&& !indicadorOperacional.trim().equalsIgnoreCase("")
					&& !indicadorOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.INDICADOR_OPERACIONAL,
						indicadorOperacional));
			}

			if (idHidrometroClasseMetrologica != null
					&& Integer.parseInt(idHidrometroClasseMetrologica) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_CLASSE_METROLOGICA_ID,
						idHidrometroClasseMetrologica));
			}

			if (idHidrometroMarca != null
					&& Integer.parseInt(idHidrometroMarca) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometro.HIDROMETRO_MARCA_ID,
								idHidrometroMarca));
			}

			if (idHidrometroDiametro != null
					&& Integer.parseInt(idHidrometroDiametro) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_DIAMETRO_ID,
						idHidrometroDiametro));
			}

			if (idHidrometroCapacidade != null
					&& Integer.parseInt(idHidrometroCapacidade) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_CAPACIDADE_ID,
						idHidrometroCapacidade));
			}
			if (idHidrometroTipo != null
					&& Integer.parseInt(idHidrometroTipo) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_TIPO_ID, idHidrometroTipo));
			}

			if (idLocalArmazenagem != null && !idLocalArmazenagem.equals("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_ID,
						idLocalArmazenagem));
			}

			if (idHidrometroSituacao != null
					&& Integer.parseInt(idHidrometroSituacao) > ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometro
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometro.HIDROMETRO_SITUACAO_ID,
								pesquisarHidrometroActionForm
										.getIdHidrometroSituacao()));
			}

			// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");

			// Faz a pesquisa baseada no filtro
			//hidrometros = fachada.pesquisar(filtroHidrometro, Hidrometro.class
				//	.getName());
			
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroHidrometro, Hidrometro.class.getName());
			hidrometros = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

		}

		// Verificar se a pesquisa de hidrometros retornou vazia
		if (hidrometros == null || hidrometros.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Hidr�metro");

		}

		sessao.setAttribute("colecaoHidrometros",
				hidrometros);
		// Manda a cole��o dos hidr�metros pesquisados para o request
		httpServletRequest.getSession(false).setAttribute("colecaoHidrometros",
				hidrometros);

		return retorno;
	}
}
