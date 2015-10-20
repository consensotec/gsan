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
package gcom.gui.relatorio.faturamento.conta;

import gcom.gui.ActionServletException;
import gcom.gui.relatorio.faturamento.RelatorioContasCanceladasRetificadasActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladas;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladasRetificadasHelper;
import gcom.relatorio.faturamento.conta.RelatorioContasRetificadas;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioContasCanceladasRetificadasAction extends ExibidorProcessamentoTarefaRelatorio {

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("telaSucesso");

        RelatorioContasCanceladasRetificadasActionForm form = (RelatorioContasCanceladasRetificadasActionForm) actionForm;
        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        Usuario usuarioLogado = (Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado");
        
        sessao.removeAttribute("indicadorAtualizar");
        
        String mesAno = (String)form.getMesAno();
        String unidadeNegocio = (String) form.getIdUnidadeNegocio();
        
        boolean parametroInformado = false;
        
        RelatorioContasCanceladasRetificadasHelper helper = new RelatorioContasCanceladasRetificadasHelper();
        
        //recebe valores
        String anoMes = form.getMesAno();
        String localidade = form.getLocalidadeFiltro();
        String[] motivo = form.getIdMotivo();
        String[] categoria = form.getIdCategoria();
        String[] perfil = form.getIdPerfil();
        String[] esfera = form.getIdEsferaPoder();
        String responsavel = form.getResponsavelFiltro();
        String valor = form.getValor();
        String relatorioTipo = form.getRelatorioTipo();
        String ordenacaoTipo = form.getOrdenacaoTipo();
        String periodoInicial = form.getPeriodoInicial();
        String periodoFinal = form.getPeriodoFinal();
        String grupo = form.getGrupo();
        String idGerenciaRegional = form.getIdGerenciaRegional();
        
        if (!"".equals(periodoInicial)) {
        	Date data = Util.converteStringParaDate(periodoInicial);
			if (data == null) {
				throw new ActionServletException("atencao.data.inicial.invalida");
			}
        }
        
        if (!"".equals(periodoFinal)) {
			Date data = Util.converteStringParaDate(periodoFinal);			
			if (data == null) {
				throw new ActionServletException("atencao.data.final.invalida");
			}
        }
    	if (periodoInicial != null && !periodoInicial.trim().equals("")
				&& periodoFinal != null && !periodoFinal.trim().equals("")) {
			if ((Util.converteStringParaDate(periodoInicial)).compareTo(Util
					.converteStringParaDate(periodoFinal)) > 0) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido");
			}
		}
		
        
        //mesAno
        if(mesAno != null && !mesAno.equals("")){
        	parametroInformado = true;
        	helper.setMesAno(mesAno);
        }
        
        if(periodoInicial != null && !periodoInicial.equals("")){
        	parametroInformado = true;
        	helper.setPeriodoInicial(periodoInicial);
        }
        
        if(periodoFinal != null && !periodoFinal.equals("")){
        	parametroInformado = true;
        	helper.setPeriodoFinal(periodoFinal);
        }
        
        if(relatorioTipo != null && !relatorioTipo.equals("")){
        	parametroInformado = true;
        	
        	if(relatorioTipo.equals(ConstantesSistema.SINTETICO+"")){
        		helper.setRelatorioTipo("SINTETICO");	
        	}else if(relatorioTipo.equals(ConstantesSistema.ANALITICO+"")){
        		helper.setRelatorioTipo("ANALITICO");	
        	}
        }
        //localidade
        if(localidade != null && !localidade.equals("")){
        	parametroInformado = true;
        	helper.setIdLocalidade(localidade);
        }
        //motivo
        if(motivo != null && motivo.length > 0 && !motivo[0].equals("-1")){
        	parametroInformado = true;
        	helper.setMotivoArray(motivo);
        }
        
        if(unidadeNegocio != null && !unidadeNegocio.equals("") && !unidadeNegocio.equals("-1")){
        	parametroInformado = true;
        	helper.setUnidadeNegocio(unidadeNegocio);
        }else{
        	helper.setUnidadeNegocio("");
        }
        	
        
        //categoria
        if(categoria != null && categoria.length > 0 && !categoria[0].equals("-1")){
        	parametroInformado = true;
        	helper.setCategoriaArray(categoria);
        }
        //perfil do imovel
        if(perfil != null && perfil.length > 0 && !perfil[0].equals("-1")){
        	parametroInformado = true;
        	helper.setPerfilArray(perfil);
        }
        //esfera
        if(esfera != null && esfera.length > 0 && !esfera[0].equals("-1")){
        	parametroInformado = true;
        	helper.setEsferaArray(esfera);
        }
        //responsavel
        if(responsavel != null && !responsavel.equals("")){
        	
        	
        	FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, responsavel));

			// Retorna o usu�rio
			Collection colecaoPesquisa = 
				this.getFachada().pesquisar(filtroUsuario,
					Usuario.class.getName());
			
			if(colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				parametroInformado = true;
				helper.setIdResponsavel(usuario.getId().toString());
			}


        }
        //valor
        if(valor != null && !valor.equals("")){
        	parametroInformado = true;
        	helper.setValor(valor);
        }

        if(ordenacaoTipo != null && !ordenacaoTipo.equals("")){
        	parametroInformado = true;
        	helper.setOrdenacaoTipo(ordenacaoTipo);
        }

        if(grupo != null && !grupo.equals("")){
        	
        	helper.setGrupo(grupo);
        }

        if(idGerenciaRegional != null && !idGerenciaRegional.equals("")&&
        		!idGerenciaRegional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	helper.setIdGerenciaRegional(idGerenciaRegional);
        }
        
        
        String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

        if(parametroInformado){
        	//chamar metodo de filtragem
        	try {
        		if(form.getTipoConta().equals("1")){
            		 
        			RelatorioContasCanceladas relatorioContasCanceladas = 
        				new RelatorioContasCanceladas(usuarioLogado);

     				relatorioContasCanceladas.addParametro("mesAno", anoMes);
     				relatorioContasCanceladas.addParametro("relatorioTipo",helper.getRelatorioTipo());
     				relatorioContasCanceladas.addParametro("ordenacaoTipo",helper.getOrdenacaoTipo());
     				relatorioContasCanceladas.addParametro("relatorioContasCanceladasRetificadasHelper",helper);

     				if (tipoRelatorio == null) {
     					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
     				}

     				relatorioContasCanceladas.addParametro("tipoFormatoRelatorio",
     						Integer.parseInt(tipoRelatorio));

            		retorno = processarExibicaoRelatorio(relatorioContasCanceladas,
    						tipoRelatorio, httpServletRequest, httpServletResponse,
    						actionMapping);
        		}else{
        			RelatorioContasRetificadas relatorioContasRetificadas = 
        				new RelatorioContasRetificadas(usuarioLogado);

     				relatorioContasRetificadas.addParametro("mesAno", anoMes);
     				relatorioContasRetificadas.addParametro("relatorioTipo",helper.getRelatorioTipo());
     				relatorioContasRetificadas.addParametro("ordenacaoTipo",helper.getOrdenacaoTipo());
     				relatorioContasRetificadas.addParametro("relatorioContasCanceladasRetificadasHelper",helper);
     				
     				if (tipoRelatorio == null) {
     					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
     				}

     				relatorioContasRetificadas.addParametro("tipoFormatoRelatorio",
     						Integer.parseInt(tipoRelatorio));

            		retorno = processarExibicaoRelatorio(relatorioContasRetificadas,
    						tipoRelatorio, httpServletRequest, httpServletResponse,
    						actionMapping);
        		 }

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
        	
        }else{
        	throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
        }
        
		
        return retorno;
    }

}