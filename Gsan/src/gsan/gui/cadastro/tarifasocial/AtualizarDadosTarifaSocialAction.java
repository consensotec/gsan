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
package gsan.gui.cadastro.tarifasocial;

import gsan.cadastro.imovel.AreaConstruidaFaixa;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelEconomia;
import gsan.cadastro.sistemaparametro.FiltroSistemaParametro;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.tarifasocial.FiltroRendaTipo;
import gsan.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gsan.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gsan.cadastro.tarifasocial.RendaTipo;
import gsan.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gsan.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.ControladorAlteracaoGcomAction;
import gsan.gui.ControladorAlteracaoGcomActionForm;
import gsan.gui.ControladorGcomAction;
import gsan.util.FachadaException;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author thiago toscano
 */
public class AtualizarDadosTarifaSocialAction extends ControladorAlteracaoGcomAction {
	

	public static final String CASO_USO = AtualizarDadosTarifaSocialAction.class.getSimpleName()  + ".do";
	
	public static final String ACAO_EXIBIR = CASO_USO + "?" + ControladorGcomAction.PARAMETRO_ACAO + "=" + ControladorGcomAction.PARAMETRO_ACAO_EXIBIR;

	public static final String ACAO_PROCESSAR = CASO_USO + "?" + ControladorGcomAction.PARAMETRO_ACAO + "=" + ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR;


	public ActionForward concluir(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		processar(actionMapping, actionForm, request, response);

		return actionMapping.findForward(ControladorGcomAction.FORWARD_CONCLUIR);
	}


	public ActionForward exibirAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtualizarDadosTarifaSocialActionForm form = (AtualizarDadosTarifaSocialActionForm) actionForm;
		
		OTDManterDadosClienteImovelEconomia otd = (OTDManterDadosClienteImovelEconomia) form.getOTD(request);
		// pegando otd corrente e pro objeto escolhido 
		// apresenta o consumo celpe area construida e iptu
		if (otd != null) {
			Collection coll = otd.getOtdClienteEconomia();
			Iterator it = coll.iterator();
			while (it.hasNext()) {
				if (otd.getQuantidadeEconomia() == 1) {
					OTDClienteEconomia clienteEconomia = (OTDClienteEconomia) it.next();
					Imovel obj = (Imovel) clienteEconomia.getImovel();
					if (obj != null){
						if (obj.getAreaConstruida() != null)
							form.setAreaConstruida((obj.getAreaConstruida() + "").replace('.',','));
						if (obj.getAreaConstruidaFaixa() != null)
							form.setAreaConstruidaFaixa(obj.getAreaConstruidaFaixa().getId() + "");
						if (obj.getNumeroIptu() != null && obj.getNumeroIptu().intValue() != 0) 
							form.setNumeroIPTU(obj.getNumeroIptu() + "");
						if (obj.getNumeroCelpe() != null  && obj.getNumeroCelpe().intValue() != 0)
							form.setNumeroContratoCelpe(obj.getNumeroCelpe() + "");
						if (clienteEconomia.getCliente() != null) {
							form.setClienteNome(clienteEconomia.getCliente().getNome());
						} 
						form.setComplementoEndereco(obj.getComplementoEndereco());
					}
				} else {
					OTDClienteEconomia clienteEconomia = (OTDClienteEconomia) it.next();
					//Imovel obj = (Imovel) clienteEconomia.getImovel();
					ImovelEconomia obj = (ImovelEconomia)clienteEconomia.getImovelEconomia();
					
					if (obj != null) {
						if (form.getId().equals(clienteEconomia.getTarifaSocialDadoEconomia().getId()+"")) {
							if (obj.getAreaConstruida() != null)
								form.setAreaConstruida((obj.getAreaConstruida() + "").replace('.',','));
							if (obj.getAreaConstruidaFaixa() != null)
								form.setAreaConstruidaFaixa(obj.getAreaConstruidaFaixa().getId() + "");
							if (obj.getNumeroIptu() != null && obj.getNumeroIptu().intValue() != 0) 
								form.setNumeroIPTU(obj.getNumeroIptu() + "");
							if (obj.getNumeroCelpe() != null && obj.getNumeroCelpe().intValue() != 0) 
								form.setNumeroContratoCelpe(obj.getNumeroCelpe() + "");
							
							if (clienteEconomia.getCliente() != null) {
								form.setClienteNome(clienteEconomia.getCliente().getNome());
							} 
							form.setComplementoEndereco(obj.getComplementoEndereco());
						}
					}
				}
			}
		}

