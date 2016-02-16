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

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
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
 * classe respons�vel por criar o relat�rio de [UC0484]Imprimir Guia de Devolu��o
 * 
 * @author Ana Maria
 * @date 05/10/06
 * 
 */
public class RelatorioGuiaDevolucao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioGuiaDevolucao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GUIA_DEVOLUCAO);
	}
	
	@Deprecated
	public RelatorioGuiaDevolucao() {
		super(null, "");
	}

	private Collection<RelatorioGuiaDevolucaoBean> inicializarBeanRelatorio(
			Collection<GuiaPagamentoRelatorioHelper> dadosRelatorio) {

		Collection<RelatorioGuiaDevolucaoBean> retorno = new ArrayList();
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			GuiaDevolucaoRelatorioHelper guiaDevolucaoRelatorioHelper = 
				(GuiaDevolucaoRelatorioHelper)iterator.next();
			
           Date dataAtual = new Date();
			
			String ano = "" + Util.getAno(dataAtual);
			
			String sequencialAno = "" + guiaDevolucaoRelatorioHelper.getSequencial() + "/" + ano;
			String valor = "";
			if (guiaDevolucaoRelatorioHelper.getValorDevolucao() != null){
				valor = "R$ "+ Util.formatarMoedaReal(guiaDevolucaoRelatorioHelper.getValorDevolucao());
			}
			
			String registroServicoAtendimento = "" ;
			if (guiaDevolucaoRelatorioHelper.getIdRegistroAtendimento() != null){
				registroServicoAtendimento = "" + guiaDevolucaoRelatorioHelper.getIdRegistroAtendimento();
			}
			
			String matriculaImovel = "";
			if (guiaDevolucaoRelatorioHelper.getIdMatriculaImovel() != null){
				matriculaImovel = "" + guiaDevolucaoRelatorioHelper.getMatriculaFormatada();
			}
			String cliente = null;
			if(guiaDevolucaoRelatorioHelper.getIdCliente() != null){
				cliente = "" + guiaDevolucaoRelatorioHelper.getIdCliente();
			}
			
			String nomeCliente = "";
			if(guiaDevolucaoRelatorioHelper.getNomeCliente() != null){
				nomeCliente = "" + guiaDevolucaoRelatorioHelper.getNomeCliente();
			}
			
			String cpfCnpj = "";
			if(guiaDevolucaoRelatorioHelper.getCpfCliente() != null){
				cpfCnpj = guiaDevolucaoRelatorioHelper.getCpfFormatado();
			}else if (guiaDevolucaoRelatorioHelper.getCnpjCliente() != null){
				cpfCnpj = "" + guiaDevolucaoRelatorioHelper.getCnpjFormatado();
			}
			
			String endereco = guiaDevolucaoRelatorioHelper.getEndereco();
			
			String identidade = "";
			if(guiaDevolucaoRelatorioHelper.getIdentidadeCliente() != null){
				identidade = "" + guiaDevolucaoRelatorioHelper.getRgFormatada();
			}
			
			String valorExtenso = "";
			if(guiaDevolucaoRelatorioHelper.getValorExtenso() != null){
			  valorExtenso = guiaDevolucaoRelatorioHelper.getValorExtenso();
			}
			
			String observacao =guiaDevolucaoRelatorioHelper.getObservacao();
			String dataDigitacao = Util.formatarData(new Date());
			String dataAnalise = Util.formatarData(new Date());
			String dataAutorizacao = Util.formatarData(new Date());
			String nomeUsuario = guiaDevolucaoRelatorioHelper.getUsuario();
			String nomeAnalista = guiaDevolucaoRelatorioHelper.getFuncionarioAnalista();
			String nomeAutorizador = guiaDevolucaoRelatorioHelper.getFuncionarioAutorizador();
			
			String contaDebito = "";
			if(guiaDevolucaoRelatorioHelper.getConta() != null && guiaDevolucaoRelatorioHelper.getAgencia() != null
					&& guiaDevolucaoRelatorioHelper.getDataValidade() != null){
				contaDebito = "CONTA D�BITO: "+guiaDevolucaoRelatorioHelper.getConta()+" - AG: "+guiaDevolucaoRelatorioHelper.getAgencia()
							  +" - N�O PAGAR AP�S "+ Util.formatarData(guiaDevolucaoRelatorioHelper.getDataValidade());	
			}
			
			RelatorioGuiaDevolucaoBean beanCliente = new RelatorioGuiaDevolucaoBean(sequencialAno, valor,
					registroServicoAtendimento,matriculaImovel,cliente,nomeCliente,cpfCnpj,
					endereco,identidade,valorExtenso,observacao,dataDigitacao,dataAnalise,dataAutorizacao,nomeUsuario,
					nomeAnalista,nomeAutorizador,"VIA CLIENTE",contaDebito);
			
			retorno.add(beanCliente);
			
			RelatorioGuiaDevolucaoBean beanArrecadador = new RelatorioGuiaDevolucaoBean(sequencialAno, valor,
					registroServicoAtendimento,matriculaImovel,cliente,nomeCliente,cpfCnpj,
					endereco,identidade,valorExtenso,observacao,dataDigitacao,dataAnalise,dataAutorizacao,nomeUsuario,
					nomeAnalista,nomeAutorizador,"VIA AG. ARRECADADOR", contaDebito);
			
			retorno.add(beanArrecadador);
		
			RelatorioGuiaDevolucaoBean beanCompesa = new RelatorioGuiaDevolucaoBean(sequencialAno, valor,
					registroServicoAtendimento,matriculaImovel,cliente,nomeCliente,cpfCnpj,
					endereco,identidade,valorExtenso,observacao,dataDigitacao,dataAnalise,dataAutorizacao,nomeUsuario,
					nomeAnalista,nomeAutorizador,"VIA " + sistemaParametro.getNomeAbreviadoEmpresa() ,contaDebito);
			
			retorno.add(beanCompesa);
			
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
		
		String[] ids = (String[])getParametro("idsGuiaDevolucao");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagemConta", sistemaParametro.getImagemConta());

		Collection dadosRelatorio = fachada.pesquisarGuiaDevolucaoRelatorio(ids);

		Collection<RelatorioGuiaDevolucaoBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_GUIA_DEVOLUCAO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GUIA_DEVOLUCAO,
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
		AgendadorTarefas.agendarTarefa("RelatorioGuiaDevolucao", this);
	}
}