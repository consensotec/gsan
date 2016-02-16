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
package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.bean.ContratoPrestacaoServicoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioContratoPrestacaoServico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioContratoPrestacaoServico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTRATO_PRESTACAO_SERVICO);
	}
	
	@Deprecated
	public RelatorioContratoPrestacaoServico() {
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

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer idImovel = (Integer) getParametro("idImovel");

		Integer idCliente = (Integer) getParametro("idCliente");

		// valor de retorno
		byte[] retorno = null;
		
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioContratoPrestacaoServicoBean relatorioBean = null;
		
		Collection colecaoContratoPrestacaoServicoHelper = fachada.obterDadosContratoPrestacaoServico(idImovel, idCliente);


		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoContratoPrestacaoServicoHelper != null
				&& !colecaoContratoPrestacaoServicoHelper.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoContratoPrestacaoServicoHelperIterator = colecaoContratoPrestacaoServicoHelper
					.iterator();
			
			Date dataCorrente = new Date();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoContratoPrestacaoServicoHelperIterator.hasNext()) {

				ContratoPrestacaoServicoHelper contratoPrestacaoServicoHelper = (ContratoPrestacaoServicoHelper) colecaoContratoPrestacaoServicoHelperIterator
						.next();
				
				// Faz as valida��es dos campos necess�rios e formata a String
				// para a forma como ir� aparecer no relat�rio
				
				// Nome Cliente
				String nomeCliente = "";
				if (contratoPrestacaoServicoHelper.getCliente().getNome() != null) {
					nomeCliente = contratoPrestacaoServicoHelper.getCliente().getNome(); 
				}
				
				// Nome Localidade
				String nomeLocalidade = "";
				if (contratoPrestacaoServicoHelper.getNomeLocalidade() != null) {
					nomeLocalidade = contratoPrestacaoServicoHelper.getNomeLocalidade(); 
				}
				
				// Nome Respons�vel
				String nomeResponsavel = "";
				if (contratoPrestacaoServicoHelper.getClienteResponsavel().getNome() != null) {
					nomeResponsavel = contratoPrestacaoServicoHelper.getClienteResponsavel().getNome(); 
				}
				
				// CPF Respons�vel
				String cpfResponsavel = "";
				if (contratoPrestacaoServicoHelper.getClienteResponsavel().getCpf() != null) {
					cpfResponsavel = contratoPrestacaoServicoHelper.getClienteResponsavel().getCpfFormatado(); 
				}
				
				// RG Respons�vel
				String rgResponsavel = "";
				if (contratoPrestacaoServicoHelper.getClienteResponsavel().getRg() != null) {
					rgResponsavel = contratoPrestacaoServicoHelper.getClienteResponsavel().getRg(); 
				}
				
				// CPF Cliente
				String cpfCliente = "";
				if (contratoPrestacaoServicoHelper.getCliente().getCpf() != null) {
					cpfCliente = contratoPrestacaoServicoHelper.getCliente().getCpfFormatado(); 
				}
				
				// RG Cliente
				String rgCliente = "";
				if (contratoPrestacaoServicoHelper.getCliente().getRg() != null) {
					rgCliente = contratoPrestacaoServicoHelper.getCliente().getRg(); 
				}
				
				// Consumo M�nimo
				String consumoMinimo = "";
				if (contratoPrestacaoServicoHelper.getConsumoMinimo() != null) {
					consumoMinimo = contratoPrestacaoServicoHelper.getConsumoMinimo().toString(); 
				}
				
				String anoCorrente = "" + Util.getAno(dataCorrente);
				
//				 Pega a Data Atual formatada da seguinte forma: dd de m�s(por
				// extenso) de aaaa
				// Ex: 23 de maio de 1985
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale
						.getDefault());
				String dataCorrenteFormatada = df.format(new Date());
				
				
				// Dados da 1� p�gina
				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
						
						// Indicador Pessoa F�sica
						"1",
						
						// N�mero P�gina
						"1",
						
						// N�mero Contrato
						idImovel.toString() + anoCorrente,
						
						// Nome Cliente
						nomeCliente,
						
						// Nome Localidade
						nomeLocalidade,
						
						// Nome Respons�vel
						nomeResponsavel,
						
						// CPF Respons�vel
						cpfResponsavel,
						
						// RG Respons�vel
						rgResponsavel,
						
						// CPF Cliente
						cpfCliente,
						
						// RG Cliente
						rgCliente,
						
						// Endere�o Cliente
						contratoPrestacaoServicoHelper.getEnderecoCliente(),
						
						// Id Im�vel
						idImovel.toString(),
						
						// Endere�o Im�vel
						contratoPrestacaoServicoHelper.getEnderecoImovel(),
						
						// Categoria
						contratoPrestacaoServicoHelper.getCategoria(),
						
						// Consumo M�nimo
						consumoMinimo,
						
						// Data Corrente
						"",
				
						// Munic�pio
						"");
				
				relatorioBeans.add(relatorioBean);
				
				// Dados da 2� p�gina
				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
						
						// Indicador Pessoa F�sica
						"1",
						
						// N�mero P�gina
						"2",
						
						// N�mero Contrato
						"",
						
						// Nome Cliente
						"",
						
						// Nome Unidade Neg�cio
						nomeLocalidade,
						
						// Nome Respons�vel
						"",
						
						// CPF Respons�vel
						"",
						
						// RG Respons�vel
						"",
						
						// CPF Cliente
						"",
						
						// RG Cliente
						"",
						
						// Endere�o Cliente
						"",
						
						// Id Im�vel
						"",
						
						// Endere�o Im�vel
						"",
						
						// Categoria
						"",
						
						// Consumo M�nimo
						"",
						
						// Data Corrente
						dataCorrenteFormatada,
						
						// Munic�pio
						contratoPrestacaoServicoHelper.getNomeMunicipio());

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
				
//				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
//						
//						// Indicador Pessoa F�sica
//						"1",
//						
//						// N�mero P�gina
//						"2");

				// adiciona o bean a cole��o
//				relatorioBeans.add(relatorioBean);
				
//				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
//						
//						// Indicador Pessoa F�sica
//						"2",
//						
//						// N�mero P�gina
//						"1");
//
//				// adiciona o bean a cole��o
//				relatorioBeans.add(relatorioBean);
//				
//				relatorioBean = new RelatorioContratoPrestacaoServicoBean(
//						
//						// Indicador Pessoa F�sica
//						"2",
//						
//						// N�mero P�gina
//						"2");
//
//				// adiciona o bean a cole��o
//				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTRATO_PRESTACAO_SERVICO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_RESOLUCAO_DIRETORIA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterResolucaoDiretoria", this);
	}
}