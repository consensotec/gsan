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
package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CertidaoNegativaDebito;
import gcom.cobranca.FiltroCertidaoNegativaDebito;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.GerarCertidaoNegativaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00738] Gerar Certid�o Negativa
 * 
 * @author 
 *
 * @date 28/11/2007
 */

public class GerarCertidaoNegativaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirCertidaoNegativa");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form
		GerarCertidaoNegativaActionForm form = 
			(GerarCertidaoNegativaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		if(httpServletRequest.getParameter("lojaVirtual") != null && httpServletRequest.getParameter("lojaVirtual").equals("sim")){
			String ip = httpServletRequest.getRemoteAddr(); 
			fachada.verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.CERTIDAO_NEGATIVA_DEBITOS, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		}
		
		
		Integer matricula = null;
		if ( sessao.getAttribute("matricula") != null && !sessao.getAttribute("matricula").equals("") ) {
			matricula = (Integer) sessao.getAttribute("matricula");
		}

		// Imovel que foi informado
		Imovel imo = null;
		
		if ((form.getIdImovel() != null && 
			!form.getIdImovel().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) || matricula != null ) {
			
			imo = new Imovel();
			if ( matricula != null ) {
				imo.setId( matricula );
			} else {
				imo.setId( Integer.valueOf( form.getIdImovel() ) );
			}
			
			//[FS0006] Validar CPF/CNPJ
			SistemaParametro sistemaParametro = this.getSistemaParametro();
			if(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_CAEMA)){
				boolean cpfCnpjValido = this.getFachada().verificarClienteImovelCpfCnpjValidos(imo.getId());
				if(!cpfCnpjValido){
					throw new ActionServletException("atencao.certidao_neg_cpf_cnpj_nao_validado");
				}
			}
			
			FiltroCertidaoNegativaDebito filtroCertidaoNegativaDebito = new FiltroCertidaoNegativaDebito();
			filtroCertidaoNegativaDebito.adicionarParametro(
				new ParametroSimples(FiltroCertidaoNegativaDebito.IMOVEL_ID, imo.getId()));
			filtroCertidaoNegativaDebito.adicionarParametro(
				new ParametroSimples(FiltroCertidaoNegativaDebito.DATA_GERACAO, new Date()));
			
			Collection<CertidaoNegativaDebito> colecaoCertidaoNegativaDebito = 
					fachada.pesquisar(filtroCertidaoNegativaDebito, CertidaoNegativaDebito.class.getName());
			
			//[FS0005] - Verificar exist�ncia de certid�o negativa de d�bito
			if (colecaoCertidaoNegativaDebito != null && 
				!colecaoCertidaoNegativaDebito.isEmpty()) {
				
				CertidaoNegativaDebito certidaoNegativaDebito = 
					(CertidaoNegativaDebito) 
						Util.retonarObjetoDeColecao(colecaoCertidaoNegativaDebito);
				
				httpServletResponse.addHeader("Content-Disposition",
						"attachment; filename=relatorio.pdf");
				String mimeType = "application/pdf";
				httpServletResponse.setContentType(mimeType);
				OutputStream out = null;
				try {
					out = httpServletResponse.getOutputStream();
					out.write(certidaoNegativaDebito.getDocumentoGerado());
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					// manda o erro para a p�gina no request atual
					reportarErros(httpServletRequest, "erro.sistema");
					// seta o mapeamento de retorno para a tela de erro de popup
					retorno = actionMapping.findForward("telaErroPopup");
				}

				return retorno;
			}
			
			if (! fachada.esferaPoderPermiteGerarCertidaoNegativa(imo.getId()) ) {	
				throw new ActionServletException("atencao.esfera_poder_responsavel_nao_permite_geracao_certidao_negativa");
			}
			
		}		
		
		Boolean lojaVirtual = false;
		if (httpServletRequest.getParameter("lojaVirtual") != null && 
			httpServletRequest.getParameter("lojaVirtual").toString().trim().equalsIgnoreCase("sim")) {
			
			lojaVirtual = true;
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		Usuario usuarioLogado = this.getUsuarioLogado( httpServletRequest );
		
		TarefaRelatorio relatorio = 
			new RelatorioCertidaoNegativa( usuarioLogado );		
		
		relatorio.addParametro("imovel", imo);
		relatorio.addParametro("usuarioLogado", usuarioLogado);
		relatorio.addParametro("lojaVirtual", lojaVirtual);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}	

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
}