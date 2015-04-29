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
package gsan.micromedicao;

import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.Localidade;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.micromedicao.ConsultarArquivoTextoLeituraActionForm;
import gsan.micromedicao.bean.ConsultarArquivoTextoRoteiroEmpresaHelper;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Thiago Ten�rio e Thiago Nascimento
 * @date 05/08/2006
 */
public class ConsultarArquivoTextoLeituraAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servi�o
	 * 
	 * [UC0437] Pesquisar Tipo de Servi�o de Refer�ncia
	 * 
	 * 
	 * @author Thiago Ten�rio e Thiago Nascimento 
	 * @date 31/07/2006 
	 * 
	 * Yara T. Souza  
	 * 19/12/2008
	 * 
	 * 
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
	
			ActionForward retorno = actionMapping
					.findForward("consultarArquivoTextoLeitura");
	
			HttpSession sessao = httpServletRequest.getSession(false);
	
			ConsultarArquivoTextoLeituraActionForm consultarArquivoTextoLeituraActionForm = (ConsultarArquivoTextoLeituraActionForm) actionForm;
	
			ConsultarArquivoTextoRoteiroEmpresaHelper consultarArquivoTextoRoteiroEmpresaHelper = new ConsultarArquivoTextoRoteiroEmpresaHelper();
			
			boolean peloMenosUmParametroInformado = false;
			
			/**
			 * CRC775
			 * autor: Yara T. Souza
			 * data: 19/12/2008
			 * Comentei todos os objetos que antes era setados na sess�o e deixei apenas no FORM.
			 */
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	
			sessao.removeAttribute("permissao");
			if (usuarioLogado.getEmpresa().getIndicadorEmpresaPrincipal().equals(
					new Short("1"))) {
				sessao.setAttribute("permissao", "1");
			} else {
				sessao.setAttribute("permissao", "2");
			}
			
			//EMPRESA
			String empresaID = consultarArquivoTextoLeituraActionForm.getEmpresaID();
			
			if (empresaID != null && !empresaID.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
				peloMenosUmParametroInformado = true;
	
				consultarArquivoTextoRoteiroEmpresaHelper.setIdEmpresa(empresaID);
			}
			
			//SERVICO_TIPO_CELULAR
			String servicoTipoCelular = consultarArquivoTextoLeituraActionForm.getServicoTipoCelular();
			
			if(servicoTipoCelular != null && !servicoTipoCelular.trim().equalsIgnoreCase("")){
				
				peloMenosUmParametroInformado = true;
				
				consultarArquivoTextoRoteiroEmpresaHelper.setIdServicoTipoCelular(servicoTipoCelular);
			}
	
			//FATURAMENTO_GRUPO
			String grupoFaturamentoID = consultarArquivoTextoLeituraActionForm.getGrupoFaturamentoID();
			
			if (grupoFaturamentoID != null && !grupoFaturamentoID.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO) && 
				!grupoFaturamentoID.equals("-1") && !grupoFaturamentoID.equals("")) {
	
				peloMenosUmParametroInformado = true;
	
				consultarArquivoTextoRoteiroEmpresaHelper.setIdFaturamentoGrupo(grupoFaturamentoID);
	
			}
	
			//ANO MES REFERENCIA
			String mesAno = consultarArquivoTextoLeituraActionForm.getMesAno();
			
			if (mesAno != null && !mesAno.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				
				consultarArquivoTextoRoteiroEmpresaHelper.setAnoMesReferencia((Util.formatarMesAnoComBarraParaAnoMes(mesAno)).toString());
			}
	
			//SITUACAO_TRANSMISSAO_LEITURA
			String situaTransmLeitura = consultarArquivoTextoLeituraActionForm.getSituaTransmLeitura();
			
			if (situaTransmLeitura != null && !situaTransmLeitura.trim().equalsIgnoreCase("")) {
	
				peloMenosUmParametroInformado = true;
	
				consultarArquivoTextoRoteiroEmpresaHelper.setIdSituacaoTransmissaoLeitura(situaTransmLeitura);
			}
			
			Fachada fachada = Fachada.getInstancia();
			
			
			// filtro por localidade
			
			String localidadeID = consultarArquivoTextoLeituraActionForm.getIdLocalidade();
			
			if(localidadeID != null && !localidadeID.trim().equalsIgnoreCase(
			"" + ConstantesSistema.NUMERO_NAO_INFORMADO) && !localidadeID.equals("")){
				
				peloMenosUmParametroInformado = true;
				
				consultarArquivoTextoRoteiroEmpresaHelper.setIdLocalidade(localidadeID);
			}
			
			Localidade localidade = null;
			if(consultarArquivoTextoLeituraActionForm.getIdLocalidade() != null && !consultarArquivoTextoLeituraActionForm.getIdLocalidade().equals("")){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, consultarArquivoTextoLeituraActionForm.getIdLocalidade()));
				
				Collection<Localidade> colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				
				if(!Util.isVazioOrNulo(colLocalidade)){
					localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
				}else{ 
					throw new ActionServletException("atencao.localidade.inexistente");
				
				}
			}
			
		
	
			//LEITURISTA
			String leiturista = consultarArquivoTextoLeituraActionForm.getLeituristaID();
			
			if (leiturista != null && !leiturista.equals("") && !leiturista.equals("-1")) {
	
				peloMenosUmParametroInformado = true;
				
				consultarArquivoTextoRoteiroEmpresaHelper.setIdLeiturista(leiturista);
	
			}
	
			// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
			if (!peloMenosUmParametroInformado) {
				
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
			
			
			
			//1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
			/*Integer totalRegistros = fachada.filtrarArquivoTextoRoteiroEmpresaCount
			(consultarArquivoTextoRoteiroEmpresaHelper);*/
			
			//2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
			//retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
			
			/*
			 * 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando 
			 * o numero de paginas da pesquisa que est� no request
			 */
			Collection colecaoArquivoTextoRoteiroEmpresa = fachada.filtrarArquivoTextoRoteiroEmpresaParaPaginacao(
			consultarArquivoTextoRoteiroEmpresaHelper, ((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")));
	
			sessao.setAttribute("colecaoArquivoTextoRoteiroEmpresa", colecaoArquivoTextoRoteiroEmpresa);
			
			if(!colecaoArquivoTextoRoteiroEmpresa.isEmpty()){
				sessao.setAttribute("achou","1");
			}else{
				sessao.setAttribute("achou","2");
			}
			
			if ( fachada.verificarPermissaoEspecial( PermissaoEspecial.GERAR_ARQUIVO_TEXTO_IMOVEIS_NAO_ENVIADOS, usuarioLogado ) ){
				httpServletRequest.setAttribute( "permissaoRegerarArquivoImoveisNaoEnviados", "SIM" );
			} else {
				httpServletRequest.setAttribute( "permissaoRegerarArquivoImoveisNaoEnviados", "NAO" );
			}			
			
			if (consultarArquivoTextoLeituraActionForm.getServicoTipoCelular()!= null && consultarArquivoTextoLeituraActionForm.getServicoTipoCelular().equals("2")){
				httpServletRequest.setAttribute("desabilitaBotao", true);
			}else {
				httpServletRequest.removeAttribute("desabilitaBotao");
			}
		

			return retorno;
	}
}