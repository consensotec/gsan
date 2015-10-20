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
package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Efetua a substitui��o do hidr�metro de acordo com os par�metros informados
 * 
 * @author Ana Maria
 * @date 19/07/2006
 */

public class EfetuarSubstituicaoHidrometroAction extends GcomAction {
	/**
	 * Este caso de uso permite efetuar substitui��o de hidr�metro
	 * 
	 * [UC0364] Efetuar Substitui��o de Hidr�metro
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		EfetuarSubstituicaoHidrometroActionForm efetuarSubstituicaoHidrometroActionForm = 
			(EfetuarSubstituicaoHidrometroActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		 //Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Imovel imovelComLocalidade = new Imovel();
		
		String matriculaImovel = efetuarSubstituicaoHidrometroActionForm.getMatriculaImovel();
		String numeroHidrometro = efetuarSubstituicaoHidrometroActionForm.getNumeroHidrometro();
		String tipoMedicaoAtual = efetuarSubstituicaoHidrometroActionForm.getTipoMedicaoAtual();		
        String situacaoHidrometroSubstituido = efetuarSubstituicaoHidrometroActionForm.getSituacaoHidrometro();
        String medicaoTipo = efetuarSubstituicaoHidrometroActionForm.getTipoMedicao();
        
        String localArmazenagemHidrometro = null;
        String hidrometroExtraviado = (String) sessao.getAttribute("hidrometroExtravido");
        sessao.removeAttribute("hidrometroExtravido");
        
        // caso o hidrometro esteja extraviado, nao pega o local de armazenagem
        if(hidrometroExtraviado == null || !hidrometroExtraviado.equals("sim")){
        localArmazenagemHidrometro = efetuarSubstituicaoHidrometroActionForm.getLocalArmazenagemHidrometro();        	
        }
        
        String numeroLeituraRetiradaHidrometro = efetuarSubstituicaoHidrometroActionForm.getNumeroLeitura();
		String idServicoMotivoNaoCobranca = efetuarSubstituicaoHidrometroActionForm.getMotivoNaoCobranca();
		String valorPercentual = efetuarSubstituicaoHidrometroActionForm.getPercentualCobranca();

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
		
        //Constr�i o filtro para pesquisa da Ordem de Servi�o		
		OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
        
        if(ordemServico.getImovel() != null){
            matriculaImovel =  ordemServico.getImovel().getId().toString();
        }else{
            matriculaImovel =  ordemServico.getRegistroAtendimento().getImovel().getId().toString();
        }
			
		if (numeroHidrometro != null) {

			//Constr�i o filtro para pesquisa do Hidr�metro
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro.adicionarParametro(new ParametroSimples(
					FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
			filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometro.HIDROMETRO_CAPACIDADE);
	        
			//Realiza a pesquisa do Hidr�metro
			Collection colecaoHidrometro = null;
			colecaoHidrometro = fachada.pesquisar(filtroHidrometro,Hidrometro.class.getName());
			
			//verifica se o n�mero do hidr�metro n�o est� cadastrado
			if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
				throw new ActionServletException("atencao.numero_hidrometro_inexistente", null, 
						efetuarSubstituicaoHidrometroActionForm.getNumeroHidrometro());
			}
			
			if(!situacaoHidrometroSubstituido.equals("4") 
					&& localArmazenagemHidrometro.equals("-1")){
				throw new ActionServletException("atencao.campo.informado", null, 
					"Local de Armazenagem");
			}
			
			Iterator iteratorHidrometro = colecaoHidrometro.iterator();
			Hidrometro hidrometro = (Hidrometro) iteratorHidrometro.next();
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL);
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, matriculaImovel));
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			
			if (imovelComLocalidade != null && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null &&
					hidrometro.getHidrometroLocalArmazenagem() != null &&
				!hidrometro.getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
					throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
			}
			
			hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		}

		//Atualiza a entidade com os valores do formul�rio
		efetuarSubstituicaoHidrometroActionForm.setFormValues(hidrometroInstalacaoHistorico);
		
		HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico = 
			(HidrometroInstalacaoHistorico)sessao.getAttribute("hidrometroSubstituicaoHistorico");
		
		Date dataRetirada = Util.converteStringParaDate(efetuarSubstituicaoHidrometroActionForm.getDataRetirada());
		
		hidrometroSubstituicaoHistorico.setDataRetirada(dataRetirada);
		
		if (numeroLeituraRetiradaHidrometro != null && 
			!numeroLeituraRetiradaHidrometro.equalsIgnoreCase("")){
			hidrometroSubstituicaoHistorico.setNumeroLeituraRetirada(new Integer(numeroLeituraRetiradaHidrometro));
		}
		
		hidrometroSubstituicaoHistorico.setUltimaAlteracao(new Date());
				
		BigDecimal valorAtual = new BigDecimal(0);
		
		if(ordemServico != null 
			 && efetuarSubstituicaoHidrometroActionForm.getIdTipoDebito() != null){
				
			 ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
				
			 ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);
			
				if (efetuarSubstituicaoHidrometroActionForm.getValorDebito() != null
						&& !efetuarSubstituicaoHidrometroActionForm.getValorDebito().equals("")  ) {
				    String valorDebito = efetuarSubstituicaoHidrometroActionForm
				     	.getValorDebito().toString().replace(".", "");
				    
				    valorDebito = valorDebito.replace(",", ".");
				    
				    valorAtual = new BigDecimal(valorDebito);

				    ordemServico.setValorAtual(valorAtual);
				}
				
			 if(idServicoMotivoNaoCobranca != null && !idServicoMotivoNaoCobranca.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(idServicoMotivoNaoCobranca));
			 }
			 ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
				
			 if(valorPercentual != null){
				ordemServico.setPercentualCobranca(new BigDecimal(efetuarSubstituicaoHidrometroActionForm.getPercentualCobranca()));
			 }
				
			 ordemServico.setUltimaAlteracao(new Date());				
		}
		
		String qtdParcelas = efetuarSubstituicaoHidrometroActionForm.getQuantidadeParcelas();
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		
		integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
		integracaoComercialHelper.setHidrometroSubstituicaoHistorico(hidrometroSubstituicaoHistorico);
		integracaoComercialHelper.setSituacaoHidrometroSubstituido(situacaoHidrometroSubstituido);
		if(localArmazenagemHidrometro != null){
		integracaoComercialHelper.setLocalArmazenagemHidrometro(new Integer(localArmazenagemHidrometro));
		}
		integracaoComercialHelper.setMatriculaImovel(matriculaImovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		integracaoComercialHelper.setImovel(imovelComLocalidade);
		
		if(efetuarSubstituicaoHidrometroActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			fachada.efetuarSubstituicaoHidrometro(integracaoComercialHelper);
		}else{
//			fachada.validacaoSubstituicaoHidrometro(matriculaImovel,hidrometroInstalacaoHistorico.getHidrometro().getNumero(),hidrometroInstalacaoHistorico.getHidrometro().getHidrometroSituacao().getId().toString());
			fachada.validacaoSubstituicaoHidrometro(matriculaImovel,hidrometroInstalacaoHistorico.getHidrometro().getNumero(), situacaoHidrometroSubstituido, medicaoTipo);
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			
			sessao.setAttribute("integracaoComercialHelper", integracaoComercialHelper);
			
			if(sessao.getAttribute("semMenu") == null){
				retorno = actionMapping.findForward("encerrarOrdemServicoAction");
			}else{
				retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}
			
	   //Inserir na base de dados a instala��o de hidr�metro e a atualiza��o da substitui��o do hidr�metro
/*		fachada.efetuarSubstituicaoHidrometro(hidrometroInstalacaoHistorico,matriculaImovel, hidrometroSubstituicaoHistorico, 
					situacaoHidrometroSubstituido, new Integer(localArmazenagemHidrometro), ordemServico, efetuarSubstituicaoHidrometroActionForm.getVeioEncerrarOS().toString());	
*/		
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){
		// Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Substitui��o de Hidr�metro para "+ tipoMedicaoAtual +
				" no im�vel "+matriculaImovel+ " efetuada com sucesso.", 
				"Efetuar outra Substitui��o de Hidr�metro", "exibirEfetuarSubstituicaoHidrometroAction.do");
		}
        
		sessao.removeAttribute("EfetuarSubstituicaoHidrometroActionForm");
		sessao.removeAttribute("hidrometroSubstituicaoHistorico");

		return retorno;
	}
	
}
