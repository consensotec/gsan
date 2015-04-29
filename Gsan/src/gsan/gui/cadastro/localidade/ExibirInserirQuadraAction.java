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
package gsan.gui.cadastro.localidade;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.cadastro.dadocensitario.FiltroIbgeSetorCensitario;
import gsan.cadastro.dadocensitario.IbgeSetorCensitario;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.FiltroBairro;
import gsan.cadastro.localidade.AreaTipo;
import gsan.cadastro.localidade.FiltroAreaTipo;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadraPerfil;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroZeis;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.QuadraFace;
import gsan.cadastro.localidade.QuadraPerfil;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.Zeis;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.operacional.Bacia;
import gsan.operacional.DistritoOperacional;
import gsan.operacional.FiltroBacia;
import gsan.operacional.FiltroDistritoOperacional;
import gsan.operacional.FiltroSistemaEsgoto;
import gsan.operacional.SistemaEsgoto;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

/**
 * Classe respons�vel pela exibi��o da tela de cadastro da quadra 
 *
 * @author R�mulo Aur�lio, Raphael Rossiter
 * @date 08/07/2006, 01/04/2009
 */

public class ExibirInserirQuadraAction extends GcomAction {

	private String localidadeID;

    private Collection colecaoPesquisa;

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirInserirQuadra");

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);

        InserirQuadraActionForm inserirQuadraActionForm = (InserirQuadraActionForm) actionForm;

        if(inserirQuadraActionForm.getSetorComercialCD() != null && 
        		inserirQuadraActionForm.getSetorComercialCD().equals("")){
        	
        	inserirQuadraActionForm.setMunicipioID("");
        	inserirQuadraActionForm.setIndicadorRelacionamentoQuadraBairro("2");
        }
        
        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

           
            localidadeID = inserirQuadraActionForm.getLocalidadeID();
          
            switch (Integer.parseInt(objetoConsulta)) {
            
            //Localidade
            case 1:

                pesquisarLocalidade(inserirQuadraActionForm, fachada,
                httpServletRequest);

                break;
            //Setor Comercial
            case 2:

                pesquisarLocalidade(inserirQuadraActionForm, fachada,
                httpServletRequest);

                pesquisarSetorComercial(inserirQuadraActionForm, fachada,
                httpServletRequest);

                break;

            //Distrito Operacional
            case 5:

                //DISTRITO OPERACIONAL INFORMADO
                String distritoOperacionalID = inserirQuadraActionForm
                .getDistritoOperacionalID();

                FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

                filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
                FiltroDistritoOperacional.ID, distritoOperacionalID));

                filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
                FiltroDistritoOperacional.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
                DistritoOperacional.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    
                	//DISTRITO OPERACIONAL N�O ENCONTRADO
                	inserirQuadraActionForm.setDistritoOperacionalID("");
                    inserirQuadraActionForm.setDistritoOperacionalDescricao("Distrito operacional inexistente.");
                    httpServletRequest.setAttribute("corDistritoOperacional", "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
                } 
                else {
                    
                	DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
                    
                	inserirQuadraActionForm.setDistritoOperacionalID(String
                    .valueOf(objetoDistritoOperacional.getId()));
                    inserirQuadraActionForm.setDistritoOperacionalDescricao(objetoDistritoOperacional
                    .getDescricao());
                    httpServletRequest.setAttribute("corDistritoOperacional", "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "setorCensitarioID");
                }

                break;
            //Setor censit�rio
            case 6:

                //SETOR CENSITARIO INFORMADO
            	String setorCensitarioID = inserirQuadraActionForm
                .getSetorCensitarioID();

                FiltroIbgeSetorCensitario filtroIbgeSetorCensitario = new FiltroIbgeSetorCensitario();

                filtroIbgeSetorCensitario.adicionarParametro(new ParametroSimples(
                FiltroIbgeSetorCensitario.ID, setorCensitarioID));

                filtroIbgeSetorCensitario.adicionarParametro(new ParametroSimples(
                FiltroIbgeSetorCensitario.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroIbgeSetorCensitario,
                IbgeSetorCensitario.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    
                	//SETOR CENSITARIO N�O ENCONTRADO
                	inserirQuadraActionForm.setSetorCensitarioID("");
                    inserirQuadraActionForm.setSetorCensitarioDescricao("Setor censit�rio inexistente.");
                    httpServletRequest.setAttribute("corSetorCensitario", "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "setorCensitarioID");
                } 
                else {
                    
                	IbgeSetorCensitario objetoIbgeSetorCensitario = (IbgeSetorCensitario) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
                    
                	inserirQuadraActionForm.setSetorCensitarioID(String
                    .valueOf(objetoIbgeSetorCensitario.getId()));
                    inserirQuadraActionForm
                    .setSetorCensitarioDescricao(objetoIbgeSetorCensitario.getDescricao());
                    
                    httpServletRequest.setAttribute("corSetorCensitario", "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "zeisID");
                }

                break;
            
            //Bacia
            case 7:
                
            	//SISTEMA DE ESGOTO INFORMADO
            	String sistemaEsgotoID = inserirQuadraActionForm.getSistemaEsgotoID();

                if (sistemaEsgotoID != null && !sistemaEsgotoID
                    .equalsIgnoreCase(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

                    FiltroBacia filtroBacia = new FiltroBacia();

                    filtroBacia.adicionarParametro(new ParametroSimples(
                    FiltroBacia.SISTEMA_ESGOTO_ID, sistemaEsgotoID));

                    filtroBacia.adicionarParametro(new ParametroSimples(
                    FiltroBacia.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroBacia, Bacia.class.getName());

                    sessao.setAttribute("colecaoBacia", colecaoPesquisa);

                } 
                else {
                    
                	inserirQuadraActionForm.setBaciaID(String
                    .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
                    
                	sessao.removeAttribute("colecaoBacia");
                }

                break;
            //Rota
            case 8:

            	//ROTA INFORMADA
            	String idRota = inserirQuadraActionForm.getRotaID();
            	
            	if (idRota != null && !idRota.trim().equals("")) {

            		FiltroRota filtroRota = new FiltroRota();
            		filtroRota.adicionarCaminhoParaCarregamentoEntidade("leituraTipo");
            		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
            		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
            		
            		Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
            		
            		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
            		
            		if(rota.getIndicadorRotaAlternativa().shortValue() != ConstantesSistema.SIM){
            			
            			inserirQuadraActionForm.setCodigoRota(String.valueOf(rota.getCodigo()));
                		inserirQuadraActionForm.setRotaMensagem(rota.getLeituraTipo().getDescricao());
                		inserirQuadraActionForm.setLocalidadeID(rota.getSetorComercial().getLocalidade().getId().toString());
                		inserirQuadraActionForm.setLocalidadeNome(rota.getSetorComercial().getLocalidade().getDescricao());
                		inserirQuadraActionForm.setSetorComercialID(rota.getSetorComercial().getId().toString());
                		inserirQuadraActionForm.setSetorComercialCD("" + rota.getSetorComercial().getCodigo());
                		inserirQuadraActionForm.setSetorComercialNome(rota.getSetorComercial().getDescricao());
                		httpServletRequest.setAttribute("corRotaMensagem", "valor");
            		}else{
            			inserirQuadraActionForm.setRotaID("");
	                    inserirQuadraActionForm.setCodigoRota("");
            			
                    	throw new ActionServletException("atencao.rota_alternativa_nao_pode_associar_quadra");
            		}
            		
            	} 
            	else {
                
            		String codigoRota = inserirQuadraActionForm.getCodigoRota();
            		String setorComercialCD = inserirQuadraActionForm.getSetorComercialCD();
                
	            	if (setorComercialCD == null || setorComercialCD.equalsIgnoreCase("")) {
	                    throw new ActionServletException(
	                    "atencao.setor_comercial_nao_informado");
	                }
            	

	                FiltroRota filtroRota = new FiltroRota();
	                
	                filtroRota.adicionarCaminhoParaCarregamentoEntidade("leituraTipo");
	                filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
	                
	                filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.LOCALIDADE_ID, inserirQuadraActionForm.getLocalidadeID()));
	           
	                filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.SETOR_COMERCIAL_CODIGO,  inserirQuadraActionForm.getSetorComercialCD()));
	                
	                filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.CODIGO_ROTA, codigoRota));
	
	                filtroRota.adicionarParametro(new ParametroSimples(
	                FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

	                colecaoPesquisa = fachada.pesquisar(filtroRota, Rota.class.getName());
	
	                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	                    
	                	//ROTA N�O ENCONTRADA
	                	inserirQuadraActionForm.setRotaID("");
	                    inserirQuadraActionForm.setCodigoRota("");
	                    inserirQuadraActionForm.setRotaMensagem("Rota inexistente.");
	                    httpServletRequest.setAttribute("corRotaMensagem", "exception");
	                    
	                    httpServletRequest.setAttribute("nomeCampo", "codigoRota");
	                } 
	                else {
	                    
	                	Rota objetoRota = (Rota) Util.retonarObjetoDeColecao(colecaoPesquisa);
	
	                	if(objetoRota.getIndicadorRotaAlternativa().shortValue() == ConstantesSistema.SIM){
	                		inserirQuadraActionForm.setRotaID("");
		                    inserirQuadraActionForm.setCodigoRota("");
	            			
	                    	throw new ActionServletException("atencao.rota_alternativa_nao_pode_associar_quadra");
	                	}else{
	                		inserirQuadraActionForm.setRotaID(String
	        	                    .valueOf(objetoRota.getId()));
	        	                    inserirQuadraActionForm.setCodigoRota(String
	        	                    .valueOf(objetoRota.getCodigo()));
	        	                    inserirQuadraActionForm.setRotaMensagem(objetoRota
	        	                    .getLeituraTipo().getDescricao());
	        	                    httpServletRequest.setAttribute("corRotaMensagem", "valor");
	        	                    
	        	                    httpServletRequest.setAttribute("nomeCampo", "codigoRota");	
	                	}
	                    
	                  }
            	}
            	
                break;
                
            // Rota
            case 9:
            	
            	if(inserirQuadraActionForm.getBairroID() != null && 
            			!inserirQuadraActionForm.getBairroID().equals("")){
            		
            		pesquisarBairro(inserirQuadraActionForm, fachada,
                            httpServletRequest);
            	}
            	
            	break;
            
            default:

                break;
            }
        } 
        
        //CARREGAMENTO INICIAL DO FORMULARIO
        carregamentoInicialFormulario(fachada, httpServletRequest,inserirQuadraActionForm, sessao);
        
        //OP��O DESFAZER
        desfazer(httpServletRequest, sessao, inserirQuadraActionForm);
        
        //OP��O REMOVER QUADRA FACE
        removerQuadraFace(httpServletRequest, sessao);
        
        return retorno;
    }

    private void pesquisarLocalidade(
            InserirQuadraActionForm inserirQuadraActionForm, Fachada fachada,
            HttpServletRequest httpServletRequest) {

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, localidadeID));

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna localidade
        colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class
                .getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Localidade nao encontrada
            //Limpa o campo localidadeID do formul�rio
            inserirQuadraActionForm.setLocalidadeID("");
            inserirQuadraActionForm
                    .setLocalidadeNome("Localidade inexistente.");
            httpServletRequest.setAttribute("corLocalidade", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "localidadeID");
        } else {
            Localidade objetoLocalidade = (Localidade) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
            inserirQuadraActionForm.setLocalidadeID(String
                    .valueOf(objetoLocalidade.getId()));
            inserirQuadraActionForm.setLocalidadeNome(objetoLocalidade
                    .getDescricao());
            httpServletRequest.setAttribute("corLocalidade", "valor");
            
            httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
        }
    }

    private void pesquisarSetorComercial(
            InserirQuadraActionForm inserirQuadraActionForm, Fachada fachada,
            HttpServletRequest httpServletRequest) {

        if (localidadeID == null || localidadeID.trim().equalsIgnoreCase("")) {
            //Limpa os campos setorComercialCD e setorComercialID do formulario
            inserirQuadraActionForm.setSetorComercialCD("");
            inserirQuadraActionForm.setSetorComercialID("");
            inserirQuadraActionForm
                    .setSetorComercialNome("Informe a localidade.");
            httpServletRequest.setAttribute("corSetorComercial", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "localidadeID");
        } else {
            //Recebe o valor do campo setorComercialCD do formul�rio.
            String setorComercialCD = inserirQuadraActionForm
                    .getSetorComercialCD();

            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    setorComercialCD));
            filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(
            		FiltroSetorComercial.MUNICIPIO);

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna setorComercial
            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //setorComercial nao encontrado
                //Limpa os campos setorComercialCD e setorComercialID do
                // formulario
                inserirQuadraActionForm.setSetorComercialCD("");
                inserirQuadraActionForm.setSetorComercialID("");
                inserirQuadraActionForm
                        .setSetorComercialNome("Setor comercial inexistente.");
                httpServletRequest.setAttribute("corSetorComercial",
                        "exception");
                
                httpServletRequest.setAttribute("nomeCampo", "setorComercialCD");
                
            } else {
                SetorComercial objetoSetorComercial = (SetorComercial) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                inserirQuadraActionForm.setSetorComercialCD(String
                        .valueOf(objetoSetorComercial.getCodigo()));
                inserirQuadraActionForm.setSetorComercialID(String
                        .valueOf(objetoSetorComercial.getId()));
                inserirQuadraActionForm
                        .setSetorComercialNome(objetoSetorComercial
                                .getDescricao());
                httpServletRequest.setAttribute("corSetorComercial", "valor");
                
                httpServletRequest.setAttribute("nomeCampo", "quadraNM");
                
                inserirQuadraActionForm.setIndicadorRelacionamentoQuadraBairro(objetoSetorComercial.getMunicipio().getIndicadorRelacaoQuadraBairro().toString());
                inserirQuadraActionForm.setMunicipioID(objetoSetorComercial.getMunicipio().getId().toString());
                
                int numeroQuadra = fachada.pesquisarMaximoCodigoQuadra(objetoSetorComercial.getId());
                
                numeroQuadra = numeroQuadra + 1;
                
                inserirQuadraActionForm.setQuadraNM("" + numeroQuadra);
            }

        }

    }
    
    private void carregamentoInicialFormulario(Fachada fachada, HttpServletRequest httpServletRequest,
    	InserirQuadraActionForm inserirQuadraActionForm, HttpSession sessao){
    	
    	httpServletRequest.setAttribute("nomeCampo", "localidadeID");
    	
    	if (inserirQuadraActionForm.getIndicadorRedeAguaAux() == null ||
    		inserirQuadraActionForm.getIndicadorRedeAguaAux().equals("")){
    		
    		inserirQuadraActionForm.setIndicadorRedeAguaAux(Quadra.COM_REDE.toString());
    	}
    	
    	if (inserirQuadraActionForm.getIndicadorRedeEsgotoAux() == null ||
        	inserirQuadraActionForm.getIndicadorRedeEsgotoAux().equals("")){
        		
        	inserirQuadraActionForm.setIndicadorRedeEsgotoAux(Quadra.COM_REDE.toString());
        }
    	
    	//QUADRA_PERFIL
        FiltroQuadraPerfil filtroQuadraPerfil = new FiltroQuadraPerfil();

        filtroQuadraPerfil.adicionarParametro(new ParametroSimples(
        FiltroQuadraPerfil.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        colecaoPesquisa = fachada.pesquisar(filtroQuadraPerfil,
        QuadraPerfil.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            
        	throw new ActionServletException(
            "atencao.pesquisa.nenhum_registro_tabela", null, "Quadra_Perfil");
        } 
        else {
            
        	httpServletRequest.setAttribute("colecaoPerfilQuadra", colecaoPesquisa);
        }

        //AREA_TIPO
        FiltroAreaTipo filtroAreaTipo = new FiltroAreaTipo();

        filtroAreaTipo.adicionarParametro(new ParametroSimples(
        FiltroAreaTipo.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        colecaoPesquisa = fachada.pesquisar(filtroAreaTipo,
        AreaTipo.class.getName());

        httpServletRequest.setAttribute("colecaoAreaTipo", colecaoPesquisa);
        
        // Indicador de Incremento do Lote inicia com valor = 2
        if (inserirQuadraActionForm.getIndicadorIncrementoLote() == null || inserirQuadraActionForm.getIndicadorIncrementoLote().equals("")){
        	inserirQuadraActionForm.setIndicadorIncrementoLote("2");
        }
        
     // Indicador de Atualiza��o Cadastral inicia com valor = 2
        if (inserirQuadraActionForm.getIndicadorAtualizacaoCadastral() == null || inserirQuadraActionForm.getIndicadorAtualizacaoCadastral().equals("")){
        	inserirQuadraActionForm.setIndicadorAtualizacaoCadastral("2");
        }
        
        
        //ZEIS
        FiltroZeis filtroZeis = new FiltroZeis();

        filtroZeis.adicionarParametro(new ParametroSimples(
        FiltroZeis.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

        colecaoPesquisa = fachada.pesquisar(filtroZeis, Zeis.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            
        	throw new ActionServletException(
            "atencao.pesquisa.nenhum_registro_tabela", null, "ZEIS");
        } 
        else {
        	httpServletRequest.setAttribute("colecaoZeis", colecaoPesquisa);
        }
        
        //PERMISS�O PARA ADICIONAR FACE(S) PARA A QUADRA
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        httpServletRequest.setAttribute("permissaoAdicionarQuadraFace", 
        sistemaParametro.getIndicadorQuadraFace().toString());
        
        if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.NAO)){
        	
        	//SISTEMA_ESGOTO
            FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();

            filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(
            FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroSistemaEsgoto,
            SistemaEsgoto.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela", null,
                "Sistema_Esgoto");
            } 
            else {
            	httpServletRequest.setAttribute("colecaoSistemaEsgoto", colecaoPesquisa);
            }
        }
    }

    private void desfazer(HttpServletRequest httpServletRequest, HttpSession sessao,
    		InserirQuadraActionForm inserirQuadraActionForm){
    	
    	if (httpServletRequest.getParameter("desfazer") != null
            && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")){
            // -------------- bt DESFAZER ---------------
                
    		inserirQuadraActionForm.setIndicadorRedeAguaAux(Quadra.COM_REDE.toString());
    		inserirQuadraActionForm.setIndicadorRedeEsgotoAux(Quadra.COM_REDE.toString());

            inserirQuadraActionForm.setBaciaID("");
            inserirQuadraActionForm.setDistritoOperacionalDescricao("");
            inserirQuadraActionForm.setDistritoOperacionalID("");
            inserirQuadraActionForm.setIndicadorUso("");
            inserirQuadraActionForm.setLocalidadeID("");
            inserirQuadraActionForm.setLocalidadeNome("");
            inserirQuadraActionForm.setPerfilQuadra("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            inserirQuadraActionForm.setAreaTipoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            inserirQuadraActionForm.setQuadraID("");
            inserirQuadraActionForm.setQuadraNM("");
            inserirQuadraActionForm.setRotaID("");
            inserirQuadraActionForm.setCodigoRota("");
            inserirQuadraActionForm.setRotaMensagem("");
            inserirQuadraActionForm.setSetorCensitarioDescricao("");
            inserirQuadraActionForm.setSetorCensitarioID("");
            inserirQuadraActionForm.setSetorComercialCD("");
            inserirQuadraActionForm.setSetorComercialID("");
            inserirQuadraActionForm.setSetorComercialNome("");
            inserirQuadraActionForm.setSistemaEsgotoID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            inserirQuadraActionForm.setZeisID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
            
            sessao.removeAttribute("colecaoQuadraFace");
            sessao.removeAttribute("telaRetorno");
    	}
    }
    
    private void removerQuadraFace(HttpServletRequest httpServletRequest, HttpSession sessao){
    	
    	Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");
    	
    	if (httpServletRequest.getParameter("numeroQuadraFace") != null && 
    		(colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty())){
	    	
    		Integer numeroQuadraFaceParaRemover = Integer.valueOf(
    		httpServletRequest.getParameter("numeroQuadraFace"));
    		
    		Iterator it = colecaoQuadraFace.iterator();
				
			while (it.hasNext()){
					
				QuadraFace quadraFace = (QuadraFace) it.next();
					
				if (quadraFace.getNumeroQuadraFace().equals(numeroQuadraFaceParaRemover)){
					
					colecaoQuadraFace.remove(quadraFace);
					break;
				}
			}
			
			if (colecaoQuadraFace.isEmpty()){
				sessao.removeAttribute("colecaoQuadraFace");
			}
			else{
				sessao.setAttribute("colecaoQuadraFace", colecaoQuadraFace);
			}
    	}
    }
    
    /**
     * 
     * Pesquisar Bairro
     * 
     * @Autor Hugo Leonardo
     * @Date 14/01/2011
     */
    private void pesquisarBairro(
            InserirQuadraActionForm inserirQuadraActionForm, Fachada fachada,
            HttpServletRequest httpServletRequest) {

        FiltroBairro filtroBairro = new FiltroBairro();

        filtroBairro.adicionarParametro(new ParametroSimples(
        		FiltroBairro.CODIGO, inserirQuadraActionForm.getBairroID()));
        
        if(inserirQuadraActionForm.getMunicipioID() != null && 
        		!inserirQuadraActionForm.getMunicipioID().equals("")){
        	
        	filtroBairro.adicionarParametro(new ParametroSimples(
            		FiltroBairro.MUNICIPIO_ID, inserirQuadraActionForm.getMunicipioID()));
        }

        filtroBairro.adicionarParametro(new ParametroSimples(
        		FiltroBairro.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna localidade
        colecaoPesquisa = fachada.pesquisar(filtroBairro, Bairro.class
                .getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
            //Bairo nao encontrado
            //Limpa o campo bairroID do formul�rio
            inserirQuadraActionForm.setBairroID("");
            inserirQuadraActionForm
                    .setBairroDescricao("Bairro inexistente.");
            httpServletRequest.setAttribute("corBairro", "exception");
            
            httpServletRequest.setAttribute("nomeCampo", "bairroID");
        } else {
            Bairro objetoBairro = (Bairro) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
            inserirQuadraActionForm.setBairroID(String
                    .valueOf(objetoBairro.getCodigo()));
            inserirQuadraActionForm.setBairroDescricao(objetoBairro.getNome());
            httpServletRequest.setAttribute("corBairro", "valor");
            
        }
    }
}
