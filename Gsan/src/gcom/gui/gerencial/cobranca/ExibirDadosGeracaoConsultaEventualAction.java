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
package gcom.gui.gerencial.cobranca;


import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author S�vio Luiz
 * @date 26/10/2006
 * 
 */
public class ExibirDadosGeracaoConsultaEventualAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirDadosGeracaoConsultaEventual");
		
		//obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper = 
        	(InformarDadosGeracaoResumoAcaoConsultaEventualHelper)sessao.getAttribute("informarDadosGeracaoResumoAcaoConsultaEventualHelper");
        
		//Pesquisa os dados do resumo das a��es de cobran�a
		Collection colecaoResumoAcaoCobranca = fachada.consultarResumoCobrancaAcaoEventual(informarDadosGeracaoResumoAcaoConsultaEventualHelper);
		
		sessao.setAttribute("colecaoResumoAcaoCobranca", colecaoResumoAcaoCobranca);

		Collection colecaoAgrupamento = fachada.criarColecaoAgrupamentoResumosCobrancaAcaoEventual(informarDadosGeracaoResumoAcaoConsultaEventualHelper);
		
		sessao.setAttribute("colecaoAgrupamento", colecaoAgrupamento);
		
		StatusWizard statusWizard = (StatusWizard)sessao.getAttribute("statusWizard");
		
		adicionarTextoParametrosParaHintStatusWizard(statusWizard, informarDadosGeracaoResumoAcaoConsultaEventualHelper);
				
		sessao.removeAttribute("colecaoImoveisPerfil");
		sessao.removeAttribute("colecaoLigacaoAgua");
		sessao.removeAttribute("colecaoImoveisPerfil");
		sessao.removeAttribute("colecaoLigacaoEsgoto");
		sessao.removeAttribute("colecaoCategoria");
		sessao.removeAttribute("colecaoEsferaPoder");
		sessao.removeAttribute("colecaoGrupoCobranca");
		sessao.removeAttribute("colecaoEmpresa");
		
 		return retorno;
	}
	
	private void adicionarTextoParametrosParaHintStatusWizard(StatusWizard statusWizard, 
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper helper){
		StringBuffer texto = new StringBuffer();
		
		texto.append("<B>Par�metros:</B>");
		texto.append("<BR>Per�odo de Emiss�o: <I>" + helper.getFormatarDataEmissaoInicial() + " a " +
				helper.getFormatarDataEmissaoFinal() + "</I>");

		texto.append("<BR>T�tulo do comando: <I>" + helper.getTituloCobrancaAcaoAtividadeComando() + "</I>");
		
		String grupos = "";
		if (helper.getColecaoCobrancaGrupo() != null){
			for (Iterator iter = helper.getColecaoCobrancaGrupo().iterator();iter.hasNext();) {
				CobrancaGrupo grupo = (CobrancaGrupo) iter.next();
				grupos += grupo.getDescricao() + " / ";
			}
			if (!grupos.equals("")){
				grupos = grupos.substring(0, grupos.length() - 3);
			}
		} else {
			grupos = "TODOS";
		}
		texto.append("<BR>Grupo de Cobran�a:<I>" + grupos + "</I>");
		
		String gerencias = "";
		if (helper.getColecaoGerenciaRegional() != null){
			for (Iterator iter = helper.getColecaoGerenciaRegional().iterator();iter.hasNext();) {
				GerenciaRegional GR = (GerenciaRegional) iter.next();
				gerencias += GR.getNome() + " / ";
			}
			if (!gerencias.equals("")){
				gerencias = gerencias.substring(0, gerencias.length() - 3);
			}
		} else {
			gerencias = "TODAS";
		}
		texto.append("<BR>Ger�ncia Regional: <I>" + gerencias + "</I>");
		
		String unidadesNeg = "";
		if (helper.getColecaoUnidadeNegocio() != null){
			for (Iterator iter = helper.getColecaoUnidadeNegocio().iterator();iter.hasNext();) {
				UnidadeNegocio unidade = (UnidadeNegocio) iter.next();
				unidadesNeg += unidade.getNome() + " / ";
			}
			if (!unidadesNeg.equals("")){
				unidadesNeg = unidadesNeg.substring(0, unidadesNeg.length() - 3);
			}
		} else {
			unidadesNeg = "TODAS";
		}
		texto.append("<BR>Unidade Neg�cio: <I>" + unidadesNeg + "</I>");

		if (helper.getEloPolo() != null){
			texto.append("<BR>Elo Polo: <I>" + helper.getEloPolo().getDescricao() + "</I>");
		}
		if (helper.getLocalidade() != null){
			texto.append("<BR>Localidade: <I>" + helper.getLocalidade().getDescricaoParaRegistroTransacao() + "</I>");
		}
		if (helper.getSetorComercial() != null){
			texto.append("<BR>Setor Comercial: <I>" + helper.getSetorComercial().getCodigo() + "</I>");
		}
		if (helper.getQuadra() != null){
			texto.append("<BR>Quadra: <I>" + helper.getQuadra().getNumeroQuadra() + "</I>");
		}
						
		String perfisImovel = "";
		if (helper.getColecaoImovelPerfil() != null){
			for (Iterator iter = helper.getColecaoImovelPerfil().iterator();iter.hasNext();) {
				ImovelPerfil imovelPerfil = (ImovelPerfil) iter.next();
				perfisImovel += imovelPerfil.getDescricao() + " / ";
			}
			if (!perfisImovel.equals("")){
				perfisImovel = perfisImovel.substring(0, perfisImovel.length() - 3);
			}
		} else {
			perfisImovel = "TODOS";
		}
		texto.append("<BR>Perfil do Im�vel: <I>" + perfisImovel + "</I>");
		
		String situacoesLigacaoAgua = "";
		if (helper.getColecaoLigacaoAguaSituacao() != null){
			for (Iterator iter = helper.getColecaoLigacaoAguaSituacao().iterator();iter.hasNext();) {
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) iter.next();
				situacoesLigacaoAgua += ligacaoAguaSituacao.getDescricao() + " / ";
			}
			if (!situacoesLigacaoAgua.equals("")){
				situacoesLigacaoAgua = situacoesLigacaoAgua.substring(0, situacoesLigacaoAgua.length() - 3);
			}
		} else {
			situacoesLigacaoAgua = "TODAS";
		}
		texto.append("<BR>Situa��o de Liga��o de �gua: <I>" + situacoesLigacaoAgua + "</I>");

		String situacoesLigacaoEsgoto = "";
		if (helper.getColecaoLigacaoEsgotoSituacao() != null){
			for (Iterator iter = helper.getColecaoLigacaoEsgotoSituacao().iterator();iter.hasNext();) {
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iter.next();
				situacoesLigacaoEsgoto += ligacaoEsgotoSituacao.getDescricao() + " / ";
			}
			if (!situacoesLigacaoEsgoto.equals("")){
				situacoesLigacaoEsgoto = situacoesLigacaoEsgoto.substring(0, situacoesLigacaoEsgoto.length() - 3);
			}
		} else {
			situacoesLigacaoEsgoto = "TODAS";
		}
		texto.append("<BR>Situa��o de Liga��o de Esgoto: <I>" + situacoesLigacaoEsgoto + "</I>");
		
		String categorias = "";
		if (helper.getColecaoCategoria() != null){
			for (Iterator iter = helper.getColecaoCategoria().iterator();iter.hasNext();) {
				Categoria categoria = (Categoria) iter.next();
				categorias += categoria.getDescricao() + " / ";
			}
			if (!categorias.equals("")){
				categorias = categorias.substring(0, categorias.length() - 3);
			}
		} else {
			categorias = "TODAS";
		}
		texto.append("<BR>Categoria: <I>" + categorias + "</I>");
		
		String esferasPoder = "";
		if (helper.getColecaoEsferaPoder() != null){
			for (Iterator iter = helper.getColecaoEsferaPoder().iterator();iter.hasNext();) {
				EsferaPoder esferaPoder = (EsferaPoder) iter.next();
				esferasPoder += esferaPoder.getDescricao() + " / ";
			}
			if (!esferasPoder.equals("")){
				esferasPoder = esferasPoder.substring(0, esferasPoder.length() - 3);
			}
		} else {
			esferasPoder = "TODAS";
		}
		texto.append("<BR>Esfera de Poder: <I>" + esferasPoder + "</I>");
		
		String empresas = "";
		if (helper.getColecaoEmpresa() != null){
			for (Iterator iter = helper.getColecaoEmpresa().iterator();iter.hasNext();) {
				Empresa empresa = (Empresa) iter.next();
				empresas += empresa.getDescricao() + " / ";
			}
			if (!empresas.equals("")){
				empresas = empresas.substring(0, empresas.length() - 3);
			}
		} else {
			empresas = "TODAS";
		}
		texto.append("<BR>Empresa: <I>" + empresas + "</I>");
		
		statusWizard.adicionarItemHint("",texto.toString());
	}
	
}