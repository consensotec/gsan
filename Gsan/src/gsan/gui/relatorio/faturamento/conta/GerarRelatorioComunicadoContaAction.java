
/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/
package gsan.gui.relatorio.faturamento.conta;

import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.gui.faturamento.conta.FiltrarContaComunicadoActionForm;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.faturamento.conta.RelatorioComunicadoConta;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Cesar Medeiros
 * @date 13/02/2015
 */

public class GerarRelatorioComunicadoContaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;		
		
		FiltrarContaComunicadoActionForm form = (FiltrarContaComunicadoActionForm) actionForm;

		String abrangencia = "Todos";
		String referencia = "";
		String titulo = "";
		String gerenciaRegional = "";
		String localidade = "";
		String indicadorUso = "Todos";
		String[] setorComercial = form.getSetorComercial();
		String[] rota = form.getRota();
		String[] quadra = form.getQuadra();
		String[] grupoFaturamento = form.getGrupoFaturamento();
		
		if (form.getAbrangencia() != null && !form.getAbrangencia().trim().equals("")) {
			if (form.getAbrangencia().equals("todos")) {
				abrangencia = "Todos";
			} else if (form.getAbrangencia().equals("todos")) {
				abrangencia = "Todos";
			} else if (form.getAbrangencia().equals("estado")) {
				abrangencia = "Estado";
			} else if (form.getAbrangencia().equals("faturamentoGrupo")) {
				abrangencia = "Grupo de Faturamento";
			} else if (form.getAbrangencia().equals("gerencia")) {
				abrangencia = "Gerência Regional";
			} else if (form.getAbrangencia().equals("localidade")) {
				abrangencia = "Localidade";
			} else if (form.getAbrangencia().equals("setor")) {
				abrangencia = "Setor Comercial";
			} else if (form.getAbrangencia().equals("rota")) {
				abrangencia = "Rota";
			} else if (form.getAbrangencia().equals("quadra")) {
				abrangencia = "Quadra";
			}
		}
		
		if (form.getReferenciaInicial() != null && !form.getReferenciaInicial().trim().equals("")) {
			referencia = form.getReferenciaInicial() + " a " + form.getReferenciaFinal();
		}
		
		if (form.getTitulo() != null && !form.getTitulo().trim().equals("")) {
			titulo = form.getTitulo();
		}
		
		if (form.getGerenciaRegional() != null && !form.getGerenciaRegional().trim().equals("") && !form.getGerenciaRegional().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, form.getGerenciaRegional()));
			
			Collection<GerenciaRegional> colecaoGerenciaRegional = Fachada.getInstancia().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			
			GerenciaRegional gerenciaRegionalFiltro = colecaoGerenciaRegional.iterator().next();
			
			gerenciaRegional = gerenciaRegionalFiltro.getNome();
		}
		
		if (form.getLocalidade() != null && !form.getLocalidade().trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getLocalidade()));
			
			Collection<Localidade> colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			Localidade localidadeFiltro = colecaoLocalidade.iterator().next();
			localidade = localidadeFiltro.getId() + " - " + localidadeFiltro.getDescricao();
		}
		
		String setorComercialParametro = ""; 
		if(setorComercial != null){
			
			for(int i = 0; i < setorComercial.length; i++){
				
				if(!setorComercial[i].equals("")){
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, setorComercial[i].toString()));
					Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
					SetorComercial setorComercialFiltro = colecaoSetorComercial.iterator().next();
					
					if (i == 0) {
							
						setorComercialParametro = "" + setorComercialFiltro.getCodigo();
						
					} else {
						
						setorComercialParametro += "," + setorComercialFiltro.getCodigo();
					}
					 	
					if (i == 20){
						setorComercialParametro += " e outros.";
						break;
					}
				}
			}
			
			
			
			
			
		}
		
		String rotaParametro = "";
		if(rota != null){
			
			for(int i = 0; i < rota.length; i++){
				
				if(!rota[i].equals("")){
					
					FiltroRota filtroRota = new FiltroRota();
					filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, rota[i].toString()));				
					Collection<Rota> colecaoRota = Fachada.getInstancia().pesquisar(filtroRota, Rota.class.getName());				
					Rota rotaFiltro = colecaoRota.iterator().next();
					
					if (i == 0) {
						
						rotaParametro = "" + rotaFiltro.getCodigo();
					} else {
						
						rotaParametro += "," + rotaFiltro.getCodigo();
						
					}
					 	
					if (i == 20){
						rotaParametro += " e outras.";
						break;
					}
				}
			}
		}
		
		String quadraParametro = "";
		if(quadra != null){
			
			for(int i = 0; i < quadra.length; i++){
				
				if(!quadra[i].equals("")){
					FiltroQuadra filtroQuadra = new FiltroQuadra();
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, quadra[i].toString()));				
					Collection<Quadra> colecaoQuadra = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());				
					Quadra quadraFiltro = colecaoQuadra.iterator().next();
					
					if (i == 0) {
						
						quadraParametro = ""+quadraFiltro.getNumeroQuadra();
					} else {
						quadraParametro += ","+quadraFiltro.getNumeroQuadra();
					}
					 	
					if (i == 20){
						quadraParametro += " e outras.";
						break;
					}
				}
			}
		}
	
		String grupoFaturamentoParametro = "";
		if(grupoFaturamento != null){
			
			for(int i = 0; i < grupoFaturamento.length; i++){
				
				if(!grupoFaturamento[i].equals("")){
								
					FiltroFaturamentoGrupo filtroGrupo = new FiltroFaturamentoGrupo();
					filtroGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, grupoFaturamento[i].toString()));				
					Collection<FaturamentoGrupo> colecaoGrupo = Fachada.getInstancia().pesquisar(filtroGrupo, FaturamentoGrupo.class.getName());				
					FaturamentoGrupo grupoFiltro = colecaoGrupo.iterator().next();
					
					if (i == 0) {
						grupoFaturamentoParametro = grupoFiltro.getDescricao(); 
					} else {
						grupoFaturamentoParametro += "," + grupoFiltro.getDescricao();
					}
					 	
					if (i == 8){
						grupoFaturamentoParametro += " e outros.";
						break;
					}
				}
			}
		}
		
		if (form.getIcUso() != null && !form.getIcUso().trim().equals("")) {
			if (form.getIcUso().equals("" + ConstantesSistema.INDICADOR_USO_ATIVO)) {
				indicadorUso = "Ativo";
			} else if (form.getIcUso().equals("" + ConstantesSistema.INDICADOR_USO_DESATIVO)) {
				indicadorUso = "Inativo";
			}
		}
		
		RelatorioComunicadoConta relatorioComunicadoConta = new RelatorioComunicadoConta(getUsuarioLogado(httpServletRequest));
		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		
		relatorioComunicadoConta
			.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
		
		relatorioComunicadoConta.addParametro("abrangenciaParametro", abrangencia);
		relatorioComunicadoConta.addParametro("referenciaParametro", referencia);
		relatorioComunicadoConta.addParametro("tituloParametro", titulo);
		relatorioComunicadoConta.addParametro("gerenciaRegionalParametro", gerenciaRegional);
		relatorioComunicadoConta.addParametro("localidadeParametro", localidade);
		relatorioComunicadoConta.addParametro("setorComercialParametro", setorComercialParametro);
		relatorioComunicadoConta.addParametro("rotaParametro", rotaParametro);
		relatorioComunicadoConta.addParametro("quadraParametro", quadraParametro);
		relatorioComunicadoConta.addParametro("grupoFaturamentoParametro", grupoFaturamentoParametro);
		relatorioComunicadoConta.addParametro("indicadorUsoParametro", indicadorUso);
		
		relatorioComunicadoConta.addParametro("colecaoComunicadoConta", httpServletRequest.getSession().getAttribute("colecaoComunicadoHelper"));
		
		retorno = processarExibicaoRelatorio(relatorioComunicadoConta, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		
		return retorno;
	}
	
}