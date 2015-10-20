/* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.gui.cobranca;


import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirCobrancaSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de uma Situacao de Cobranca
	 * 
	 * [UC0858] Inserir Situacao de Cobranca
	 * 
	 * @author Arthur Carvalho
	 * @date 04/09/2008
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

		InserirCobrancaSituacaoActionForm form = (InserirCobrancaSituacaoActionForm) actionForm;

		String descricao = form.getDescricao();
		String contaMotivoRevisao = form.getContaMotivoRevisao();
		Short indicadorExigenciaAdvogado = form.getIndicadorExigenciaAdvogado();
		Short indicadorBloqueioParcelamento = form.getIndicadorBloqueioParcelamento();
		Short indicadorBloqueioRetirada = form.getIndicadorBloqueioRetirada();
		Short indicadorBloqueioInclusao = form.getIndicadorBloqueioInclusao();
		Short indicadorSelecaoApenasComPermissao = form.getIndicadorSelecaoApenasComPermissao();
		Integer indicadorPrescricaoImoveisParticulares = form.getIndicadorPrescricaoImoveisParticulares();
		Integer indicadorNaoIncluirImoveisEmCobrancaResultado = form.getIndicadorNaoIncluirImoveisEmCobrancaResultado();
		String profissao = form.getProfissao();
		String ramoAtividade = form.getRamoAtividade();
		Short indicadorCancelarImovelNegativacao = form.getIndicadorCancelarImovelNegativacao();
		Short indicadorAlterarVencimentoConta = form.getIndicadorAlterarVencimentoConta();
		String indicadorBloqueioCertidaoDebNegativado = form.getIndicadorBloqueioCertidaoDebNegativado();
		
		
		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
		
		Collection colecaoPesquisa = null;		
		
		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(FiltroCobrancaSituacao.DESCRICAO, descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		//Descri��o
		if (!descricao.equals("")) {
			cobrancaSituacao.setDescricao(descricao);
		} else {
			throw new ActionServletException("atencao.required", null,"Descri��o");
		}
		
		//Motivo de revisao da conta
		if(contaMotivoRevisao != null && 
			!contaMotivoRevisao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			ContaMotivoRevisao motivoRevisao = new ContaMotivoRevisao();
			motivoRevisao.setId(new Integer(form
					.getContaMotivoRevisao()));
			
			cobrancaSituacao.setContaMotivoRevisao(motivoRevisao);
		}
			
		//Exige advogado
		if(indicadorExigenciaAdvogado != null && 
			!indicadorExigenciaAdvogado.equals("")){
			
			cobrancaSituacao.setIndicadorExigenciaAdvogado(indicadorExigenciaAdvogado);
			
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
		
		//Bloqueia Parcelamento
		if(indicadorBloqueioParcelamento != null && 
			!indicadorBloqueioParcelamento.equals("")){
			
			cobrancaSituacao.setIndicadorBloqueioParcelamento(indicadorBloqueioParcelamento);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Indicador Bloqueio Parcelamento");
		}
		
		//Profissao
		if(profissao != null && 
			!profissao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			Profissao prof = new Profissao();
			prof.setId( new Integer( form.getProfissao() ) );
			
			cobrancaSituacao.setProfissao(prof);
		}
		
		//Ramo Atividade
		if(ramoAtividade != null && 
			!ramoAtividade.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			RamoAtividade ramoAtiv = new RamoAtividade();
			ramoAtiv.setId( new Integer( form.getRamoAtividade() ) );
			
			cobrancaSituacao.setRamoAtividade(ramoAtiv);
		}
		
		//Indicador Bloqueio Retirada
		if(indicadorBloqueioRetirada != null && 
			!indicadorBloqueioRetirada.equals("")){
			
			cobrancaSituacao.setIndicadorBloqueioRetirada(indicadorBloqueioRetirada);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Indicador Bloqueio Retirada");
		}
		
		//Indicador Bloqueio Inclusao
		if(indicadorBloqueioInclusao != null && 
			!indicadorBloqueioInclusao.equals("")){
			
			cobrancaSituacao.setIndicadorBloqueioInclusao(indicadorBloqueioInclusao);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Indicador Bloqueio Inclusao");
		}
				
		// Indicador de Sele��o da Situa��o Apenas pelos Usu�rios com Permiss�o Especial 
		if(indicadorSelecaoApenasComPermissao != null && 
			!indicadorSelecaoApenasComPermissao.equals("")){
			
			cobrancaSituacao.setIndicadorSelecaoApenasComPermissao(indicadorSelecaoApenasComPermissao);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Indicador de Sele��o da Situa��o Apenas pelos Usu�rios com Permiss�o Especial");
		}
		
		// Indicador Prescricao Imoveis Particulares
		if(indicadorPrescricaoImoveisParticulares != null && 
				!indicadorPrescricaoImoveisParticulares.equals("")){
			
			cobrancaSituacao.setIndicadorPrescricaoImoveisParticulares(indicadorPrescricaoImoveisParticulares);
		}else{
			throw new ActionServletException("atencao.required", null,
			"Indicador de Prescri��o para Im�veis Particulares");
		}
		
		// Indicador de n�o inclus�o da cobran�a por resultado
		if(indicadorNaoIncluirImoveisEmCobrancaResultado != null && 
				!indicadorNaoIncluirImoveisEmCobrancaResultado.equals("")){
			
			cobrancaSituacao.setIndicadorNaoIncluirImoveisEmCobrancaResultado(indicadorNaoIncluirImoveisEmCobrancaResultado);
		}else{
			throw new ActionServletException("atencao.required", null,
			"Indicador de n�o inclus�o da cobran�a por resultado");
		}
		
		if(indicadorBloqueioCertidaoDebNegativado!= null){
			cobrancaSituacao.setIndicadorBloqueioCertidaoDebNegativado(Integer.parseInt(indicadorBloqueioCertidaoDebNegativado));
		}
		
		//Indicador de retirar da negativa��o
				if(indicadorCancelarImovelNegativacao  != null && 
					!indicadorCancelarImovelNegativacao.equals("")){
					
					cobrancaSituacao.setIndicadorCancelarImovelNegativacao(indicadorCancelarImovelNegativacao);
				} else {
					throw new ActionServletException("atencao.required", null,
					"Indicador de retirar da negativa��o");
				}
				
		/**
		 * MA20140610677 - Alterar vencimentos para contas negativadas
		 * @author Diogo Luiz
		 * @date 23/06/2014
		 * RM11229 - [UC0858] - Inserir Situa��o de Cobran�a		
		 */		
		if(indicadorAlterarVencimentoConta != null && !indicadorAlterarVencimentoConta.equals("")){	
			cobrancaSituacao.setIndicadorAlterarVencimentoConta(indicadorAlterarVencimentoConta);			
		}
		
		cobrancaSituacao.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		cobrancaSituacao.setUltimaAlteracao(new Date());
		
		Integer idCobrancaSituacao = (Integer) this.getFachada().inserir(cobrancaSituacao);

		montarPaginaSucesso(httpServletRequest,
				"Situa��o de Cobran�a " + descricao
						+ " inserido com sucesso.",
				"Inserir outra Situa��o de Cobran�a",
				"exibirInserirCobrancaSituacaoAction.do?menu=sim",
				"exibirAtualizarCobrancaSituacaoAction.do?idRegistroAtualizacao="
						+ idCobrancaSituacao,
				"Atualizar Situa��o de Cobran�a Inserida");

		return retorno;
		
	}
}		
