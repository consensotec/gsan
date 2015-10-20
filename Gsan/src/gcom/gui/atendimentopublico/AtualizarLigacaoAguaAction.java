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
* R�mulo Aur�lio de Melo Souza Filho
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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 *  Action que define o processamento da p�gina de atualizar liga��o de �gua
 *
 * @author 	Rafael Pinto 
 * @date  	18/07/2006
 */
public class AtualizarLigacaoAguaAction extends GcomAction {

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
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarLigacaoAguaActionForm atualizarLigacaoAguaActionForm = 
			(AtualizarLigacaoAguaActionForm) actionForm;
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada fachada = Fachada.getInstancia();
		
		OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
		
		String idImovel  = atualizarLigacaoAguaActionForm.getIdImovel();
		

		if(ordemServico != null){
		
			Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
			
			LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
			
			//Data de Ligacao
			String dataLigacao = atualizarLigacaoAguaActionForm.getDataLigacao();
			if (dataLigacao != null && !dataLigacao.equals("")){
				ligacaoAgua.setDataLigacao(Util.converteStringParaDate(dataLigacao));
			}				
			
			//Diametro Ligacao
			String diametroLigacaoId = atualizarLigacaoAguaActionForm.getDiametroLigacao();
			if (diametroLigacaoId != null && !diametroLigacaoId.equals("") &&
				!diametroLigacaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

				LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro(); 	
				ligacaoAguaDiametro.setId(new Integer(diametroLigacaoId));
				ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);

			}			
			
