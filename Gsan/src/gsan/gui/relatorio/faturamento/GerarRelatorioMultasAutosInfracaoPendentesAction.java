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

package gsan.gui.relatorio.faturamento;
import gsan.cadastro.funcionario.FiltroFuncionario;
import gsan.cadastro.funcionario.Funcionario;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.gui.ActionServletException;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.faturamento.RelatorioMultasAutosInfracaoPendentes;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioMultasAutosInfracaoPendentesAction extends
ExibidorProcessamentoTarefaRelatorio{

	
	/**
	 * 
	 * 
	 * RM 944 - Relat�rio das Multas de Autos de Infra��o Pendentes
	 * 
	 * @author Hugo Azevedo 
	 * @date 11/07/2011
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
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		GerarRelatorioMultasAutosInfracaoPendentesActionForm form = (GerarRelatorioMultasAutosInfracaoPendentesActionForm) actionForm;
		@SuppressWarnings("unused")
		HttpSession sessao = httpServletRequest.getSession(false);
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");		
		TarefaRelatorio relatorio = null;
		Date periodoAtuacaoInicialDate = null;
		Date periodoAtuacaoFinalDate = null;
		httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		
		
		//Recuperando os dados do formul�rio
		String grupo = form.getGrupo();
		String idFuncionario = form.getIdFuncionario();		
		String periodoAtuacaoInicial = form.getPeriodoAtuacaoInicial();
		String periodoAtuacaoFinal =  form.getPeriodoAtuacaoFinal();
		String idRota = form.getIdRota();
		
		//Inteiros para os IDs
		Integer grupoINT = null;
		Integer idFuncionarioINT = null;
		Integer idRotaINT = null;
		
		if(Integer.parseInt(grupo) != -1)
			grupoINT = new Integer(grupo);
		else
			grupoINT = new Integer("0");
		
		if(!idFuncionario.equals("") )
			idFuncionarioINT  = new Integer(idFuncionario);
		else
			idFuncionarioINT = new Integer("0");
		
		if(!idRota.equals("") )
			idRotaINT  = new Integer(idRota);
		else
			idRotaINT = new Integer("0");

		
		//Nenhum par�metro informado
		if(grupoINT.equals(0) && idFuncionarioINT.equals(0) && idRotaINT.equals(0) && periodoAtuacaoInicial.equals("") && periodoAtuacaoFinal.equals("")){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		//Verifica se Per�odo de Atua��o Inicial � menor que o Per�odo de Atua��o Final
        if(periodoAtuacaoInicial != null && !periodoAtuacaoInicial.trim().equals("")){
        	periodoAtuacaoInicialDate = Util.converteStringParaDate(periodoAtuacaoInicial);
        	if(periodoAtuacaoFinal != null && !periodoAtuacaoFinal.trim().equals("")){
        		periodoAtuacaoFinalDate = Util.converteStringParaDate(periodoAtuacaoFinal);
        		if(periodoAtuacaoInicialDate.compareTo(periodoAtuacaoFinalDate) > 0){
        			throw new ActionServletException("atencao.data.inicial.maior.final");
        		}       		
        	}       	
        }
		
		//Recuperando o grupo
		FiltroFaturamentoGrupo filtroFG = new FiltroFaturamentoGrupo();
		filtroFG.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, grupo));
		Collection<?> colecaoRetornoGru = this.getFachada().pesquisar(filtroFG, FaturamentoGrupo.class.getName());
		FaturamentoGrupo fg = null;
		if(colecaoRetornoGru.size() > 0){
			fg = (FaturamentoGrupo)Util.retonarObjetoDeColecao(colecaoRetornoGru);
		}
		
		//Recuperando o funcion�rio
		Funcionario func = null;
		if(idFuncionario != null && !idFuncionario.trim().equals("")){
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));
			Collection<?> colecaoRetornoFunc = this.getFachada().pesquisar(filtroFuncionario, Funcionario.class.getName());
			if(colecaoRetornoFunc.size() > 0){
				func = (Funcionario)Util.retonarObjetoDeColecao(colecaoRetornoFunc);
			}else{
				throw new ActionServletException("atencao.funcionario.inexistente");
			}
		}
		

		
		Collection<?> colecaoMultas = 
			this.getFachada().pesquisarDadosRelatorioAutoInfracaoPendentes(grupoINT,idFuncionarioINT, idRotaINT, periodoAtuacaoInicialDate, periodoAtuacaoFinalDate);
		
		
		//Nenhum par�metro retornado
		if(colecaoMultas == null || colecaoMultas.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "relat�rio de multas de autos de infra��o pendentes");
		}
				
		relatorio = new RelatorioMultasAutosInfracaoPendentes(
			(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));		
    	
		String codigoRota = "";
		if(idRota != null && !idRota.equals("")){
	    	FiltroRota filtroRota = new FiltroRota();
	    	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
	    	
	    	Collection<?> colecaoRota = this.getFachada().pesquisar(filtroRota, Rota.class.getName());

	    	if(colecaoRota != null && !colecaoRota.isEmpty()){
	    		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
	    		codigoRota = ""+rota.getCodigo();
	    	}
			
		}

		
		
		relatorio.addParametro("colecaoMultas",colecaoMultas);
		relatorio.addParametro("grupo",fg);
		relatorio.addParametro("funcionario",func);
		relatorio.addParametro("rota", codigoRota);
		relatorio.addParametro("periodoAtuacaoInicial", periodoAtuacaoInicial);
		relatorio.addParametro("periodoAtuacaoFinal", periodoAtuacaoFinal);
		

		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		montarPaginaSucesso(httpServletRequest,
				"Relat�rio gerado com sucesso",
				"Gerar outro relat�rio",
				"exibirGerarRelatorioMultasAutosInfracaoPendentesAction.do?limparForm=OK");
		
		return retorno;
	}
}
