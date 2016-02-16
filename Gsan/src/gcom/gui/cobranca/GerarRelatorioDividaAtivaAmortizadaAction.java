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
* Anderson Cabral do Nascimento
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

package gcom.gui.cobranca;

import gcom.cobranca.bean.DadosAmortizacaoDividaAtivaHelper;
import gcom.cobranca.bean.DadosAmortizacaoDividaAtivaSinteticoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1585] - Emitir Relatório Divida Ativa Amortizada 
 * 
 * @author Anderson Cabral
 * @date 12/02/2014
 */
public class GerarRelatorioDividaAtivaAmortizadaAction extends ExibidorProcessamentoTarefaRelatorio {

	private Fachada fachada; 
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		fachada = Fachada.getInstancia();
		RelatorioDividaAtivaAmortizada relatorioDividaAtivaAmortizada = null;
		RelatorioDividaAtivaAmortizadaSintetico relatorioDividaAtivaAmortizadaSintetico = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		
		FiltrarRelatorioDividaAtivaActionForm form = (FiltrarRelatorioDividaAtivaActionForm) actionForm;
		
		String periodoInscricao = "";
		Date periodoInscricaoInicial = null;
		Date periodoInscricaoFinal   = null;
		if(form.getPeriodoInscricaoInicial() != null && !form.getPeriodoInscricaoInicial().equals("")
				&& form.getPeriodoInscricaoFinal() != null && !form.getPeriodoInscricaoFinal().equals("")){
			periodoInscricao = form.getPeriodoInscricaoInicial() + " a " + form.getPeriodoInscricaoFinal();
			periodoInscricaoInicial = Util.gerarDataInicialApartirAnoMesRefencia(Util.formatarMesAnoComBarraParaAnoMes(form.getPeriodoInscricaoInicial()));
			periodoInscricaoFinal = Util.gerarDataApartirAnoMesRefencia(Util.formatarMesAnoComBarraParaAnoMes(form.getPeriodoInscricaoFinal()));
		}
		
		String periodoAtualizacao = "";
		Date periodoAtualizacaoInicial = null;
		Date periodoAtualizacaoFinal   = null;
		if(form.getPeriodoAtualizacaoInicial() != null && !form.getPeriodoAtualizacaoInicial().equals("")
				&& form.getPeriodoAtualizacaoFinal() != null && !form.getPeriodoAtualizacaoFinal().equals("")){
			periodoAtualizacao = form.getPeriodoAtualizacaoInicial() + " a "  + form.getPeriodoAtualizacaoFinal();
			periodoAtualizacaoInicial = Util.gerarDataInicialApartirAnoMesRefencia(Util.formatarMesAnoComBarraParaAnoMes(form.getPeriodoAtualizacaoInicial()));
			periodoAtualizacaoFinal = Util.gerarDataApartirAnoMesRefencia(Util.formatarMesAnoComBarraParaAnoMes(form.getPeriodoAtualizacaoFinal()));
		}
		
		Integer idImovel = null;
		String descricaoLocalidade = "";
		String descricaoImovel = "";
		if(form.getIdImovel() != null && !form.getIdImovel().equals("")){
			idImovel = Integer.parseInt(form.getIdImovel()); 
			descricaoImovel = idImovel + " - " + form.getInscricaoImovel(); 
		}
		
		Short indicadorIntra = null;
		if(form.getIndicadorIntra() != null){
			indicadorIntra = Short.parseShort(form.getIndicadorIntra());
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if(tipoRelatorio == null){
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		String formatoRelatorio = null;
		
		if(form.getIndicadorRelatorioSinteticoAnalitico() != null){
			formatoRelatorio = form.getIndicadorRelatorioSinteticoAnalitico();
		}		
		
		
		
		//Sintético
		if(formatoRelatorio != null && Integer.parseInt(formatoRelatorio) == 2){
			Collection<DadosAmortizacaoDividaAtivaSinteticoHelper> colecaoDadosAmortizacaoDividaAtivaSinteticoHelper = fachada.obterDadosAmortizacoesDividaAtivaSintetico
																																	(periodoInscricaoInicial,
																																		periodoInscricaoFinal, 
																																		periodoAtualizacaoInicial, 
																																		periodoAtualizacaoFinal, 
																																		idImovel, indicadorIntra);

			relatorioDividaAtivaAmortizadaSintetico = new RelatorioDividaAtivaAmortizadaSintetico((Usuario) httpServletRequest.getSession().getAttribute("usuarioLogado"));
			
			relatorioDividaAtivaAmortizadaSintetico.addParametro("colecaoDadosAmortizacaoDividaAtivaSinteticoHelper", colecaoDadosAmortizacaoDividaAtivaSinteticoHelper);
			relatorioDividaAtivaAmortizadaSintetico.addParametro("tipoformatoRelatorio", Integer.parseInt(tipoRelatorio));
			relatorioDividaAtivaAmortizadaSintetico.addParametro("intra", indicadorIntra);
			relatorioDividaAtivaAmortizadaSintetico.addParametro("periodoInscricao",periodoInscricao);
			relatorioDividaAtivaAmortizadaSintetico.addParametro("periodoAmortizacao",periodoAtualizacao);
			relatorioDividaAtivaAmortizadaSintetico.addParametro("descricaoLocalidade",descricaoLocalidade);
			relatorioDividaAtivaAmortizadaSintetico.addParametro("descricaoImovel",descricaoImovel);
		//Analítico
		}else{
			Collection<DadosAmortizacaoDividaAtivaHelper> colecaoDadosAmortizacaoDividaAtivaHelper = fachada.obterDadosAmortizacoesDividaAtiva(periodoInscricaoInicial,
																																            periodoInscricaoFinal, 
																																			periodoAtualizacaoInicial, 
																																			periodoAtualizacaoFinal, 
																																			idImovel, indicadorIntra);
		
			relatorioDividaAtivaAmortizada = new RelatorioDividaAtivaAmortizada((Usuario) httpServletRequest.getSession().getAttribute("usuarioLogado"));
		
			relatorioDividaAtivaAmortizada.addParametro("colecaoDadosAmortizacaoDividaAtivaHelper", colecaoDadosAmortizacaoDividaAtivaHelper);
			relatorioDividaAtivaAmortizada.addParametro("tipoformatoRelatorio", Integer.parseInt(tipoRelatorio));
			relatorioDividaAtivaAmortizada.addParametro("intra", indicadorIntra);
			relatorioDividaAtivaAmortizada.addParametro("periodoInscricao",periodoInscricao);
			relatorioDividaAtivaAmortizada.addParametro("periodoAmortizacao",periodoAtualizacao);
			relatorioDividaAtivaAmortizada.addParametro("descricaoImovel",descricaoImovel);
		}
		
		try{
			
			if(relatorioDividaAtivaAmortizada != null){
				retorno = processarExibicaoRelatorio(relatorioDividaAtivaAmortizada, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
			}else{
				if(relatorioDividaAtivaAmortizadaSintetico != null){
					retorno = processarExibicaoRelatorio(relatorioDividaAtivaAmortizadaSintetico, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
				}
			}
			
		}catch(SistemaException ex){
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
			
		}catch(RelatorioVazioException ex){
			// manda o erro para a página no request atual
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		return retorno;
	}
	
}