		return null;
	}

	public ActionForward processarAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AtualizarDadosTarifaSocialActionForm form = (AtualizarDadosTarifaSocialActionForm) actionForm;

		MostrarDadosTarifaSocialActionForm f = (MostrarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"MostrarDadosTarifaSocialActionForm");

		OTDManterDadosClienteImovelEconomia otd = (OTDManterDadosClienteImovelEconomia) form.getOTD(request);
		// pegando otd corrente e pro objeto escolhido 
		// apresenta o consumo celpe area construida e iptu
		if (otd != null) {
			Collection coll = otd.getOtdClienteEconomia();
			Iterator it = coll.iterator();
			while (it.hasNext()) {
				if (otd.getQuantidadeEconomia() == 1) {
					OTDClienteEconomia clienteEconomia = (OTDClienteEconomia) it.next();
					Imovel obj = (Imovel) clienteEconomia.getImovel();
					if (obj != null){
						if (!"".equals(form.getAreaConstruida().trim()))
							obj.setAreaConstruida(new BigDecimal(form.getAreaConstruida().replace(',','.')));
						if (!"".equals(form.getAreaConstruidaFaixa().trim())) {
							AreaConstruidaFaixa area = new AreaConstruidaFaixa();
							area.setId(new Integer(form.getAreaConstruidaFaixa()));
							obj.setAreaConstruidaFaixa(area);
						} else {
							obj.setAreaConstruidaFaixa(null);
						}
						if (!"".equals(form.getNumeroIPTU().trim())) {
							obj.setNumeroIptu(new BigDecimal(form.getNumeroIPTU()));
							validarDuplicidadeContratoIPTU(form.getNumeroIPTU(),obj, null,request);
						} else {
							obj.setNumeroIptu(null);
						}
						if (!"".equals(form.getNumeroContratoCelpe().trim())) {
							obj.setNumeroCelpe(new Long(form.getNumeroContratoCelpe()));
							validarDuplicidadeContratoCELPE(form.getNumeroContratoCelpe(),obj, null,request);
						} else {
							obj.setNumeroCelpe(null);
						}
					}
				} else {

					OTDClienteEconomia clienteEconomia = (OTDClienteEconomia) it.next();
					//Imovel obj = (Imovel) clienteEconomia.getImovel();

					ImovelEconomia obj = (ImovelEconomia)clienteEconomia.getImovelEconomia();
						if (obj != null) {
						if (form.getId().equals(clienteEconomia.getTarifaSocialDadoEconomia().getId()+"")) {
							if (!"".equals(form.getAreaConstruida().trim()))
								obj.setAreaConstruida(new BigDecimal(form.getAreaConstruida()));
							if (!"".equals(form.getAreaConstruidaFaixa().trim())) {
								AreaConstruidaFaixa area = new AreaConstruidaFaixa();
								area.setId(new Integer(form.getAreaConstruidaFaixa()));
								obj.setAreaConstruidaFaixa(area);
							} else {
								obj.setAreaConstruidaFaixa(null);
							}

							if (!"".equals(form.getNumeroIPTU().trim())) {
								obj.setNumeroIptu(new BigDecimal(form.getNumeroIPTU()));
								validarDuplicidadeContratoIPTU(form.getNumeroIPTU(),null,obj, request);
							} else {
								obj.setNumeroIptu(null);
							}

							if (!"".equals(form.getNumeroContratoCelpe().trim())) {
								obj.setNumeroCelpe(new Long(form.getNumeroContratoCelpe()));
								validarDuplicidadeContratoCELPE(form.getNumeroContratoCelpe(),null,obj, request);
							} else {
								obj.setNumeroCelpe(null);
							}
							
//							if (!"".equals(form.getConsumoCelpe().trim())) {
//								obj.setcoNumeroCelpe(new Long(form.getNumeroContratoCelpe()));
//							}
	
						}
					}
				}
			}
		}

		Long numeroCelpe = (!"".equals(form.getNumeroContratoCelpe().trim())) ? (new Long(form.getNumeroContratoCelpe())) : null; 
		BigDecimal areaConstruida = (!"".equals(form.getAreaConstruida().trim())) ? (new BigDecimal(form.getAreaConstruida())) : null; 
		BigDecimal numeroIPTU = (!"".equals(form.getNumeroIPTU().trim()))? (new BigDecimal(form.getNumeroIPTU())): null; 
		Integer idImovel = new Integer(f.getIdRegistroAtualizacao());
		String numeroCartaoSocial = (!"".equals(form.getNumeroCartaoProgramaSocial().trim()))?form.getNumeroCartaoProgramaSocial():null;
		String dataValidadeCartaoSocial = (!"".equals(form.getDataValidadeCartao().trim()))? form.getDataValidadeCartao() : null;
		String numeroParcelasCartaoSocial = (!"".equals(form.getNumeroMesesAdesao().trim()))? form.getNumeroMesesAdesao() : null;
		Integer consumoMedio = (!"".equals(form.getConsumoCelpe().trim())) ? new Integer(form.getConsumoCelpe()) : null;
		BigDecimal valorRendaFamiliar = (!"".equals(form.getValorRendaFamiliar().trim()))? new BigDecimal(form.getValorRendaFamiliar()): null;
		String tarifaSocialCartaoTipo = (!"".equals(form.getTipoCartao().trim()) && form.getTipoCartao() != null) ? form.getTipoCartao(): null;
		String tipoRenda = (!"".equals(form.getRendaTipo().trim())) ? form.getRendaTipo() : null;

        Fachada.getInstancia().verificarPreenchimentoInserirDadosTarifaSocial(
        		numeroCelpe, 
        		areaConstruida, 
        		numeroIPTU, 
        		idImovel, 
        		numeroCartaoSocial,
        		dataValidadeCartaoSocial,
        		numeroParcelasCartaoSocial,
        		consumoMedio,
        		valorRendaFamiliar,
        		tarifaSocialCartaoTipo,
        		tipoRenda);        		

		return null;
	}

	public void atualizarObjeto(Object obj, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception {
		
		AtualizarDadosTarifaSocialActionForm form = (AtualizarDadosTarifaSocialActionForm) actionForm;

		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) obj;
		
        OTDManterDadosClienteImovelEconomia otd = (OTDManterDadosClienteImovelEconomia) form.getOTD(request);

        Collection coll = otd.getOtdClienteEconomia();

        if(coll != null) {
        	Iterator it = coll.iterator();
        	while (it.hasNext()) {
        		OTDClienteEconomia clienteEconomia = (OTDClienteEconomia) it.next();
        		if (form.getId().equals(clienteEconomia.getTarifaSocialDadoEconomia().getId()+"")) {
        			clienteEconomia.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
        		}
        	}
        }
//        f.setCollObjeto(novoColl);
	}

	public Object consultarObjetoSistema(String[] chavesPrimarias, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception {
		
		AtualizarDadosTarifaSocialActionForm form = (AtualizarDadosTarifaSocialActionForm) actionForm; 

		//MostrarDadosTarifaSocialActionForm f = (MostrarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"MostrarDadosTarifaSocialActionForm");

        TarifaSocialDadoEconomia tarifaSocialDadoEconomia = null;
        
        OTDManterDadosClienteImovelEconomia otd = (OTDManterDadosClienteImovelEconomia) form.getOTD(request);
        
        Collection coll = otd.getOtdClienteEconomia();

        if(coll != null) {
        	Iterator it = coll.iterator();
        	while (it.hasNext()) {
        		OTDClienteEconomia clienteEconomia = (OTDClienteEconomia) it.next();
//        		TarifaSocialDadoEconomia tarifa = (TarifaSocialDadoEconomia)it.next();
        		if (form.getId().equals(clienteEconomia.getTarifaSocialDadoEconomia().getId()+"")) {
        			tarifaSocialDadoEconomia =  clienteEconomia.getTarifaSocialDadoEconomia();
        			break;
        		}
        	}
        }
        return tarifaSocialDadoEconomia;
	}

	public void carregarColecao(ControladorAlteracaoGcomActionForm actionForm) {
//
//		AtualizarDadosTarifaSocialActionForm form = (AtualizarDadosTarifaSocialActionForm) actionForm;
//
//        Collection collTarifaSocialCartaoTipo = Fachada.getInstancia().pesquisar(new FiltroTarifaSocialCartaoTipo(FiltroTarifaSocialCartaoTipo.DESCRICAO),TarifaSocialCartaoTipo.class.getSimpleName());
//        form.setCollTipoCartao(collTarifaSocialCartaoTipo);
//
//        Collection collAreaConstruida = Fachada.getInstancia().pesquisar(new FiltroAreaConstruidaFaixa(FiltroAreaConstruidaFaixa.MAIOR_FAIXA),AreaConstruidaFaixa.class.getSimpleName());
//        form.setCollAreaConstruida(collAreaConstruida);
//
//        Collection collRendaTipo = Fachada.getInstancia().pesquisar(new FiltroRendaTipo(FiltroRendaTipo.DESCRICAO),RendaTipo.class.getSimpleName());
//        form.setCollRendaTipo(collRendaTipo);
//
	}

	private void validarDuplicidadeContratoCELPE(String numeroCELPE, Imovel imovel, ImovelEconomia imovelEconomia, HttpServletRequest request) throws ActionServletException {
		//MostrarDadosTarifaSocialActionForm form = (MostrarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"MostrarDadosTarifaSocialActionForm");
		
		Integer setorComercial = null;
		
		try {
			if (imovelEconomia != null && imovelEconomia.getImovelSubcategoria() != null && 
					imovelEconomia.getImovelSubcategoria().getComp_id() != null &&
					imovelEconomia.getImovelSubcategoria().getComp_id().getImovel() != null &&
					imovelEconomia.getImovelSubcategoria().getComp_id().getImovel().getSetorComercial() != null &&
					imovelEconomia.getImovelSubcategoria().getComp_id().getImovel().getSetorComercial().getId() != null) {
				imovelEconomia.getImovelSubcategoria().getComp_id().getImovel().getSetorComercial().getId();
			}
			if (imovel != null && imovel.getSetorComercial() != null && imovel.getSetorComercial().getId() != null ) {
				setorComercial = imovel.getSetorComercial().getId();
			}
		} catch (Exception e) {// caso tenha algum nullpoiter nao faz nada}
			
		}
		try {	
			Fachada.getInstancia().verificarExistenciaCELPE(numeroCELPE, setorComercial);
			// se levantar execao e o id do imovel for o imovel manipulado nao faz nada
		} catch (FachadaException e) {
			System.out.println(e.getParametroMensagem());
			MostrarDadosTarifaSocialActionForm f = (MostrarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"MostrarDadosTarifaSocialActionForm");
			if (!f.getIdRegistroAtualizacao().equals(e.getParametroMensagem())) {				
				throw e;
			}
		}
	}

	private void validarDuplicidadeContratoIPTU( String numeroIPTU, Imovel imovel, ImovelEconomia imovelEconomia, HttpServletRequest request) throws ActionServletException {
		//MostrarDadosTarifaSocialActionForm form = (MostrarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"MostrarDadosTarifaSocialActionForm");

		Integer setorComercial = null;
		
		try {
			if (imovelEconomia != null && imovelEconomia.getImovelSubcategoria() != null && 
					imovelEconomia.getImovelSubcategoria().getComp_id() != null &&
					imovelEconomia.getImovelSubcategoria().getComp_id().getImovel() != null &&
					imovelEconomia.getImovelSubcategoria().getComp_id().getImovel().getSetorComercial() != null &&
					imovelEconomia.getImovelSubcategoria().getComp_id().getImovel().getSetorComercial().getId() != null) {
				imovelEconomia.getImovelSubcategoria().getComp_id().getImovel().getSetorComercial().getId();
			}
			if (imovel != null && imovel.getSetorComercial() != null && imovel.getSetorComercial().getId() != null ) {
				setorComercial = imovel.getSetorComercial().getId();
			}
		} catch (Exception e) {// caso tenha algum nullpoiter nao faz nada}
			
		}
		
		try {
			Fachada.getInstancia().verificarExistenciaIPTU(numeroIPTU, setorComercial);
			// se levantar execao e o id do imovel for o imovel manipulado nao faz nada
		} catch (FachadaException e) {
			System.out.println(e.getParametroMensagem());
			MostrarDadosTarifaSocialActionForm f = (MostrarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"MostrarDadosTarifaSocialActionForm");
			if (!f.getIdRegistroAtualizacao().equals(e.getParametroMensagem())) {				
				throw e;
			}
		}
		
	}

	private void validarDuplicidadeCartaoProgramaSocial( HttpServletRequest request, String numeroCartaoProgramaSocial, String  tarifaSocialCartaoTipo) throws ActionServletException {
		
		//MostrarDadosTarifaSocialActionForm f = (MostrarDadosTarifaSocialActionForm) getControladorGcomActionForm(request,"MostrarDadosTarifaSocialActionForm");
		if (! "".equals(numeroCartaoProgramaSocial.trim()) && numeroCartaoProgramaSocial != null && 
				tarifaSocialCartaoTipo != null && !"".equals(tarifaSocialCartaoTipo.trim())) {
			

	        //Objetos que ser�o retornados pelo Hibernate atrav�s do clienteImovel
	        FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
	        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO);
	        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.RENDA_TIPO);
	        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_DADO);
	        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.IMOVEL_ECONOMIA);
	        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_DADO_IMOVEL);
	
	        filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples("numeroCartaoProgramaSocial",numeroCartaoProgramaSocial));
	        filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples("tarifaSocialCartaoTipo",tarifaSocialCartaoTipo));
	
	        Collection coll = Fachada.getInstancia().pesquisarTarifaSocialDadoEconomia(filtroTarifaSocialDadoEconomia);
	        if (coll != null && !coll.isEmpty()) {
	        	Iterator it = coll.iterator();
	        	while (it.hasNext()) {
	        		//TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) it.next();
	        		//TODO comentado devido a remo��o de Tarifa Social Dado
					//if (!f.getIdRegistroAtualizacao().equals(tarifaSocialDadoEconomia.getTarifaSocialDado().getImovel().getId() + "")) {
			        //	throw new ActionServletException("atencao.duplicidade_cartao_programa_social",null,tarifaSocialDadoEconomia.getTarifaSocialDado().getImovel().getId().toString());
					//}
				}
	        }
		}
    }

	/**
	 * Gera o objeto que veio da tela
	 * 
	 */
	public Object gerarObject(ControladorAlteracaoGcomActionForm actionForm, HttpServletRequest request) {
		AtualizarDadosTarifaSocialActionForm form = (AtualizarDadosTarifaSocialActionForm) actionForm;

		validarDuplicidadeCartaoProgramaSocial(request, form.getNumeroCartaoProgramaSocial(), form.getTipoCartao());
		
		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = new TarifaSocialDadoEconomia();

		if (!"".equals(form.getId().trim())) 
			tarifaSocialDadoEconomia.setId(new Integer(form.getId()));
		if (!"".equals(form.getNumeroCartaoProgramaSocial().trim())) 
			tarifaSocialDadoEconomia.setNumeroCartaoProgramaSocial(new Long(form.getNumeroCartaoProgramaSocial()));
		if (!"".equals(form.getDataValidadeCartao().trim())) {
			Date data = gsan.util.Util.converteStringParaDate(form.getDataValidadeCartao());
			if (data == null) {
				throw new ActionServletException("atencao.tarifasocial.data_validade_cartao_invalida");
			}
			if (data.getTime() < new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException("atencao.tarifasocial.data_validade_cartao_maior_que_hoje");
			}
			tarifaSocialDadoEconomia.setDataValidadeCartao(data);
		}
		
		if (!"".equals(form.getNumeroMesesAdesao().trim()))
			tarifaSocialDadoEconomia.setNumeroMesesAdesao(new Short(form.getNumeroMesesAdesao()));

        if (!"".equals(form.getValorRendaFamiliar().trim())) 
			tarifaSocialDadoEconomia.setValorRendaFamiliar(new BigDecimal(form.getValorRendaFamiliar()));

        if (!"".equals(form.getConsumoCelpe().trim()))
			tarifaSocialDadoEconomia.setConsumoCelpe(new Integer(form.getConsumoCelpe()));

        if (!"".equals(form.getTipoCartao().trim()) && form.getTipoCartao() != null) { 

        	if ((!"".equals(form.getTipoCartao().trim()) && 
        			tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo() != null) && 
        			tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo().getId().intValue() != new Integer(form.getTipoCartao()).intValue() || (
        			tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo() == null)) {

            	FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo ();
            	filtroTarifaSocialCartaoTipo.adicionarParametro(new ParametroSimples("id",new Integer(form.getTipoCartao()).intValue()));
            	Collection collCartaoTipo = Fachada.getInstancia().pesquisar(filtroTarifaSocialCartaoTipo,TarifaSocialCartaoTipo.class.getSimpleName());
            	if (collCartaoTipo != null && !collCartaoTipo.isEmpty()) {
            		tarifaSocialDadoEconomia.setTarifaSocialCartaoTipo((TarifaSocialCartaoTipo) collCartaoTipo.iterator().next());
            	}
        	} 
        } else {
        	tarifaSocialDadoEconomia.setTarifaSocialCartaoTipo(null);
        }

        if (!"".equals(form.getRendaTipo().trim())) {
        	FiltroRendaTipo filtroRendaTipo = new FiltroRendaTipo();
        	filtroRendaTipo.adicionarParametro(new ParametroSimples("id",new Integer(form.getRendaTipo())));
        	
        	Collection collRendaTipo = Fachada.getInstancia().pesquisar(filtroRendaTipo,RendaTipo.class.getSimpleName());
        	if (collRendaTipo != null && !collRendaTipo.isEmpty()) {
        		tarifaSocialDadoEconomia.setRendaTipo((RendaTipo) collRendaTipo.iterator().next());
        	}
        } else {
        	tarifaSocialDadoEconomia.setRendaTipo(null);
        }
        return tarifaSocialDadoEconomia;   
	}
	
	public void montarFormulario(Object obj, ControladorAlteracaoGcomActionForm actionForm) {

		AtualizarDadosTarifaSocialActionForm form = (AtualizarDadosTarifaSocialActionForm) actionForm;

		if (obj instanceof TarifaSocialDadoEconomia) {
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) obj; 
	        if (tarifaSocialDadoEconomia != null) {
	        	if (tarifaSocialDadoEconomia.getRendaTipo() != null)
	        		form.setRendaTipo(tarifaSocialDadoEconomia.getRendaTipo().getId().toString()); 

	        	if (tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo() != null)
	        		form.setTipoCartao(tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo().getId().toString()); 
	        }
	        if (tarifaSocialDadoEconomia.getId() != null)  
		        form.setId(tarifaSocialDadoEconomia.getId() + "");
	        if (tarifaSocialDadoEconomia.getNumeroCartaoProgramaSocial() != null)  
		        form.setNumeroCartaoProgramaSocial(tarifaSocialDadoEconomia.getNumeroCartaoProgramaSocial() + "");
	        if (tarifaSocialDadoEconomia.getDataValidadeCartao() != null)  
		        form.setDataValidadeCartao(gsan.util.Util.formatarData(tarifaSocialDadoEconomia.getDataValidadeCartao()));
	        if (tarifaSocialDadoEconomia.getNumeroMesesAdesao() != null)  
		        form.setNumeroMesesAdesao(tarifaSocialDadoEconomia.getNumeroMesesAdesao() + "");
	        if (tarifaSocialDadoEconomia.getConsumoCelpe() != null)  
		        form.setConsumoCelpe(tarifaSocialDadoEconomia.getConsumoCelpe() + "");
	        if (tarifaSocialDadoEconomia.getValorRendaFamiliar() != null)  
		        form.setValorRendaFamiliar((tarifaSocialDadoEconomia.getValorRendaFamiliar() + "").replace('.',','));
	        if (tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo() != null)  
		        form.setTipoCartao(tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo().getId() + "");
	        if (tarifaSocialDadoEconomia.getRendaTipo() != null)  
		        form.setRendaTipo(tarifaSocialDadoEconomia.getRendaTipo().getId() + "");
	        
	        Collection coll = Fachada.getInstancia().pesquisar(new FiltroSistemaParametro(),SistemaParametro.class.getSimpleName());
	        if (coll != null && !coll.isEmpty()) {
	        	form.setSalarioMinimo(((SistemaParametro)coll.iterator().next()).getValorSalarioMinimo().toString());
	        	form.setConsumoEnergiaMaximoTarifaSocial(((SistemaParametro)coll.iterator().next()).getConsumoEnergiaMaximoTarifaSocial().toString());
	        	form.setAreaMaximaTarifaSocial(((SistemaParametro)coll.iterator().next()).getAreaMaximaTarifaSocial().toString());
	        }
		}
	}
}	