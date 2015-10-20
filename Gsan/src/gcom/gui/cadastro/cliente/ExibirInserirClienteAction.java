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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.ClienteVirtual;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteVirtual;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * [UC0000] Inserir Cliente 
 *
 * @author Rodrigo, Roberta Costa
 * @date 28/06/2006
 */
public class ExibirInserirClienteAction extends GcomAction {

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

        // localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping
                .findForward("inserirClienteNomeTipo");

        //obt�m a inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        //limpa a sess�o
        sessao.removeAttribute("colecaoClienteFone");
        
        //Fachada
        Fachada fachada = Fachada.getInstancia();
        
        DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;
        
       
        
        //**********************************************************************
		// Autor: Ivan Sergio
		// Data: 23/07/2009
		// CRC2103
		// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
		// cliente � invocado pelo Inserir/Manter Imovel como PopUp
		//**********************************************************************
		if (sessao.getAttribute("colecaoEnderecos") != null) {
			Collection colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecos");
			Object obj = colecaoEndereco.iterator().next();
			if (obj instanceof Imovel) {
				sessao.setAttribute("colecaoEnderecosImovel", sessao.getAttribute("colecaoEnderecos"));
			}
		}
        
        sessao.removeAttribute("colecaoEnderecos");
        sessao.removeAttribute("foneTipos");
        sessao.removeAttribute("municipios");
        sessao.removeAttribute("colecaoResponsavelSuperiores");
        sessao.removeAttribute("InserirEnderecoActionForm");
        sessao.removeAttribute("InserirClienteActionForm");
        sessao.removeAttribute("tipoPesquisaRetorno");
        
        //Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
                "inserirClienteWizardAction", "inserirClienteAction",
                "cancelarInserirClienteAction","exibirInserirClienteAction.do"
                );
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "ClienteNomeTipoA.gif", "ClienteNomeTipoD.gif",
                        "exibirInserirClienteNomeTipoAction",
                        "inserirClienteNomeTipoAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "ClientePessoaA.gif", "ClientePessoaD.gif",
                        "exibirInserirClientePessoaAction",
                        "inserirClientePessoaAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        3, "ClienteEnderecoA.gif", "ClienteEnderecoD.gif",
                        "exibirInserirClienteEnderecoAction",
                        "inserirClienteEnderecoAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        4, "ClienteTelefoneA.gif", "ClienteTelefoneD.gif",
                        "exibirInserirClienteTelefoneAction",
                        "inserirClienteTelefoneAction"));

        //manda o statusWizard para a sessao
        sessao.setAttribute("statusWizard", statusWizard);
        
        
        
        clienteActionForm.set("indicadorPessoaFisicaJuridica", ClienteTipo.INDICADOR_PESSOA_FISICA.toString());
        
        clienteActionForm.set("indicadorGeraFaturaAntecipada", ConstantesSistema.NAO.toString());
        
        clienteActionForm.set("indicadorNegativacaoPeriodo", ConstantesSistema.NAO.toString());
        
        clienteActionForm.set("indicadorEnviarSms", ConstantesSistema.NAO);
        
        clienteActionForm.set("indicadorEnviarEmail", ConstantesSistema.NAO);
        
