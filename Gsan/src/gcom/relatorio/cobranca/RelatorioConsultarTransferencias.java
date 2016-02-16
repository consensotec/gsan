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
package gcom.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ConsultarTransferenciasDebitoHelper;
import gcom.cobranca.bean.TransferenciasDebitoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioConsultarTransferencias extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioConsultarTransferencias(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_TRANSFERENCIAS_CONSULTAR);
	}
	
	@Deprecated
	public RelatorioConsultarTransferencias() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {


		Collection<TransferenciasDebitoHelper> colecaoContasTransferidas = (Collection<TransferenciasDebitoHelper>) getParametro("colecaoContasTransferidas");
		Collection<TransferenciasDebitoHelper> colecaoDebitosACobrarTransferidos = (Collection<TransferenciasDebitoHelper>) getParametro("colecaoDebitosACobrarTransferidos");
		Collection<TransferenciasDebitoHelper> colecaoCreditosARealizarTransferidos = (Collection<TransferenciasDebitoHelper>) getParametro("colecaoCreditosARealizarTransferidos");
		Collection<TransferenciasDebitoHelper> colecaoGuiasPagamentoTransferidas = (Collection<TransferenciasDebitoHelper>) getParametro("colecaoGuiasPagamentoTransferidas");
		ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper = (ConsultarTransferenciasDebitoHelper) getParametro("consultarTransferenciasDebitoHelper");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		if (colecaoContasTransferidas != null
				&& !colecaoContasTransferidas.isEmpty()) {
			adicionarContas(colecaoContasTransferidas, relatorioBeans);
		}
		
		if (colecaoDebitosACobrarTransferidos != null
				&& !colecaoDebitosACobrarTransferidos.isEmpty()) {
			adicionarDebitosACobrar(colecaoDebitosACobrarTransferidos, relatorioBeans);
		}
		
		if (colecaoCreditosARealizarTransferidos != null
				&& !colecaoCreditosARealizarTransferidos.isEmpty()) {
			adicionarCreditosARealizar(colecaoCreditosARealizarTransferidos, relatorioBeans);
		}
		
		if (colecaoGuiasPagamentoTransferidas != null
				&& !colecaoGuiasPagamentoTransferidas.isEmpty()) {
			adicionarGuiasPagamento(colecaoGuiasPagamentoTransferidas, relatorioBeans);
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if (consultarTransferenciasDebitoHelper.getIdImovelOrigem() != null) {
			parametros.put("idImovelOrigem", consultarTransferenciasDebitoHelper.getIdImovelOrigem().toString());
		} else {
			parametros.put("idImovelOrigem", "");
		}
		
		if (consultarTransferenciasDebitoHelper.getIdImovelDestino() != null) {
			parametros.put("idImovelDestino", consultarTransferenciasDebitoHelper.getIdImovelDestino().toString());
		} else {
			parametros.put("idImovelDestino", "");
		}
		
		if (consultarTransferenciasDebitoHelper.getDataInicial() != null) {
			parametros.put("periodoTransferencia", Util.formatarData(consultarTransferenciasDebitoHelper.getDataInicial()) + " a " + Util.formatarData(consultarTransferenciasDebitoHelper.getDataInicial()));
		} else {
			parametros.put("periodoTransferencia", "");
		}

		if (consultarTransferenciasDebitoHelper.getIdUsuario() != null) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, consultarTransferenciasDebitoHelper.getIdUsuario()));
			
			Collection colecaoUsuarios = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarios);
			
			parametros.put("usuario", usuario.getNomeUsuario());
		} else {
			parametros.put("usuario", "");
		}
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_TRANSFERENCIAS_CONSULTAR,
				parametros, ds, tipoFormatoRelatorio);
		
		// retorna o relat�rio gerado
		return retorno;
	}

	private void adicionarContas(Collection<TransferenciasDebitoHelper> colecaoContasTransferidas, List relatorioBeans) {
		RelatorioConsultarTransferenciasBean relatorioBean;
		for (TransferenciasDebitoHelper transferenciasDebitoHelper : colecaoContasTransferidas) {
			
			// Faz as valida��es dos campos necess�riose e formata a String
			// para a forma como ir� aparecer no relat�rio
			
			// Refer�ncia da Conta
			String mesAnoConta = "";
			
			if (transferenciasDebitoHelper.getConta() != null && transferenciasDebitoHelper.getConta().getReferencia() > 0) {
				mesAnoConta = Util.formatarAnoMesParaMesAno(transferenciasDebitoHelper.getConta().getReferencia());
			}
			
			// Data Transfer�ncia
			String dataTransferencia = "";
			
			if (transferenciasDebitoHelper.getDataTransferencia() != null) {
				dataTransferencia = Util.formatarData(transferenciasDebitoHelper.getDataTransferencia());
			}
			
			// Usu�rio
			String nomeUsuario = "";
			
			if (transferenciasDebitoHelper.getUsuario() != null) {
				nomeUsuario = transferenciasDebitoHelper.getUsuario().getNomeUsuario();
			}
			
			relatorioBean = new RelatorioConsultarTransferenciasBean(
					
					// Im�vel Origem
					transferenciasDebitoHelper.getImovelOrigem().getId().toString(),
					
					// Im�vel Destino
					transferenciasDebitoHelper.getImovelDestino().getId().toString(),
					
					// Data Transfer�ncia
					dataTransferencia,
					
					// Usu�rio
					nomeUsuario,
					
					// Id da Conta
					transferenciasDebitoHelper.getConta().getId().toString(),
					
					// M�s/Ano da Conta
					mesAnoConta,
					
					// Id do D�bito a Cobrar
					null,
					
					// Id da Guia de Pagamento
					null,
					
					// Tipo do D�bito
					null,
					
					// Id do Cr�dito a Realizar
					null,
					
					// Tipo do Cr�dito
					null);

			// adiciona o bean a cole��o
			relatorioBeans.add(relatorioBean);
		}
	}
	
	private void adicionarDebitosACobrar(Collection<TransferenciasDebitoHelper> colecaoDebitosACobrarTransferidos, List relatorioBeans) {
		RelatorioConsultarTransferenciasBean relatorioBean;
		for (TransferenciasDebitoHelper transferenciasDebitoHelper : colecaoDebitosACobrarTransferidos) {
			
			// Faz as valida��es dos campos necess�riose e formata a String
			// para a forma como ir� aparecer no relat�rio
			
			// Tipo do D�bito
			String tipoDebito = "";
			
			if (transferenciasDebitoHelper.getDebitoACobrar() != null && transferenciasDebitoHelper.getDebitoACobrar().getDebitoTipo() != null) {
				tipoDebito = transferenciasDebitoHelper.getDebitoACobrar().getDebitoTipo().getDescricao();
			}
			
			// Data Transfer�ncia
			String dataTransferencia = "";
			
			if (transferenciasDebitoHelper.getDataTransferencia() != null) {
				dataTransferencia = Util.formatarData(transferenciasDebitoHelper.getDataTransferencia());
			}
			
			// Usu�rio
			String nomeUsuario = "";
			
			if (transferenciasDebitoHelper.getUsuario() != null) {
				nomeUsuario = transferenciasDebitoHelper.getUsuario().getNomeUsuario();
			}
			
			relatorioBean = new RelatorioConsultarTransferenciasBean(
					
					// Im�vel Origem
					transferenciasDebitoHelper.getImovelOrigem().getId().toString(),
					
					// Im�vel Destino
					transferenciasDebitoHelper.getImovelDestino().getId().toString(),
					
					// Data Transfer�ncia
					dataTransferencia,
					
					// Usu�rio
					nomeUsuario,
					
					// Id da Conta
					null,
					
					// M�s/Ano da Conta
					null,
					
					// Id do D�bito a Cobrar
					transferenciasDebitoHelper.getDebitoACobrar().getId().toString(),
					
					// Id da Guia de Pagamento
					null,
					
					// Tipo do D�bito
					tipoDebito,
					
					// Id do Cr�dito a Realizar
					null,
					
					// Tipo do Cr�dito
					null);

			// adiciona o bean a cole��o
			relatorioBeans.add(relatorioBean);
		}
	}
	
	private void adicionarCreditosARealizar(Collection<TransferenciasDebitoHelper> colecaoCreditosARealizarTransferidos, List relatorioBeans) {
		RelatorioConsultarTransferenciasBean relatorioBean;
		for (TransferenciasDebitoHelper transferenciasDebitoHelper : colecaoCreditosARealizarTransferidos) {
			
			// Faz as valida��es dos campos necess�riose e formata a String
			// para a forma como ir� aparecer no relat�rio
			
			// Tipo do Cr�dito
			String tipoCredito = "";
			
			if (transferenciasDebitoHelper.getCreditoARealizar() != null && transferenciasDebitoHelper.getCreditoARealizar().getCreditoTipo() != null) {
				tipoCredito = transferenciasDebitoHelper.getCreditoARealizar().getCreditoTipo().getDescricao();
			}
			
			// Data Transfer�ncia
			String dataTransferencia = "";
			
			if (transferenciasDebitoHelper.getDataTransferencia() != null) {
				dataTransferencia = Util.formatarData(transferenciasDebitoHelper.getDataTransferencia());
			}
			
			// Usu�rio
			String nomeUsuario = "";
			
			if (transferenciasDebitoHelper.getUsuario() != null) {
				nomeUsuario = transferenciasDebitoHelper.getUsuario().getNomeUsuario();
			}
			
			relatorioBean = new RelatorioConsultarTransferenciasBean(
					
					// Im�vel Origem
					transferenciasDebitoHelper.getImovelOrigem().getId().toString(),
					
					// Im�vel Destino
					transferenciasDebitoHelper.getImovelDestino().getId().toString(),
					
					// Data Transfer�ncia
					dataTransferencia,
					
					// Usu�rio
					nomeUsuario,
					
					// Id da Conta
					null,
					
					// M�s/Ano da Conta
					null,
					
					// Id do D�bito a Cobrar
					null,
					
					// Id da Guia de Pagamento
					null,
					
					// Tipo do D�bito
					null,
					
					// Id do Cr�dito a Realizar
					transferenciasDebitoHelper.getCreditoARealizar().getId().toString(),
					
					// Tipo do Cr�dito
					tipoCredito);

			// adiciona o bean a cole��o
			relatorioBeans.add(relatorioBean);
		}
	}
	
	private void adicionarGuiasPagamento(Collection<TransferenciasDebitoHelper> colecaoGuiasPagamentoTransferidas, List relatorioBeans) {
		RelatorioConsultarTransferenciasBean relatorioBean;
		for (TransferenciasDebitoHelper transferenciasDebitoHelper : colecaoGuiasPagamentoTransferidas) {
			
			// Faz as valida��es dos campos necess�riose e formata a String
			// para a forma como ir� aparecer no relat�rio
			
			// Tipo do D�bito
			String tipoDebito = "";
			
			if (transferenciasDebitoHelper.getGuiaPagamento() != null && transferenciasDebitoHelper.getGuiaPagamento().getDebitoTipo() != null) {
				tipoDebito = transferenciasDebitoHelper.getGuiaPagamento().getDebitoTipo().getDescricao();
			}
			
			// Data Transfer�ncia
			String dataTransferencia = "";
			
			if (transferenciasDebitoHelper.getDataTransferencia() != null) {
				dataTransferencia = Util.formatarData(transferenciasDebitoHelper.getDataTransferencia());
			}
			
			// Usu�rio
			String nomeUsuario = "";
			
			if (transferenciasDebitoHelper.getUsuario() != null) {
				nomeUsuario = transferenciasDebitoHelper.getUsuario().getNomeUsuario();
			}
			
			relatorioBean = new RelatorioConsultarTransferenciasBean(
					
					// Im�vel Origem
					transferenciasDebitoHelper.getImovelOrigem().getId().toString(),
					
					// Im�vel Destino
					transferenciasDebitoHelper.getImovelDestino().getId().toString(),
					
					// Data Transfer�ncia
					dataTransferencia,
					
					// Usu�rio
					nomeUsuario,
					
					// Id da Conta
					null,
					
					// M�s/Ano da Conta
					null,
					
					// Id do D�bito a Cobrar
					null,
					
					// Id da Guia de Pagamento
					transferenciasDebitoHelper.getGuiaPagamento().getId().toString(),
					
					// Tipo do D�bito
					tipoDebito,
					
					// Id do Cr�dito a Realizar
					null,
					
					// Tipo do Cr�dito
					null);

			// adiciona o bean a cole��o
			relatorioBeans.add(relatorioBean);
		}
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarTransferencias", this);
	}
}