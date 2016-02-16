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
package gcom.relatorio.mobile.execucaoOrdemServico;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.batch.Relatorio;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.mobile.execucaoordemservico.bean.GerarArquivoTxtSmartphoneHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioArquivoTextoOrdensServicoSmartphone extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioArquivoTextoOrdensServicoSmartphone(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ARQUIVO_TEXTO_ORDENS_SERVICO_SMARTPHONE);				
	}	

	@Override
	public Object executar() throws TarefaException {
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		Fachada fachada = Fachada.getInstancia();
		byte[] retorno = null;
		
		Integer idComando = (Integer) getParametro("idComando");
		Integer tipoFiltro = (Integer) getParametro("tipoFiltro");
		Integer idGrupoCobranca = (Integer) getParametro("idGrupoCobranca");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		String referenciaGrupoCobranca = (String) getParametro("referencia");
		String referenciaInicial = (String) getParametro("referenciaInicial");
		String referenciaFinal = (String) getParametro("referenciaFinal");
		Integer idTipoServico = (Integer) getParametro("idTipoServico");
		Integer tipoRelatorio =  Integer.parseInt((String) getParametro("escolhaTipoRelatorio"));
		Usuario usuarioLogado = (Usuario) getParametro("usuarioLogado");
		List<GerarArquivoTxtSmartphoneHelper> listBean = (List<GerarArquivoTxtSmartphoneHelper>) getParametro("list");
		

		Empresa empresa = fachada.pesquisar(Empresa.class, idEmpresa);
		ServicoTipo servicoTipo = fachada.pesquisar(ServicoTipo.class, idTipoServico);
		//Localidade localidade = fachada.pesquisar(Localidade.class, idLocalidade);

		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if(Util.isVazioOrNulo(listBean)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();
		if(tipoFiltro == 1){
			CobrancaGrupo grupoCobranca = fachada.pesquisar(CobrancaGrupo.class, idGrupoCobranca);
			// Grupo de cobranca		
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("idGrupoCobranca", grupoCobranca.getDescricao());
			parametros.put("idEmpresa", empresa.getDescricao());
			parametros.put("referencia", referenciaGrupoCobranca);
			parametros.put("usuario", usuarioLogado.getNomeUsuario() );
			parametros.put("idTipoServico", servicoTipo.getDescricao());
		}else{
		// Cobranca eventual
			CobrancaAcaoAtividadeComando atividadeComando = fachada.pesquisar(CobrancaAcaoAtividadeComando.class, idComando);
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("idEmpresa", empresa.getDescricao());
			parametros.put("usuario", usuarioLogado.getNomeUsuario() );
			parametros.put("referenciaInicial", referenciaInicial);
			parametros.put("referenciaFinal", referenciaFinal);
			parametros.put("idTipoServico", servicoTipo.getDescricao());
			parametros.put("idComando", atividadeComando.getDescricaoTitulo());
		}
		
		List relatorioBeans = new ArrayList();
		
		relatorioBeans = (ArrayList) listBean;

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ARQUIVO_TEXTO_ORDENS_SERVICO_SMARTPHONE,
				parametros, ds, tipoRelatorio);

		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_ARQUIVO_TEXTO_ORDENS_SERVICO_SMARTPHONE,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		
		return retorno;
	
	}

	@Override
	public void agendarTarefaBatch() {
		
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	
	
}