//        clienteActionForm.set("indicadorVencimentoMesSeguinte", ConstantesSistema.NAO.toString());
        
        if (httpServletRequest.getParameter("desfazer") != null) {
        	clienteActionForm.set("codigoCliente", null);
        	clienteActionForm.set("nome", null);
        	clienteActionForm.set("nomeAbreviado", null);
        	clienteActionForm.set("email", null);
        	clienteActionForm.set("tipoPessoa", null);
        	clienteActionForm.set("tipoPessoaAntes", null);
        	clienteActionForm.set("cpf", null);
        	clienteActionForm.set("rg", null);
        	clienteActionForm.set("dataEmissao", null);
        	clienteActionForm.set("idOrgaoExpedidor", null);
        	clienteActionForm.set("idUnidadeFederacao", null);
        	clienteActionForm.set("dataNascimento", null);
        	clienteActionForm.set("idProfissao", null);
        	clienteActionForm.set("idPessoaSexo", null);
        	clienteActionForm.set("nomeMae", null);
        	clienteActionForm.set("cnpj", null);
        	clienteActionForm.set("idRamoAtividade", null);
        	clienteActionForm.set("codigoClienteResponsavel", null);
        	clienteActionForm.set("nomeClienteResponsavel", null);
        	clienteActionForm.set("setorComercialOrigemCD", null);
        	clienteActionForm.set("enderecoCorrespondenciaSelecao", null);
        	clienteActionForm.set("ddd", null);
        	clienteActionForm.set("idTipoTelefone", null);
        	clienteActionForm.set("indicadorTelefonePadrao", null);
        	clienteActionForm.set("ramal", null);
        	clienteActionForm.set("contato", null);
        	clienteActionForm.set("telefone", null);
        	clienteActionForm.set("dddTelefone", null);
        	clienteActionForm.set("botaoClicado", null);
        	clienteActionForm.set("botaoAdicionar", null);
        	clienteActionForm.set("botaoRemover", null);
        	clienteActionForm.set("idMunicipio", null);
        	clienteActionForm.set("descricaoMunicipio", null);
        	clienteActionForm.set("idRegistroRemocao", null);
        	clienteActionForm.set("textoSelecionado", null);
        	clienteActionForm.set("idRegistrosRemocao", null);
        	clienteActionForm.set("indicadorUso", null);
        	clienteActionForm.set("indicadorAcrescimos", null);
        	//clienteActionForm.set("indicadorPessoaFisicaJuridica", null);
        	//clienteActionForm.set("indicadorGeraFaturaAntecipada", null);
        	clienteActionForm.set("diaVencimento", null);
        	clienteActionForm.set("indicadorVencimentoMesSeguinte", null);
        	
        }
        
        if(httpServletRequest.getParameter("idCliente")!=null 
        		&& !httpServletRequest.getParameter("idCliente").toString().equals("") ){
        	
        	FiltroCliente filtroCliente = new FiltroCliente();
        	
        	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,httpServletRequest.getParameter("idCliente")));
        	
        	filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);
        	
        	Collection clientes = fachada.pesquisar(filtroCliente,Cliente.class.getName());
        	
        	if(clientes!=null && !clientes.isEmpty()){
        		Cliente cliente = (Cliente) clientes.iterator().next();
        		clienteActionForm.set("nome", cliente.getNome());
                clienteActionForm.set("tipoPessoa", new Short(cliente.getClienteTipo().getId().toString()));
                clienteActionForm.set("indicadorPessoaFisicaJuridica",cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().toString());
                httpServletRequest.setAttribute("nome",cliente.getNome());
                httpServletRequest.setAttribute("tipoPessoa",new Short(cliente.getClienteTipo().getId().toString()));
                httpServletRequest.setAttribute("indicadorPessoaFisicaJuridica",cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica());
            }
        	
        }
        
        if(httpServletRequest.getParameter("idClienteVirtual")!=null 
        		&& !httpServletRequest.getParameter("idClienteVirtual").toString().equals("") ){
        	
        	FiltroClienteVirtual filtroClienteVirtual = new FiltroClienteVirtual();
        	filtroClienteVirtual.adicionarParametro(new ParametroSimples(FiltroClienteVirtual.ID,httpServletRequest.getParameter("idClienteVirtual")));
        	filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.FONE_TIPO);
        	filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.PESSOA_SEXO);
        	filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.ID_IMOVEL);
        	filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.PROFISSAO);
        	filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.RAMO_ATIVIDADE);
        	filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.UNIDADE_FEDERACAO);
        	filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.ORGAO_EXPEDIDOR);
        	
        	Collection<ClienteVirtual> clientesVirtual = fachada.pesquisar(filtroClienteVirtual,ClienteVirtual.class.getName());
        	
        	if(clientesVirtual!=null && !clientesVirtual.isEmpty()){
        		ClienteVirtual clienteVirtual = (ClienteVirtual) clientesVirtual.iterator().next();
        	
	        	clienteActionForm.set("nome", clienteVirtual.getNome());
	        	
	        	FoneTipo foneTipo = clienteVirtual.getFoneTipo();
	        	clienteActionForm.set("idTipoTelefone", foneTipo.getId().toString());
	        	clienteActionForm.set("ramal", clienteVirtual.getNumeroFoneRamal());
	        	clienteActionForm.set("telefone", clienteVirtual.getNumeroTelefone());
	        	clienteActionForm.set("contato", clienteVirtual.getNomeContato());
	        	clienteActionForm.set("ddd", clienteVirtual.getDdd());
	        	clienteActionForm.set("idClienteVirtual", clienteVirtual.getId());
	        	clienteActionForm.set("email", clienteVirtual.getEmail());
	        	
	        	ClienteEndereco enderecoCliente = new ClienteEndereco();
	        	enderecoCliente = fachada.pesquisarClienteEnderecoAPartirDoEnderecoDoImovel(clienteVirtual.getImovel().getId());
	        	Collection colecaoEnderecos = new ArrayList();
	        	EnderecoTipo enderecoTipo = new EnderecoTipo();
	        	
	            clienteActionForm.set("indicadorPessoaFisicaJuridica",clienteVirtual.getIndicadorPessoaFisicaJuridica().toString());
	            
	            if ( clienteVirtual.getCpf() != null && !clienteVirtual.getCpf().equals("") ) {
	            	clienteActionForm.set("cpf", clienteVirtual.getCpf());
	            	clienteActionForm.set("rg", clienteVirtual.getRg());
	            	clienteActionForm.set("dataEmissao", Util.formatarData(clienteVirtual.getDataEmissaoRg()) );
	            	clienteActionForm.set("dataNascimento", Util.formatarData(clienteVirtual.getDataNascimento()) );
	            	clienteActionForm.set("nomeMae", clienteVirtual.getNomeMae() );
	            	
	            	OrgaoExpedidorRg expedidorRg = clienteVirtual.getOrgaoExpedidorRg();
	            	clienteActionForm.set("idOrgaoExpedidor", expedidorRg.getId());
	            	
	            	UnidadeFederacao federacao = clienteVirtual.getUnidadeFederacao();
	            	clienteActionForm.set("idUnidadeFederacao", federacao.getId());
	            	
	            	Profissao profissao = clienteVirtual.getProfissao();
	            	clienteActionForm.set("idProfissao", profissao.getId() );
	            	
	            	PessoaSexo pessoaSexo = clienteVirtual.getPessoaSexo();
	            	clienteActionForm.set("idPessoaSexo", pessoaSexo.getId());
	            	
	            	enderecoTipo.setId(new Integer(1));
	            	enderecoTipo.setDescricao("RESIDENCIAL");
	            } else {
	            	RamoAtividade ramo = clienteVirtual.getRamoAtividade();
	            	clienteActionForm.set("idRamoAtividade", ramo.getId());
	            	
	            	clienteActionForm.set("cnpj", clienteVirtual.getCnpj());
	            	enderecoTipo.setId(new Integer(2));
	            	enderecoTipo.setDescricao("COMERCIAL");
	            }
	            enderecoCliente.setEnderecoTipo(enderecoTipo);
	            colecaoEnderecos.add(enderecoCliente);
	        	sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
        	}
        }
        	
        
        //**********************************************************************
		// CRC2103
		// Autor: Ivan Sergio
		// Data: 17/07/2009
		// Recupera o action de retorno do inserir cliente exibido como um PopUp
		//**********************************************************************
		if (httpServletRequest.getParameter("PopUpInserirClienteRetorno") != null) {
			String actionRetorno = httpServletRequest.getParameter("PopUpInserirClienteRetorno");
			sessao.setAttribute("PopUpInserirClienteRetorno", actionRetorno);
		}
		
		if (httpServletRequest.getParameter("idClienteRelacaoTipo") != null) {
			sessao.setAttribute("idClienteRelacaoTipo",
					httpServletRequest.getParameter("idClienteRelacaoTipo"));
		}
		//**********************************************************************

        return retorno;
    }
}
