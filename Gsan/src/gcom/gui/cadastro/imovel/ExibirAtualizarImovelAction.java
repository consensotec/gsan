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
package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelRamoAtividade;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Classe respon�vel pelo processamento inicial de atualiza��o de um im�vel 
 *
 * @author Raphael Rossiter
 * @date 12/05/2009
 */
public class ExibirAtualizarImovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirAtualizarImovelAction");

        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

        String idImovel = null;
        
        StatusWizard statusWizard = null;
        if(httpServletRequest.getParameter("desfazer") == null){
	    
	       	if(httpServletRequest.getParameter("idRegistroAtualizacao") != null){
	        	idImovel = (String) httpServletRequest.getParameter("idRegistroAtualizacao");
	        }
	       	else if(httpServletRequest.getAttribute("idRegistroAtualizacao") != null){
	        	idImovel = (String) httpServletRequest.getAttribute("idRegistroAtualizacao");
	        }
	       	else if(httpServletRequest.getParameter("idRegistroTransferencia") != null){
	       		idImovel = (String) httpServletRequest.getParameter("idRegistroTransferencia");
	       	}
	       	
	       	FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.IMOVEL_PERFIL_CORPORATIVO,ConstantesSistema.SIM));
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
			
			// pesquisa a cole�ao de imovel corporativo
			Collection<?> imoveis = fachada.pesquisar(filtroImovel, Imovel.class
					.getName());
			if(imoveis.size() > 0){
				
				boolean possuiPermissaoManterClienteResponsavelImoveisPublicos = 
		    			fachada.verificarPermissaoEspecialAtiva(
		    					PermissaoEspecial.MANTER_IMOVEL_PERFIL_CORPORATIVO,usuarioLogado);
		    		
		    		if(!possuiPermissaoManterClienteResponsavelImoveisPublicos){
		    			throw new ActionServletException(
		    					"atencao.usuario_permissao_corporativo");
		    		}
			}
	       	
			
			
	        /*
	       	 * Verifica se chegou no atualizar imovel atrav�s da tela de filtrar devido a um �nico registro
	       	 * ou atrav�s da lista de im�veis no manter imovel 
	       	 */
	       	if(httpServletRequest.getAttribute("atualizar") != null){
	       		
		        statusWizard = new StatusWizard(
		                "atualizarImovelWizardAction", "atualizarImovelAction",
		                "cancelarAtualizarImovelAction","exibirFiltrarImovelAction",
		                "exibirAtualizarImovelAction.do", 
		                idImovel);
	       	}
	       	else if(httpServletRequest.getParameter("sucesso") != null){
	       		
		        statusWizard = new StatusWizard(
		                "atualizarImovelWizardAction", "atualizarImovelAction",
		                "cancelarAtualizarImovelAction","exibirFiltrarImovelAction",
		                "exibirAtualizarImovelAction.do", 
		                idImovel);
	       	}else if(httpServletRequest.getParameter("promais") != null){
	       		
		        statusWizard = new StatusWizard(
		                "atualizarImovelWizardAction", "atualizarImovelAction",
		                "cancelarAtualizarImovelAction","exibirConsultarImovelAction",
		                "exibirAtualizarImovelAction.do", 
		                idImovel);
	       	}       	
	       	else{
		        statusWizard = new StatusWizard(
		                "atualizarImovelWizardAction", "atualizarImovelAction",
		                "cancelarAtualizarImovelAction","exibirManterImovelAction",
		                "exibirAtualizarImovelAction.do",
		                idImovel);	       		
	       	}
	       	

	        
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        1, "LocalidadeA.gif", "LocalidadeD.gif",
	                        "exibirAtualizarImovelLocalidadeAction",
	                        "atualizarImovelLocalidadeAction"));
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        2, "EnderecoA.gif", "EnderecoD.gif",
	                        "exibirAtualizarImovelEnderecoAction",
	                        "atualizarImovelEnderecoAction"));
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        3, "ClienteA.gif", "ClienteD.gif",
	                        "exibirAtualizarImovelClienteAction",
	                        "atualizarImovelClienteAction"));
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        4, "SubcategoriaA.gif", "SubcategoriaD.gif",
	                        "exibirAtualizarImovelSubCategoriaAction",
	                        "atualizarImovelSubCategoriaAction"));
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        5, "CaracteristicaA.gif", "CaracteristicaD.gif",
	                        "exibirAtualizarImovelCaracteristicasAction",
	                        "atualizarImovelCaracteristicasAction"));
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        6, "ConclusaoA.gif", "ConclusaoD.gif",
	                        "exibirAtualizarImovelConclusaoAction",
	                        "atualizarImovelConclusaoAction"));

	        sessao.setAttribute("statusWizard", statusWizard);
	        
        }
        else{
        	statusWizard = (StatusWizard)sessao.getAttribute("statusWizard");
        	
        	idImovel = statusWizard.getId();
        }
        
        //LIMPANDO A SESS�O
        sessao.removeAttribute("imoveisPrincipal");
        sessao.removeAttribute("colecaoEnderecos");
        sessao.removeAttribute("imovelAtualizacao");
        sessao.removeAttribute("colecaoImovelSubCategorias");
        
        sessao.removeAttribute("idClienteImovelUsuario");
        sessao.removeAttribute("idClienteImovelResponsavel");
        sessao.removeAttribute("imovelClientesNovos");
        sessao.removeAttribute("setorComercial");
        sessao.removeAttribute("setorComerciais");
        sessao.removeAttribute("quadra");
        sessao.removeAttribute("quadraCaracteristicas");
        sessao.removeAttribute("idImoveilPrincipal");
        sessao.removeAttribute("tipoClientesImoveis");
        sessao.removeAttribute("clienteObj");
        sessao.removeAttribute("colecaoImovelEnnvioConta");
        sessao.removeAttribute("subCategorias");
        sessao.removeAttribute("categorias");
        sessao.removeAttribute("colecaoClientesImoveisFimRelacao");
        sessao.removeAttribute("colecaoClientesImoveisRemovidos");
        sessao.removeAttribute("colecaoImovelSubcategoriasRemoviadas");

        DynaValidatorForm atualizarImovelActionForm = (DynaValidatorForm) actionForm;

        //[FS0006] - Verificar Exist�ncia de RA
        Usuario usuario = this.getUsuarioLogado(httpServletRequest);
        
        boolean temPermissao = fachada.verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_IMOVEL,usuario);
        
        if(!temPermissao){
            
        	fachada.verificarExistenciaRegistroAtendimento(
            new Integer(idImovel), "atencao.imovel_existencia_registro_atendimento",EspecificacaoTipoValidacao.ALTERACAO_CADASTRAL); 
        }

        FiltroImovel filtroImovel = new FiltroImovel();
        
        //Objetos que ser�o retornados pelo Hibernate
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.bairro");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadraFace");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("funcionario");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto.ligacaoEsgotoEsgotamento");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
        
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelTipoHabitacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelTipoPropriedade");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelTipoConstrucao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelTipoCobertura");
        
        
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.localidade");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.quadra");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.setorComercial");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.cep");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTipo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTitulo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroBairro.bairro.municipio.unidadeFederacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.enderecoReferencia");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTitulo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.ligacaoAguaSituacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.ligacaoEsgotoSituacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.imovelPrincipal");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.COLECAO_CLIENTE_IMOVEL);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.ROTA_ENTREGA);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.ROTA_ALTERNATIVA);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL);
        filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel)));

        Collection imoveis = 
        	fachada.pesquisar(filtroImovel, Imovel.class.getName());

        Iterator iteratorImovel = imoveis.iterator();

        Imovel imovel = (Imovel) iteratorImovel.next();        
        
        this.verificarPermissaoGrandeCliente(imovel, usuario);

        //HINT DO IM�VEL
		statusWizard.adicionarItemHint("Matr�cula:", imovel.getId().toString());
		statusWizard.adicionarItemHint("Inscri��o:", imovel.getInscricaoFormatada());
		statusWizard.adicionarItemHint("Endere�o:", imovel.getEnderecoFormatado());
        
        //------------------------------------------- Seta o form dinamico
        //------------------------ Localidade
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		atualizarImovelActionForm.set("matricula",imovel.getId().toString());
        atualizarImovelActionForm.set("idLocalidade",""
                + 
                Util.adicionarZerosEsquedaNumero(3,
                formatarResultado(imovel.getLocalidade()).toString()));
        
        
        atualizarImovelActionForm.set("idSetorComercial",""
                + 
                Util.adicionarZerosEsquedaNumero(3,
                new Integer(imovel.getSetorComercial().getCodigo()).toString()));
        
        
        atualizarImovelActionForm.set("idQuadra",""
                + 
                Util.adicionarZerosEsquedaNumero(3,
           		new Integer(imovel.getQuadra().getNumeroQuadra()).toString()));
        
        /*
		 * Caso a empresa utilize o conceito de face da quadra (PARM_ICQUADRAFACE = 1 da tabela SISTEMA_PARAMETROS) 
		 * O campo face da quadra ficar� dispon�vel para atualiza��o;
		 */
        if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM) &&
        	imovel.getQuadraFace() != null){
        	
        	atualizarImovelActionForm.set("idQuadraFace", imovel.getQuadraFace().getId().toString());
        }
        else{
        	
        	atualizarImovelActionForm.set("idQuadraFace", "");
        }
        
        atualizarImovelActionForm.set("lote",""
                + 
                Util.adicionarZerosEsquedaNumero(4,
                new Integer(imovel.getLote()).toString()));
        
        
        atualizarImovelActionForm.set("subLote",""
                + 
                Util.adicionarZerosEsquedaNumero(3,
                new Short(formatarResultado("" + imovel.getSubLote())).toString()));
        
        
        
        String testadaLote = null;
        if(imovel.getTestadaLote() != null){
        	testadaLote  = "" + imovel.getTestadaLote();
        }
        else{
        	testadaLote  = "";
        }
        atualizarImovelActionForm.set("testadaLote",testadaLote);
        
        //------------------------ Endere�o
       if (imovel.getLogradouroCep().getLogradouro() != null && 
    	   !imovel.getLogradouroCep().getLogradouro().getId().toString().trim().equalsIgnoreCase("")) {
            
    	   atualizarImovelActionForm.set("logradouro", ""
           + formatarResultado(imovel.getLogradouroCep().getLogradouro()));
        }
        
       atualizarImovelActionForm.set("cep", ""
       + formatarResultado(imovel.getLogradouroCep().getCep()));

        
       //AREA_CONSTRUIDA
       String valorAreaConstruida = null;
        
        if(imovel.getAreaConstruida() != null){
        	
        	valorAreaConstruida = formatarResultado(
            imovel.getAreaConstruida().toString()).replace('.', ',');
        }
        else{
        	valorAreaConstruida = "";
        }

        atualizarImovelActionForm.set("areaConstruida", valorAreaConstruida);
        if(imovel.getAreaConstruidaFaixa() != null){
        	
        	atualizarImovelActionForm.set("faixaAreaConstruida", ""
            + formatarResultado(imovel.getAreaConstruidaFaixa()));	
        }
        else{
        	atualizarImovelActionForm.set("faixaAreaConstruida", "-1");
        }
        
        //verifica o valor do volume reservatorio inferior se nulo
        String volumeReservatorioInferior = null;
        if(imovel.getVolumeReservatorioInferior() != null){
        	volumeReservatorioInferior = formatarResultado(
                    imovel.getVolumeReservatorioInferior().toString()).replace('.', ',');
        }else{
        	volumeReservatorioInferior = "";
        }
        
        atualizarImovelActionForm.set("reservatorioInferior",volumeReservatorioInferior);
        
        atualizarImovelActionForm.set("faixaReservatorioInferior",""+ 
        		formatarResultado(imovel.getReservatorioVolumeFaixaInferior()));
        
        //verifica o valor do volume reservatorio superior se nulo
        String volumeReservatorioSuperior = null;
        if(imovel.getVolumeReservatorioSuperior() != null){
        	volumeReservatorioSuperior = formatarResultado(
                    imovel.getVolumeReservatorioSuperior().toString()).replace('.', ',');
        }else{
        	volumeReservatorioSuperior = "";
        }
        
        atualizarImovelActionForm.set("reservatorioSuperior",volumeReservatorioSuperior);
        atualizarImovelActionForm.set("faixaResevatorioSuperior",
                ""
                        + formatarResultado(imovel
                                .getReservatorioVolumeFaixaSuperior()));
        //verifica o valor da piscina se nula
        String voPiscina = null;
        if(imovel.getVolumePiscina() != null){
        	voPiscina = formatarResultado(imovel.getVolumePiscina().toString()).replace('.', ',');
        }else{
        	voPiscina = "";
        }
        atualizarImovelActionForm.set("piscina", voPiscina);
        
       	if(imovel.getIndicadorJardim() != null){
       		atualizarImovelActionForm.set("jardim", imovel.getIndicadorJardim().toString());
       	}else{
       		atualizarImovelActionForm.set("jardim", "2");
       	}
       	
       	atualizarImovelActionForm.set("indicadorNivelInstalacaoEsgoto", imovel.getIndicadorNivelInstalacaoEsgoto().toString());
       	
       	//Quadra de Entrega
       	if (imovel.getNumeroQuadraEntrega() != null){
       		atualizarImovelActionForm.set("numeroQuadraEntrega", imovel.getNumeroQuadraEntrega().toString());
       	}else{
       		atualizarImovelActionForm.set("numeroQuadraEntrega", "");
       	}

       	//Sequencial Rota
       	if(imovel.getNumeroSequencialRota() != null){
       		atualizarImovelActionForm.set("sequencialRota", imovel.getNumeroSequencialRota().toString());
       	}else{
       		atualizarImovelActionForm.set("sequencialRota", "");
       	}
       	
       	//Sequencial Rota de Entrega
       	if(imovel.getNumeroSequencialRotaEntrega() != null){
       		atualizarImovelActionForm.set("sequencialRotaEntrega", imovel.getNumeroSequencialRotaEntrega().toString());
       	}else{
       		atualizarImovelActionForm.set("sequencialRotaEntrega", "");
       	}
       	
       	//Numero Quadra de Entrega
       	if(imovel.getNumeroQuadraEntrega() != null){
       		atualizarImovelActionForm.set("numeroQuadraEntrega", imovel.getNumeroQuadraEntrega().toString());
       	}else{
       		atualizarImovelActionForm.set("numeroQuadraEntrega", "");
       	}
       	       	
       	// Rota de Entrega
       	if(imovel.getRotaEntrega() != null){
       		atualizarImovelActionForm.set("idRota", imovel.getRotaEntrega().getId().toString());
       		atualizarImovelActionForm.set("codigoRota", imovel.getRotaEntrega().getCodigo().toString());
       	}else{
       		atualizarImovelActionForm.set("idRota", "");
       		atualizarImovelActionForm.set("codigoRota", "");
       	}

       	// Rota Alternativa
       	if(imovel.getRotaAlternativa() != null){
       		atualizarImovelActionForm.set("idRotaAlternativa", imovel.getRotaAlternativa().getId().toString());
       		atualizarImovelActionForm.set("codigoRotaAlternativa", imovel.getRotaAlternativa().getCodigo().toString());
       	}else{
       		atualizarImovelActionForm.set("idRotaAlternativa", "");
       		atualizarImovelActionForm.set("codigoRotaAlternativa", "");
       	}
       	

       	if(imovel.getFuncionario() != null){
       		atualizarImovelActionForm.set("idFuncionario", imovel.getFuncionario().getId().toString());
       		atualizarImovelActionForm.set("nomeFuncionario", imovel.getFuncionario().getNome());
       	}else{
       		atualizarImovelActionForm.set("idFuncionario", null);
       		atualizarImovelActionForm.set("nomeFuncionario", null);
       	}
        
       	atualizarImovelActionForm.set("faixaPiscina", ""+ formatarResultado(imovel.getPiscinaVolumeFaixa()));
        atualizarImovelActionForm.set("pavimentoCalcada", ""+ formatarResultado(imovel.getPavimentoCalcada()));
        atualizarImovelActionForm.set("pavimentoRua", ""+ formatarResultado(imovel.getPavimentoRua()));
        atualizarImovelActionForm.set("fonteAbastecimento", ""+ formatarResultado(imovel.getFonteAbastecimento()));
        atualizarImovelActionForm.set("situacaoLigacaoAgua", ""+ formatarResultado(imovel.getLigacaoAguaSituacao()));
        atualizarImovelActionForm.set("situacaoLigacaoEsgoto", ""+ formatarResultado(imovel.getLigacaoEsgotoSituacao()));
        atualizarImovelActionForm.set("perfilImovel", ""+ formatarResultado(imovel.getImovelPerfil()));
       
        
        if(imovel.getPocoTipo() != null){
            atualizarImovelActionForm.set("poco", ""+ formatarResultado(imovel.getPocoTipo()));
        }else{
            atualizarImovelActionForm.set("poco", "");
        }
        
        if ( imovel.getDespejo() != null ){
        	atualizarImovelActionForm.set("tipoDespejo", ""+ formatarResultado(imovel.getDespejo()));
        } else {
        	atualizarImovelActionForm.set("tipoDespejo", "");
        }
        
        String idImovelTipoHabitacao = "";
        String idImovelTipoPropriedade = "";
        String idImovelTipoConstrucao = "";
        String idImovelTipoCobertura = "";
        String idLigacaoEsgotoEsgotamento = "";
        
        if(imovel.getImovelTipoHabitacao() != null){
        	idImovelTipoHabitacao = imovel.getImovelTipoHabitacao().getId().toString();
        }
        if(imovel.getImovelTipoPropriedade() != null){
        	idImovelTipoPropriedade = imovel.getImovelTipoPropriedade().getId().toString();
        }
        if(imovel.getImovelTipoConstrucao() != null){
        	idImovelTipoConstrucao = imovel.getImovelTipoConstrucao().getId().toString();
        }
        if(imovel.getImovelTipoCobertura() != null){
        	idImovelTipoCobertura = imovel.getImovelTipoCobertura().getId().toString();
        }
        if(imovel.getLigacaoEsgoto() != null && imovel.getLigacaoEsgoto().getLigacaoEsgotoEsgotamento() != null){
        	idLigacaoEsgotoEsgotamento = imovel.getLigacaoEsgoto().getLigacaoEsgotoEsgotamento().getId().toString();
        }
        
        atualizarImovelActionForm.set("imovelTipoHabitacao",idImovelTipoHabitacao);
        atualizarImovelActionForm.set("imovelTipoPropriedade",idImovelTipoPropriedade);
        atualizarImovelActionForm.set("imovelTipoConstrucao", idImovelTipoConstrucao);
        atualizarImovelActionForm.set("imovelTipoCobertura", idImovelTipoCobertura);
        atualizarImovelActionForm.set("idLigacaoEsgotoEsgotamento", idLigacaoEsgotoEsgotamento);
        
        
        //------------------------ Cliente
        
        //------------------------ Conclus�o
        String numeroPontos = null;
        //verifica o numero de pontos de utiliza��o
        if(imovel.getNumeroPontosUtilizacao() != null){
        	numeroPontos = "" + imovel.getNumeroPontosUtilizacao();
        }else{
        	numeroPontos = "";
        }
        atualizarImovelActionForm.set("numeroPontos",numeroPontos);
        //verifica o numero de moradores
        String numeroMoradores = null;
        if(imovel.getNumeroMorador() != null){
        	numeroMoradores = "" + imovel.getNumeroMorador();
        }else{
        	numeroMoradores = "";
        }
        atualizarImovelActionForm.set("numeroMoradores", numeroMoradores);
        //verifica se o numero do iptu � nulo
        String numeroIptu = null;
        if(imovel.getNumeroIptu() != null){
        	numeroIptu = formatarResultado(imovel.getNumeroIptu().toString());
        }else{
        	numeroIptu = "";
        }
        atualizarImovelActionForm.set("numeroIptu", numeroIptu);
        //verifica se o numero do contrato da celpe � nulo
        String numeroContratoCelpe = null;
        if(imovel.getNumeroCelpe() != null){
        	numeroContratoCelpe = formatarResultado(imovel.getNumeroCelpe().toString());
        }else{
        	numeroContratoCelpe = "";
        }
        atualizarImovelActionForm.set("numeroContratoCelpe", numeroContratoCelpe);
        if(imovel.getImovelContaEnvio() != null){
        	atualizarImovelActionForm.set("imovelContaEnvio", ""
                    + formatarResultado(imovel.getImovelContaEnvio()));	
        }else{
        	atualizarImovelActionForm.set("imovelContaEnvio", "-1");
        }
       
        String numeroMedidorEnergia = null;
        if(imovel.getNumeroMedidorEnergia() != null){
        	numeroMedidorEnergia = imovel.getNumeroMedidorEnergia();
        }else{
        	numeroMedidorEnergia = "";
        }
        atualizarImovelActionForm.set("numeroMedidorEnergia", numeroMedidorEnergia);
       
        if(imovel.getDataVisitaComercial() != null){
        	atualizarImovelActionForm.set("dataVisitaComercial", ""
                    + Util.formatarData(imovel.getDataVisitaComercial()));	
        }else{
        	atualizarImovelActionForm.set("dataVisitaComercial", "");
        }

        //atualizarImovelActionForm.set("tipoOcupacao", ""
        //        + formatarResultado(imovel.getNomeConta()));
        //verifica se a coordena X � nula
        String coordenadaUtmX = null;
        if(imovel.getCoordenadaX() != null){	
        	if(imovel.getCoordenadaX().compareTo(BigDecimal.ZERO) == 0){
        		coordenadaUtmX = "0";
        	}else{
        		coordenadaUtmX = formatarResultado(imovel.getCoordenadaX().toString().replace('.',','));
        	}
        }else{
        	coordenadaUtmX = "";
        }
        atualizarImovelActionForm.set("cordenadasUtmX",coordenadaUtmX);
        //verifica se a coordena Y � nula
        String coordenadaUtmY = null;
        if(imovel.getCoordenadaY() != null){	
        	if(imovel.getCoordenadaY().compareTo(BigDecimal.ZERO) == 0){
        		coordenadaUtmY = "0";
        	}else{
        		coordenadaUtmY = formatarResultado(imovel.getCoordenadaY().toString().replace('.',','));
        	}
        }else{
        	coordenadaUtmY = "";
        }
        atualizarImovelActionForm.set("cordenadasUtmY", coordenadaUtmY);
        
        /*
         * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Aryed Lins)
         * OBJ: Marcar o indicador de emiss�o de extrato de faturamento, na aba de conclus�o,
         * de acordo com o campo IndicadorEmissaoExtratoFaturamento da tabela de Imovel
         */
        if (imovel.getIndicadorEmissaoExtratoFaturamento() != null){
        	atualizarImovelActionForm.set("extratoResponsavel",  
        	imovel.getIndicadorEmissaoExtratoFaturamento().toString());
        }
        else{
        	 atualizarImovelActionForm.set("extratoResponsavel", ConstantesSistema.NAO.toString());
        }
        
        
        //--------------------------------------------fim form dinamico

        //------------------------ Cole�ao endere�o imovel
        Imovel imovel_endereco = new Imovel();
        LogradouroCep logradouroCep = new LogradouroCep();
        LogradouroBairro logradouroBairro = new LogradouroBairro();
        
        if (imovel.getLogradouroCep() != null && imovel.getLogradouroCep().getCep() != null  
                && !imovel.getLogradouroCep().getCep().getCepId().toString().trim()
                        .equalsIgnoreCase("")) {
        	
        	logradouroCep.setCep(imovel.getLogradouroCep().getCep());
        }
        
        if (imovel.getLogradouroCep() != null && imovel.getLogradouroCep().getLogradouro() != null 
                && !imovel.getLogradouroCep().getLogradouro().getId().toString().trim()
                        .equalsIgnoreCase("")) {
            
        	logradouroCep.setLogradouro(imovel.getLogradouroCep().getLogradouro());

        }
        
        if (logradouroCep.getCep() != null && logradouroCep.getLogradouro() != null){
        	
        	logradouroCep = fachada.pesquisarAssociacaoLogradouroCep(logradouroCep.getCep().getCepId(),
        	logradouroCep.getLogradouro().getId());
        	
        	imovel_endereco.setLogradouroCep(logradouroCep);
        }
        
        if (imovel.getComplementoEndereco() != null
                && !imovel.getComplementoEndereco().trim().equalsIgnoreCase("")) {
            
        	imovel_endereco.setComplementoEndereco(imovel
                    .getComplementoEndereco());
        }
        
        if (imovel.getNumeroImovel() != null
                && !imovel.getNumeroImovel().trim().equalsIgnoreCase("")) {
            
        	imovel_endereco.setNumeroImovel(imovel.getNumeroImovel());
        }
        if (imovel.getLogradouroBairro() != null && imovel.getLogradouroBairro().getBairro() != null 
            && !imovel.getLogradouroBairro().getBairro().getId().toString().trim().equalsIgnoreCase("")) {
            
        	logradouroBairro = fachada.pesquisarAssociacaoLogradouroBairro(imovel.getLogradouroBairro()
        	.getBairro().getId(), logradouroCep.getLogradouro().getId());
        	
        }
        if (imovel.getEnderecoReferencia() != null){
        	imovel_endereco.setEnderecoReferencia(imovel.getEnderecoReferencia());
        }
        
        if (imovel.getPerimetroInicial() != null){
        	imovel_endereco.setPerimetroInicial(imovel.getPerimetroInicial());
        }
        
        if (imovel.getPerimetroFinal() != null){
        	imovel_endereco.setPerimetroFinal(imovel.getPerimetroFinal());
        }
        
        if (logradouroBairro != null && logradouroBairro.getBairro() != null && logradouroBairro.getLogradouro() != null){
        	
        	logradouroBairro = fachada.pesquisarAssociacaoLogradouroBairro(logradouroBairro.getBairro().getId(),
        	logradouroBairro.getLogradouro().getId());
        	
        	imovel_endereco.setLogradouroBairro(logradouroBairro);
        	
        	if(logradouroBairro.getBairro().getMunicipio() != null){
        		imovel_endereco.getLogradouroBairro().getLogradouro().setMunicipio(logradouroBairro.getBairro().getMunicipio());
        	}
        }
        
        if(imovel.getQuadra() != null){
        	
        	imovel_endereco.setQuadra(imovel.getQuadra());
        	sessao.setAttribute("idQuadraInicial", imovel.getQuadra().getId());
        }

        Collection imoveisPrincipais = new HashSet();
        if (imovel.getImovelPrincipal() != null) {
            imoveisPrincipais.add(imovel.getImovelPrincipal());
            atualizarImovelActionForm.set("idImovel", ""
                    + formatarResultado(imovel.getImovelPrincipal()));
        }else{
            atualizarImovelActionForm.set("idImovel", "");
        	
        }
  
        sessao.setAttribute("imoveisPrincipal", imoveisPrincipais);

        //-------------- Imovel Endere�o
        Collection enderecos = new HashSet();
        
        //A data de �ltima altera��o ser� utilizada para identificar o endere�o do im�vel.
        imovel_endereco.setUltimaAlteracao(new Date());
        
        enderecos.add(imovel_endereco);
        sessao.setAttribute("colecaoEnderecos", enderecos);

        //-------------- Imovel Cliente
        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        
        //Objetos que ser�o retornados pelo Hibernate
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
               
        filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
        filtroClienteImovel.adicionarParametro(new ParametroSimples(
                FiltroClienteImovel.IMOVEL_ID, new Integer(idImovel)));
        
        Collection imoveisCliente = fachada.pesquisar(filtroClienteImovel,
                ClienteImovel.class.getName());
        sessao.setAttribute("imovelClientesNovos", imoveisCliente);
        
        if(imoveisCliente != null && !imoveisCliente.isEmpty()){
        	Iterator iteratorClientes = imoveisCliente.iterator();
        	
        	while (iteratorClientes.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) iteratorClientes.next();
				if(clienteImovel.getClienteRelacaoTipo().getId().intValue() == ClienteRelacaoTipo.USUARIO.intValue()){
					sessao.setAttribute("idClienteImovelUsuario",clienteImovel.getCliente().getId());
					atualizarImovelActionForm.set("idClienteImovelUsuario", clienteImovel.getCliente().getId().toString());
				}else if(clienteImovel.getClienteRelacaoTipo().getId().intValue() == ClienteRelacaoTipo.RESPONSAVEL.intValue()){
					sessao.setAttribute("idClienteImovelResponsavel",clienteImovel.getCliente().getId());
				}
				
			}
        }
        
        //-------------- SubCategoria
        FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
        
        //Objetos que ser�o retornados pelo Hibernate
        filtroImovelSubCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.subcategoria.categoria");
        filtroImovelSubCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.imovel");
        filtroImovelSubCategoria.adicionarCaminhoParaCarregamentoEntidade("imovelEconomias");
        filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(
                FiltroImovelSubCategoria.IMOVEL_ID, new Integer(idImovel)));
        Collection subCategorias = fachada.pesquisar(filtroImovelSubCategoria,
                ImovelSubcategoria.class.getName());
        sessao.setAttribute("colecaoImovelSubCategorias", subCategorias);
        
        if (!subCategorias.isEmpty()) {
            ImovelSubcategoria imovelSubCategoria = (ImovelSubcategoria) subCategorias
                    .iterator().next();
            atualizarImovelActionForm.set("idSubCategoriaImovel", ""
                    + formatarResultado(imovelSubCategoria.getComp_id()
                            .getSubcategoria()));
        }
        
        FiltroImovelRamoAtividade filtroImovelRamoAtividade = new FiltroImovelRamoAtividade();
        filtroImovelRamoAtividade.adicionarCaminhoParaCarregamentoEntidade("comp_id.imovel");
        filtroImovelRamoAtividade.adicionarCaminhoParaCarregamentoEntidade("comp_id.ramo_atividade");
        
        filtroImovelRamoAtividade.adicionarParametro(new ParametroSimples(
                FiltroImovelRamoAtividade.IMOVEL_ID, new Integer(idImovel)));
        Collection ramosAtividades = fachada.pesquisar(filtroImovelRamoAtividade,
                ImovelRamoAtividade.class.getName());
        sessao.setAttribute("colecaoImovelRamosAtividade", ramosAtividades);
        
        sessao.setAttribute("imovelAtualizacao", imovel);
        
        if(httpServletRequest.getParameter("promais")!=null){
        	sessao.setAttribute("promais",httpServletRequest.getParameter("promais"));
        	sessao.setAttribute("caminhoVoltarPromais", statusWizard.getCaminhoActionVoltarFiltro());
        }

        return retorno;
    }
    
  //verificar se o usu�rio tem permiss�o para alterar um im�vel com perfil Grande Cliente ou Cliente Corporativo.
    private void verificarPermissaoGrandeCliente(Imovel imovel, Usuario usuario){    	
    	
    	if(imovel.getImovelPerfil() != null){
    		FiltroImovelPerfil filtro = new FiltroImovelPerfil();
    		filtro.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, imovel.getImovelPerfil().getId()));
    		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelPerfil.PERMISSAO_ESPECIAL);
    		Collection colecaoPerfil = Fachada.getInstancia().pesquisar(filtro, ImovelPerfil.class.getName());
    		
    		if(!Util.isVazioOrNulo(colecaoPerfil)){
	    		ImovelPerfil imovelPerfil = (ImovelPerfil) colecaoPerfil.iterator().next();
	    		
	    		if(imovelPerfil.getPermissaoEspecial() != null){
	    			
	    			if(!Fachada.getInstancia().verificarPermissaoGrandeCorporativoCliente(imovelPerfil, usuario)){
	    			throw new ActionServletException("atencao.permissao_grande_cliente",null, imovelPerfil.getDescricao());
	    			
	    			}
	    		}
    		}    	
    	}
    }
  
    private Integer formatarResultado(Object parametro) {
        if (parametro != null) {
            try {
            	Class[] clasz = null;
            	Object[] obj = null;
                return (Integer) parametro.getClass().getMethod("getId", clasz)
                        .invoke(parametro, obj);
            } catch (SecurityException ex) {
                return null;
            } catch (NoSuchMethodException ex) {
                return null;
            } catch (InvocationTargetException ex) {
                return null;
            } catch (IllegalArgumentException ex) {
                return null;
            } catch (IllegalAccessException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    //------------------------------------------- Funcoes

    private String formatarResultado(String parametro) {
        if (parametro != null) {
            return parametro.trim();
        } else {
            return "";
        }
    }

}