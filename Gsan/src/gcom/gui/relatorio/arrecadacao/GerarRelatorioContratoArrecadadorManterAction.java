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
package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.FiltrarContratoArrecadadorActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterContratoArrecadador;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corr�a
 * @version 1.0
 */

public class GerarRelatorioContratoArrecadadorManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarContratoArrecadadorActionForm filtrarContratoArrecadadorActionForm = (FiltrarContratoArrecadadorActionForm) actionForm;

		FiltroArrecadadorContrato filtroContratoArrecadador = (FiltroArrecadadorContrato) sessao.getAttribute("filtroArrecadadorContrato");

		ArrecadadorContrato contratoArrecadadorParametros = new ArrecadadorContrato();
		
		// pega os par�metros informados na tela de consulta de d�bitos autom�ticos
		
		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIdArrecadador())){
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID,Integer.parseInt(filtrarContratoArrecadadorActionForm.getIdArrecadador().trim())));

			Arrecadador arrecadador = (Arrecadador) Fachada.getInstancia().pesquisar(filtroArrecadador,Arrecadador.class.getName()).iterator().next();
			contratoArrecadadorParametros.setArrecadador(arrecadador);
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIdCliente())){
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,Integer.parseInt(filtrarContratoArrecadadorActionForm.getIdCliente().trim())));

			Cliente cliente = (Cliente) Fachada.getInstancia().pesquisar(filtroCliente,Cliente.class.getName()).iterator().next();
			contratoArrecadadorParametros.setCliente(cliente);
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIdContaBancariaArrecadador())){
			FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
			filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.ID,Integer.parseInt(filtrarContratoArrecadadorActionForm.getIdContaBancariaArrecadador().trim())));
			filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia");
			filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");

			ContaBancaria contaBancariaDeposito = (ContaBancaria) Fachada.getInstancia().pesquisar(filtroContaBancaria,ContaBancaria.class.getName()).iterator().next();
			contratoArrecadadorParametros.setContaBancariaDepositoArrecadacao(contaBancariaDeposito);
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIdContaBancariaTarifa())){
			FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
			filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.ID,Integer.parseInt(filtrarContratoArrecadadorActionForm.getIdContaBancariaTarifa().trim())));
			filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia");
			filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");

			ContaBancaria contaBancariaTarifa = (ContaBancaria) Fachada.getInstancia().pesquisar(filtroContaBancaria,ContaBancaria.class.getName()).iterator().next();
			contratoArrecadadorParametros.setContaBancariaDepositoTarifa(contaBancariaTarifa);
		}
		
		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getIndicadorCobranca())){
			contratoArrecadadorParametros.setIndicadorCobrancaIss(Short.parseShort(filtrarContratoArrecadadorActionForm.getIndicadorCobranca().trim()));
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getDtInicioContrato())){
			contratoArrecadadorParametros.setDataContratoInicio(Util.converteStringParaDate(filtrarContratoArrecadadorActionForm.getDtInicioContrato()));
		}

		if(Util.verificarNaoVazio(filtrarContratoArrecadadorActionForm.getDtFimContrato())){
			contratoArrecadadorParametros.setDataContratoFim(Util.converteStringParaDate(filtrarContratoArrecadadorActionForm.getDtFimContrato()));
		}
		
		contratoArrecadadorParametros.setDescricaoEmail(filtrarContratoArrecadadorActionForm.getEmailCliente());
		contratoArrecadadorParametros.setNumeroContrato(filtrarContratoArrecadadorActionForm.getNumeroContrato());
		contratoArrecadadorParametros.setCodigoConvenio(filtrarContratoArrecadadorActionForm.getIdConvenio());

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioManterContratoArrecadador relatorioManterContratoArrecadador = new RelatorioManterContratoArrecadador((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterContratoArrecadador.addParametro("filtroArrecadadorContrato",filtroContratoArrecadador);
		relatorioManterContratoArrecadador.addParametro("contratoArrecadadorParametros",contratoArrecadadorParametros);
		relatorioManterContratoArrecadador.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorioManterContratoArrecadador,
					tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

}