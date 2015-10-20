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
package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
 * @author Rafael Corr�a
 * @version 1.0
 */

public class RelatorioManterContratoArrecadador extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioManterContratoArrecadador(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_DEBITO_AUTOMATICO);
	}
	
	public Object executar() throws TarefaException {

		FiltroArrecadadorContrato filtroContratoArrecadador = (FiltroArrecadadorContrato) getParametro("filtroArrecadadorContrato");
		ArrecadadorContrato contratoArrecadadorParametros = (ArrecadadorContrato) getParametro("contratoArrecadadorParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = null;

		List relatorioBeans = new ArrayList();

		RelatorioManterContratoArrecadadorBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroContratoArrecadador.setConsultaSemLimites(true);

		Collection<ArrecadadorContrato> colecaoContratosArrecadadores = fachada.pesquisar(filtroContratoArrecadador,ArrecadadorContrato.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoContratosArrecadadores != null && !colecaoContratosArrecadadores.isEmpty()) {

			// la�o para criar a cole��o de par�metros da analise
			for (ArrecadadorContrato contratoArrecadador : colecaoContratosArrecadadores) {

				String numeroContrato = contratoArrecadador.getNumeroContrato();
				String idArrecadador= "";
				String idCliente = "";
				String nomeCliente = "";
				
				if(contratoArrecadador.getArrecadador()!=null){
					idArrecadador = contratoArrecadador.getArrecadador().getId().toString();
				}
				
				if(contratoArrecadador.getCliente()!=null){
					idCliente = contratoArrecadador.getCliente().getId().toString();
					nomeCliente = contratoArrecadador.getCliente().getNome();
				}
				
				relatorioBean = new RelatorioManterContratoArrecadadorBean(numeroContrato,idArrecadador,idCliente,nomeCliente); 
						
				relatorioBeans.add(relatorioBean);				
			}			
		}

		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if(contratoArrecadadorParametros.getArrecadador()!=null){
			parametros.put("arrecadador",contratoArrecadadorParametros.getArrecadador().getId().toString());
		}

		if(contratoArrecadadorParametros.getCliente()!=null){
			parametros.put("cliente",contratoArrecadadorParametros.getCliente().getId() + " - " + contratoArrecadadorParametros.getCliente().getNomeAbreviado());
		}

		if(contratoArrecadadorParametros.getContaBancariaDepositoArrecadacao()!=null){
			ContaBancaria contaDeposito = contratoArrecadadorParametros.getContaBancariaDepositoArrecadacao();
			parametros.put("contaBancariaDeposito",contaDeposito.getAgencia().getBanco().getId() + " - " 
					+ contaDeposito.getAgencia().getCodigoAgencia() + " - " + contaDeposito.getNumeroConta());			
		}

		if(contratoArrecadadorParametros.getContaBancariaDepositoTarifa()!=null){
			ContaBancaria contaTarifa = contratoArrecadadorParametros.getContaBancariaDepositoTarifa();
			parametros.put("contaBancariaTarifa",contaTarifa.getAgencia().getBanco().getId() + " - " 
					+ contaTarifa.getAgencia().getCodigoAgencia() + " - " + contaTarifa.getNumeroConta());			
		}
		
		if(contratoArrecadadorParametros.getIndicadorCobrancaIss()!=null && contratoArrecadadorParametros.getIndicadorCobrancaIss() > 0){
			parametros.put("indicadorCobrancaIss",contratoArrecadadorParametros.getIndicadorCobrancaIss().toString());
		}

		if(contratoArrecadadorParametros.getDataContratoInicio()!=null){
			parametros.put("dataContratoInicio",contratoArrecadadorParametros.getDataContratoInicio());
		}

		if(contratoArrecadadorParametros.getDataContratoFim() !=null){
			parametros.put("dataContratoFim",contratoArrecadadorParametros.getDataContratoFim());
		}

		if(contratoArrecadadorParametros.getDescricaoEmail()!=null){
			parametros.put("email",contratoArrecadadorParametros.getDescricaoEmail());
		}
		if(contratoArrecadadorParametros.getNumeroContrato()!=null){
			parametros.put("numeroContrato",contratoArrecadadorParametros.getNumeroContrato());
		}
		if(contratoArrecadadorParametros.getCodigoConvenio()!=null){
			parametros.put("codigoConvenio",contratoArrecadadorParametros.getCodigoConvenio());
		}

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_CONTRATO_ARRECADADOR, parametros,ds, tipoFormatoRelatorio);

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterContratoArrecadador", this);
	}

}