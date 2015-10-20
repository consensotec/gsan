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
package gcom.gui.relatorio.cobranca;

import gcom.gui.cadastro.imovel.ImovelCurvaAbcDebitosActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioGerarCurvaAbcDebitos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarCurvaAbcDebitosAction  extends ExibidorProcessamentoTarefaRelatorio  {

	/**
	 * @author Ivan S�rgio
	 * @created 07/08/2007
	 * 
	 * <<Descri��o do m�todo>>
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
		
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;

		RelatorioGerarCurvaAbcDebitos relatorioGerarCurvaAbcDebitos = new RelatorioGerarCurvaAbcDebitos(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioGerarCurvaAbcDebitos.addParametro("classificacao",
				imovelCurvaAbcDebitosActionForm.getClassificacao());
		relatorioGerarCurvaAbcDebitos.addParametro("referenciaCobrancaInicial",
				imovelCurvaAbcDebitosActionForm.getReferenciaCobrancaInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("referenciaCobrancaFinal",
				imovelCurvaAbcDebitosActionForm.getReferenciaCobrancaFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorImovelMedicaoIndividualizada",
				imovelCurvaAbcDebitosActionForm.getIndicadorImovelMedicaoIndividualizada());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorImovelParalizacaoFaturamentoCobranca",
				imovelCurvaAbcDebitosActionForm.getIndicadorImovelParalizacaoFaturamentoCobranca());
		relatorioGerarCurvaAbcDebitos.addParametro("idMunicipio",
				imovelCurvaAbcDebitosActionForm.getIdMunicipio());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeMunicipio",
				imovelCurvaAbcDebitosActionForm.getNomeMunicipio());
		relatorioGerarCurvaAbcDebitos.addParametro("gerenciaRegional",
				imovelCurvaAbcDebitosActionForm.getGerenciaRegional());
		relatorioGerarCurvaAbcDebitos.addParametro("idLocalidadeInicial",
				imovelCurvaAbcDebitosActionForm.getIdLocalidadeInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("idLocalidadeFinal",
				imovelCurvaAbcDebitosActionForm.getIdLocalidadeFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("idSetorComercialInicial",
				imovelCurvaAbcDebitosActionForm.getIdSetorComercialInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("idSetorComercialFinal",
				imovelCurvaAbcDebitosActionForm.getIdSetorComercialFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeLocalidadeInicial",
				imovelCurvaAbcDebitosActionForm.getNomeLocalidadeInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeLocalidadeFinal",
				imovelCurvaAbcDebitosActionForm.getNomeLocalidadeFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("codigoSetorComercialInicial",
				imovelCurvaAbcDebitosActionForm.getCodigoSetorComercialInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("codigoSetorComercialFinal",
				imovelCurvaAbcDebitosActionForm.getCodigoSetorComercialFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeSetorComercialInicial",
				imovelCurvaAbcDebitosActionForm.getNomeSetorComercialInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeSetorComercialFinal",
				imovelCurvaAbcDebitosActionForm.getNomeSetorComercialFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("idMunicipio", 
				imovelCurvaAbcDebitosActionForm.getIdMunicipio());
		relatorioGerarCurvaAbcDebitos.addParametro("nomeMunicipio", 
				imovelCurvaAbcDebitosActionForm.getNomeMunicipio());
		
		relatorioGerarCurvaAbcDebitos.addParametro("situacaoLigacaoAgua",
				imovelCurvaAbcDebitosActionForm.getSituacaoLigacaoAgua());
		relatorioGerarCurvaAbcDebitos.addParametro("situacaoLigacaoEsgoto",
				imovelCurvaAbcDebitosActionForm.getSituacaoLigacaoEsgoto());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloMesesCortadoSuprimidoInicial",
				imovelCurvaAbcDebitosActionForm.getIntervaloMesesCortadoSuprimidoInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloMesesCortadoSuprimidoFinal",
				imovelCurvaAbcDebitosActionForm.getIntervaloMesesCortadoSuprimidoFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloConsumoMinimoFixadoEsgotoInicial",
				imovelCurvaAbcDebitosActionForm.getIntervaloConsumoMinimoFixadoEsgotoInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloConsumoMinimoFixadoEsgotoFinal",
				imovelCurvaAbcDebitosActionForm.getIntervaloConsumoMinimoFixadoEsgotoFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorMedicao",
				imovelCurvaAbcDebitosActionForm.getIndicadorMedicao());
		relatorioGerarCurvaAbcDebitos.addParametro("idTipoMedicao",
				imovelCurvaAbcDebitosActionForm.getIdTipoMedicao());
		
		relatorioGerarCurvaAbcDebitos.addParametro("idPerfilImovel",
				imovelCurvaAbcDebitosActionForm.getIdPerfilImovel());
		relatorioGerarCurvaAbcDebitos.addParametro("idTipoCategoria",
				imovelCurvaAbcDebitosActionForm.getIdTipoCategoria());
		relatorioGerarCurvaAbcDebitos.addParametro("categoria",
				imovelCurvaAbcDebitosActionForm.getCategoria());
		relatorioGerarCurvaAbcDebitos.addParametro("idSubCategoria",
				imovelCurvaAbcDebitosActionForm.getIdSubCategoria());
		
		relatorioGerarCurvaAbcDebitos.addParametro("valorMinimoDebito",
				imovelCurvaAbcDebitosActionForm.getValorMinimoDebito());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloQuantidadeDocumentosInicial",
				imovelCurvaAbcDebitosActionForm.getIntervaloQuantidadeDocumentosInicial());
		relatorioGerarCurvaAbcDebitos.addParametro("intervaloQuantidadeDocumentosFinal",
				imovelCurvaAbcDebitosActionForm.getIntervaloQuantidadeDocumentosFinal());
		relatorioGerarCurvaAbcDebitos.addParametro("indicadorPagamentosNaoClassificados",
				imovelCurvaAbcDebitosActionForm.getIndicadorPagamentosNaoClassificados());
		
		
		relatorioGerarCurvaAbcDebitos.addParametro("colecaoGerenciasRegionais",
				sessao.getAttribute("colecaoGerenciasRegionais"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionsLigacaoAguaSituacao",
				sessao.getAttribute("collectionsLigacaoAguaSituacao"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionLigacaoEsgotoSituacao",
				sessao.getAttribute("collectionLigacaoEsgotoSituacao"));
		
		
		relatorioGerarCurvaAbcDebitos.addParametro("collectionImovelPerfil",
				sessao.getAttribute("collectionImovelPerfil"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionCategoriaTipo",
				sessao.getAttribute("collectionCategoriaTipo"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionImovelCategoria",
				sessao.getAttribute("collectionImovelCategoria"));
		relatorioGerarCurvaAbcDebitos.addParametro("collectionImovelSubcategoria",
				sessao.getAttribute("collectionImovelSubcategoria"));
		
		relatorioGerarCurvaAbcDebitos.addParametro("localidadeInicial",
				sessao.getAttribute("localidadeInicial"));
		relatorioGerarCurvaAbcDebitos.addParametro("localidadeFinal",
				sessao.getAttribute("localidadeFinal"));
		relatorioGerarCurvaAbcDebitos.addParametro("setorComercialInicial",
				sessao.getAttribute("setorComercialInicial"));
		relatorioGerarCurvaAbcDebitos.addParametro("setorComercialFinal",
				sessao.getAttribute("setorComercialFinal"));
		
		relatorioGerarCurvaAbcDebitos.addParametro("idMunicipio",
				sessao.getAttribute("idMunicipio"));
		relatorioGerarCurvaAbcDebitos.addParametro("nomeMunicipio",
				sessao.getAttribute("nomeMunicipio"));
		
        // Flag para tela de sucesso apos a tela de espera de processamento de relatorio
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		
		// Fim da parte que vai mandar os parametros para o relat�rio
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioGerarCurvaAbcDebitos.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorioGerarCurvaAbcDebitos,
				tipoRelatorio, httpServletRequest,
				httpServletResponse, actionMapping);
		
		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}
}
