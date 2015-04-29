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
package gsan.gui.micromedicao;

import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroTelemetriaRetMot;
import gsan.micromedicao.TelemetriaLog;
import gsan.micromedicao.TelemetriaRetMot;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado
 * 
 * 
 * 
 * @author Rodrigo Silveira , Thiago Nascimento e Yara Taciane
 * @since 28/07/2008
 */
public class ProcessarRequisicaoTelemetriaAction extends GcomAction {

	/**
	 * Action que captura as requisi��es vindas da Telametria.
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		ActionForward actionForward = null;
		
		String dadosTelemetria = request.getParameter("dadosTelemetria");
		
		TelemetriaLog telemetriaLog= 
			this.getFachada().processarLeituraViaTelemetria(dadosTelemetria);

		// 2*2008-07-25 15:25:00*2008-07-25
		// 15:00:00;123456;987654;11111;3333;1*2008-07-25
		// 14:00:00;55555;77777;4444;2222;1

		// quantidade de consumidores
		// data/hora de envio
		// data/hora de leitura
		// inscri��o
		// matr�cula
		// leitura
		// n�mero do hidrometro
		// Tipo de Medi��o (1 - �gua | 2 - Po�o)

		/*Vector<DadosMovimentacao> v = new Vector<DadosMovimentacao>();

		String dadosTelemetria = request.getParameter("dadosTelemetria");

		String[] dados = dadosTelemetria.split("\\*");

		int quantidadeConsumidores = 0;

		SimpleDateFormat formatacaoData = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("____________________________________________________________________________________");

		// QuantidadeConsumidores
		quantidadeConsumidores = Integer.parseInt(dados[0]);
		System.out
				.println("quantidadeConsumidores = " + quantidadeConsumidores);

		// Data Envio
		Date dataEnvio = null;
		try {
			dataEnvio = formatacaoData.parse(dados[1]);
			System.out.println("dataEnvio = " + Util.formatarData(dataEnvio));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("........................................................");
		// Apartir da posi��o 2 do vetor.
		// Processar o resto dos dados
		for (int i = 2; i < dados.length; i++) {			

			String medicao = dados[i];

			String[] medicaoDados = medicao.split(";");

			Date dataLeitura = null;
			try {
				dataLeitura = formatacaoData.parse(medicaoDados[0]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			String matricula = medicaoDados[2];
			System.out.println("matricula = " + matricula);

			int leitura = Integer.parseInt(medicaoDados[3]);

			int tipoMedicao = Integer.parseInt(medicaoDados[5]);

			v.add(new DadosMovimentacao(new Integer(matricula), new Integer(
					leitura), new Integer(0), dataLeitura, null, new Byte(
					(byte) 0), new Integer(tipoMedicao)));

		}
		System.out.println("........................................................");
*/
		try {
			//Fachada.getInstancia().atualizarLeituraTelemetria(v);

			URL url = new URL("http", "192.168.1.10", 33128,
					"http://www.smartok.com.br/fechamento/retorno.php");

			System.out.println("conectando : " + url);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);

			OutputStreamWriter writer = new OutputStreamWriter(connection
					.getOutputStream(), "UTF-8");
			
			FiltroTelemetriaRetMot retorno = new FiltroTelemetriaRetMot();
			retorno.adicionarParametro(
				new ParametroSimples(FiltroTelemetriaRetMot.ID,
					telemetriaLog.getTelemetriaRetMot().getId()));
			
			Collection colecaoRetorno = 
				this.getFachada().pesquisar(retorno, TelemetriaRetMot.class.getName());

			TelemetriaRetMot telemetriaRetMot	= 
				(TelemetriaRetMot) Util.retonarObjetoDeColecao(colecaoRetorno);
			
			String msgRetorno = "";
			if(telemetriaRetMot != null){
				msgRetorno = telemetriaRetMot.getDescricaoRetorno();
			}
			
			writer.write("retornoDado=" + dadosTelemetria + " " + msgRetorno);

			writer.flush();

			writer.close();

			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			writer.close();
			reader.close();

			connection.disconnect();

			System.out.println("disconectado");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

		return actionForward;

	}

}