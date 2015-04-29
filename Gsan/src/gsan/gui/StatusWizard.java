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
package gsan.gui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * < <Descri��o da Classe>>
 * 
 * @author rodrigo
 */
public class StatusWizard implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Map relacaoNumeroPaginaCaminho = new TreeMap();

	private String caminhoActionPrincipalWizard;

	private String caminhoActionConclusao;

	private String caminhoActionCancelamento;

	private String caminhoActionVoltarFiltro;

	private String caminhoActionDesfazer;
	
	// Criado por que existe processos de aba onde a pesquisa do objeto n�o esta no action principal e
	// a pesquisa foi implementada no action da 1� aba (Ex.: ExibirAtualizarComandoAtividadeFaturamentoAction).
	// Tamb�m quando � necess�rio passar par�metros espec�ficos do caso de uso.
	// Rafael Santos e Roberta Costa
	private String caminhoActionDesfazerVoltarAvancar;
	
	// Criado para as abas com objetivo de inser��o na base de dados as quais o bot�o desfazer serve
	// para inicializar o formul�rio com menu = sim
	// Roberta Costa 01/08/2006
	private String caminhoActionDesfazerInserir;
	
	private String id;
	
	private Map<String, String> hint = new HashMap();

	private String nomeBotaoConcluir;

	private String botaoConcluirDesabilitado = "";

	/**
	 * @return Retorna o campo caminhoActionVoltarFiltro.
	 */
	public String getCaminhoActionVoltarFiltro() {
		return caminhoActionVoltarFiltro;
	}

	/**
	 * @param caminhoActionVoltarFiltro
	 *            O caminhoActionVoltarFiltro a ser setado.
	 */
	public void setCaminhoActionVoltarFiltro(String caminhoActionVoltarFiltro) {
		this.caminhoActionVoltarFiltro = caminhoActionVoltarFiltro;
	}

	/**
	 * @param relacaoNumeroPaginaCaminho
	 *            O relacaoNumeroPaginaCaminho a ser setado.
	 */
	public void setRelacaoNumeroPaginaCaminho(Map relacaoNumeroPaginaCaminho) {
		this.relacaoNumeroPaginaCaminho = relacaoNumeroPaginaCaminho;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	/**
	 * Construtor da classe StatusWizard
	 * 
	 * Usado para inser��o
	 * 
	 * @param caminhoActionPrincipalWizard
	 *            Descri��o do par�metro
	 * @param caminhoActionConclusao
	 *            Descri��o do par�metro
	 * @param caminhoActionCancelamento
	 *            Descri��o do par�metro
	 */
	public StatusWizard(String caminhoActionPrincipalWizard,
			String caminhoActionConclusao, String caminhoActionCancelamento,
			String caminhoActionDesfazerInserir) {
		super();
		this.caminhoActionPrincipalWizard = caminhoActionPrincipalWizard;
		this.caminhoActionConclusao = caminhoActionConclusao;
		this.caminhoActionCancelamento = caminhoActionCancelamento;
		this.caminhoActionDesfazerInserir = caminhoActionDesfazerInserir;
	}

	public StatusWizard(String caminhoActionPrincipalWizard,
			String caminhoActionConclusao, String caminhoActionCancelamento,
			String caminhoActionVoltarFiltro, String caminhoActionDesfazer) {
		super();
		this.caminhoActionPrincipalWizard = caminhoActionPrincipalWizard;
		this.caminhoActionConclusao = caminhoActionConclusao;
		this.caminhoActionCancelamento = caminhoActionCancelamento;
		this.caminhoActionVoltarFiltro = caminhoActionVoltarFiltro;
		this.caminhoActionDesfazer = caminhoActionDesfazer;
	}

	public StatusWizard(String caminhoActionPrincipalWizard,
			String caminhoActionConclusao, String caminhoActionCancelamento,
			String caminhoActionVoltarFiltro, String caminhoActionDesfazer,
			String idRegistro) {
		super();
		this.caminhoActionPrincipalWizard = caminhoActionPrincipalWizard;
		this.caminhoActionConclusao = caminhoActionConclusao;
		this.caminhoActionCancelamento = caminhoActionCancelamento;
		this.caminhoActionVoltarFiltro = caminhoActionVoltarFiltro;
		this.caminhoActionDesfazer = caminhoActionDesfazer;
		this.id = idRegistro;
	}
	
	// Criado por que existe processos de aba onde a pesquisa do objeto n�o est� no action principal
	// a pesquisa foi implementada no action da 1� aba (Ex.: ExibirAtualizarComandoAtividadeFaturamentoAction)
	// Quando � necess�rio passar par�metros especificos do caso de uso
	// Rafael Santos e Roberta Costa
	public StatusWizard(String caminhoActionPrincipalWizard,
			String caminhoActionConclusao, String caminhoActionCancelamento,
			String caminhoActionVoltarFiltro, String caminhoActionDesfazer,
			String caminhoActionDesfazerVoltarAvancar,String idRegistro) {
		super();
		this.caminhoActionPrincipalWizard = caminhoActionPrincipalWizard;
		this.caminhoActionConclusao = caminhoActionConclusao;
		this.caminhoActionCancelamento = caminhoActionCancelamento;
		this.caminhoActionVoltarFiltro = caminhoActionVoltarFiltro;
		this.caminhoActionDesfazer = caminhoActionDesfazer;
		this.caminhoActionDesfazerVoltarAvancar = caminhoActionDesfazerVoltarAvancar;
		this.id = idRegistro;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param itemWizard
	 *            Descri��o do par�metro
	 */
	public void inserirNumeroPaginaCaminho(StatusWizardItem itemWizard) {
		this.relacaoNumeroPaginaCaminho.put(new Integer(itemWizard
				.getNumeroPagina()), itemWizard);
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param itemWizard
	 *            Descri��o do par�metro
	 */
	public void removerNumeroPaginaCaminho(StatusWizardItem itemWizard) {
		this.relacaoNumeroPaginaCaminho.remove(new Integer(itemWizard
				.getNumeroPagina()));
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param numeroDaPagina
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public StatusWizard.StatusWizardItem retornarItemWizard(int numeroDaPagina) {
		return (StatusWizard.StatusWizardItem) this.relacaoNumeroPaginaCaminho
				.get(new Integer(numeroDaPagina));
	}

	/**
	 * Retorna o valor de relacaoNumeroPaginaCaminho
	 * 
	 * @return O valor de relacaoNumeroPaginaCaminho
	 */
	public Map getRelacaoNumeroPaginaCaminho() {
		return relacaoNumeroPaginaCaminho;
	}

	/**
	 * Seta o valor de caminhoActionConclusao
	 * 
	 * @param caminhoActionConclusao
	 *            O novo valor de caminhoActionConclusao
	 */
	public void setCaminhoActionConclusao(String caminhoActionConclusao) {
		this.caminhoActionConclusao = caminhoActionConclusao;
	}

	/**
	 * Retorna o valor de caminhoActionConclusao
	 * 
	 * @return O valor de caminhoActionConclusao
	 */
	public String getCaminhoActionConclusao() {
		return caminhoActionConclusao;
	}

	/**
	 * Retorna o valor de caminhoActionCancelamento
	 * 
	 * @return O valor de caminhoActionCancelamento
	 */
	public String getCaminhoActionCancelamento() {
		return caminhoActionCancelamento;
	}

	/**
	 * Seta o valor de caminhoActionCancelamento
	 * 
	 * @param caminhoActionCancelamento
	 *            O novo valor de caminhoActionCancelamento
	 */
	public void setCaminhoActionCancelamento(String caminhoActionCancelamento) {
		this.caminhoActionCancelamento = caminhoActionCancelamento;
	}

	/**
	 * < <Descri��o da Classe>>
	 * 
	 * @author rodrigo
	 */
	public class StatusWizardItem {
		private int numeroPagina;

		private String imagemSelecionada;

		private String imagemNaoSelecionada;

		private String caminhoActionInicial;

		private String caminhoActionFinal;

		/**
		 * Construtor da classe StatusWizardItem
		 * 
		 * @param numeroPagina
		 *            Descri��o do par�metro
		 * @param imagemSelecionada
		 *            Descri��o do par�metro
		 * @param imagemNaoSelecionada
		 *            Descri��o do par�metro
		 * @param caminhoActionInicial
		 *            Descri��o do par�metro
		 * @param caminhoActionFinal
		 *            Descri��o do par�metro
		 */
		public StatusWizardItem(int numeroPagina, String imagemSelecionada,
				String imagemNaoSelecionada, String caminhoActionInicial,
				String caminhoActionFinal) {
			this.numeroPagina = numeroPagina;
			this.imagemNaoSelecionada = imagemNaoSelecionada;
			this.imagemSelecionada = imagemSelecionada;
			this.caminhoActionInicial = caminhoActionInicial;
			this.caminhoActionFinal = caminhoActionFinal;
		}

		/**
		 * Retorna o valor de imagemSelecionada
		 * 
		 * @return O valor de imagemSelecionada
		 */
		public String getImagemSelecionada() {
			return this.imagemSelecionada;
		}

		/**
		 * Retorna o valor de imagemNaoSelecionada
		 * 
		 * @return O valor de imagemNaoSelecionada
		 */
		public String getImagemNaoSelecionada() {
			return this.imagemNaoSelecionada;
		}

		/**
		 * Retorna o valor de caminhoActionInicial
		 * 
		 * @return O valor de caminhoActionInicial
		 */
		public String getCaminhoActionInicial() {
			return this.caminhoActionInicial;
		}

		/**
		 * Retorna o valor de caminhoActionFinal
		 * 
		 * @return O valor de caminhoActionFinal
		 */
		public String getCaminhoActionFinal() {
			return this.caminhoActionFinal;
		}

		/**
		 * Retorna o valor de numeroPagina
		 * 
		 * @return O valor de numeroPagina
		 */
		public int getNumeroPagina() {
			return this.numeroPagina;
		}

	}

	/**
	 * The main program for the StatusWizard class
	 * 
	 * @param args
	 *            The command line arguments
	 */
	public static void main(String[] args) {
		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard("inserirWizard",
				"inserirFinal", "cancelarFinal", "");

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "1a.gif", "1c.gif", "exibirInserirClienteNomeTipo",
						"inserirClienteNomeTipo"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "2a.gif", "2c.gif", "exibirInserirClientePessoa",
						"inserirClientePessoa"));
		System.out.println(statusWizard);

	}

	/**
	 * Retorna o valor de caminhoActionPrincipalWizard
	 * 
	 * @return O valor de caminhoActionPrincipalWizard
	 */
	public String getCaminhoActionPrincipalWizard() {
		return caminhoActionPrincipalWizard;
	}

	/**
	 * Seta o valor de caminhoActionPrincipalWizard
	 * 
	 * @param caminhoActionPrincipalWizard
	 *            O novo valor de caminhoActionPrincipalWizard
	 */
	public void setCaminhoActionPrincipalWizard(
			String caminhoActionPrincipalWizard) {
		this.caminhoActionPrincipalWizard = caminhoActionPrincipalWizard;
	}

	/**
	 * Adiciona um item no hint do processo
	 * 
	 * @author Rodrigo Silveira
	 * @date 09/06/2006
	 * 
	 * @param titulo
	 * @param conteudo
	 */
	public void adicionarItemHint(String titulo, String conteudo) {
		if (conteudo == null) {
			conteudo = "";
		}
		this.hint.put(titulo, conteudo);

	}

	public String getCaminhoActionDesfazer() {
		String retorno = null;
		if( caminhoActionDesfazer != null ){
			retorno = caminhoActionDesfazer+"?desfazer=true";
		}	
		return retorno;
	}

	public void setCaminhoActionDesfazer(String caminhoActionDesfazer) {
		this.caminhoActionDesfazer = caminhoActionDesfazer;
	}

	/**
	 * @return Retorna o campo caminhoActionDesfazerVoltarAvancar.
	 */
	public String getCaminhoActionDesfazerVoltarAvancar() {
		return caminhoActionDesfazerVoltarAvancar;
	}

	/**
	 * @param caminhoActionDesfazerVoltarAvancar O caminhoActionDesfazerVoltarAvancar a ser setado.
	 */
	public void setCaminhoActionDesfazerVoltarAvancar(
			String caminhoActionDesfazerVoltarAvancar) {
		this.caminhoActionDesfazerVoltarAvancar = caminhoActionDesfazerVoltarAvancar;
	}

	/**
	 * @return Retorna o campo caminhoActionDesfazerInserir.
	 */
	public String getCaminhoActionDesfazerInserir() {
		String retorno = null;
		if( caminhoActionDesfazerInserir != null ){
			int ocorrenciaInt = caminhoActionDesfazerInserir.indexOf('?');
			if(ocorrenciaInt == -1)
				retorno = caminhoActionDesfazerInserir+"?menu=sim";
			else
				retorno = caminhoActionDesfazerInserir;
		}	
		return retorno;
	}

	/**
	 * @param caminhoActionDesfazerInserir O caminhoActionDesfazerInserir a ser setado.
	 */
	public void setCaminhoActionDesfazerInserir(String caminhoActionDesfazerInserir) {
		this.caminhoActionDesfazerInserir = caminhoActionDesfazerInserir;
	}

	/**
	 * Remove o item do hint do processo
	 * 
	 * @author Rodrigo Silveira
	 * @date 28/06/2006
	 * 
	 * @param chaveDescricao
	 *            Primeiro argumento da cria��o do hint
	 */
	public void removerItemHint(String chaveDescricao) {
		this.hint.remove(chaveDescricao);
	}

	/**
	 * Fun��o que gera o hint para o processo
	 * 
	 * @author Rodrigo Silveira
	 * @date 10/06/2006
	 * 
	 * @return
	 */
	public String gerarHint() {
		StringBuilder hint = new StringBuilder("");
		Iterator<Map.Entry<String, String>> iterator = this.hint.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			hint.append(entry.getKey() + " " + entry.getValue() + "<br>");

		}

		return hint.toString();

	}

	public String getNomeBotaoConcluir() {
		return nomeBotaoConcluir;
	}

	public void setNomeBotaoConcluir(String nomeBotaoConcluir) {
		this.nomeBotaoConcluir = nomeBotaoConcluir;
	}

	public String getBotaoConcluirDesabilitado() {
		return botaoConcluirDesabilitado;
	}

	public void setBotaoConcluirDesabilitado(String botaoConcluirDesabilitado) {
		this.botaoConcluirDesabilitado = botaoConcluirDesabilitado;
	}

}
