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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.ControladorConsultaGcomAction;
import gcom.gui.ControladorConsultaGcomActionForm;
import gcom.gui.ControladorGcomAction;
import gcom.util.filtro.Filtro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consulta o imovel da tarifa social
 * 
 * @author thiago toscano
 * @date 
 */
public class ConsultaImovelTarifaSocialAction extends ControladorConsultaGcomAction  {

	public static final String CASO_USO = ConsultaImovelTarifaSocialAction.class.getSimpleName()  + ".do";

	public static final String ACAO_EXIBIR = CASO_USO + "?" + ControladorGcomAction.PARAMETRO_ACAO + "=" + ControladorGcomAction.PARAMETRO_ACAO_EXIBIR;

	public static final String ACAO_PROCESSAR = CASO_USO + "?" + ControladorGcomAction.PARAMETRO_ACAO + "=" + ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR;

	public Collection pesquisarObjetoSistema(Filtro filtro ) throws Exception{
/*
		FiltroClienteImovel filtroClienteImovel = (FiltroClienteImovel) filtro;

		Collection  coll = Fachada.getInstancia().pesquisarImovelTarfiaSocial(filtroClienteImovel);

		if (coll == null || coll.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null, "Im�vel com dados da tarifa social");
        }
*/
		return null;
	}


	public ActionForward remover(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ConsultaImovelTarifaSocialActionForm form = (ConsultaImovelTarifaSocialActionForm) actionForm;
		String[] ids =  form.getIdRegistrosRemocao();
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// temporario
		if (("".equals(form.getTarifaSocialExclusaoMotivo()) || form.getTarifaSocialExclusaoMotivo() == null ) && request.getParameter("tarifaSocialExclusaoMotivo") != null) {
			form.setTarifaSocialExclusaoMotivo(request.getParameter("tarifaSocialExclusaoMotivo").toString());
		}

		if (!"".equals(form.getTarifaSocialExclusaoMotivo()) && form.getTarifaSocialExclusaoMotivo() != null) {
			for (int i = 0; i < ids.length; i++) {
				try {
					Fachada.getInstancia().removerImovelTarfiaSocial(new Integer(ids[i]),new Integer(form.getTarifaSocialExclusaoMotivo()));
				} catch (NumberFormatException e) {

				}
			}
		}

        request.setAttribute("caminhoFuncionalidade","exibirFiltrarImovelAction.do?menu=sim&acao=exibir&redirecionar=ManterDadosTarifaSocial");
		request.setAttribute("labelPaginaSucesso","Realizar outra Manuten��o de Tarifa Social");
		request.setAttribute("mensagemPaginaSucesso","Dados da Tarifa Social removido(s) com sucesso.");

		return retorno;//this.processar(actionMapping, actionForm, request, response);
	}

	public ActionForward exibirRemover(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ConsultaImovelTarifaSocialActionForm form = (ConsultaImovelTarifaSocialActionForm) actionForm;
		
		FiltroTarifaSocialExclusaoMotivo filtroTarifaSocialExclusaoMotivo = new FiltroTarifaSocialExclusaoMotivo();
		
		filtroTarifaSocialExclusaoMotivo.setCampoOrderBy(FiltroTarifaSocialExclusaoMotivo.DESCRICAO);
		
		form.setCollTarifaSocialExclusaoMotivo(Fachada.getInstancia().pesquisar(filtroTarifaSocialExclusaoMotivo, TarifaSocialExclusaoMotivo.class.getName()));
		
		if (form.getCollTarifaSocialExclusaoMotivo() == null || form.getCollTarifaSocialExclusaoMotivo().isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",null," A Tabela Motivo da Exclus�o est� ");
		}

		return actionMapping.findForward(ControladorGcomAction.FORWARD_REMOVER);
	}

	public void carregarColecao(ControladorConsultaGcomActionForm actionForm) {

	}
	
	

	@Override
	public ActionForward processarAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConsultaImovelTarifaSocialActionForm form = (ConsultaImovelTarifaSocialActionForm) actionForm; 	
        FiltroClienteImovel filtroClienteImovel = null;
		
