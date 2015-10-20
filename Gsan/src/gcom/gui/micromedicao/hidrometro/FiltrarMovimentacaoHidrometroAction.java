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

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.sql.Time;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0000] Filtrar Movimenta��o de Hidr�metro
 * 
 * @author Fernanda Paiva, Roberta Costa
 * @created 23 de Janeiro de 2006, 03/08/2006
 */
public class FiltrarMovimentacaoHidrometroAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;
		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		retorno = actionMapping.findForward("consultarMovimentacaoHidrometro");
		
		// Recupera os par�metros do form
		String fixo = hidrometroActionForm.getFixo();
		
		String faixaInicial = hidrometroActionForm.getFaixaInicial();
		
		String faixaFinal = hidrometroActionForm.getFaixaFinal();
		
		String dataMovimentacaoInicial = hidrometroActionForm
				.getDataMovimentacaoInicial();
		
		sessao.setAttribute("dataMovimentacaoInicial", dataMovimentacaoInicial);
		
		String dataMovimentacaoFinal = hidrometroActionForm
				.getDataMovimentacaoFinal();
		
		sessao.setAttribute("dataMovimentacaoFinal", dataMovimentacaoFinal);
		
		String horaMovimentacaoInicial = hidrometroActionForm
				.getHoraMovimentacaoInicial();
		
		sessao.setAttribute("horaMovimentacaoInicial", horaMovimentacaoInicial);
		
		String horaMovimentacaoFinal = hidrometroActionForm
				.getHoraMovimentacaoFinal();
		
		sessao.setAttribute("horaMovimentacaoFinal", horaMovimentacaoFinal);
		
		String localArmazenagemDestino = hidrometroActionForm
				.getLocalArmazenagemDestino();
		
		sessao.setAttribute("localArmazenagemDestino", localArmazenagemDestino);
		
		String localArmazenagemOrigem = hidrometroActionForm
				.getLocalArmazenagemOrigem();
		
		sessao.setAttribute("localArmazenagemOrigem", localArmazenagemOrigem);
		
		Integer motivoMovimentacao = new Integer(hidrometroActionForm.getMotivoMovimentacao());
		
		sessao.setAttribute("motivoMovimentacao", motivoMovimentacao);
		
		String usuario = hidrometroActionForm.getUsuario();
		
		sessao.setAttribute("usuario", usuario);

		FiltroHidrometroMovimentacao filtroHidrometroMovimentacao = new FiltroHidrometroMovimentacao();
		
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroMotivoMovimentacao");
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemOrigem");
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemDestino");
		
		filtroHidrometroMovimentacao.adicionarCaminhoParaCarregamentoEntidade("usuario");
		
		boolean peloMenosUmParametroInformado = false;

		// Insere os par�metros informados no filtro
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
			
			peloMenosUmParametroInformado = true;
			
			sessao.removeAttribute("dataMovimentacaoInicial");
			sessao.removeAttribute("dataMovimentacaoFinal");
			sessao.removeAttribute("horaMovimentacaoInicial");
			sessao.removeAttribute("horaMovimentacaoFinal");
			sessao.removeAttribute("localArmazenagemDestino");
			sessao.removeAttribute("localArmazenagemOrigem");
			sessao.removeAttribute("motivoMovimentacao");
			sessao.removeAttribute("usuario");
			
		} else{
			if (dataMovimentacaoInicial != null
					&& !dataMovimentacaoInicial.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new MaiorQue(
						FiltroHidrometroMovimentacao.DATA,
						Util.converteStringParaDate(dataMovimentacaoInicial)));
			}
			if (dataMovimentacaoInicial != null
					&& !dataMovimentacaoInicial.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new MaiorQue(
						FiltroHidrometroMovimentacao.DATA,
						Util.converteStringParaDate(dataMovimentacaoInicial)));
			}
			
			if (dataMovimentacaoInicial != null
					&& !dataMovimentacaoInicial.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new MaiorQue(
						FiltroHidrometroMovimentacao.DATA,
						Util.converteStringParaDate(dataMovimentacaoInicial)));
			}
			if (dataMovimentacaoFinal != null
					&& !dataMovimentacaoFinal.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao.adicionarParametro(new MenorQue(
						FiltroHidrometroMovimentacao.DATA, Util.converteStringParaDate(dataMovimentacaoFinal)));
			}
			if (horaMovimentacaoInicial != null
					&& !horaMovimentacaoInicial.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new MaiorQue(
						FiltroHidrometroMovimentacao.HORA,
						new Time(Util.converterStringParaHoraMinuto(horaMovimentacaoInicial).getTime())));
			}
			if (horaMovimentacaoFinal != null
					&& !horaMovimentacaoFinal.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao.adicionarParametro(new MenorQue(
						FiltroHidrometroMovimentacao.HORA, Util.converterStringParaHoraMinuto(horaMovimentacaoFinal)));
			}
			if (motivoMovimentacao != null 
					&& motivoMovimentacao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentacao.HIDROMETRO_MOTIVO,
						motivoMovimentacao));
			}
			if (localArmazenagemOrigem != null
					&& !localArmazenagemOrigem.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentacao.HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_ORIGEM_ID,
						localArmazenagemOrigem));
			}
			if (localArmazenagemDestino != null
					&& !localArmazenagemDestino.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentacao.HIDROMETRO_MOVIMENTACAO_HIDROMETRO_LOCAL_ARMAZENAGEM_DESTINO_ID,
						localArmazenagemDestino));
			}
			
			if (usuario != null && !usuario.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroMovimentacao.adicionarCaminhoParaCarregamentoEntidade("usuario"); 
				filtroHidrometroMovimentacao.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMovimentacao.USUARIO, usuario)); 
			}
			
			sessao.removeAttribute("fixo");
			sessao.removeAttribute("faixaFinal");
			sessao.removeAttribute("faixaInicial");
		}
		

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroHidrometroMovimentacao.setConsultaSemLimites(true);
		
		// Manda o filtro pelo request
		httpServletRequest.setAttribute("filtroMovimentacaoHidrometro",
				filtroHidrometroMovimentacao);
		
		sessao.setAttribute("filtroMovimentacaoHidrometro", filtroHidrometroMovimentacao);				

		return retorno;
	}
}
