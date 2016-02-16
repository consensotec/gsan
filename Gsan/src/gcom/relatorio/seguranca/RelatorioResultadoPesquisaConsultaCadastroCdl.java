/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.relatorio.seguranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.FiltroConsultaCadastroCdl;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
 * @author Rodrigo Cabral
 * @version 1.0
 */

public class RelatorioResultadoPesquisaConsultaCadastroCdl extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResultadoPesquisaConsultaCadastroCdl(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSULTA_CADASTRO_CDL);
	}
	
	@Deprecated
	public RelatorioResultadoPesquisaConsultaCadastroCdl() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroConsultaCadastroCdl filtroConsultaCadastroCdl = (FiltroConsultaCadastroCdl) getParametro("filtroConsultaCadastroCdl");
		String periodoAcessoInicial = (String) getParametro("periodoAcessoInicial");
		String periodoAcessoFinal = (String) getParametro("periodoAcessoFinal");
		
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioResultadoPesquisaConsultaCadastroCdlBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoConsultaCadastroCdl = fachada.pesquisar(filtroConsultaCadastroCdl,
				ConsultaCdl.class.getName());
		
		Integer total = 0;

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoConsultaCadastroCdl != null && !colecaoConsultaCadastroCdl.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator consultaCadastroCdlIterator = colecaoConsultaCadastroCdl.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (consultaCadastroCdlIterator.hasNext()) {

				ConsultaCdl consultaCdl = (ConsultaCdl) consultaCadastroCdlIterator.next();
			
				String numeroImovelCliente = "";
				if (consultaCdl.getNumeroImovelCliente() != null){
					numeroImovelCliente = consultaCdl.getNumeroImovelCliente().toString();
				}
				
				String cpfCliente = "";
				if (consultaCdl.getCpfCliente() != null){
					cpfCliente = Util.formatarCpf(consultaCdl.getCpfCliente());
				}
				
				String cnpjCliente = "";
				if (consultaCdl.getCnpjCliente() != null){
					cnpjCliente = Util.formatarCnpj(consultaCdl.getCnpjCliente());
				}
				
				String acaoOperador = "";
				if (consultaCdl.getCodigoAcaoOperador() != null){
					
					if (consultaCdl.getCodigoAcaoOperador() == 1){
						acaoOperador = "Aceita";
					} else if (consultaCdl.getCodigoAcaoOperador() == 2){
						acaoOperador = "Rejeitada";
					} else {
						acaoOperador = "Dados conferem";
					}
					
				}
				
				
				relatorioBean = new RelatorioResultadoPesquisaConsultaCadastroCdlBean(
						// Login operador
						consultaCdl.getLoginUsuario(),
						// Cod. Cliente GSAN
						consultaCdl.getCodigoCliente().getId().toString(),
						// CPF Cliente
						cpfCliente,
						// CNPJ Cliente
						cnpjCliente,
						// Nome Cliente/Razao Social
						consultaCdl.getNomeCliente(),
						// Op��o Operador
						acaoOperador,
						// Logradouro Cliente
						consultaCdl.getLogradouroCliente(),
						// N�mero 
						numeroImovelCliente,
						// Complemento
						consultaCdl.getComplementoEnderecoCliente(),
						// Bairro
						consultaCdl.getBairroCliente(),
						// Cidade
						consultaCdl.getCidadeCliente(),
						// UF
						consultaCdl.getUf(),
						// Telefone
						consultaCdl.getTelefoneComercialCliente()
						);
				
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
				
				total++;
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if(periodoAcessoInicial != null && !periodoAcessoInicial.equals("")){
	
			parametros.put("periodoAcessoInicial", periodoAcessoInicial);
	
		}
		
		if(periodoAcessoFinal != null && !periodoAcessoFinal.equals("")){
			
			parametros.put("periodoAcessoFinal", periodoAcessoFinal);
	
		}
		
		if(total != null){
		
			parametros.put("total", total);
		
		}
		
		parametros.put("tipo", "R1090" );
		
		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONSULTA_CADASTRO_CDL, parametros,
				ds, tipoFormatoRelatorio);
		
		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResultadoPesquisaConsultaCadastroCdl", this);
	}

}