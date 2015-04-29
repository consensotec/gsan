
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
package gsan.gui.cobranca;


import java.util.Collection;

import gsan.cadastro.cliente.Profissao;
import gsan.cadastro.cliente.RamoAtividade;
import gsan.cobranca.CobrancaSituacao;
import gsan.cobranca.FiltroCobrancaSituacao;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesDiferenteDe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCobrancaSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCobrancaSituacaoActionForm atualizarCobrancaSituacaoActionForm = (AtualizarCobrancaSituacaoActionForm) actionForm;

		CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) sessao.getAttribute("atualizarCobrancaSituacao");
		
		Collection colecaoPesquisa = null;
		
		if(atualizarCobrancaSituacaoActionForm.getCodigo()!= null 
				&& !atualizarCobrancaSituacaoActionForm.getCodigo().equals("")){
			cobrancaSituacao.setId(new Integer(atualizarCobrancaSituacaoActionForm.getCodigo()));
		}else{
			cobrancaSituacao.setId(null);
		}
		
		String codigo = atualizarCobrancaSituacaoActionForm.getCodigo();		
        String descricao = atualizarCobrancaSituacaoActionForm.getDescricao();
        Short indicadorUso = atualizarCobrancaSituacaoActionForm.getIndicadorUso();
        String contaMotivoRevisao = atualizarCobrancaSituacaoActionForm.getContaMotivoRevisao();
        Short indicadorBloqueioParcelamento = atualizarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento();
        Short indicadorExigenciaAdvogado = atualizarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado();
        Short indicadorBloqueioRetirada = atualizarCobrancaSituacaoActionForm.getIndicadorBloqueioRetirada();
        String profissao = atualizarCobrancaSituacaoActionForm.getProfissao();
        String ramoAtividade = atualizarCobrancaSituacaoActionForm.getRamoAtividade();
        Short indicadorBloqueioInclusao = atualizarCobrancaSituacaoActionForm.getIndicadorBloqueioInclusao();
        Short indicadorSelecaoApenasComPermissao = atualizarCobrancaSituacaoActionForm.getIndicadorSelecaoApenasComPermissao();
        Integer indicadorPrescricaoImoveisParticulares = atualizarCobrancaSituacaoActionForm.getIndicadorPrescricaoImoveisParticulares();
        Integer indicadorNaoIncluirImoveisEmCobrancaResultado = atualizarCobrancaSituacaoActionForm.getIndicadorNaoIncluirImoveisEmCobrancaResultado();
        Short indicadorCancelarImovelNegativacao = atualizarCobrancaSituacaoActionForm.getIndicadorCancelarImovelNegativacao();
        Short indicadorAlterarVencimentoConta = atualizarCobrancaSituacaoActionForm.getIndicadorAlterarVencimentoConta();
        String indicadorBloqueioCertidaoDebNegativado = atualizarCobrancaSituacaoActionForm.getIndicadorBloqueioCertidaoDebNegativado();
        
        cobrancaSituacao.setDescricao(descricao);
        
        
		//Descricao
        FiltroCobrancaSituacao filtroCobSit= new FiltroCobrancaSituacao();
        filtroCobSit.adicionarParametro(
				new ParametroSimples(FiltroCobrancaSituacao.DESCRICAO, descricao));
        filtroCobSit.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCobrancaSituacao.ID, cobrancaSituacao.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroCobSit, CobrancaSituacao.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		
		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		filtroCobrancaSituacao.adicionarParametro(
					new ParametroSimples(FiltroCobrancaSituacao.ID, codigo));
		filtroCobrancaSituacao.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCobrancaSituacao.ID, cobrancaSituacao.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigo+"");
		}
        
        
        
        //Motivo de Revis�o da Conta
		if(contaMotivoRevisao != null && !contaMotivoRevisao.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			ContaMotivoRevisao contaMotivo = new ContaMotivoRevisao();
			contaMotivo.setId(new Integer(atualizarCobrancaSituacaoActionForm
					.getContaMotivoRevisao()));
			
			cobrancaSituacao.setContaMotivoRevisao(contaMotivo);
			
		} else {
			cobrancaSituacao.setContaMotivoRevisao(null);
		}
		
		//Profissao
		if(profissao != null && !profissao.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			Profissao prof = new Profissao();
			prof.setId(new Integer(atualizarCobrancaSituacaoActionForm
					.getProfissao()));
			
			cobrancaSituacao.setProfissao(prof);
			
		} else {
			cobrancaSituacao.setProfissao(null);
		}
		
		//Ramo Atividade
		if(ramoAtividade != null && !ramoAtividade.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			RamoAtividade ramoAtiv = new RamoAtividade();
			ramoAtiv.setId(new Integer(atualizarCobrancaSituacaoActionForm
					.getRamoAtividade()));
			
			cobrancaSituacao.setRamoAtividade(ramoAtiv);
			
		} else {
			cobrancaSituacao.setRamoAtividade(null);
		}
		
		//Exige advogado
		if(indicadorExigenciaAdvogado != null && 
			!indicadorExigenciaAdvogado.equals("")){
			
			cobrancaSituacao.setIndicadorExigenciaAdvogado(atualizarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado());
			
			if ( indicadorExigenciaAdvogado == 1 ) {
				//Profissao
				if(profissao != null && 
					profissao.equals("-1")){
					
					throw new ActionServletException("atencao.required", null,
					"Profiss�o");
				}
				//Ramo Atividade
				if(ramoAtividade != null && 
					ramoAtividade.equals("-1")){
					
					throw new ActionServletException("atencao.required", null,
					"Ramo Atividade");
				} 
			}			
		}		
		
		/**
		 * MA20140610677 - Alterar vencimentos para contas negativadas
		 * @author Diogo Luiz
		 * @date 23/06/2014
		 * RM11230 - [UC0858] - Manter Situa��o de Cobran�a		
		 */
		if(indicadorAlterarVencimentoConta != null && !indicadorAlterarVencimentoConta.equals("")){
			cobrancaSituacao.setIndicadorAlterarVencimentoConta(indicadorAlterarVencimentoConta);
		}		
		
		cobrancaSituacao.setIndicadorUso(indicadorUso);
		cobrancaSituacao.setIndicadorBloqueioParcelamento(indicadorBloqueioParcelamento);
		cobrancaSituacao.setIndicadorExigenciaAdvogado(indicadorExigenciaAdvogado);
		cobrancaSituacao.setIndicadorBloqueioRetirada(indicadorBloqueioRetirada);
		cobrancaSituacao.setIndicadorBloqueioInclusao(indicadorBloqueioInclusao);
		cobrancaSituacao.setIndicadorSelecaoApenasComPermissao(indicadorSelecaoApenasComPermissao);
        cobrancaSituacao.setIndicadorCancelarImovelNegativacao(indicadorCancelarImovelNegativacao);
        //inserirCobrancaSituacaoActionForm.setIndicadorCancelarImovelNegativacao(ConstantesSistema.NAO);       
        cobrancaSituacao.setIndicadorBloqueioCertidaoDebNegativado(Integer.parseInt(indicadorBloqueioCertidaoDebNegativado));
        //inserirCobrancaSituacaoActionForm.setIndicadorCancelarImovelNegativacao(ConstantesSistema.NAO);
        
		if(indicadorPrescricaoImoveisParticulares != null){
			cobrancaSituacao.setIndicadorPrescricaoImoveisParticulares(indicadorPrescricaoImoveisParticulares);
		}
		if(indicadorNaoIncluirImoveisEmCobrancaResultado != null){
			cobrancaSituacao.setIndicadorNaoIncluirImoveisEmCobrancaResultado(indicadorNaoIncluirImoveisEmCobrancaResultado);
		}
		
		fachada.atualizar(cobrancaSituacao);
		
		montarPaginaSucesso(httpServletRequest, "Situa��o de Cobran�a "
				+ descricao + " atualizado com sucesso.",
				"Realizar outra Manuten��o de Situa��o de Cobran�a",
				"exibirFiltrarCobrancaSituacaoAction.do?menu=sim");        
        
		return retorno;
	}
}