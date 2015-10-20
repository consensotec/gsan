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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimentoParcelamentoCliente;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC1349] Gerar Movimento de D�bito Autom�tico de Parcelamento por Cliente
 * 
 * @author Hugo Azevedo
 * @date 27/06/2012
 */
public class ExibirGerarMovimentoDebitoAutomaticoParcClienteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("gerarMovimentoDebitoAutomaticoParcCliente");
		
		
		GerarMovimentoDebitoAutomaticoParcClienteActionForm gerarMovimentoDebitoAutomaticoParcClienteActionForm = 
				(GerarMovimentoDebitoAutomaticoParcClienteActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		

		//[SB0006] - Carregar Data das Parcelas
		//1. O sistema gera as data da parcelas (Data de refer�ncia = Data atual mais um dia �tel. (RN2013087365 - Alterado de 5 p/ 1 dia util) 
		//   Com a  Data de refer�ncia pega os pr�ximos dias 10, 20 e 30 gerando assim tr�s datas para a sele��o do usu�rio)
		
		
		Collection<String> colecaoDataParcelas = new ArrayList<String>();
		
		GregorianCalendar dataParcela = new GregorianCalendar();
		dataParcela.add(Calendar.DAY_OF_MONTH, 1);
		
		
		Date data1 = null;
		Date data2 = null;
		Date data3 = null;
		
		if(dataParcela.get(Calendar.DAY_OF_MONTH) < 10){
			
			dataParcela.set(Calendar.DAY_OF_MONTH, 10);
			data1 = dataParcela.getTime();
			
			dataParcela.set(Calendar.DAY_OF_MONTH, 20);
			data2 = dataParcela.getTime();
			
			//Caso o m�s seja fevereiro, pegar o pr�ximo vencimento v�lido
			if(dataParcela.get(Calendar.MONTH) == Calendar.FEBRUARY){
				dataParcela.add(Calendar.MONTH, 1);
				dataParcela.set(Calendar.DAY_OF_MONTH, 10);
				data3 = dataParcela.getTime();
			}
			
			else{
				dataParcela.set(Calendar.DAY_OF_MONTH, 30);
				data3 = dataParcela.getTime();
			}
		}
		
		else if(10 <= dataParcela.get(Calendar.DAY_OF_MONTH) && 
					  dataParcela.get(Calendar.DAY_OF_MONTH) < 20){
			
			dataParcela.set(Calendar.DAY_OF_MONTH, 20);
			data1 = dataParcela.getTime();
			
			//Caso o m�s seja fevereiro, pegar o pr�ximo vencimento v�lido
			if(dataParcela.get(Calendar.MONTH) == Calendar.FEBRUARY){
				dataParcela.add(Calendar.MONTH, 1);
				dataParcela.set(Calendar.DAY_OF_MONTH, 10);
				data2 = dataParcela.getTime();
				
				dataParcela.set(Calendar.DAY_OF_MONTH, 20);
				data3 = dataParcela.getTime();	
				
			}
			
			else{
				dataParcela.set(Calendar.DAY_OF_MONTH, 30);
				data2 = dataParcela.getTime();
				
				
				dataParcela.add(Calendar.MONTH, 1);
				dataParcela.set(Calendar.DAY_OF_MONTH, 10);
				data3 = dataParcela.getTime();
				
			}	
		}
		
		else{
			
			if(dataParcela.get(Calendar.MONTH) == Calendar.FEBRUARY){
				
				dataParcela.set(Calendar.DAY_OF_MONTH, 28); //Altera��o solicitada por Eduardo Borges para que em caso de ser fevereiro e j� for maior que dia 21, sempre pegue dia 28.
				data1 = dataParcela.getTime();
				
				dataParcela.add(Calendar.MONTH, 1);
				dataParcela.set(Calendar.DAY_OF_MONTH, 10);
				data2 = dataParcela.getTime();
				
				dataParcela.set(Calendar.DAY_OF_MONTH, 20);
				data3 = dataParcela.getTime();
		
			}
			else{
				dataParcela.set(Calendar.DAY_OF_MONTH, 30);
				data1 = dataParcela.getTime();
				
				dataParcela.add(Calendar.MONTH, 1);
				dataParcela.set(Calendar.DAY_OF_MONTH, 10);
				data2 = dataParcela.getTime();
				
				dataParcela.set(Calendar.DAY_OF_MONTH, 20);
				data3 = dataParcela.getTime();				
			}
			
		}
		
		colecaoDataParcelas.add(Util.formatarData(data1));
		colecaoDataParcelas.add(Util.formatarData(data2));
		colecaoDataParcelas.add(Util.formatarData(data3));
		
		sessao.setAttribute("colecaoDataParcelas", colecaoDataParcelas);
		
		
		//[SB0002] - Carregar Lista de Bancos
		if (httpServletRequest.getParameter("criaColecaoBanco") != null
				&& !httpServletRequest.getParameter("criaColecaoBanco").equals(
						"")) {
			
			//[FS0001] - Validar data da parcela
			//Caso o usu�rio n�o selecione data das parcelas, exibir a mensagem "Informe a data das parcelas para a gera��o"
			if(gerarMovimentoDebitoAutomaticoParcClienteActionForm.getIdDataParcela() == null || 
					gerarMovimentoDebitoAutomaticoParcClienteActionForm.getIdDataParcela().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))
				throw new ActionServletException("atencao.informe_data_parcelas");
			
			Date dataParcelas = 
					Util.converteStringParaDate(gerarMovimentoDebitoAutomaticoParcClienteActionForm.getIdDataParcela());
			
			Map<Banco, Collection<DebitoAutomaticoMovimentoParcelamentoCliente>> debitosAutomaticoBancosMap = 
							fachada.pesquisaDebitoAutomaticoMovimentoParcelamentoCliente(dataParcelas);
			
			
			//[FS0002] Verificar lista de bancos vazia
			//Caso a lista de bancos esteja vazia, da data das parcelas, 
			//exibir a mensagem "N�o existem movimentos de d�bito autom�tico a serem gerados para a data das parcelas informada"
			if (debitosAutomaticoBancosMap != null
					&& !debitosAutomaticoBancosMap.isEmpty()) {
				sessao.setAttribute("debitosAutomaticoBancosMap",
						debitosAutomaticoBancosMap);
			} else {
				sessao.removeAttribute("debitosAutomaticoBancosMap");
				
				throw new ActionServletException(
						"atencao.nao_existe_movimentos_deb_automatico_data_parc");
			}
			
		}else {
			sessao.removeAttribute("debitosAutomaticoBancosMap");
		}
		
		if (httpServletRequest.getParameter("limpaColecao") != null
				&& !httpServletRequest.getParameter("limpaColecao").equals("")) {
			sessao.removeAttribute("horaInicioOperacao");
		}
		
		if (httpServletRequest.getParameter("limparListaBancos") != null
				&& !httpServletRequest.getParameter("limparListaBancos").equals("")){
			
			sessao.removeAttribute("debitosAutomaticoBancosMap");
		}
			
		
		return retorno;
	}
	
}
