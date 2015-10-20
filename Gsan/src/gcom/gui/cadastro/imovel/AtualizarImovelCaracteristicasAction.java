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

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class AtualizarImovelCaracteristicasAction extends GcomAction {
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

        ActionForward retorno = actionMapping
                .findForward("gerenciadorProcesso");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //instanciando o ActionForm de InserirImovelLocalidadeActionForm
        DynaValidatorForm inserirImovelCaracteristicasActionForm = (DynaValidatorForm) actionForm;
        Imovel imovelAtualizar = (Imovel) sessao.getAttribute("imovelAtualizacao");
        
        String areaConstruida = (String) inserirImovelCaracteristicasActionForm.get("areaConstruida");
		String faixaAreaConstruida = (String) inserirImovelCaracteristicasActionForm.get("faixaAreaConstruida");
        String reservatorioInferior = (String) inserirImovelCaracteristicasActionForm.get("reservatorioInferior");
        String reservatorioSuperior = (String) inserirImovelCaracteristicasActionForm.get("reservatorioSuperior");
        String piscina = (String) inserirImovelCaracteristicasActionForm.get("piscina");
		String pavimentoCalcada = (String) inserirImovelCaracteristicasActionForm.get("pavimentoCalcada");
		String pavimentoRua = (String) inserirImovelCaracteristicasActionForm.get("pavimentoRua");
		String fonteAbastecimento = (String) inserirImovelCaracteristicasActionForm.get("fonteAbastecimento");
		String situacaoLigacaoAgua = (String) inserirImovelCaracteristicasActionForm.get("situacaoLigacaoAgua");
		String situacaoLigacaoEsgoto = (String) inserirImovelCaracteristicasActionForm.get("situacaoLigacaoEsgoto");
		String idLigacaoEsgotoEsgotamento = (String) inserirImovelCaracteristicasActionForm.get("idLigacaoEsgotoEsgotamento");
		String perfilImovel = (String) inserirImovelCaracteristicasActionForm.get("perfilImovel");
		
		String idSetorComercial = (String) inserirImovelCaracteristicasActionForm.get("idSetorComercial");
		String idQuadra = (String) inserirImovelCaracteristicasActionForm.get("idQuadra");
		
		String indicadorNivelInstalacaoEsgoto = (String) inserirImovelCaracteristicasActionForm.get("indicadorNivelInstalacaoEsgoto");

		String idImovelTipoHabitacao = (String) inserirImovelCaracteristicasActionForm.get("imovelTipoHabitacao");
		String idImovelTipoPropriedade = (String) inserirImovelCaracteristicasActionForm.get("imovelTipoPropriedade");
		String idImovelTipoConstrucao = (String) inserirImovelCaracteristicasActionForm.get("imovelTipoConstrucao");
		String idImovelTipoCobertura = (String) inserirImovelCaracteristicasActionForm.get("imovelTipoCobertura");

        
		ImovelAbaCaracteristicasHelper helperCaracteristica = new ImovelAbaCaracteristicasHelper();

		helperCaracteristica.setImovelAtualizar(imovelAtualizar);
		helperCaracteristica.setAreaConstruida(areaConstruida);
		helperCaracteristica.setIdAreaConstruidaFaixa(faixaAreaConstruida);
		helperCaracteristica.setVolumeReservatorioInferior(reservatorioInferior);
		helperCaracteristica.setVolumeReservatorioSuperior(reservatorioSuperior);
		helperCaracteristica.setVolumePiscinaMovel(piscina);
		helperCaracteristica.setIdPavimentoCalcada(pavimentoCalcada);
		helperCaracteristica.setIdPavimentoRua(pavimentoRua);
		helperCaracteristica.setIdFonteAbastecimento(fonteAbastecimento);
		helperCaracteristica.setIdLigacaoAguaSituacao(situacaoLigacaoAgua);
		helperCaracteristica.setIdLigacaoEsgotoSituacao(situacaoLigacaoEsgoto);
		helperCaracteristica.setIdLigacaoEsgotoEsgotamento(idLigacaoEsgotoEsgotamento);
		helperCaracteristica.setIdImovelPerfil(perfilImovel);
		helperCaracteristica.setIdSetorComercial(idSetorComercial);
		helperCaracteristica.setIdQuadra(idQuadra);
		helperCaracteristica.setIdNivelInstalacaoEsgoto(indicadorNivelInstalacaoEsgoto);

		helperCaracteristica.setIdImovelTipoHabitacao(idImovelTipoHabitacao);
		helperCaracteristica.setIdImovelTipoPropriedade(idImovelTipoPropriedade);
		helperCaracteristica.setIdImovelTipoConstrucao(idImovelTipoConstrucao);
		helperCaracteristica.setIdImovelTipoCobertura(idImovelTipoCobertura);
		
		ImovelAbaCaracteristicasRetornoHelper resultado = 
			this.getFachada().validarImovelAbaCaracteristicas(helperCaracteristica);

		if (resultado.getAreaConstruidaFaixa() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaAreaConstruida", resultado.getAreaConstruidaFaixa().getId() + "");
		}
		if (resultado.getReservatorioVolumeFaixaInferior() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaReservatorioInferior", resultado.getReservatorioVolumeFaixaInferior().getId() + "");
		}
		if (resultado.getReservatorioVolumeFaixaSuperior() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaResevatorioSuperior", resultado.getReservatorioVolumeFaixaSuperior().getId() + "");
		}
		if (resultado.getPiscinaVolumeFaixa() != null) {
			inserirImovelCaracteristicasActionForm.set(
					"faixaPiscina", resultado.getPiscinaVolumeFaixa().getId() + "");
		} 


        return retorno;
    }

}
