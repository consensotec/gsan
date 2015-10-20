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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de certidao negativa
 * 
 * @author Bruno Barros
 * @created 29/01/2008
 */
public class RelatorioCertidaoNegativaCliente extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioCertidaoNegativaCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA);
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
	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection<Integer> idsClientes = (Collection<Integer>) getParametro("idsClientes");
		Cliente clienteInformado = (Cliente) getParametro("clienteInformado");
		Usuario usuarioLogado = (Usuario) getParametro("usuarioLogado");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Short icPossuiImovel = (Short) getParametro("icPossuiImovel");		
		Boolean indicadorContaVencidas = (Boolean) getParametro("indicadorContaVencidas");
		Boolean imovelComParcelamento = (Boolean) getParametro("imovelComParcelamento");
		
		Boolean contaDebito = false;
		

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Collection<RelatorioCertidaoNegativaClienteBean> colecao = null; 	
		
		colecao = fachada.pesquisarRelatorioCertidaoNegativaCliente(
			idsClientes,clienteInformado, icPossuiImovel, indicadorContaVencidas, imovelComParcelamento);		
		
		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {		
			
			// adiciona o bean a cole��o
			relatorioBeans.addAll(colecao);		
			
			Iterator itContas = colecao.iterator();
			
			while(itContas.hasNext()){
				RelatorioCertidaoNegativaClienteBean bean = (RelatorioCertidaoNegativaClienteBean) itContas.next();
				if(bean.getItens() != null && bean.getItens().size() > 0){
					contaDebito = true;
					break;
				}
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Date dataAtual = new Date();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA_CLIENTE;
		String textoCertidaoNegativa = "";
		
		if(!imovelComParcelamento && !indicadorContaVencidas){
			textoCertidaoNegativa = "Pelo presente instrumento certificamos, para fins de direito, " +
					"que revendo os nossos controles, n�o encontramos d�bitos referente(s) ao(s) im�vel(eis) acima especificado(s) " +
					"at� a presente data: " + Util.formatarData(dataAtual) + ".";
		}
		
		if(icPossuiImovel.equals(ConstantesSistema.NAO) && (!imovelComParcelamento && !indicadorContaVencidas)){
			textoCertidaoNegativa = "Pelo presente instrumento certificamos, para fins de direito, que revendo os nossos " + 
					"controles, n�o encontramos d�bitos referentes ao cliente acima especificado at� a " +
					"presente data: " + Util.formatarData(dataAtual) + ".";
		}
		
		if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSAMA)) {
			
			parametros.put("textoCertidaoNegativa", textoCertidaoNegativa);
	
			parametros.put("validade", "IMPORTANTE: Qualquer rasura tornar� nulo o efeito desta certid�o, que tem validade de 60 dias.");
			parametros.put("atendente", usuarioLogado.getNomeUsuario());
			parametros.put("nomeEmpresa", "COMPANHIA DE SANEAMENTO AMBIENTAL DO MARANH�O");
			parametros.put("cnpjEmpresa", Util.formatarCnpj( sistemaParametro.getCnpjEmpresa()) );
			parametros.put("inscricaoEstadual", Util.formatarInscricaoEstadualCaema( sistemaParametro.getInscricaoEstadual()) );
			parametros.put("nomeRelatorio", "CERTID�O NEGATIVA DE D�BITOS POR CLIENTE");
			
			if(indicadorContaVencidas){
				parametros.put("nomeRelatorio", "CERTID�O POSITIVA DE D�BITOS POR CLIENTE");
			}
			
			if(imovelComParcelamento && !indicadorContaVencidas){
				parametros.put("nomeRelatorio", "CERTID�O NEGATIVA DE D�BITOS POR CLIENTE - COM EFEITO POSITIVO");
			}
			
			nomeRelatorio = ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA_CLIENTE_CAEMA;
			
		} else {
			
			if(contaDebito){
				throw new ActionServletException("atencao.cliente_com_debitos");
			}
			
			parametros.put("textoCertidaoNegativa", textoCertidaoNegativa
							+ Util.formatarData(dataAtual) + ".");
	
			parametros.put("validade", "IMPORTANTE: Qualquer rasura tornar� nulo o efeito desta certid�o, que tem validade de 60 dias.");
			parametros.put("atendente", usuarioLogado.getNomeUsuario());
			parametros.put("nomeEmpresa", sistemaParametro.getNomeEmpresa());
			parametros.put("cnpjEmpresa", Util.formatarCnpj( sistemaParametro.getCnpjEmpresa()) );
			parametros.put("inscricaoEstadual", sistemaParametro.getInscricaoEstadual());
			parametros.put("nomeRelatorio", "CERTID�O NEGATIVA DE D�BITOS POR CLIENTE");
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(nomeRelatorio,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.CERTIDAO_NEGATIVA,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
//		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioCertidaoNegativaCliente", this);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
}