			//Material Ligacao
			String materialLigacaoId = atualizarLigacaoAguaActionForm.getMaterialLigacao();
			if (materialLigacaoId != null && !materialLigacaoId.equals("") &&
				!materialLigacaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

				LigacaoAguaMaterial ligacaoAguaMaterial = new LigacaoAguaMaterial(); 	
				ligacaoAguaMaterial.setId(new Integer(materialLigacaoId));
				ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterial);

			}
			
			//Perfil Ligacao
			String idPerfilLigacao	= atualizarLigacaoAguaActionForm.getPerfilLigacao();
			if (idPerfilLigacao != null && !idPerfilLigacao.equals("") &&
				!idPerfilLigacao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				//verificar tarifa de consumo associada. 
				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				filtroConsumoTarifa.adicionarParametro(new ParametroSimples (
						FiltroConsumoTarifa.LIGACAO_AGUA_PERFIL,
						idPerfilLigacao));
				
				Collection pesquisa = fachada.pesquisar(
						filtroConsumoTarifa, ConsumoTarifa.class.getName());
				
				if (!pesquisa.isEmpty()){
					Boolean existeTarifaIgual = false;
					Iterator iteratorColecaoConsumoTarifa = pesquisa.iterator();
					Imovel imovelConsulta=null;
					
					if(atualizarLigacaoAguaActionForm.getMatriculaImovel() != null && atualizarLigacaoAguaActionForm.getMatriculaImovel() != ""){
						imovelConsulta= fachada.pesquisarImovel(new Integer(atualizarLigacaoAguaActionForm.getMatriculaImovel()));
					}else{
					    //caso apenas para usuario com permissao especial de atualizarLigacaoAguaSemRA
						if(atualizarLigacaoAguaActionForm.getIdImovel() != null && atualizarLigacaoAguaActionForm.getIdImovel() != ""){
							imovelConsulta= fachada.pesquisarImovel(new Integer(atualizarLigacaoAguaActionForm.getIdImovel()));
						}
					}
					
					while(iteratorColecaoConsumoTarifa.hasNext()){
						ConsumoTarifa consumoTarifa = (ConsumoTarifa) iteratorColecaoConsumoTarifa.next();
						if (consumoTarifa.getLigacaoAguaPerfil() != null){
							if (imovelConsulta != null){
								if (imovelConsulta.getConsumoTarifa().getId().intValue() == consumoTarifa.getId().intValue()){
									existeTarifaIgual = true;
								}
							}
						}
					}
					
					if (!existeTarifaIgual){
						throw new ActionServletException("atencao.tarifa_consumo_perfil_ligacao",null, "Perfil da Liga��o");
					}
					
					LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(new Integer(idPerfilLigacao));
					ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
				} else {

					LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(new Integer(idPerfilLigacao));
					ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
				}
			}
			
			//Ramal Local Instalacao
			String ramalLocaolInstalacaoId	= atualizarLigacaoAguaActionForm.getRamalLocalInstalacao();
			if (ramalLocaolInstalacaoId != null && !ramalLocaolInstalacaoId.equals("") &&
				!ramalLocaolInstalacaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

				RamalLocalInstalacao ramalLocalInstalacao = new RamalLocalInstalacao(); 	
				ramalLocalInstalacao.setId(new Integer(ramalLocaolInstalacaoId));
				ligacaoAgua.setRamalLocalInstalacao(ramalLocalInstalacao);
			}
			
			//Motivo do Corte
			String motivoCorteId	= atualizarLigacaoAguaActionForm.getMotivoCorte();
			if (motivoCorteId != null && !motivoCorteId.equals("") &&
				!motivoCorteId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				MotivoCorte motivoCorte = new MotivoCorte();
				motivoCorte.setId(new Integer(motivoCorteId));
				
				ligacaoAgua.setMotivoCorte(motivoCorte);
				
			}else {
				ligacaoAgua.setMotivoCorte(null);
			}
			
			//Tipo de Corte
			String tipoCorteId 	= atualizarLigacaoAguaActionForm.getTipoCorte();
			if (tipoCorteId != null && !tipoCorteId.equals("") && 
					!tipoCorteId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				CorteTipo corteTipo = new CorteTipo();
				corteTipo.setId(new Integer(tipoCorteId));
	
				ligacaoAgua.setCorteTipo(corteTipo);
			
			}else {
				ligacaoAgua.setCorteTipo(null);
			}
			
			//Numero Selo Corte
			String numSeloCorte = atualizarLigacaoAguaActionForm.getNumSeloCorte();
			if(numSeloCorte != null && !numSeloCorte.equals("") && 
					!numSeloCorte.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				ligacaoAgua.setNumeroSeloCorte(new Integer(numSeloCorte));	
	
			}else{
				ligacaoAgua.setNumeroSeloCorte(null);	
			}
	
			//Motivo Supressao
			String motivoSupressaoId = atualizarLigacaoAguaActionForm.getMotivoSupressao();
			if (motivoSupressaoId != null && !motivoSupressaoId.equals("") && 
					!motivoSupressaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				SupressaoMotivo supressaoMotivo = new SupressaoMotivo();
				supressaoMotivo.setId(new Integer(motivoSupressaoId));
				
				ligacaoAgua.setSupressaoMotivo(supressaoMotivo);
				
			}else {
				ligacaoAgua.setSupressaoMotivo(null);
			}
	
			//Tipo Supressao
			String tipoSupressaoId 	= atualizarLigacaoAguaActionForm.getTipoSupressao();
			if (tipoSupressaoId != null && !tipoSupressaoId.equals("") && 
				!tipoSupressaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				SupressaoTipo supressaoTipo = new SupressaoTipo();
				supressaoTipo.setId(new Integer(tipoSupressaoId));
				
				ligacaoAgua.setSupressaoTipo(supressaoTipo);

			}else {
				ligacaoAgua.setSupressaoTipo(null);
			}
			
			//Selo Supressao
			String numSeloSupressao = atualizarLigacaoAguaActionForm.getNumSeloSupressao();
			if (numSeloSupressao != null && !numSeloSupressao.equals("") && 
				!numSeloSupressao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				ligacaoAgua.setNumeroSeloSupressao(new Integer(numSeloSupressao));

			}else {
				
				ligacaoAgua.setNumeroSeloSupressao(null);
			}
			
			String numeroLacre = atualizarLigacaoAguaActionForm.getNumeroLacre();
			ligacaoAgua.setNumeroLacre(numeroLacre);
			
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = 
				ligacaoAgua.getHidrometroInstalacaoHistorico();
			
			//Leitura Corte
			String leituraCorte = atualizarLigacaoAguaActionForm.getLeituraCorte();
			if (leituraCorte != null && !leituraCorte.equals("") && 
					!leituraCorte.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				hidrometroInstalacaoHistorico.setNumeroLeituraCorte(new Integer(leituraCorte));

			}else if(leituraCorte == null || leituraCorte.equals("") ||
					leituraCorte.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				//hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
			}

			//Leitura Supressao
			String leituraSupressao = atualizarLigacaoAguaActionForm.getLeituraSupressao();
			if (leituraSupressao != null && !leituraSupressao.equals("") && 
					!leituraSupressao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(new Integer(leituraSupressao));

			}else if(leituraSupressao == null || leituraSupressao.equals("") ||
					leituraSupressao.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				//hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
			}
			
			if(hidrometroInstalacaoHistorico != null){
				hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
				ligacaoAgua.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);				
			}

			// Colocado por R�mulo Aur�lio Analista: S�vio Luiz Data:03/09/2008
			// Ligacao Origem
			String idLigacaoOrigem = atualizarLigacaoAguaActionForm
					.getIdLigacaoOrigem();
			if (idLigacaoOrigem != null
					&& !idLigacaoOrigem.equals("")
					&& !idLigacaoOrigem.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
				ligacaoOrigem.setId(new Integer(idLigacaoOrigem));

				ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);

			}
			
			//efetuar liga��o de �gua
			ligacaoAgua.setImovel(imovel);
			
			Date dataConcorrencia = atualizarLigacaoAguaActionForm.getDataConcorrencia();
			ligacaoAgua.setUltimaAlteracao(dataConcorrencia);
			
			fachada.atualizarLigacaoAgua(ligacaoAgua, usuario);
			
			sessao.removeAttribute("imovel");
			
			//Monta a p�gina de sucesso
			montarPaginaSucesso(httpServletRequest,"Atualiza��o da Liga��o de �gua efetuada com Sucesso",
					"Efetuar outra Atualiza��o Liga��o de �gua",
					"exibirAtualizarLigacaoAguaAction.do?menu=sim",
					"exibirAtualizarLigacaoAguaAction.do?idOrdemServico="+ordemServico.getId(),
					"Atualiza��o Liga��o de �gua efetuada");
		}else if(sessao.getAttribute("imovel")!=null){
			
				Imovel imovel = (Imovel) sessao.getAttribute("imovel");
				
				LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
				
				//Data de Ligacao
				String dataLigacao = atualizarLigacaoAguaActionForm.getDataLigacao();
				if (dataLigacao != null && !dataLigacao.equals("")){
					ligacaoAgua.setDataLigacao(Util.converteStringParaDate(dataLigacao));
				}				
				
				//Diametro Ligacao
				String diametroLigacaoId = atualizarLigacaoAguaActionForm.getDiametroLigacao();
				if (diametroLigacaoId != null && !diametroLigacaoId.equals("") &&
					!diametroLigacaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

					LigacaoAguaDiametro ligacaoAguaDiametro = new LigacaoAguaDiametro(); 	
					ligacaoAguaDiametro.setId(new Integer(diametroLigacaoId));
					ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDiametro);

				}			
				
				//Material Ligacao
				String materialLigacaoId = atualizarLigacaoAguaActionForm.getMaterialLigacao();
				if (materialLigacaoId != null && !materialLigacaoId.equals("") &&
					!materialLigacaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

					LigacaoAguaMaterial ligacaoAguaMaterial = new LigacaoAguaMaterial(); 	
					ligacaoAguaMaterial.setId(new Integer(materialLigacaoId));
					ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaMaterial);

				}
				
				//Perfil Ligacao
				String perfilLigacaoId	= atualizarLigacaoAguaActionForm.getPerfilLigacao();
				if (perfilLigacaoId != null && !perfilLigacaoId.equals("") &&
					!perfilLigacaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

					LigacaoAguaPerfil ligacaoAguaPerfil = new LigacaoAguaPerfil(); 	
					ligacaoAguaPerfil.setId(new Integer(perfilLigacaoId));
					ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaPerfil);
				}
				
				//Ramal Local Instalacao
				String ramalLocaolInstalacaoId	= atualizarLigacaoAguaActionForm.getRamalLocalInstalacao();
				if (ramalLocaolInstalacaoId != null && !ramalLocaolInstalacaoId.equals("") &&
					!ramalLocaolInstalacaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

					RamalLocalInstalacao ramalLocalInstalacao = new RamalLocalInstalacao(); 	
					ramalLocalInstalacao.setId(new Integer(ramalLocaolInstalacaoId));
					ligacaoAgua.setRamalLocalInstalacao(ramalLocalInstalacao);
				}
				
				//Motivo do Corte
				String motivoCorteId	= atualizarLigacaoAguaActionForm.getMotivoCorte();
				if (motivoCorteId != null && !motivoCorteId.equals("") &&
					!motivoCorteId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					MotivoCorte motivoCorte = new MotivoCorte();
					motivoCorte.setId(new Integer(motivoCorteId));
					
					ligacaoAgua.setMotivoCorte(motivoCorte);
					
				}else {
					ligacaoAgua.setMotivoCorte(null);
				}
				
				//Tipo de Corte
				String tipoCorteId 	= atualizarLigacaoAguaActionForm.getTipoCorte();
				if (tipoCorteId != null && !tipoCorteId.equals("") && 
						!tipoCorteId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					CorteTipo corteTipo = new CorteTipo();
					corteTipo.setId(new Integer(tipoCorteId));
		
					ligacaoAgua.setCorteTipo(corteTipo);
				
				}else {
					ligacaoAgua.setCorteTipo(null);
				}
				
				//Numero Selo Corte
				String numSeloCorte = atualizarLigacaoAguaActionForm.getNumSeloCorte();
				if(numSeloCorte != null && !numSeloCorte.equals("") && 
						!numSeloCorte.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					ligacaoAgua.setNumeroSeloCorte(new Integer(numSeloCorte));	
		
				}else{
					ligacaoAgua.setNumeroSeloCorte(null);	
				}
		
				//Motivo Supressao
				String motivoSupressaoId = atualizarLigacaoAguaActionForm.getMotivoSupressao();
				if (motivoSupressaoId != null && !motivoSupressaoId.equals("") && 
						!motivoSupressaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					SupressaoMotivo supressaoMotivo = new SupressaoMotivo();
					supressaoMotivo.setId(new Integer(motivoSupressaoId));
					
					ligacaoAgua.setSupressaoMotivo(supressaoMotivo);
					
				}else {
					ligacaoAgua.setSupressaoMotivo(null);
				}
		
				//Tipo Supressao
				String tipoSupressaoId 	= atualizarLigacaoAguaActionForm.getTipoSupressao();
				if (tipoSupressaoId != null && !tipoSupressaoId.equals("") && 
					!tipoSupressaoId.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					SupressaoTipo supressaoTipo = new SupressaoTipo();
					supressaoTipo.setId(new Integer(tipoSupressaoId));
					
					ligacaoAgua.setSupressaoTipo(supressaoTipo);

				}else {
					ligacaoAgua.setSupressaoTipo(null);
				}
				
				//Selo Supressao
				String numSeloSupressao = atualizarLigacaoAguaActionForm.getNumSeloSupressao();
				if (numSeloSupressao != null && !numSeloSupressao.equals("") && 
					!numSeloSupressao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					ligacaoAgua.setNumeroSeloSupressao(new Integer(numSeloSupressao));

				}else {
					
					ligacaoAgua.setNumeroSeloSupressao(null);
				}
				
				
				
				String numeroLacre = atualizarLigacaoAguaActionForm.getNumeroLacre();
				ligacaoAgua.setNumeroLacre(numeroLacre);
				



				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = 
					ligacaoAgua.getHidrometroInstalacaoHistorico();
				
				//Leitura Corte
				String leituraCorte = atualizarLigacaoAguaActionForm.getLeituraCorte();
				if (leituraCorte != null && !leituraCorte.equals("") && 
						!leituraCorte.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					hidrometroInstalacaoHistorico.setNumeroLeituraCorte(new Integer(leituraCorte));

				}else if(leituraCorte == null || leituraCorte.equals("") ||
						leituraCorte.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					//hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
				}

				//Leitura Supressao
				String leituraSupressao = atualizarLigacaoAguaActionForm.getLeituraSupressao();
				if (leituraSupressao != null && !leituraSupressao.equals("") && 
						!leituraSupressao.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(new Integer(leituraSupressao));

				}else if(leituraSupressao == null || leituraSupressao.equals("") ||
						leituraSupressao.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					//hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
				}
				
				if(hidrometroInstalacaoHistorico != null){
					hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
					ligacaoAgua.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);				
				}
				
