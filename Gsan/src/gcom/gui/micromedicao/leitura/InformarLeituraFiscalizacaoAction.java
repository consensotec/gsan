/**
 * 
 */
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
package gcom.gui.micromedicao.leitura;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraFiscalizacao;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author R�mulo Aur�lio
 *
 */
public class InformarLeituraFiscalizacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite informar ou corrigir leitura de fiscaliza��o
	 * 
	 * [UC0100] Informar Leitura de Fiscaliza��o
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 16/05/2007
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

		HttpSession sessao = httpServletRequest.getSession(false);

		InformarLeituraFiscalizacaoActionForm form = (InformarLeituraFiscalizacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		String matricula = (String) sessao.getAttribute("matricula");

		String dataLeituraFiscalizacao = form.getDataLeituraFiscalizacao();

		String matriculaLeituristaFiscalizacao = form
				.getMatriculaLeituristaFiscalizacao();

		LeituraFiscalizacao leituraFiscalizacao = null;

		if (sessao.getAttribute("leituraFiscalizacao") != null) {

			leituraFiscalizacao = (LeituraFiscalizacao) sessao
					.getAttribute("leituraFiscalizacao");

		} else {

			leituraFiscalizacao = new LeituraFiscalizacao();
		}

		/**
		 * [FS0003]-Verificar existencia da matr�cula do funcionario
		 */
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, matriculaLeituristaFiscalizacao));

		Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario,
				Funcionario.class.getName());

		if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

			Funcionario funcionario = (Funcionario) colecaoFuncionario
					.iterator().next();

			funcionario.setId(new Integer(matriculaLeituristaFiscalizacao));

			leituraFiscalizacao.setFuncionario(funcionario);

		} else {

			throw new ActionServletException("atencao.funcionario.inexistente");
		}

		/**
		 * [FS0005]-Validar Quantidade de D�gitos da Leitura
		 */
		Short numeroDigitosLeitura = (Short) sessao
				.getAttribute("numeroDigitosLeitura");

		if (form.getLeituraFiscalizacao().length() > numeroDigitosLeitura) {
			throw new ActionServletException("atencao.quantidade_digitos_leitura_invalido");
		}

		/**
		 * [FS0004]-Validar Data de Leitura
		 */
		String mesAnoReferencia = form.getMesAnoReferencia();

		String mesReferencia = mesAnoReferencia.substring(0, 2);

		String anoReferencia = mesAnoReferencia.substring(3, 7);

		String anoMesReferencia = anoReferencia + mesReferencia;

		Integer anoMesReferenciaInt = new Integer(anoMesReferencia);

		String mes = dataLeituraFiscalizacao.substring(3, 5);

		String ano = dataLeituraFiscalizacao.substring(6, 10);

		String anoMes = ano + mes;

		Integer anoMesInteiro = new Integer(anoMes);

		if (!((Util.compararAnoMesReferencia(anoMesReferenciaInt,
				anoMesInteiro, "=")) || (Util.compararAnoMesReferencia(
				anoMesReferenciaInt + 1, anoMesInteiro, "=")))) {

			throw new ActionServletException(
					"atencao.data_leitura_incompativel_ciclo_faturamento");

		}

		leituraFiscalizacao.setdataLeituraEmpresa(Util
				.converteStringParaDate(dataLeituraFiscalizacao));

		LeituraAnormalidade leituraAnormalidade = new LeituraAnormalidade();

		if(!form
				.getAnormalidadeFiscalizacao().equalsIgnoreCase("-1")){
		leituraAnormalidade.setId(new Integer(form
				.getAnormalidadeFiscalizacao()));
		}else{
			leituraAnormalidade.setId(new Integer(0));
		}
		leituraFiscalizacao.setLeituraAnormalidade(leituraAnormalidade);

		MedicaoHistorico medicaoHistorico = (MedicaoHistorico) sessao
				.getAttribute("medicaoHistorico");

		leituraFiscalizacao.setMedicaoHistorico(medicaoHistorico);

		leituraFiscalizacao.setId(medicaoHistorico.getId());

		if (!form.getLeituraFiscalizacao().equalsIgnoreCase("")) {

			Integer numeroLeituraEmpresa = new Integer(form
					.getLeituraFiscalizacao());

			leituraFiscalizacao.setNumeroLeituraEmpresa(numeroLeituraEmpresa
					.intValue());

		}

		fachada.informarLeituraFiscalizacao(usuarioLogado, leituraFiscalizacao);

		montarPaginaSucesso(httpServletRequest,
				"Leitura de Fiscaliza��o para o im�vel " + matricula
						+ " informada com sucesso.",
				"Informar outra Fiscaliza��o",
				"exibirFiltrarInformarLeituraFiscalizacaoAction.do?menu=sim");

		return retorno;
	}

}
