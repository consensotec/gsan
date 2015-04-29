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
package gsan.gui.faturamento;

import gsan.faturamento.FaturamentoSituacaoTipo;
import gsan.faturamento.FiltroFaturamentoSituacaoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gsan.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirFaturamentoSituacaoTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o do tipo da situa��o de faturamento
	 * 
	 * [UC0845] Inserir Tipo da Situa��o de Faturamento
	 * 
	 * @author Arthur Carvalho
	 * @date 19/08/2008
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

		InserirFaturamentoSituacaoTipoActionForm inserirFaturamentoSituacaoTipoActionForm = (InserirFaturamentoSituacaoTipoActionForm) actionForm;
		
		String descricao = inserirFaturamentoSituacaoTipoActionForm.getDescricao();
		Short indicadorParalisacaoFaturamento = inserirFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoFaturamento();
		Short indicadorParalisacaoLeitura = inserirFaturamentoSituacaoTipoActionForm.getIndicadorParalisacaoLeitura();
		Short indicadorValidoAgua = inserirFaturamentoSituacaoTipoActionForm.getIndicadorValidoAgua();
		Short indicadorValidoEsgoto = inserirFaturamentoSituacaoTipoActionForm.getIndicadorValidoEsgoto();
		Short indicadorInformarConsumo = inserirFaturamentoSituacaoTipoActionForm.getIndicadorInformarConsumo();
	    Short indicadorInformarVolume = inserirFaturamentoSituacaoTipoActionForm.getIndicadorInformarVolume();
		String leituraAnormalidadeLeituraComLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeLeituraComLeitura();
		String leituraAnormalidadeLeituraSemLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeLeituraSemLeitura();
		String leituraAnormalidadeConsumoComLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeConsumoComLeitura();
		String leituraAnormalidadeConsumoSemLeitura = inserirFaturamentoSituacaoTipoActionForm
			.getLeituraAnormalidadeConsumoSemLeitura();
		
		Short indicadorParalisaFatEsgoto = inserirFaturamentoSituacaoTipoActionForm.
				getIndicadorParalisaFatEsgoto();
		Short indicadorParalisaFatAgua = inserirFaturamentoSituacaoTipoActionForm.
				getIndicadorParalisaFatAgua();
		
		FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
		Collection colecaoPesquisa = null;

		
		// Descri��o
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descri��o");
		}
		
		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
		filtroFaturamentoSituacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroFaturamentoSituacaoTipo.DESCRICAO, descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		//Indicador de Paralisa��o do Faturamento
		if (!"".equals(indicadorParalisacaoFaturamento)) {
			faturamentoSituacaoTipo.setIndicadorParalisacaoFaturamento(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorParalisacaoFaturamento());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorParalisacaoFaturamento");
		}
		
		//Indicador de Paralisa��o de Leitura
		if (!"".equals(indicadorParalisacaoLeitura)) {
			faturamentoSituacaoTipo.setIndicadorParalisacaoLeitura(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorParalisacaoLeitura());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorParalisacaoLeitura");
		}
		
		//Indicador de Valido para �gua
		if (!"".equals(indicadorValidoAgua)) {
			faturamentoSituacaoTipo.setIndicadorValidoAgua(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorValidoAgua());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorValidoAgua");
		}
		
		//Indicador de Valido para Esgoto
		if (!"".equals(indicadorValidoEsgoto)) {
			faturamentoSituacaoTipo.setIndicadorValidoEsgoto(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorValidoEsgoto());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorValidoEsgoto");
		}
		
		//Indicador de Informar Consumo Fixo
		if (!"".equals(indicadorInformarConsumo)) {
			faturamentoSituacaoTipo.setIndicadorInformarConsumo(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorInformarConsumo());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicadorInformarConsumo");
		}	
		
		//Indicador de Informar Volume Fixo
		if (!"".equals(indicadorInformarVolume)) {
			faturamentoSituacaoTipo.setIndicadorInformarVolume(inserirFaturamentoSituacaoTipoActionForm
					.getIndicadorInformarVolume());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Indicador de Informar Volume Fixo");
		}	
		
		//Anormalidade de Consumo Cobrar Sem Leitura
        if (leituraAnormalidadeConsumoSemLeitura != null && 
        		!leituraAnormalidadeConsumoSemLeitura.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	LeituraAnormalidadeConsumo leituraConsumoSemLeitura = new LeituraAnormalidadeConsumo();
        	leituraConsumoSemLeitura.setId(new Integer( leituraAnormalidadeConsumoSemLeitura ));
        	faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoSemLeitura( leituraConsumoSemLeitura );
        }else {
			throw new ActionServletException("atencao.required", null,
			"Anormalidade de Consumo Cobrar Sem Leitura");
        }	
		
        //Leitura Anormalidade Consumo Com Leitura
		if(leituraAnormalidadeConsumoComLeitura != null &&
				!leituraAnormalidadeConsumoComLeitura.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			LeituraAnormalidadeConsumo leituraConsumoComLeitura = new LeituraAnormalidadeConsumo();
			leituraConsumoComLeitura.setId(new Integer(leituraAnormalidadeConsumoComLeitura));
			faturamentoSituacaoTipo.setLeituraAnormalidadeConsumoComLeitura(leituraConsumoComLeitura);
		}else {
			throw new ActionServletException("atencao.required", null,
			"Leitura Anormalidade Consumo Com Leitura");
        }	
		
		//Leitura Anormalidade Leitura Sem Leitura
		if(leituraAnormalidadeLeituraSemLeitura != null &&
				!leituraAnormalidadeLeituraSemLeitura.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			LeituraAnormalidadeLeitura leituraSemLeitura = new LeituraAnormalidadeLeitura();
			leituraSemLeitura.setId(new Integer(leituraAnormalidadeLeituraSemLeitura));
			faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraSemLeitura(leituraSemLeitura);
		}else {
			throw new ActionServletException("atencao.required", null,
			"Leitura Anormalidade Leitura Sem Leitura");
        }	
		
		//Leitura Anormalidade Leitura Com Leitura
		if(leituraAnormalidadeLeituraComLeitura != null &&
				!leituraAnormalidadeLeituraComLeitura.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			LeituraAnormalidadeLeitura leituraComLeitura = new LeituraAnormalidadeLeitura();
			leituraComLeitura.setId(new Integer(leituraAnormalidadeLeituraComLeitura));
			faturamentoSituacaoTipo.setLeituraAnormalidadeLeituraComLeitura(leituraComLeitura);
		}else {
			throw new ActionServletException("atencao.required", null,
			"Leitura Anormalidade Leitura Com Leitura");
        }	
		
		
		//Indicador Paralisa Faturamento Esgoto
		if(indicadorParalisaFatEsgoto != null && !indicadorParalisaFatEsgoto.equals("")){
			 faturamentoSituacaoTipo.setIndicadorParalisaFatEsgoto(indicadorParalisaFatEsgoto);			
		}else{
			throw new ActionServletException("atencao.required", null,
					"Indicador Paralisa Faturamento Esgoto");
		}
		
		//Indicador Paralisa Faturamento �gua
		if(indicadorParalisaFatAgua != null && !indicadorParalisaFatAgua.equals("")){
			faturamentoSituacaoTipo.setIndicadorParalisaFatAgua(indicadorParalisaFatAgua);
		}else{
			throw new ActionServletException("atencao.required", null,
					"Indicador Paralisa Faturamento �gua");
		}
        
			faturamentoSituacaoTipo.setDescricao(descricao);
			faturamentoSituacaoTipo.setUltimaAlteracao(new Date());
			faturamentoSituacaoTipo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

			Integer idFaturamentoSituacaoTipo = (Integer) this.getFachada().inserir(faturamentoSituacaoTipo);

			montarPaginaSucesso(httpServletRequest,
					"Tipo da Situa��o de Faturamento " + descricao
							+ " inserida com sucesso.",
					"Inserir outro tipo de situa��o de faturamento",
					"exibirInserirFaturamentoSituacaoTipoAction.do?menu=sim",
					"exibirAtualizarFaturamentoSituacaoTipoAction.do?idRegistroAtualizacao="
							+ idFaturamentoSituacaoTipo,
					"Atualizar tipo de situa��o de faturamento Inserida");

			this.getSessao(httpServletRequest).removeAttribute("InserirFaturamentoSituacaoTipoActionForm");

			return retorno;
		
		
	}
}		
