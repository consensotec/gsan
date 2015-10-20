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
* Ivan S�rgio Virginio da Silva J�nior
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
package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.ordemservico.GerarRelatorioBoletimOrdensServicoConcluidasActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioBoletimOrdensServicoConcluidas;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Ivan S�rgio
 * @created 07/05/2008
 * 
 * [UC0766] Gerar Relatorio Boletim de Ordens de Servico Concluidas
 * 
 */
public class GerarRelatorioBoletimOrdensServicoConcluidasAction extends
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

		GerarRelatorioBoletimOrdensServicoConcluidasActionForm form = 
			(GerarRelatorioBoletimOrdensServicoConcluidasActionForm) actionForm;

		// Recupera os valores do form para serem passados como par�metros para o Relatorio
		String idFirma = form.getIdFirma();
		String nomeFirma = form.getNomeFirma();
		String idLocalidade = form.getIdLocalidade();
		String nomeLocalidade = form.getNomeLocalidade();
		String idSetorComercial = form.getIdSetorComercial();
		String codigoSetorComercial = form.getCodigoSetorComercial();
		String nomeSetorComercial = form.getNomeSetorComercial();
		String anoMesReferenciaEncerramento = form.getAnoMesReferenciaEncerramento();
		String situacao = form.getSituacao();

		// Valida a Localidade Informada
		if (idLocalidade != null && !idLocalidade.equals("")) {
			Localidade localidade = validarLocalidade(idLocalidade);
			
			if (nomeLocalidade == null || nomeLocalidade.equals("")) {
				nomeLocalidade = localidade.getDescricao();
			}
		}
		
		// Valida o Setor Informado
		if (codigoSetorComercial != null && !codigoSetorComercial.equals("")) {
			SetorComercial setorComercial = validarSetorComercial(idLocalidade, codigoSetorComercial);
			
			if (idSetorComercial == null || idSetorComercial.equals("")) {
				idSetorComercial = setorComercial.getId().toString();
			}
			if (nomeSetorComercial == null || nomeSetorComercial.equals("")) {
				nomeSetorComercial = setorComercial.getDescricao();
			}
		}
		
		String tipoGeracaoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		RelatorioBoletimOrdensServicoConcluidas relatorio = 
			new RelatorioBoletimOrdensServicoConcluidas(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		relatorio.addParametro("idFirma", idFirma);
		relatorio.addParametro("nomeFirma", nomeFirma);
		relatorio.addParametro("idLocalidade", idLocalidade);
		relatorio.addParametro("nomeLocalidade", nomeLocalidade);
		relatorio.addParametro("idSetorComercial", idSetorComercial);
		relatorio.addParametro("codigoSetorComercial", codigoSetorComercial);
		relatorio.addParametro("nomeSetorComercial", nomeSetorComercial);
		relatorio.addParametro("anoMesReferenciaEncerramento", anoMesReferenciaEncerramento);
		relatorio.addParametro("situacao", situacao);
		
		if (tipoGeracaoRelatorio == null) {
			tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
		
		retorno = processarExibicaoRelatorio(
				relatorio, tipoGeracaoRelatorio, httpServletRequest,
				httpServletResponse, actionMapping);

		return retorno;
	}

	private Localidade validarLocalidade(String localidade) {
		Localidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		FiltroLocalidade filtroLocalidade = null;
		Collection colecaoLocalidade = null;
		
		// Verifica se a Localidade existe
		if (localidade != null && !localidade.equals("")) {
			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidade));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Iterator iColecaoLocalidade = colecaoLocalidade.iterator();
				retorno = (Localidade) iColecaoLocalidade.next();
			}else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
			}
		}
		
		return retorno;
	}
	
	private SetorComercial validarSetorComercial(String localidade, String codigoSetorComercial) {
		SetorComercial retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		FiltroSetorComercial filtroSetorComercial = null;
		Collection colecaoSetorComercial = null;
		
		// Verifica se o Setor existe
		if (codigoSetorComercial != null && !codigoSetorComercial.equals("")) {
			filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, localidade));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
				Iterator iColecaoSetorComercial = colecaoSetorComercial.iterator();
				retorno = (SetorComercial) iColecaoSetorComercial.next();
			}else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
			}
		}
		
		return retorno;
	}
}