        // tenta pegar do request pois quando vem o filtrar imovel vem pelo request
        if (request.getAttribute("filtroImovelPreenchido") != null) {
            filtroClienteImovel = (FiltroClienteImovel) request.getAttribute("filtroImovelPreenchido");
            // seta na sessao
            getSessao(request).setAttribute("filtroImovelPreenchido",filtroClienteImovel);
        } else {
        	if (getSessao(request).getAttribute("filtroImovelPreenchido") != null) {
        		filtroClienteImovel = (FiltroClienteImovel) getSessao(request).getAttribute("filtroImovelPreenchido");
        	} else {
        		filtroClienteImovel = new FiltroClienteImovel();
        	}
        }
    	
        //Objetos que ser�o retornados pelo Hibernate atrav�s do clienteImovel
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
        /*filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.lote");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.subLote");*/
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
    	
        ActionForward retorno = actionMapping.findForward("processar");

        
		// 1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
		int totalRegistros = Fachada.getInstancia()
				.pesquisarQuantidadeImovelTarfiaSocial(filtroClienteImovel);

		// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
		retorno = this.controlarPaginacao(request, retorno,
				totalRegistros);

		// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
		// da pesquisa que est� no request
        Collection imoveis = Fachada.getInstancia().pesquisarImovelTarfiaSocial(filtroClienteImovel,
        		(Integer) request
						.getAttribute("numeroPaginasPesquisa"));

        if (imoveis == null || imoveis.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null, "Im�vel com dados da tarifa social");
        }
        
  		form.setCollObjeto(imoveis);

        return retorno;
	}


	public Filtro gerarFiltro(ControladorConsultaGcomActionForm actionForm) {
 		
		//ConsultaImovelTarifaSocialActionForm form = (ConsultaImovelTarifaSocialActionForm) actionForm; 	
        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        
        
        
 /*       
        //Objetos que ser�o retornados pelo Hibernate atrav�s do clienteImovel
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouro.logradouroTipo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouro.logradouroTitulo");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.bairro.municipio.unidadeFederacao");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.cep");
        
        if (form.getMatricula() != null && !"".equals(form.getMatricula())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, form.getMatricula()));
        }
        if (form.getIdLocalidade() != null && !"".equals(form.getIdLocalidade())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.LOCALIDADE_ID, form.getIdLocalidade()));
        }
        if (form.getIdSetorComercial() != null && !"".equals(form.getIdSetorComercial())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.SETOR_COMERCIAL_ID, form.getIdSetorComercial()));
        }
        if (form.getIdQuadra() != null && !"".equals(form.getIdQuadra())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.QUADRA_NUMERO, form.getLote()));
        }
        if (form.getLote() != null && !"".equals(form.getLote())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.LOTE, form.getLote()));
        }
        if (form.getSubLote() != null && !"".equals(form.getSubLote())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.SUBLOTE, form.getSubLote()));
        }
        if (form.getCodigoCliente() != null && !"".equals(form.getCodigoCliente())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, form.getCodigoCliente()));
        }
        if (form.getIdMunicipio() != null && !"".equals(form.getIdMunicipio())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.MUNICIPIO_SETOR_COMERICAL_CODIGO, form.getIdMunicipio()));
        }
        if (form.getCep() != null && !"".equals(form.getCep())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CEP_CODIGO, form.getCep()));
        }
        if (form.getIdBairro() != null && !"".equals(form.getIdBairro())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.BAIRRO_CODIGO, form.getIdBairro()));
        }
        if (form.getLogradouro() != null && !"".equals(form.getLogradouro())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.LOGRADOURO_NOME, form.getLogradouro()));
        }
        if (form.getIndicadorUso() != null && !"".equals(form.getIndicadorUso())) {
            filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.INDICADOR_USO, form.getIndicadorUso()));
        }*/
		return filtroClienteImovel;
	}


	@Override
	public ActionForward exibirAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return null;
	}
}