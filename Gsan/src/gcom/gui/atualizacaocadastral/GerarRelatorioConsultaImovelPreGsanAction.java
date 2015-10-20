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

package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.ImovelSubcategoriaAtualizacaoCadastralDM;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1446] Gerar Relatorio de Consulta aos Dados do Imovel no Ambiente Pre-GSAN
 * 
 * @author Anderson Cabral
 * @author Bruno Sá Barreto
 * 
 * @date 20/03/2013
 */
public class GerarRelatorioConsultaImovelPreGsanAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
//		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ArrayList<ImovelSubcategoriaAtualizacaoCadastralDM> colecaoSubCategoria = (ArrayList<ImovelSubcategoriaAtualizacaoCadastralDM>) sessao.getAttribute("colecaoSubCategoria");
		
		//adicionando coleção de ocorrenciasCadastro ao relatório
		ArrayList<CadastroOcorrencia> colecaoCadastroOcorrencia = (ArrayList<CadastroOcorrencia>) sessao.getAttribute("listaCadastroOcorrencia");
		
		//Form com os dados do imovel a ser exibido no relatorio
		ConsultarDadosImovelAmbienteVirtualPopupActionForm form = (ConsultarDadosImovelAmbienteVirtualPopupActionForm) actionForm;
		
		RelatorioConsultaImovelPreGsan relatorioConsultaImovelPreGsan = new RelatorioConsultaImovelPreGsan(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioConsultaImovelPreGsan.addParametro("form", form);
		relatorioConsultaImovelPreGsan.addParametro("colecaoSubCategoria", colecaoSubCategoria);
		relatorioConsultaImovelPreGsan.addParametro("colecaoCadastroOcorrencia", colecaoCadastroOcorrencia);
		relatorioConsultaImovelPreGsan.addParametro("tipoFormatoRelatorio",	Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(relatorioConsultaImovelPreGsan, tipoRelatorio, 
				httpServletRequest, httpServletResponse, actionMapping);
		
		return retorno;	
	}
}
