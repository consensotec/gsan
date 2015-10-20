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

package gcom.relatorio.faturamento.conta;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroQualidadeAguaPadrao;
import gcom.faturamento.QualidadeAguaPadrao;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * [UC0482] Emitir 2� Via de Conta
 * Subfluxo CAERN
 * Emiss�o com atributos de qualidade da �gua:
 * nitrato, coliformes e ph
 * 
 * @author Ricardo Dias e Rejane Souza
 * @date 21/09/2010
 */

@SuppressWarnings("serial")
public class Relatorio2ViaContaCaern extends Relatorio2ViaConta {
		
	public Relatorio2ViaContaCaern(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_2_VIA_CONTA_CAERN);
	}	

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
	
		Fachada fachada = Fachada.getInstancia();

		Collection idsConta = (Collection) getParametro("idsConta");
		boolean cobrarTaxaEmissaoConta = (Boolean) getParametro("cobrarTaxaEmissaoConta");
		Short contaSemCodigoBarras = (Short) getParametro("contaSemCodigoBarras");
		
		Integer idCliente = (Integer) getParametro("idCliente");
		
		Integer idContaHistorico = (Integer)getParametro("idContaHistorico");
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Collection colecaoEmitirContaHelper = new ArrayList();
		if (idContaHistorico == null) {
			colecaoEmitirContaHelper = fachada.emitir2ViaContas(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		} else {
			colecaoEmitirContaHelper = fachada.emitir2ViaContasHistorico(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		}
		

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String idUsuario = "";
		
		Usuario usuario = this.getUsuario();
		
		String nomeUsuario = "";
		
		//**********************************************************************
		// Alterado por: Ivan Sergio
		// Data: 30/04/2009
		// CRC1656
		//**********************************************************************
		if (usuario != null) {
			if (sistemaParametro.getIndicadorImprimeUsuarioSegundaVia().equals(ConstantesSistema.SIM)) {
				idUsuario = usuario.getId().toString();
				nomeUsuario = usuario.getNomeUsuario();
			}
		} else {
			idUsuario = "INTERNET";
			nomeUsuario = "INTERNET";
		}
			
		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map<String, String> parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("nomeEmpresa",nomeEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		parametros.put("nomeUsuario", nomeUsuario);
		
		String empresa = "\n	  	"+nomeEmpresa +" - "+cnpjEmpresa;
		
		/*
		 * Autor: Jonathan Marcos
		 * Data:13/09/2013
		 * UC0482 Emitir 2aViaConta
		 * [SB0005] Obter Qualidade Agua Padrao
		 */
		FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
		Collection colecaoQualidadeAguaPadrao = fachada.pesquisar(filtroQualidadeAguaPadrao, QualidadeAguaPadrao.class.getName());
		
		QualidadeAguaPadrao qualidadeAguaPadrao = new QualidadeAguaPadrao();
		qualidadeAguaPadrao = (QualidadeAguaPadrao)Util.retonarObjetoDeColecao(colecaoQualidadeAguaPadrao);
		
		parametros.put("VMPRecomendacoesturbidez", qualidadeAguaPadrao.getDescricaoPadraoTurbidez());
		parametros.put("VMPRecomendacoesPh", qualidadeAguaPadrao.getDescricaoPadraoPh());
		parametros.put("VMPRecomendacoesColifTotais", qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais());
		parametros.put("VMPRecomendacoesCloroResidualLivre", qualidadeAguaPadrao.getDescricaoPadraoCloro());
		parametros.put("VMPRecomendacoesNitrato", qualidadeAguaPadrao.getDescricaoNitrato());
		// ======================== FIM [SB0005] Obter Qualidade Agua Padrao ===========================================
		
		Collection dadosRelatorio = colecaoEmitirContaHelper;

		Collection<Relatorio2ViaContaBean> colecaoBean = 
			this.inicializarBeanRelatorio(dadosRelatorio, sistemaParametro, empresa, fachada, idCliente);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_2_VIA_CONTA_CAERN, parametros, ds,
				tipoFormatoRelatorio);

		// retorna o relat�rio gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {	
		return 0;
	}	
}
