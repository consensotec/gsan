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
package gsan.gui.cadastro.geografico;


import gsan.atendimentopublico.contratoadesao.ContratoAdesao;
import gsan.atendimentopublico.contratoadesao.FiltroContratoAdesao;
import gsan.cadastro.geografico.FiltroMicrorregiao;
import gsan.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gsan.cadastro.geografico.FiltroUnidadeFederacao;
import gsan.cadastro.geografico.Microrregiao;
import gsan.cadastro.geografico.RegiaoDesenvolvimento;
import gsan.cadastro.geografico.UnidadeFederacao;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0001]	INSERIR MUNICIPIO
 * 
 * @author K�ssia Albuquerque
 * @date 13/12/2006
 */
 
public class ExibirInserirMunicipioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		ActionForward retorno = actionMapping.findForward("inserirMunicipio");	
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirMunicipioActionForm inserirMunicipioActionForm = (InserirMunicipioActionForm) actionForm;
		
		if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").trim().equals("")) {
        	//Retorna o maior id de Munic�pio existente 
			int id = this.getFachada().pesquisarMaximoIdMunicipio();
			// Acrescenta 1 no valor do id para setar o primeiro id vazio para o usu�rio
			id = (id + 1);
			inserirMunicipioActionForm.setCodigoMunicipio("" + id);
			httpServletRequest.setAttribute("nomeCampo", "codigoMunicipio");
			inserirMunicipioActionForm.setIndicadorRelacaoQuadraBairro("2");
		}

		
		// [FS0001]-  Verificar a existencia de dados
		
				// UNIDADE FEDERA��O
				
				FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
				
				filtroUnidadeFederacao.setCampoOrderBy(FiltroUnidadeFederacao.DESCRICAO);
				
				Collection<UnidadeFederacao> colecaoUnidadeFederacao = fachada.pesquisar(
						filtroUnidadeFederacao, UnidadeFederacao.class.getName());
				
				if (colecaoUnidadeFederacao == null || colecaoUnidadeFederacao.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Unidade Federa��o");
				}
		
				httpServletRequest.setAttribute("colecaoUnidadeFederacao",colecaoUnidadeFederacao);
				
				
				// MICRORREGIAO
				
				FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();
		
				filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);
				
				filtroMicrorregiao.adicionarParametro(new ParametroSimples(
				FiltroMicrorregiao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection<Microrregiao> colecaoMicrorregiao = fachada.pesquisar(
						filtroMicrorregiao, Microrregiao.class.getName());
				
				if (colecaoMicrorregiao == null || colecaoMicrorregiao.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Microrregiao");
				}
		
				httpServletRequest.setAttribute("colecaoMicrorregiao",colecaoMicrorregiao);
		
				
				// REGIAO DESENVOLVIMENTO
				
				FiltroRegiaoDesenvolvimento filtroRegiaoDesenv = new FiltroRegiaoDesenvolvimento();
				
				filtroRegiaoDesenv.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);
				
				filtroRegiaoDesenv.adicionarParametro(new ParametroSimples(
						FiltroRegiaoDesenvolvimento.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection<RegiaoDesenvolvimento> colecaoRegiaoDesenv = fachada.pesquisar(
						filtroRegiaoDesenv, RegiaoDesenvolvimento.class.getName());
		
				if (colecaoRegiaoDesenv == null || colecaoRegiaoDesenv.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Regiao Desenvolvimento");
				}
		
				httpServletRequest.setAttribute("colecaoRegiaoDesenv",colecaoRegiaoDesenv);
				
								
				
				// CONTRATO DE ADES�O DE SERVI�O
				
				FiltroContratoAdesao filtroContratoAdesao = new FiltroContratoAdesao();
				
				filtroContratoAdesao.setCampoOrderBy(FiltroContratoAdesao.DESCRICAO);
				filtroContratoAdesao.adicionarParametro(new ParametroSimples(FiltroContratoAdesao.INDICADOR_USO, ConstantesSistema.SIM));
				
				Collection<FiltroContratoAdesao> colecaoContratoAdesao = fachada.pesquisar(
						filtroContratoAdesao, ContratoAdesao.class.getName());
			
		
				httpServletRequest.setAttribute("colecaoContratoAdesao",colecaoContratoAdesao);
				
		return retorno;
	}
}
