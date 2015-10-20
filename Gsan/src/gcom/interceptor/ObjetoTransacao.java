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
package gcom.interceptor;

import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.Filtro;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Classe que representa o objeto de transa�o,
 * 
 * objeto transa��o � o objeto que ao ser atualizado compra com o da base pra
 * ver se vc ta usando o objeto mais novo e que registra das altera��es entre um
 * objeto e outro
 * 
 * date 16/01/2006
 * 
 * @author thiago
 */
public abstract class ObjetoTransacao extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	/** operacao realizada pelo usuario */
	private OperacaoEfetuada operacaoEfetuada = null;

	private Collection usuarioAcaoUsuario = new HashSet();
	
	// Utilizada para classes com chaves compostas
	private Integer id2;	

	/**
	 * @return Retorna o campo id2.
	 */
	public Integer getId2() {
		return id2;
	}

	/**
	 * @param id2 O id2 a ser setado.
	 */
	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public OperacaoEfetuada getOperacaoEfetuada() {
		return operacaoEfetuada;
	}

	public void setOperacaoEfetuada(int idOperacao) {
		Operacao operacao = new Operacao();
		operacao.setId(idOperacao);
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		this.operacaoEfetuada = operacaoEfetuada;
		
	}

	public void setOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada) {
		this.operacaoEfetuada = operacaoEfetuada;
	}

	public void adicionarUsuario(Usuario usuario, UsuarioAcao usuarioAcao) {
		for (Iterator iter = this.usuarioAcaoUsuario.iterator(); iter.hasNext();) {
			UsuarioAcaoUsuarioHelper UAUHExistente = (UsuarioAcaoUsuarioHelper) iter.next();
			if (UAUHExistente.getUsuario() != null && UAUHExistente.getUsuario().equals(usuario)
				&& UAUHExistente.getUsuarioAcao() != null && UAUHExistente.getUsuarioAcao().equals(usuarioAcao)){
				return;
			}
			
		}
		this.usuarioAcaoUsuario.add(new UsuarioAcaoUsuarioHelper(usuario,
				usuarioAcao));
	}

	public Collection getUsuarioAcaoUsuarioHelp() {
		return usuarioAcaoUsuario;
	}

	public void setUsuarioAcaoUsuarioHelp(Collection usuarioAcaoUsuario) {
		this.usuarioAcaoUsuario = usuarioAcaoUsuario;
	}

	/**
	 * Retorna a data da ultima altera��o do objeto
	 * 
	 * @return Date
	 */
	public abstract Date getUltimaAlteracao();

	/**
	 * Armazena a data da ultima altera��o
	 * 
	 * @param ultimaAlteracao
	 *            date
	 */
	public abstract void setUltimaAlteracao(Date ultimaAlteracao);

	/**
	 * M�todo que retorna o filtro com as chaves primarias preenchidas
	 * 
	 * @return
	 */
	public abstract Filtro retornaFiltro();
	
	/**
	 * Retorna a cole��o de atributos que ser�o registrados, ou seja,
	 * todos os atributos que contem uma Annotation @ControleAlteracao
	 * @return um array com os nomes dos atributos
	 */
	public String[] retornarAtributosSelecionadosRegistro(){
		ArrayList<String> atributos = new ArrayList<String>();
		Class classe = getClass();
		buscarCamposComControleAlteracao(classe, atributos, "");
		
		return atributos.toArray(new String[0]);	
	}
	
	
	
	private void buscarCamposComControleAlteracao(Class classe, ArrayList<String> atributos, String caminhoAtual){
		Annotation anot[] = classe.getAnnotations();
		if (temControleAlteracao(anot) != null){
			Field[] campos = classe.getDeclaredFields();
			for (int i = 0; i < campos.length; i++) {
				Annotation anotCampo = temControleAlteracao(campos[i].getAnnotations());
				if (anotCampo != null){
					Integer idOperacao = null;
					if (operacaoEfetuada != null && operacaoEfetuada.getOperacao() != null){
						idOperacao = operacaoEfetuada.getOperacao().getId();
					}
					int[] funcionalidades = ((ControleAlteracao)anotCampo).funcionalidade();
					if (idOperacao != null) {						
						for (int j = 0; j < funcionalidades.length; j++) {
							if (funcionalidades[j] == idOperacao.intValue()){				
									atributos.add(campos[i].getName());						
							}						
						}
					} else {
						atributos.add(campos[i].getName());
					}
					
				}
			}			
		}		
	}
	
	/**
	 * Retorna o retorno da chamada do get referente ao atributo passado
	 * Pode haver itens conjugados. Por exemplo: imovel.id  para o caso de
	 *   getImovel().getId())
	 * Caso algum item do conjugado esteja nulo, o retorno ser� vazio
	 * @param atributo 
	 * @return
	 */
	public Object get(String atributo){

		StringTokenizer st = new StringTokenizer(atributo, ".");
		String nomeMetodo = null;
		// este atual servir� para os casos conjugados 
		Object atual = this;
		while (st.hasMoreElements()) {
			String at = (String) st.nextElement();
			at = at.substring(0, 1).toUpperCase() + at.substring(1, at.length());
			nomeMetodo = "get"+at;
			
			try {
				// invocando o metodo do objeto atual para pegar o retorno
				Method metodo = atual.getClass().getMethod(nomeMetodo, (Class[]) null);
				atual = metodo.invoke(atual,(Object[]) null);
				if (atual == null){				
					return null;
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// casos em que foi colocado um cmainho de atributos inexistente
				return null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		return atual;
	}

	public Class getTipoAtributo(String atributo){		
		StringTokenizer st = new StringTokenizer(atributo, ".");
		String nomeMetodo = null;
		// este atual servir� para os casos conjugados 
		Object atual = this;
		Class retorno = this.getClass();
		while (st.hasMoreElements()) {
			String at = (String) st.nextElement();
			at = at.substring(0, 1).toUpperCase() + at.substring(1, at.length());
			nomeMetodo = "get"+at;
			
			try {
				// invocando o metodo do objeto atual para pegar o retorno
				Method metodo = atual.getClass().getMethod(nomeMetodo, (Class[]) null);
				retorno = atual.getClass();
				atual = metodo.invoke(atual,(Object[]) null);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// casos em que foi colocado um cmainho de atributos inexistente
				atual = "";
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		return retorno;
	}

	/**
	 * Setar o valor de um atributo da classe
	 * @param atributo nome do atributo 
	 * @param tipo tipo do atributo
	 * @param valor valor a ser atribuido
	 */
	public void set(String atributo, Class tipo, Object valor){
		String nomeMetodo = null;
		atributo = atributo.substring(0, 1).toUpperCase() + atributo.substring(1, atributo.length());
		nomeMetodo = "set"+atributo;
		
		try {
			// invocando o metodo do objeto atual para pegar o retorno
			Class[] tipos = {tipo};
			Method metodo = this.getClass().getMethod(nomeMetodo, tipos);
			Object[] args = {valor};
			metodo.invoke(this,args);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna a cole��o de atributos que ser�o usados como 
	 * na consulta de opera��o efetuada num resumo de dados  
	 * @return
	 */
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
			return null;		
	}
	
	/**
	 * Retorna a cole��o de labels referentes aos atributos que ser�o 
	 * usados na consulta de opera��o efetuada no resumo de dados 
	 * @return
	 */
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
			return null;		
	}

	public Filtro retornaFiltroRegistroOperacao(){
		return retornaFiltroRegistroOperacao(retornaFiltro());
	}

	/**
	 * Retorna um filtro que ser� usado no registro da operacao
	 * A diferen�a deste filtro � que contem como itens de carregamento
	 * os campos que foram definidos para controle de alteracao
	 * 
	 * @param filtro
	 * @see gcom.interceptor.ControleAlteracao
	 * @return
	 */
	public Filtro retornaFiltroRegistroOperacao(Filtro filtro){
		Class classe = getClass();
		Annotation anot[] = classe.getAnnotations();
		if (temControleAlteracao(anot) != null){
			Field[] campos = classe.getDeclaredFields();
			for (int i = 0; i < campos.length; i++) {
				Annotation anotCampo = temControleAlteracao(campos[i].getAnnotations());
				if (anotCampo != null){					
					filtro.adicionarCaminhoParaCarregamentoEntidade(((ControleAlteracao)anotCampo).value());
				}
			}			
		}
		filtro.setInitializeLazy(true);
		return filtro;
	}
		
	/**
	 * Retorna a anota��o do tipo ControleAlteracao contida no conjunto de 
	 * anota��es que foi passado, caso exista. Caso n�o, retorna null.
	 * @author Francisco do Nascimento 
	 * @param anot
	 * @return Anota��o do tipo ControleAlteracao ou null
	 */
	private Annotation temControleAlteracao(Annotation[] anot){
		if (anot != null){
			for (int i = 0; i < anot.length; i++) {
				if (anot[i] instanceof ControleAlteracao){
					return anot[i];
				}
			}			
		}
		return null;
	}
	
	/**
	 * verifica se esta classe cont�m alguma anota��o do tipo ControleAlteracao
	 * @author Francisco do Nascimento
	 * @return Existe ou n�o
	 */
	public boolean temControleAlteracao(){
		return temControleAlteracao(this.getClass().getAnnotations()) != null;
	}

	/**
	 * Identificador utilizado para os casos de altera��o de entidades, cujo procedimento
	 * utilizado foi a remo��o(alguns casos nem remove) seguida de uma inclus�o para operacionalizar uma altera��o.
	 * Para efeito de registro de opera��o, era necess�rio entender como uma altera��o
	 * Da�, este atributo ir� guardar o id do objeto antigo no objeto novo.
	 * @author Francisco do Nascimento
	 */
	private Object idAntigo;
	
	public Object getIdAntigo() {
		return idAntigo;
	}

	public void setIdAntigo(Object idAntigo) {
		this.idAntigo = idAntigo;
	}

	/**
	 * Em algumas situa��es, ocorre erro de dar um get numa propriedade que n�o foi carregada
	 * com a sess�o ativa do hibernate. Para solucionar isto, foi criado um atributo no filtro
	 * para determinar se, ao pesquisar algum objeto, deseja que sejam inicializadas as propriedades
	 * lazies. Caso esteja true este atributo no filtro, para cada objeto retornado da pesquisa, � chamado
	 * este m�todo. 
	 * A implementa��o deste m�todo deve conter chamadas aos get's das propriedades que est�o lazies 
	 * (Collection e outros objetos do sistema)
	 * Na classe gcom.faturamento.conta.Conta est� implementado.
	 *  
	 * @author Francisco do Nascimento
	 * @see gcom.util.filtro.Filtro
	 *
	 */
	public void initializeLazy(){
		
	}
	
	public void initilizarCollectionLazy(Collection colecao){
		if (colecao != null){
			for (Iterator iter = colecao.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof ObjetoTransacao){
					((ObjetoTransacao)element).initializeLazy();	
				}							
			}			
		}		
	}
	
	/**
	 * Este m�todo define como ser� a descri��o deste objeto no momento
	 * de registrar a transacao.
	 * Normalmente, h� um getDescricao na classe, por isso, foi definido como 
	 * default o getDescricao. Caso seja diferente, deve ser sobreescrito na classe.
	 * @return String com a descricao do objeto
	 * @author Francisco Nascimento
	 */
	public String getDescricaoParaRegistroTransacao(){
		Object retorno =  get("descricao");
		if (retorno == null){
			retorno = "";
		}
		return (String) retorno; 
	}
	
}