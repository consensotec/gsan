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
package gcom.gui.cadastro;

import gcom.cadastro.AtributosBoletimChaveHelper;
import gcom.cadastro.AtributosBoletimHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioBoletimCustoAtualizacaoCadastral;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para gera��o do boletim de 
 * custo atualizacao cadastral
 * 
 * @author Anderson Italo
 * @date 25/06/2009
 */

public class GerarBoletimCustoAtualizacaoCadastralAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

//		 Seta o caminho de retorno
		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm = (InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm) actionForm;
		
		//pesquisa a Empresa
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, 
				new Integer(informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getEmpresa())));
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
		Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresa);
		Date dataInicial = Util.converteStringParaDate(informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataInicial());
		Date dataFinal = Util.converteStringParaDate(informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataFinal());

		Object[] dadosBoletim = fachada.
		gerarBoletimCustoAtualizacaoCadastral(empresa, dataInicial, dataFinal);
		
		TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper> mapAtributosBoletim = 
			(TreeMap<AtributosBoletimChaveHelper, AtributosBoletimHelper>)dadosBoletim[0];
		
		Integer numImoveisAtualizados = (Integer)dadosBoletim[1];
		
		//Parte que vai mandar o relat�rio para a tela
		// cria uma inst�ncia da classe do relat�rio
		RelatorioBoletimCustoAtualizacaoCadastral relatorioBoletimCustoAtualizacaoCadastral = new RelatorioBoletimCustoAtualizacaoCadastral((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("mapAtributosBoletim", mapAtributosBoletim);
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("empresa", empresa);
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("dataInicial", informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataInicial());
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("dataFinal", informarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm.getDataFinal());
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("numImoveisAtualizados", numImoveisAtualizados);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		relatorioBoletimCustoAtualizacaoCadastral.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorioBoletimCustoAtualizacaoCadastral,
				tipoRelatorio, httpServletRequest, httpServletResponse,	actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		
		return retorno;
	}

}
