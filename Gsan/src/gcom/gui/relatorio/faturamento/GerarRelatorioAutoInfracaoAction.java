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
package gcom.gui.relatorio.faturamento;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.autoinfracao.GerarRelatorioAutoInfracaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.autoinfracao.RelatorioAutoInfracao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de bairro manter
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioAutoInfracaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		GerarRelatorioAutoInfracaoActionForm gerarRelatorioAutoInfracaoActionForm = (GerarRelatorioAutoInfracaoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = this.getSistemaParametro();
		
		boolean peloMenosUmParametroInformado = false;

		// Inicio da parte que vai mandar os parametros para o relat�rio

		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		Funcionario funcionario = new Funcionario();
		
		String idUnidadeNegocio = gerarRelatorioAutoInfracaoActionForm.getIdUnidadeNegocio();
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		
		if (idUnidadeNegocio != null && !idUnidadeNegocio.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocio));
			
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
			if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {
				unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			}
			
		}
		
		String idFuncionario = gerarRelatorioAutoInfracaoActionForm.getIdFuncionario();
		
		if (idFuncionario != null && !idFuncionario.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));
			
			Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Funcion�rio");
			}
			
		}

		String dataPagamentoInicialForm = gerarRelatorioAutoInfracaoActionForm.getDataPagamentoInicio();
		String dataPagamentoFinalForm = gerarRelatorioAutoInfracaoActionForm.getDataPagamentoFim();
		
		String dataPagamentoInicial = null;
		String dataPagamentoFinal = null;

		if (dataPagamentoInicialForm != null && !dataPagamentoInicialForm.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			
			dataPagamentoInicial = Util.formatarMesAnoParaAnoMesSemBarra(dataPagamentoInicialForm);
			dataPagamentoFinal = Util.formatarMesAnoParaAnoMesSemBarra(dataPagamentoFinalForm);
			
			if (dataPagamentoInicial
					.compareTo(dataPagamentoFinal) > 0) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido");
			}
			
			//Consulta a data referente ano mes arrecada��o direto da tabela e n�o quando sistema parametro � carregado.
			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
			filtroSistemaParametro.adicionarParametro( new ParametroSimples( 
					FiltroSistemaParametro.Parm_Id, sistemaParametro.getParmId() ) );
			Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
			SistemaParametro sistParam = (SistemaParametro) Util.retonarObjetoDeColecao(colecaoSistemaParametro);
			
			if(dataPagamentoFinal.compareTo(sistParam.getAnoMesArrecadacao().toString())>=0){
				throw new ActionServletException(
				"atencao.data.menor.sistema.parametro",null,Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao().toString()));
			}
		}
		
		
		
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
			"atencao.filtro.nenhum_parametro_informado");
		}

		// Fim da parte que vai mandar os parametros para o relat�rio
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioAutoInfracao relatorioAutoInfracao = new RelatorioAutoInfracao((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioAutoInfracao.addParametro("unidadeNegocio",
				unidadeNegocio);
		relatorioAutoInfracao.addParametro("funcionario",
				funcionario);
		relatorioAutoInfracao.addParametro("dataPagamentoInicial",
				new Integer(dataPagamentoInicial));
		relatorioAutoInfracao.addParametro("dataPagamentoFinal",
				new  Integer(dataPagamentoFinal));
		
		int count = fachada.countRelatorioAutoInfracao(
				unidadeNegocio.getId(),
				funcionario.getId(),
				new Integer(dataPagamentoInicial),
				new Integer(dataPagamentoFinal));
		if(count==0){
			throw new ActionServletException(
			"atencao.relatorio.vazio");
		}
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAutoInfracao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioAutoInfracao, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
