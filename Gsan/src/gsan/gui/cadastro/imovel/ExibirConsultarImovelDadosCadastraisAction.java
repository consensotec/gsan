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
package gsan.gui.cadastro.imovel;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 1� Aba do [UC0472] Consultar Im�vel
 * 
 * @author Rafael Santos
 * @since 07/09/2006
 */
public class ExibirConsultarImovelDadosCadastraisAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        HttpSession sessao = httpServletRequest.getSession(false);

        ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
        
        if( isLimparDadosTela(httpServletRequest)){

    		httpServletRequest.removeAttribute("idImovelDadosCadastraisNaoEncontrado");
    		
    		sessao.removeAttribute("exibirVisualizarCoordenadas");

        	limparFormSessao(consultarImovelActionForm, sessao);
			
        }else if( isImovelInformadoTelaDadosCadastrais(consultarImovelActionForm) 
        			|| isImovelInformadoOutraTela(sessao) ){
        	
			consultarImovelActionForm.setIdImovelDadosCadastrais(
            	definirIdImovelASerPesquisado(consultarImovelActionForm, sessao,httpServletRequest) );
        	
	        Imovel imovel = obterImovelASerPesquisado(consultarImovelActionForm,sessao);
	        
	        //deve ser chamado antes dos novos valores da sess�o serem setados
	        boolean isNovoImovelPesquisado = isNovoImovelPesquisado(consultarImovelActionForm, sessao);
	
            if (imovel != null) {
            	
                sessao.setAttribute("imovelDadosCadastrais", imovel);
                sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
               
                consultarImovelActionForm.setIdImovelDadosCadastrais(imovel.getId().toString());
                
                if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

                if(isNovoImovelPesquisado){
                	
	                httpServletRequest.removeAttribute("idImovelDadosCadastraisNaoEncontrado");
	                
	    	        setarDadosImovelNoFormESessao(consultarImovelActionForm,imovel, sessao, httpServletRequest);
                }

            } else {
                limparFormSessao(consultarImovelActionForm, sessao);

                httpServletRequest.setAttribute("idImovelDadosCadastraisNaoEncontrado", "true");
                consultarImovelActionForm.setMatriculaImovelDadosCadastrais("IM�VEL INEXISTENTE");
            }
        }else{
        	String idImovelAux = consultarImovelActionForm.getIdImovelDadosCadastrais();

         	httpServletRequest.removeAttribute("idImovelDadosCadastraisNaoEncontrado");

            limparFormSessao(consultarImovelActionForm, sessao);

        	consultarImovelActionForm.setIdImovelDadosCadastrais(idImovelAux);
        }
      
        return actionMapping.findForward("consultarImovelDadosCadastrais");
    }
   
	/**
	 * Esse m�todo seta os dados necess�rios do Imovel
	 * no form e alguns na sess�o (cole��es).
	 * 
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosImovelNoFormESessao(ConsultarImovelActionForm consultarImovelActionForm, 
			Imovel imovel,HttpSession sessao, HttpServletRequest httpServletRequest) {
		
		Fachada fachada = Fachada.getInstancia();

        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		consultarImovelActionForm.setMatriculaImovelDadosCadastrais(fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim())));
		
		consultarImovelActionForm.setEnderecoImovelDadosCadastrais(fachada.pesquisarEndereco(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim())));
		
		sessao.setAttribute("enderecoImovelDadosCadastrais",consultarImovelActionForm.getEnderecoImovelDadosCadastrais());
		
		if(imovel.getLigacaoAguaSituacao() != null){
			consultarImovelActionForm.setSituacaoAguaDadosCadastrais(imovel.getLigacaoAguaSituacao().getDescricao());
		} else {
			consultarImovelActionForm.setSituacaoAguaDadosCadastrais("");
		}

		if(imovel.getLigacaoEsgotoSituacao() != null){
			consultarImovelActionForm.setSituacaoEsgotoDadosCadastrais(imovel.getLigacaoEsgotoSituacao().getDescricao());
		} else {
			consultarImovelActionForm.setSituacaoEsgotoDadosCadastrais("");
		}
		
		sessao.setAttribute("imovelClientes",fachada.pesquisarClientesImovelExcluidoOuNao
				(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()))  );
		
		sessao.setAttribute("imovelSubcategorias",fachada.pesquisarCategoriasImovel
				(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()))   );
		
		Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao( imovel.getId() );
		
		if(clienteUsuario != null){
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado(String.valueOf(clienteUsuario.getIndicadorValidaCpfCnpj()));
		}else{
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
		}
		
		if(imovel.getImovelPerfil() != null){
			consultarImovelActionForm.setImovelPerfilDadosCadastrais(imovel.getImovelPerfil().getDescricao());
		} else {
			consultarImovelActionForm.setImovelPerfilDadosCadastrais("");
		}
		if(imovel.getDespejo() != null){
			consultarImovelActionForm.setDespejoDadosCadastrais(imovel.getDespejo().getDescricao());
		} else {
			consultarImovelActionForm.setDespejoDadosCadastrais("");
		}
		
		if(imovel.getAreaConstruida() != null){
			consultarImovelActionForm.setAreaConstruidaDadosDadosCadastrais(Util.formatarMoedaReal(imovel.getAreaConstruida()));	

		}else if(imovel.getAreaConstruidaFaixa() != null ){
			if(imovel.getAreaConstruidaFaixa().getMenorFaixa() != null && imovel.getAreaConstruidaFaixa().getMaiorFaixa() != null){
				consultarImovelActionForm.setAreaConstruidaDadosDadosCadastrais(imovel.getAreaConstruidaFaixa().getMenorFaixa().toString()+ " a " + imovel.getAreaConstruidaFaixa().getMaiorFaixa().toString());
			}
		} else {
			consultarImovelActionForm.setAreaConstruidaDadosDadosCadastrais("");
		}
		
		if(imovel.getTestadaLote() != null){
			consultarImovelActionForm.setTestadaLoteDadosCadastrais(imovel.getTestadaLote().toString());
		} else {
			consultarImovelActionForm.setTestadaLoteDadosCadastrais("");
		}

		if(imovel.getVolumeReservatorioInferior() != null){
			consultarImovelActionForm.setVolumeReservatorioInferiorDadosCadastrais(Util.formatarMoedaReal(imovel.getVolumeReservatorioInferior()));	
		
		}else if(imovel.getReservatorioVolumeFaixaInferior() != null){
			if(imovel.getReservatorioVolumeFaixaInferior().getVolumeMenorFaixa() != null && imovel.getReservatorioVolumeFaixaInferior().getVolumeMaiorFaixa() != null){
				consultarImovelActionForm.setVolumeReservatorioInferiorDadosCadastrais(Util.formatarMoedaReal(imovel.getReservatorioVolumeFaixaInferior().getVolumeMenorFaixa())+" a "+ Util.formatarMoedaReal(imovel.getReservatorioVolumeFaixaInferior().getVolumeMaiorFaixa()));
			}
		} else {
			consultarImovelActionForm.setVolumeReservatorioInferiorDadosCadastrais("");
		}
		
		if(imovel.getVolumeReservatorioSuperior() != null){
			consultarImovelActionForm.setVolumeReservatorioSuperiorDadosCadastrais(Util.formatarMoedaReal(imovel.getVolumeReservatorioSuperior()));	
		
		}else if(imovel.getReservatorioVolumeFaixaSuperior() != null){
			if(imovel.getReservatorioVolumeFaixaSuperior().getVolumeMenorFaixa() != null && imovel.getReservatorioVolumeFaixaSuperior().getVolumeMaiorFaixa() != null){
				consultarImovelActionForm.setVolumeReservatorioSuperiorDadosCadastrais(Util.formatarMoedaReal(imovel.getReservatorioVolumeFaixaSuperior().getVolumeMenorFaixa())+" a "+ Util.formatarMoedaReal(imovel.getReservatorioVolumeFaixaSuperior().getVolumeMaiorFaixa()));
			}
		} else {
			consultarImovelActionForm.setVolumeReservatorioSuperiorDadosCadastrais("");
		}

		if(imovel.getVolumePiscina() != null){
			consultarImovelActionForm.setVolumePiscinaDadosCadastrais(Util.formatarMoedaReal(imovel.getVolumePiscina()));	
		
		}else if(imovel.getPiscinaVolumeFaixa() != null){
			if(imovel.getPiscinaVolumeFaixa().getVolumeMenorFaixa() != null && imovel.getPiscinaVolumeFaixa().getVolumeMaiorFaixa() != null){
				consultarImovelActionForm.setVolumePiscinaDadosCadastrais(Util.formatarMoedaReal(imovel.getPiscinaVolumeFaixa().getVolumeMenorFaixa())+" a "+ Util.formatarMoedaReal(imovel.getPiscinaVolumeFaixa().getVolumeMaiorFaixa()));
			}
		} else {
			consultarImovelActionForm.setVolumePiscinaDadosCadastrais("");
		}
		
		if(imovel.getFonteAbastecimento() != null){
			consultarImovelActionForm.setFonteAbastecimentoDadosCadastrais(imovel.getFonteAbastecimento().getDescricao());	
		} else {
			consultarImovelActionForm.setFonteAbastecimentoDadosCadastrais("");
		}
		
		if(imovel.getPocoTipo() != null){
			consultarImovelActionForm.setPocoTipoDadosCadastrais(imovel.getPocoTipo().getDescricao());
		} else {
			consultarImovelActionForm.setPocoTipoDadosCadastrais("");
		}

		IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
		
		if(integracao.getDistritoOperacional() != null){
			consultarImovelActionForm.setDistritoOperacionalDadosCadastrais(integracao.getDistritoOperacional().getDescricao());
		} else {
			consultarImovelActionForm.setDistritoOperacionalDadosCadastrais("");
		}

		if(integracao.getBacia() != null){
			consultarImovelActionForm.setDivisaoEsgotoDadosCadastrais(integracao.getBacia().getSistemaEsgoto().getDivisaoEsgoto().getDescricao());
		} else {
			consultarImovelActionForm.setDivisaoEsgotoDadosCadastrais("");
		}

		if(imovel.getPavimentoRua() != null){
			consultarImovelActionForm.setPavimentoRuaDadosCadastrais(imovel.getPavimentoRua().getDescricao());
		} else {
			consultarImovelActionForm.setPavimentoRuaDadosCadastrais("");
		}

		if(imovel.getPavimentoCalcada() != null){
			consultarImovelActionForm.setPavimentoCalcadaDadosCadastrais(imovel.getPavimentoCalcada().getDescricao());
		} else {
			consultarImovelActionForm.setPavimentoCalcadaDadosCadastrais("");
		}

		if(imovel.getNumeroIptu() != null){
			consultarImovelActionForm.setNumeroIptuDadosCadastrais(Util.formatarMoedaReal(imovel.getNumeroIptu()));
		} else {
			consultarImovelActionForm.setNumeroIptuDadosCadastrais("");
		}

		if(imovel.getNumeroCelpe() != null){
			consultarImovelActionForm.setNumeroCelpeDadosCadastrais(imovel.getNumeroCelpe().toString());
		} else {
			consultarImovelActionForm.setNumeroCelpeDadosCadastrais("");
		}

		if(imovel.getCoordenadaX() != null){
			consultarImovelActionForm.setCoordenadaXDadosCadastrais(imovel.getCoordenadaX().toString());
		} else {
			consultarImovelActionForm.setCoordenadaXDadosCadastrais("");
		}

		if(imovel.getCoordenadaY() != null){
			consultarImovelActionForm.setCoordenadaYDadosCadastrais(imovel.getCoordenadaY().toString());
		} else {
			consultarImovelActionForm.setCoordenadaYDadosCadastrais("");
		}

		if(imovel.getCadastroOcorrencia() != null){
			consultarImovelActionForm.setCadastroOcorrenciaDadosCadastrais(imovel.getCadastroOcorrencia().getDescricao());
		} else {
			consultarImovelActionForm.setCadastroOcorrenciaDadosCadastrais("");
		}

		if(imovel.getEloAnormalidade() != null){
			consultarImovelActionForm.setEloAnormalidadeDadosCadastrais(imovel.getEloAnormalidade().getDescricao());
		} else {
			consultarImovelActionForm.setEloAnormalidadeDadosCadastrais("");
		}
		
		if(imovel.getIndicadorImovelCondominio() != null){
			if(imovel.getIndicadorImovelCondominio().equals(ConstantesSistema.SIM)){
				consultarImovelActionForm.setIndicadorImovelCondominioDadosCadastrais("SIM");		
			}else{
				consultarImovelActionForm.setIndicadorImovelCondominioDadosCadastrais("N�O");
			}
		} else {
			consultarImovelActionForm.setIndicadorImovelCondominioDadosCadastrais("");
		}
		
		if(imovel.getImovelCondominio() != null){
			consultarImovelActionForm.setImovelCondominioDadosCadastrais(imovel.getImovelCondominio().getId().toString() );
		} else {
			consultarImovelActionForm.setImovelCondominioDadosCadastrais("");
		}

		if(imovel.getImovelPrincipal() != null){
			consultarImovelActionForm.setImovelPrincipalDadosCadastrais(imovel.getImovelPrincipal().getId().toString());
		} else {
			consultarImovelActionForm.setImovelPrincipalDadosCadastrais("");
		}
		
		//Indicador Nivel Esgoto
		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
			SistemaParametro.EMPRESA_CAER)){
			httpServletRequest.setAttribute("apresentarIndicadorNivelInstalacaoEsgoto", true);
			if(imovel.getIndicadorNivelInstalacaoEsgoto() != null){
				if(imovel.getIndicadorNivelInstalacaoEsgoto().shortValue() == 1){
					consultarImovelActionForm.setIndicadorNivelInstalacaoEsgotoDadosCadastrais("SIM");	
				}else{
					consultarImovelActionForm.setIndicadorNivelInstalacaoEsgotoDadosCadastrais("N�O");
				}
			} else {
				consultarImovelActionForm.setIndicadorNivelInstalacaoEsgotoDadosCadastrais("");
			}
		} else { 
			httpServletRequest.removeAttribute("apresentarIndicadorNivelInstalacaoEsgoto");	
		}
	        

		if(imovel.getNumeroPontosUtilizacao() != null){
			consultarImovelActionForm.setNumeroPontosUtilizacaoDadosCadastrais(imovel.getNumeroPontosUtilizacao().toString());
		} else {
			consultarImovelActionForm.setNumeroPontosUtilizacaoDadosCadastrais("");
		}

		if(imovel.getNumeroMorador() != null){
			consultarImovelActionForm.setNumeroMoradoresDadosCadastrais(imovel.getNumeroMorador().toString());	
		} else {
			consultarImovelActionForm.setNumeroMoradoresDadosCadastrais("");
		}

		if(imovel.getIndicadorJardim() != null){
			if(imovel.getIndicadorJardim().shortValue() == 1){
				consultarImovelActionForm.setJardimDadosCadastrais("SIM");	
			}else{
				consultarImovelActionForm.setJardimDadosCadastrais("N�O");
			}
		} else {
			consultarImovelActionForm.setJardimDadosCadastrais("");
		}

		if(imovel.getImovelTipoHabitacao() != null){
			consultarImovelActionForm.setTipoHabitacaoDadosCadastrais(imovel.getImovelTipoHabitacao().getDescricao());	
		} else {
			consultarImovelActionForm.setTipoHabitacaoDadosCadastrais("");
		}

		if(imovel.getImovelTipoPropriedade() != null){
			consultarImovelActionForm.setTipoPropriedadeDadosCadastrais(imovel.getImovelTipoPropriedade().getDescricao());	
		} else {
			consultarImovelActionForm.setTipoPropriedadeDadosCadastrais("");
		}

		if(imovel.getImovelTipoConstrucao() != null){
			consultarImovelActionForm.setTipoConstrucaoDadosCadastrais(imovel.getImovelTipoConstrucao().getDescricao());	
		} else {
			consultarImovelActionForm.setTipoConstrucaoDadosCadastrais("");
		}

		if(imovel.getImovelTipoCobertura() != null){
			consultarImovelActionForm.setTipoCoberturaDadosCadastrais(imovel.getImovelTipoCobertura().getDescricao());	
		} else {
			consultarImovelActionForm.setTipoCoberturaDadosCadastrais("");
		}
		
		//Visualizar Coordenadas
		if(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_CAERN) || sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_SAAE) ){
			sessao.setAttribute("exibirVisualizarCoordenadas", true);
		}else{
			sessao.removeAttribute("exibirVisualizarCoordenadas");
		}
		
		//Indicador Valida CPF/CNPJ
		if(sistemaParametro.getIndicadorValidaCpfCnpj() != null){
			consultarImovelActionForm.setIndicadorValidaCPFCNPJ(String.valueOf(sistemaParametro.getIndicadorValidaCpfCnpj()));
		}
		
		try{
			Municipio municipio = imovel.getLocalidade().getMunicipio();
			if(municipio != null){
				consultarImovelActionForm.setDescricaoMunicipio(municipio.getNome());
				httpServletRequest.setAttribute("desabilitaMunicipioLocalidade", "OK");
			}
		}catch (NullPointerException e) {
			//Localidade n�o possui munic�pio associado
		}
		
		if(imovel.getDataAtualizacaoCadastral()!=null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			consultarImovelActionForm.setDataAtualizacaoCadastral(simpleDateFormat.format(imovel.getDataAtualizacaoCadastral()));
		}else{
			consultarImovelActionForm.setDataAtualizacaoCadastral(null);
		}
	}

	/**
	 * Esse m�todo retorna true se foi necess�rio consultar um novo imovel.
	 * Caso o im�vel seja o mesmo j� consultado anteriormente ele retorna false.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isNovoImovelPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		if(sessao.getAttribute("imovelDadosCadastrais") == null){
			return true;
		}
		
		Imovel imovelAux = (Imovel) sessao.getAttribute("imovelDadosCadastrais");
		
		if( !imovelAux.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()) ){
			return true;
		}

		return false;
	}

	/**
	 * Consulta o Imovel com todas as informa��es necess�rias,
	 * ou simplesmetne pega o Imovel da sess�o caso o mesmo j�
	 * tenha sido pesquisado.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private Imovel obterImovelASerPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		Imovel imovel = null;

		if(sessao.getAttribute("imovelDadosCadastrais") == null){
			imovel = Fachada.getInstancia().consultarImovelDadosCadastrais(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()));

		}else{
			imovel = (Imovel) sessao.getAttribute("imovelDadosCadastrais");
			
			if( !imovel.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()) ){
				imovel = Fachada.getInstancia().consultarImovelDadosCadastrais(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()));
			}
		}
		return imovel;
	}

	/**
	 * Esse m�todo retorna o id do im�vel a ser pesquisado,
	 * verificando se � o id possivelmente informado pelo usu�rio na tela 
	 * de dados cadastrais ou se o id j� informado em uma outra tela.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private String definirIdImovelASerPesquisado(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao, HttpServletRequest httpServletRequest) {
		
		String idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		
		if( isImovelInformadoTelaDadosCadastrais(consultarImovelActionForm)
				&& isImovelInformadoOutraTela(sessao)){
		
			if( !Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorNovo")) ){        				
					return idImovelPrincipalAba;            		
			}

		}else if(isImovelInformadoOutraTela(sessao)){
				return idImovelPrincipalAba;            		
		}

		return consultarImovelActionForm.getIdImovelDadosCadastrais();
	}

	/**
	 * Esse m�todo verifica se j� foi informado um im�vel em outra tela.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoOutraTela(HttpSession sessao) {
		return Util.verificarNaoVazio((String)sessao.getAttribute("idImovelPrincipalAba"));
	}

	/**
	 * Esse m�todo verifica se o im�vel foi informado na tela
	 * de Dados Cadastrais
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoTelaDadosCadastrais(ConsultarImovelActionForm consultarImovelActionForm) {
		return Util.verificarNaoVazio(consultarImovelActionForm.getIdImovelDadosCadastrais());
	}

	/**
	 * Caso o usu�rio tenha clicado no bot�o de limpar
	 * esse m�todo retornar� true.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isLimparDadosTela(HttpServletRequest httpServletRequest) {
		return Util.verificarNaoVazio(httpServletRequest.getParameter("limparForm"));
	}

	/**
	 *Esse m�todo limpa todos os atributos do form
	 *e os atributos na sesss�o 
	 *que s�o usados pelo action e/ou jsp.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private void limparFormSessao(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		sessao.removeAttribute("imovelDadosCadastrais");
		sessao.removeAttribute("imovelClientes");
		sessao.removeAttribute("enderecoImovelDadosCadastrais");
		sessao.removeAttribute("imovelSubcategorias");
		sessao.removeAttribute("idImovelPrincipalAba");
		sessao.removeAttribute("desabilitaMunicipioLocalidade");
		
		consultarImovelActionForm.setIdImovelDadosComplementares(null);
		consultarImovelActionForm.setIdImovelDadosCadastrais(null);
		consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setIdImovelDebitos(null);
		consultarImovelActionForm.setIdImovelPagamentos(null);
		consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
		consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
		consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
		consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
		consultarImovelActionForm.setImovIdAnt(null);
		
		consultarImovelActionForm.setMatriculaImovelDadosCadastrais(null);
		consultarImovelActionForm.setEnderecoImovelDadosCadastrais(null);
		consultarImovelActionForm.setSituacaoAguaDadosCadastrais(null);
		consultarImovelActionForm.setSituacaoEsgotoDadosCadastrais(null);
		consultarImovelActionForm.setImovelPerfilDadosCadastrais(null);
		consultarImovelActionForm.setDespejoDadosCadastrais(null);
		consultarImovelActionForm.setAreaConstruidaDadosDadosCadastrais(null);
		consultarImovelActionForm.setTestadaLoteDadosCadastrais(null);
		consultarImovelActionForm.setVolumeReservatorioInferiorDadosCadastrais(null);
		consultarImovelActionForm.setVolumeReservatorioSuperiorDadosCadastrais(null);
		consultarImovelActionForm.setVolumePiscinaDadosCadastrais(null);
		consultarImovelActionForm.setFonteAbastecimentoDadosCadastrais(null);
		consultarImovelActionForm.setPocoTipoDadosCadastrais(null);
		consultarImovelActionForm.setDistritoOperacionalDadosCadastrais(null);
		consultarImovelActionForm.setPavimentoRuaDadosCadastrais(null);
		consultarImovelActionForm.setPavimentoCalcadaDadosCadastrais(null);
		consultarImovelActionForm.setNumeroIptuDadosCadastrais(null);
		consultarImovelActionForm.setNumeroCelpeDadosCadastrais(null);
		consultarImovelActionForm.setCoordenadaXDadosCadastrais(null);
		consultarImovelActionForm.setCoordenadaYDadosCadastrais(null);
		consultarImovelActionForm.setCadastroOcorrenciaDadosCadastrais(null);
		consultarImovelActionForm.setEloAnormalidadeDadosCadastrais(null);
		consultarImovelActionForm.setIndicadorImovelCondominioDadosCadastrais(null);
		consultarImovelActionForm.setImovelCondominioDadosCadastrais(null);
		consultarImovelActionForm.setImovelPrincipalDadosCadastrais(null);
		consultarImovelActionForm.setNumeroPontosUtilizacaoDadosCadastrais(null);
		consultarImovelActionForm.setNumeroMoradoresDadosCadastrais(null);
		consultarImovelActionForm.setTipoHabitacaoDadosCadastrais(null);
		consultarImovelActionForm.setTipoPropriedadeDadosCadastrais(null);
		consultarImovelActionForm.setTipoConstrucaoDadosCadastrais(null);
		consultarImovelActionForm.setTipoCoberturaDadosCadastrais(null);
		consultarImovelActionForm.setJardimDadosCadastrais(null);
		consultarImovelActionForm.setDivisaoEsgotoDadosCadastrais(null);
		consultarImovelActionForm.setDescricaoMunicipio(null);
		consultarImovelActionForm.setIndicadorValidaCPFCNPJ("2");
		
		consultarImovelActionForm.setIndicadorNivelInstalacaoEsgotoDadosCadastrais("");
		
		consultarImovelActionForm.setDataAtualizacaoCadastral(null);
	}
	
}