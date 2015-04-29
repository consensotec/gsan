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
package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.FiltroOsProgramNaoEncerMotivo;
import gsan.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo;
import gsan.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1199] � Acompanhamento Arquivos Roteiro Informar Situa��o Ordem de Servi�o
 * 
 * @author Th�lio Ara�jo
 *
 * @date 25/08/2011
 */
public class ExibirInformarSituacaoOSAcompanhamentoArquivosRoteiroAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInformarSituacaoOS");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		String chaveArquivo = httpServletRequest.getParameter("chaveArquivo");
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico =  null;
		
		if (chaveArquivo != null){
			FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
			filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID, chaveArquivo));
			
			
			Collection<?> colecaoArquivoTxtAcompanhamentoServico = fachada.pesquisar(
					filtroArquivoTextoAcompanhamentoServico,
				    ArquivoTextoAcompanhamentoServico.class.getName());
			
			arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) colecaoArquivoTxtAcompanhamentoServico
				    .iterator().next();
			
			if (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				throw new ActionServletException("atencao.nao_possivel.informar_situacao.situacao_finalizado");
			}
		}
		
		String chaveOs = httpServletRequest.getParameter("chave");
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(new Integer(chaveOs));
		
		//[FS0007 - Verificar sele��o de ordem de servi�o encerrada]
		OSProgramacaoAcompanhamentoServico osAcompanhamentoServico = Fachada.getInstancia().pesquisarOSProgramacaoAcompServicoPorIdArqTxt(ordemServico.getId(),arquivoTextoAcompanhamentoServico.getId());
		
		if (osAcompanhamentoServico.getOrdemServicoSituacao().getId().equals(new Integer(OrdemServico.SITUACAO_ENCERRADO.intValue()))){
			throw new ActionServletException("atencao.situacao.diferente_pendente_execucao", "informar a situa��o da");
		}

		Date dataRoteiro = 
			Util.converteStringParaDate(acompanharActionForm.getDataProgramacao());		

		acompanharActionForm.setIdOrdemServico(chaveOs);
		acompanharActionForm.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
		
		String situacao;
		if (osAcompanhamentoServico.getOrdemServicoSituacao().getId().equals(new Integer(OrdemServico.SITUACAO_PENDENTE.intValue()))){
			situacao = "PENDENTE";
		}else{
			situacao = "EXECU��O EM ANDAMENTO";
		}
		acompanharActionForm.setSituacaoAtual(situacao);
		
		acompanharActionForm.setIdAcompanhamentoArquivosRoteiro(httpServletRequest.getParameter("chaveArquivo"));
		
		this.pesquisarOsProgramNaoEncerMotivo(httpServletRequest);
		
		sessao.setAttribute("chaveOsInformarSituacao", chaveOs);
		sessao.setAttribute("chaveArquivoInformarSituacao", chaveArquivo);
		sessao.setAttribute("dataRoteiroInformarSituacao", dataRoteiro);

		return retorno;
	}
	
	private Collection<OsProgramNaoEncerMotivo> pesquisarOsProgramNaoEncerMotivo(
			HttpServletRequest httpServletRequest){

		Collection<OsProgramNaoEncerMotivo> retorno = new ArrayList();
		
		FiltroOsProgramNaoEncerMotivo filtroOsProgramNaoEncerMotivo = new FiltroOsProgramNaoEncerMotivo();

		filtroOsProgramNaoEncerMotivo.setCampoOrderBy(FiltroOsProgramNaoEncerMotivo.DESCRICAO);

		retorno = 
			Fachada.getInstancia().pesquisar(filtroOsProgramNaoEncerMotivo, 
					OsProgramNaoEncerMotivo.class.getName());
		
		httpServletRequest.setAttribute("colecaoMotivoNaoEncerramento", retorno );
		
		return retorno;

	}
}