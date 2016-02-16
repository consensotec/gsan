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
package gcom.gui.relatorio.arrecadacao;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioAnaliticoPrevisaoPagamentoCartaoCredito;
import gcom.relatorio.arrecadacao.RelatorioSinteticoPrevisaoPagamentoCartaoCredito;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Gerar Relatório de Previsao/Pagamento de Cartao de Credito
 * [UC1694]
 * 
 * @author João Pedro Medeiros
 * @created 14/10/2015
 */
public class GerarRelatorioPrevisaoPagamentoCartaoCreditoAction extends 
	ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		 HttpSession sessao = httpServletRequest.getSession(false);
		 Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm gerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm = 
				(GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm) actionForm;
		
		String tipoDeRelatorio = gerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm.getTipoDeRelatorio();
		String idCliente = gerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm.getIdCliente();
		String dataVencimentoInicial = gerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm.getDataVencimentoInicial();
		String dataVencimentoFinal = gerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm.getDataVencimentoFinal();
		
		if((idCliente == null || idCliente.equals(""))){
			throw new ActionServletException("atencao.naoinformado",null, " Cliente arrecadador");
		}		
		
		if((dataVencimentoInicial == null || dataVencimentoInicial.equals(""))
				&& (dataVencimentoFinal!= null || !dataVencimentoFinal.equals(""))){
			throw new ActionServletException("atencao.informe_periodo",null,"inicial");
		}else if((dataVencimentoFinal == null || dataVencimentoFinal.equals(""))
				&& (dataVencimentoInicial != null || !dataVencimentoInicial.equals(""))){
			throw new ActionServletException("atencao.informe_periodo",null,"final");			
		}
		
		if (dataVencimentoFinal != null && !dataVencimentoFinal.equals("")
				&& dataVencimentoInicial != null && !dataVencimentoInicial.equals("")) {

				Date dtInicial = Util.converteStringParaDate(dataVencimentoInicial);
				Date dtFinal = Util.converteStringParaDate(dataVencimentoFinal);
				
				if(dtInicial.getTime() > dtFinal.getTime()){
					throw new ActionServletException("atencao.data.intervalo.invalido", null, "Data Invalida");
				}				
		}
		
		if(tipoDeRelatorio == null || tipoDeRelatorio == ""){
			throw new ActionServletException("atencao.naoinformado",null,"Tipo de relatorio");
		}
		
		String escolhaTipoRelatorio = httpServletRequest.getParameter("escolhaTipoRelatorio");
		
		TarefaRelatorio relatorio = null;
		//Relatório Analítico
		if(tipoDeRelatorio != null && tipoDeRelatorio.equals("analitico")){ 
			relatorio = new RelatorioAnaliticoPrevisaoPagamentoCartaoCredito(
							(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			relatorio.addParametro("idCliente", idCliente);
			relatorio.addParametro("dataVencimentoInicial", dataVencimentoInicial);
			relatorio.addParametro("dataVencimentoFinal", dataVencimentoFinal);
			relatorio.addParametro("usuarioLogado",usuarioLogado );
			relatorio.addParametro("codEmpresa", "14.659.593/0001-07");
			relatorio.addParametro("escolhaTipoRelatorio", escolhaTipoRelatorio);
			
		//Relatório Sintético	
		}else if(tipoDeRelatorio != null && tipoDeRelatorio.equals("sintetico")){
			relatorio = new RelatorioSinteticoPrevisaoPagamentoCartaoCredito(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorio.addParametro("idCliente", idCliente);
			relatorio.addParametro("dataVencimentoInicial", dataVencimentoInicial);
			relatorio.addParametro("dataVencimentoFinal", dataVencimentoFinal);
			relatorio.addParametro("usuarioLogado",usuarioLogado );
			relatorio.addParametro("codEmpresa", "14.659.593/0001-07");
			relatorio.addParametro("escolhaTipoRelatorio", escolhaTipoRelatorio);
			
		}
				
		retorno = processarExibicaoRelatorio(
				relatorio, escolhaTipoRelatorio, httpServletRequest,
				httpServletResponse, actionMapping);
		
		return retorno;		
		
	}
}
