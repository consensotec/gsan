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
* Anderson Italo Felinto de Lima
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

package gcom.gui.relatorio.cobranca;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.bean.FiltroSupressoesReligacoesReestabelecimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioSupressoesReligacoesRestabelecimentos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

/**
 * Descri��o da classe
 * Classe respons�vel pela chamada
 * do Relatorio Acompanhamento das Supress�es Religa��es e Reestabelecimentos
 * 
 * @author Anderson Italo
 * @date 01/08/2009
 */
public class GerarRelatorioSupressoesReligacoesRestabelecimentosAction extends ExibidorProcessamentoTarefaRelatorio {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		GerarSupressoesReligacoesReestabelecimentosActionForm form = (GerarSupressoesReligacoesReestabelecimentosActionForm) actionForm;
		FiltroSupressoesReligacoesReestabelecimentoHelper filtro = new FiltroSupressoesReligacoesReestabelecimentoHelper();
		
		//Per�odo de Emiss�o
		//==============================================================================
		String dataInicial = form.getDataEmissaoInicio();
		String dataFinal = form.getDataEmissaoFim();
		
		if ((dataInicial.trim().length() == 10)
				&& (dataFinal.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();
            
            calendarInicio.setTime( Util.converteStringParaDate( dataInicial ) );
            calendarFim.setTime( Util.converteStringParaDate( dataFinal ) );

			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
			
			filtro.setDataEmissaoInicio(dataInicial + " 00:00:00");
			filtro.setDataEmissaoFim(dataFinal + " 23:59:59");
		}
		
		if (form.getIndicadorTipoRelatorio() != null && !form.getIndicadorTipoRelatorio().equals("")){
			Integer indicadorTipoRelatorio = new Integer(form.getIndicadorTipoRelatorio());
			
			switch (indicadorTipoRelatorio) {
			case 1:
				filtro.setIndicadorTipoRelatorio(1);
				break;
			case 2:
				filtro.setIndicadorTipoRelatorio(2);
				break;
			case 3:
				if (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals("")
						&& !form.getIdGerenciaRegional().equals("-1")){
					filtro.setIdGerenciaRegional(form.getIdGerenciaRegional());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(3);
				break;
			case 4:
				filtro.setIndicadorTipoRelatorio(4);
				break;
			case 5:
				if (form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("")
						&& !form.getIdUnidadeNegocio().equals("-1")){
					filtro.setIdUnidadeNegocio(form.getIdUnidadeNegocio());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(5);
				break;
			case 6:
				filtro.setIndicadorTipoRelatorio(6);
				break;
			case 7:
				if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")
						&& !form.getIdLocalidade().equals("-1")){
					filtro.setIdLocalidade(form.getIdLocalidade());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(7);
				break;
			case 8:
				if (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals("")
						&& !form.getIdGerenciaRegional().equals("-1")){
					filtro.setIdGerenciaRegional(form.getIdGerenciaRegional());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(8);
				break;
			case 9:
				if (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals("")
						&& !form.getIdGerenciaRegional().equals("-1")){
					filtro.setIdGerenciaRegional(form.getIdGerenciaRegional());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(9);
				break;
			case 10:
				if (form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("")
						&& !form.getIdUnidadeNegocio().equals("-1")){
					filtro.setIdUnidadeNegocio(form.getIdUnidadeNegocio());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(10);
				break;
			default:
				break;
			}
			
			//Limite Religa��o ap�s Corte
			if (form.getLimititeReligacaoPosCorte() != null && !form.getLimititeReligacaoPosCorte().equals("")){
				filtro.setLimititeReligacaoPosCorte(form.getLimititeReligacaoPosCorte());
			}else{
				//assume 0
				filtro.setLimititeReligacaoPosCorte("0");
			}
			
			//empresa
			if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("") && !form.getIdEmpresa().equals("-1")){
				filtro.setIdEmpresa(form.getIdEmpresa());
			}
			
		}else{
			throw new ActionServletException("atencao.combinacao_parametros_invalida");
		}
		
		if (form == null) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		List objetosEncontrados = fachada.filtrarSupressoesReligacoesReestabelecimentos(filtro);
		
		if (objetosEncontrados == null ){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}else if (objetosEncontrados.size() < 1){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		RelatorioSupressoesReligacoesRestabelecimentos relatorio = new RelatorioSupressoesReligacoesRestabelecimentos(usuario);
		relatorio.addParametro("filtroSupressoesReligacoesReestabelecimentos", filtro);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		relatorio.addParametro("objetosEncontrados", objetosEncontrados);
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, request, response, mapping);
	
		
		
	}
}
