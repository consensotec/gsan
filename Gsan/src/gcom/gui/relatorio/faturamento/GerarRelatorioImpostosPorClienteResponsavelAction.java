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
package gcom.gui.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite atualizar um contrato de demanda [UC0513] Manter Contrato de Demanda
 * 
 * @author Rafael Corrêa, Erivan Sousa
 * @since 31/03/2006
 */

public class GerarRelatorioImpostosPorClienteResponsavelAction extends ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		
		RelatorioImpostosPorClienteResponsavelActionForm form = (RelatorioImpostosPorClienteResponsavelActionForm) actionForm;
		
		String anoMesInicial = form.getAnoMesReferenciaInicial();		

		String anoMesFinal = form.getAnoMesReferenciaFinal();
		
		Integer anoMesInicialTemp = Util.formatarMesAnoComBarraParaAnoMes(anoMesInicial);
		Integer anoMesFinalTemp = Util.formatarMesAnoComBarraParaAnoMes(anoMesFinal);
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		String relatorioTipo = form.getRelatorioTipo();

		String tipoImposto = "";
		if(form.getIndicadorTipoImposto() != null && !form.getIndicadorTipoImposto().equals("")){
			tipoImposto = form.getIndicadorTipoImposto();
		}
		
		Integer clienteID = null;
		if ( form.getClienteID() != null && !form.getClienteID().equals("") ){
			clienteID = Util.converterStringParaInteger(form.getClienteID());
		}
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		if(sistemaParametro.getAnoMesFaturamento().intValue() < anoMesInicialTemp.intValue()){
			throw new ActionServletException("atencao.mes_ano.faturamento.inferior", 
					null,""+ Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
		}
		
		
		if(anoMesFinalTemp.intValue() < anoMesInicialTemp.intValue()){
			throw new ActionServletException("atencao.ano_mes_inicio_menor_que_final", 
					null,""+Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
		}
		
		
		if(relatorioTipo != null ){
			RelatorioImpostosPorClienteResponsavel relatorioImpCliResponsavel = null;
			
			if(relatorioTipo.equals(ConstantesSistema.ANALITICO.toString()) && tipoImposto.equals("2")){
				relatorioImpCliResponsavel = 
					new RelatorioImpostosPorClienteResponsavel(
							(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), ConstantesRelatorios.RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL_ANALITICO_ARRECADACAO);
			}else{
				relatorioImpCliResponsavel = 
						new RelatorioImpostosPorClienteResponsavel(
								(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), ConstantesRelatorios.RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL);
			}
			
			if(relatorioTipo.equals(ConstantesSistema.SINTETICO+"")){
//				relatorioTipo = "SINTETICO";
				relatorioImpCliResponsavel.addParametro("tipoRelatorio", "SINTETICO");	
			}else if(relatorioTipo.equals(ConstantesSistema.ANALITICO+"")){
//				relatorioTipo = "ANALITICO";
				relatorioImpCliResponsavel.addParametro("tipoRelatorio", "ANALITICO");
			}		
			
			relatorioImpCliResponsavel.addParametro("tipoImposto", tipoImposto);
			relatorioImpCliResponsavel.addParametro("clienteID", clienteID);
			relatorioImpCliResponsavel.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			relatorioImpCliResponsavel.addParametro("anoMesInicial",anoMesInicial);
			relatorioImpCliResponsavel.addParametro("anoMesFinal",anoMesFinal);
			
			retorno = processarExibicaoRelatorio(relatorioImpCliResponsavel,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		}
		
		return retorno;
	}
	
}