//				 Colocado por R�mulo Aur�lio Analista: S�vio Luiz Data:03/09/2008
				// Ligacao Origem
				String idLigacaoOrigem = atualizarLigacaoAguaActionForm.getIdLigacaoOrigem();
				if(idLigacaoOrigem !=null && !idLigacaoOrigem.equals("") && 
					!idLigacaoOrigem.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
					ligacaoOrigem.setId(new Integer(idLigacaoOrigem));
					
					ligacaoAgua.setLigacaoOrigem(ligacaoOrigem);
					
				}
				
				
				//efetuar liga��o de �gua
				ligacaoAgua.setImovel(imovel);
				
				Date dataConcorrencia = atualizarLigacaoAguaActionForm.getDataConcorrencia();
				ligacaoAgua.setUltimaAlteracao(dataConcorrencia);
				
				fachada.atualizarLigacaoAgua(ligacaoAgua, usuario);
				
				sessao.removeAttribute("imovel");

				
//				Monta a p�gina de sucesso
				montarPaginaSucesso(httpServletRequest,"Atualiza��o da Liga��o de �gua efetuada com Sucesso",
						"Efetuar outra Atualiza��o Liga��o de �gua",
						"exibirAtualizarLigacaoAguaAction.do?menu=sim",
						"exibirAtualizarLigacaoAguaAction.do?matriculaImovel="+idImovel,
						"Atualiza��o Liga��o de �gua efetuada");
				
				
			} else{
			
			throw new ActionServletException(
					"atencao.matricula.imovel.inexistente", null,
					"IM�VEL INEXISTENTE");
			
		}
		
			
		return retorno;
	}
}
