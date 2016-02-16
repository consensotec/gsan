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
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de 
 * [UC0580]Emitir Protocolo de Documento de Cobran�a do Cronogrma
 * 
 * @author Ana Maria
 * @date 05/10/06
 * 
 */
public class RelatorioEmitirProtocoloDocumentoCobranca extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioEmitirProtocoloDocumentoCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA);
	}
	
	@Deprecated
	public RelatorioEmitirProtocoloDocumentoCobranca() {
		super(null, "");
	}

	private Collection<RelatorioEmitirProtocoloDocumentoCobrancaBean> inicializarBeanRelatorio(
			Collection<ProtocoloDocumentoCobrancaRelatorioHelper> dadosRelatorio) {

		Collection<RelatorioEmitirProtocoloDocumentoCobrancaBean> retorno = new ArrayList();
		
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			ProtocoloDocumentoCobrancaRelatorioHelper helper = 
				(ProtocoloDocumentoCobrancaRelatorioHelper)iterator.next();
			
			String empresa = "";
			
			if (helper.getEmpresa() != null) {
				empresa = helper.getEmpresa();
			}
			
			String idLocalidade = "";
			if(helper.getIdLocalidade() != null){
				idLocalidade = helper.getIdLocalidade().toString();				
			}
			
			String localidade = "";
			if(helper.getLocalidade() != null){
			  localidade = helper.getIdLocalidade()+" - "+helper.getLocalidade();
			}
			
			String setor = "";
			if(helper.getIdSetorComercial() != null){
			  setor = helper.getIdSetorComercial().toString();	
			}			
			
			String quantidadeDocumento = "";
			if(helper.getQuantidade() != null){
				quantidadeDocumento = helper.getQuantidade().toString();	
			}
			
			BigDecimal valorDocumento = new BigDecimal("0.00");
			if(helper.getValorDocumentos() != null){
				valorDocumento = helper.getValorDocumentos();	
			}
			
			Integer seqInicial = null;
			if(helper.getSeqInicial() != null){
				seqInicial = helper.getSeqInicial();	
			}
			
			Integer seqFinal = null;
			if(helper.getSeqFinal() != null){
				seqFinal = helper.getSeqFinal();	
			}
			
			String idGerencia = "";
			if(helper.getIdGerencia() != null){
				idGerencia = helper.getIdGerencia().toString();				
			}
			
			String gerencia = "";
			if(helper.getGerencia() != null){
				gerencia = helper.getIdGerencia()+" - "+helper.getGerencia()+" - "+helper.getNomeGerencia();
			}
			
			String idUnidadeNegocio = "";
			if(helper.getIdUnidadeNegocio() != null){
				idUnidadeNegocio = helper.getIdUnidadeNegocio().toString();
			}
			
			String nomeUnidadeNegocio = "";
			if(helper.getNomeUnidadeNegocio() != null){
				nomeUnidadeNegocio = helper.getNomeUnidadeNegocio();
			}
			
			RelatorioEmitirProtocoloDocumentoCobrancaBean bean = new 
					RelatorioEmitirProtocoloDocumentoCobrancaBean(empresa, idLocalidade, localidade, 
							setor, quantidadeDocumento, valorDocumento, seqInicial, seqFinal, 
							idGerencia, gerencia, idUnidadeNegocio, nomeUnidadeNegocio);
			
			retorno.add(bean);
			
		}
		
		return retorno;
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Collection dadosRelatorio = (Collection)getParametro("protocoloDocumentoCobranca");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String R0000 = (String) getParametro("R0000");
		String grupo = (String)getParametro("grupo");
		String primeiroTitulo = (String)getParametro("primeiroTitulo");
		String acaoCobranca = (String)getParametro("acaoCobranca");
		//String mesAnoCobranca = (String)getParametro("anoMesCobranca");
		//String dataRealizacao = (String)getParametro("dataRealizacao");
		//String horaRealizacao = (String)getParametro("horaRealizacao");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("R0000", R0000);
		
		parametros.put("grupo", grupo);
		
		parametros.put("primeiroTitulo" , primeiroTitulo);
		
		parametros.put("acaoCobranca", acaoCobranca);
		
		//parametros.put("mesAnoCobranca", mesAnoCobranca);
		
		//parametros.put("dataRealizacao", dataRealizacao);
		
		//parametros.put("horaRealizacao", horaRealizacao);

		Collection<RelatorioEmitirProtocoloDocumentoCobrancaBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA,
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

		//retorno = ((Collection) getParametro("idsGuiaDevolucao")).size();

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioEmitirProtocoloDocumentoCobranca", this);
	}
}