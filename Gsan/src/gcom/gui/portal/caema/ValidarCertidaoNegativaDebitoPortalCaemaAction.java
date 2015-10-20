package gcom.gui.portal.caema;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cobranca.CertidaoNegativaDebito;
import gcom.cobranca.FiltroCertidaoNegativaDebito;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC1300] Verificar Autenticidade da Certid�o Negativa de D�bito
 * 
 * @author Mariana Victor
 * @date 15/03/2012
 */
public class ValidarCertidaoNegativaDebitoPortalCaemaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("validarDados");

		ValidarCertidaoNegativaDebitoPortalCaemaActionForm form = 
				(ValidarCertidaoNegativaDebitoPortalCaemaActionForm) actionForm;
		
		httpServletRequest.setAttribute("voltarInformacoes", true);
		
		//[FS0001] - Verificar Dados Obrigat�rios
		if (form.getMatriculaImovel() == null 
			|| form.getMatriculaImovel().trim().equals("")) {
			//Valida a Matr�cula do Im�vel
			
			httpServletRequest.setAttribute("matriculaImovelObrigatoria", true);
			
		} else if (form.getNumeroAutenticacao() == null 
			|| form.getNumeroAutenticacao().trim().equals("")) {
			//Valida o N�mero de Autentica��o Eletr�nica
			
			httpServletRequest.setAttribute("numeroAutenticacaoObrigatorio", true);
			
		} else {
			
			// [FS0002] - Verificar Autenticidade da Certid�o Negativa de D�bito
			FiltroCertidaoNegativaDebito filtroCertidaoNegativaDebito = new FiltroCertidaoNegativaDebito();
			filtroCertidaoNegativaDebito.adicionarParametro(
				new ParametroSimples(FiltroCertidaoNegativaDebito.IMOVEL_ID, form.getMatriculaImovel()));
			filtroCertidaoNegativaDebito.adicionarParametro(
				new ParametroSimples(FiltroCertidaoNegativaDebito.NUMERO_AUTENTICACAO, form.getNumeroAutenticacao().toUpperCase()));
			Collection<CertidaoNegativaDebito> colecaoCertidaoNegativaDebito = 
					this.getFachada().pesquisar(filtroCertidaoNegativaDebito, CertidaoNegativaDebito.class.getName());
			
			// Caso n�o exista o n�mero da autentica��o eletr�nica para o im�vel 
			if (colecaoCertidaoNegativaDebito == null
					|| colecaoCertidaoNegativaDebito.isEmpty()) {
				
				httpServletRequest.setAttribute("certidaoNegativaInvalida", true);
				
			} else {
			
				CertidaoNegativaDebito certidaoNegativaDebito = (CertidaoNegativaDebito) 
						Util.retonarObjetoDeColecao(colecaoCertidaoNegativaDebito);
				
				// Caso exista o n�mero da autentica��o eletr�nica para o im�vel 
				//  e a data de vencimento seja menor que a data corrente 
				if (Util.compararData(certidaoNegativaDebito.getDataVencimento(), new Date()) < 0) {
					
					httpServletRequest.setAttribute("certidaoNegativaForaPeriodoValidade", true);

				} else {
					// Caso contr�rio, ou seja, exista o n�mero da autentica��o eletr�nica 
					//  para o im�vel e a data de vencimento seja maior ou igual que a data corrente 
					
					httpServletRequest.setAttribute("certidaoNegativaValida", true);
					
				}
				
			}
		}
		
		String ip = httpServletRequest.getRemoteAddr();
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.VALIDAR_CERTIDAO_NEGATIVA, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		
		return retorno;
	}
	
}
