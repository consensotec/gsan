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
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.seguranca.transacao.ControladorTransacaoLocalHome;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaLinhaAlteracao;
import gcom.seguranca.transacao.TabelaLinhaColunaAlteracao;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ControleAlteracaoColecao;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesColecao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.ejb.CreateException;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.LazyInitializationException;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;

public class Interceptador implements Interceptor {

	private static Interceptador interceptor;

	public static Interceptador getInstancia() {
		if (interceptor == null) {
			interceptor = new Interceptador();
		}
		return interceptor;
	}

	private Interceptador() {

	}

	/**
	 * Retorna o valor de controladorTarifaSocial
	 * 
	 * @return O valor de controladorTarifaSocial
	 */
	private ControladorTransacaoLocal getControladorTransacao() {
		ControladorTransacaoLocalHome localHome = null;
		ControladorTransacaoLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorTransacaoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_TRANSACAO_SEJB);
			local = localHome.create();
			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorUtilLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			local = localHome.create();
			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Recebe uma cole��o de objetos do tipo Tabelalinhacolunaalteracao para serem adicionados
	 * como filhos de uma tabelalinhaalteraco do tipo do objeto transacao (novo) passado
	 * @param novo
	 * @param operacaoEfetuada
	 * @param usuariosAcao
	 * @param tabelaLinhaColunaAlteracoes
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ControladorException
	 */
	private void salvarModificacao(ObjetoTransacao novo, OperacaoEfetuada operacaoEfetuada,
			Collection usuariosAcao, Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes, int tipoAlteracao)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, ControladorException {

		String nomeTabela = HibernateUtil.getNameTable(novo.getClass());

		FiltroTabela filtroTabela = new FiltroTabela();
		filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.NOME,nomeTabela));

		Collection coll = getControladorUtil().pesquisar(filtroTabela,Tabela.class.getSimpleName());
		Tabela tabela = null;
		
		if (coll != null && !coll.isEmpty()) {
			tabela = (Tabela) coll.iterator().next();
		} else {

			return;
			/*
			 * Foi acordado retirar a inclus�o autom�tica de tabelas e colunas n�o encontradas
			 * 					Francisco Nascimento, 05/03/08
			 * 
			tabela = new Tabela();
			tabela.setNomeTabela(nomeTabela);
			tabela.setDescricao(nomeTabela);
			tabela.setUltimaAlteracao(new Date(System.currentTimeMillis()));
			this.getControladorUtil().inserir(tabela);
			*/
		}

		AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
		alteracaoTipo.setId(tipoAlteracao);

		TabelaLinhaAlteracao tabelaLinhaAlteracao = new TabelaLinhaAlteracao();
		tabelaLinhaAlteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
		
		if (novo.getId2() != null)
			tabelaLinhaAlteracao.setId2(novo.getId2());
		
		tabelaLinhaAlteracao.setTabela(tabela);
		tabelaLinhaAlteracao.setAlteracaoTipo(alteracaoTipo);

		Integer[] ids = getIds(novo);
		if (ids != null && ids.length > 0) {
			tabelaLinhaAlteracao.setId1(ids[0]);
		}
		if (ids != null && ids.length > 1) {
			tabelaLinhaAlteracao.setId2(ids[1]);
		}

		Iterator it = tabelaLinhaColunaAlteracoes.iterator();
		while (it.hasNext()) {
			TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = (TabelaLinhaColunaAlteracao) it.next();

			FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
			filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.TABELA_ID, tabela.getId()));
			filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.COLUNA, tabelaLinhaColunaAlteracao.getTabelaColuna().getColuna()));
			coll = this.getControladorUtil().pesquisar(filtroTabelaColuna,TabelaColuna.class.getSimpleName());
			TabelaColuna tabelaColuna = null;
			
			if (coll != null && !coll.isEmpty()) {
				tabelaColuna = (TabelaColuna) coll.iterator().next();
			} else {
				System.out.println("Coluna nao encontrada: " + tabelaLinhaColunaAlteracao.getTabelaColuna().getColuna());
				continue;
				/*
				 * Foi acordado retirar a inclus�o autom�tica de tabelas e colunas n�o encontradas
				 * 					Francisco Nascimento, 05/03/08
				 *
				 *
				tabelaColuna = new TabelaColuna();
				tabelaColuna.setColuna(tabelaLinhaColunaAlteracao.getTabelaColuna().getColuna());
				tabelaColuna.setDescricaoColuna(tabelaLinhaColunaAlteracao.getTabelaColuna().getColuna());
				tabelaColuna.setTabela(tabela);
				tabelaColuna.setUltimaAlteracao(new Date(System.currentTimeMillis()));
				
				Integer idTabelaColuna = (Integer) this.getControladorUtil().inserir(tabelaColuna);
				tabelaColuna.setId(idTabelaColuna);
				// inserindo a ordem de exibicao desta tabela coluna
				OperacaoOrdemExibicao ordemColuna = new OperacaoOrdemExibicao();
				ordemColuna.setComp_id(new OperacaoOrdemExibicaoPK(new Integer(operacaoEfetuada.getOperacao().getId()),
					tabela.getId()));
				ordemColuna.setTabelaColuna(tabelaColuna);
				this.getControladorUtil().inserir(ordemColuna);
				
				*/
			}
			tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
		}
		
		this.getControladorTransacao().inserirOperacaoEfetuada(usuariosAcao, operacaoEfetuada,tabelaLinhaAlteracao, tabelaLinhaColunaAlteracoes);
		this.salvarTabelaLinhaAlteracaoPrincipal(operacaoEfetuada, novo);
	}

	public void verificarObjetoAlterado(Object arg0,String[] colecaoNomesAtributos) {

		if (arg0 instanceof ObjetoTransacao) {
			ObjetoTransacao objetoTransacao = (ObjetoTransacao) arg0;
			
			// verifica se a classe possui controle de registrar transacao
			if (!objetoTransacao.temControleAlteracao()){
				return;
			}
			
			Filtro filtro = objetoTransacao.retornaFiltroRegistroOperacao();

			if (objetoTransacao.getIdAntigo() != null){

				filtro.getParametros().clear();
				if (objetoTransacao.getIdAntigo() instanceof ObjetoGcom){
					filtro.adicionarParametro(new ParametroSimplesColecao(
							objetoTransacao.retornaCamposChavePrimaria()[0],objetoTransacao.getIdAntigo()));					
				} else {
					filtro.adicionarParametro(new ParametroSimples(
							objetoTransacao.retornaCamposChavePrimaria()[0],objetoTransacao.getIdAntigo()));					
				}
			}
			try {
				Collection coll = RepositorioUtilHBM.getInstancia().pesquisar(filtro, objetoTransacao.getClass().getName());
						
				if (coll.iterator().hasNext()) {
					ObjetoTransacao antigo = (ObjetoTransacao) coll.iterator()
							.next();

					if ((antigo.getUltimaAlteracao() != null)
							&& (objetoTransacao.getUltimaAlteracao() == null || antigo
									.getUltimaAlteracao().getTime() != objetoTransacao
									.getUltimaAlteracao().getTime())) {
						// throw new
						// IllegalArgumentException(" Data diferente.");
					}

					// Para os dados adicionais sera utilizado o objeto antigo,
					// pois
					// � garantido que os campos est�o com valores
					boolean houveAlteracao = objetoTransacao
							.getOperacaoEfetuada().preencherDadosAdicionais(
									antigo);

					Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes = compareObjetoTransacao(
							objetoTransacao, antigo, colecaoNomesAtributos);

					if (tabelaLinhaColunaAlteracoes != null
							&& !tabelaLinhaColunaAlteracoes.isEmpty()) {

						String idPrincipal = objetoTransacao
								.getOperacaoEfetuada().getIdObjetoPrincipal()
								+ "";

						// Adicionando uma linha com a descricao do objeto, caso
						// esta descricao n�o seja vazia
						String[] keys = antigo.retornaCamposChavePrimaria();
						for (int i = 0; keys != null && i < keys.length; i++) {
							String id = keys[i];

							String descricaoVelho = consultarDescricao(antigo);
							String descricaoNovo = consultarDescricao(objetoTransacao);

							if (!(descricaoVelho.equals("") && descricaoNovo
									.equals(""))
									&& !descricaoVelho.equals(idPrincipal)) {

								TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = gerarTabelaLinhaColunaAlteracao(
										id,
										objetoTransacao.getTipoAtributo(id),
										descricaoNovo, descricaoVelho);
								if (tabelaLinhaColunaAlteracao != null)
									tabelaLinhaColunaAlteracoes
											.add(tabelaLinhaColunaAlteracao);
							}
							break;
						}

						salvarModificacao(objetoTransacao,
								objetoTransacao.getOperacaoEfetuada(),
								objetoTransacao.getUsuarioAcaoUsuarioHelp(),
								tabelaLinhaColunaAlteracoes,
								AlteracaoTipo.ALTERACAO);
					} else {
						if (houveAlteracao) {
							if (objetoTransacao.getOperacaoEfetuada().getId() != null) {
								getControladorUtil().atualizar(
										objetoTransacao.getOperacaoEfetuada());
							}
						}
					}
				}
			} catch (IllegalArgumentException e) {
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
				// caso tenha acontecido algum problema na consulta do objeto ao
				// banco.
				// throw new IllegalArgumentException(" N�o pode consultar o
				// objeto transa�o do banco. msn: " + e.getMessage());
			}
		}
	}
	
	public void verificarObjetoAlterado(Object velho, Object novo) {
		if (velho instanceof ObjetoTransacao && novo instanceof ObjetoTransacao) {
			ObjetoTransacao objVelho = (ObjetoTransacao) velho;
			ObjetoTransacao objNovo = (ObjetoTransacao) novo;

			// verifica se a classe possui controle de registrar transacao
			if (objVelho !=null && !objVelho.temControleAlteracao()){
				return;
			}
			
			// Para os dados adicionais sera utilizado o objeto antigo, pois
			// � garantido que os campos est�o com valores
			objNovo.getOperacaoEfetuada().preencherDadosAdicionais(objVelho);

			Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes = compareObjetoTransacao(
				objNovo, objVelho , objVelho.retornarAtributosSelecionadosRegistro());
			if (tabelaLinhaColunaAlteracoes != null	&& !tabelaLinhaColunaAlteracoes.isEmpty()) {
				try {
					salvarModificacao(objNovo,objNovo.getOperacaoEfetuada(), objNovo.getUsuarioAcaoUsuarioHelp(),
							tabelaLinhaColunaAlteracoes, AlteracaoTipo.ALTERACAO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}		
	}

	/**
	 * M�todo que percorre os atribuitos do objeto velho e compara os com o do
	 * objeto novo. Em busca de registrar atributos diferetes.
	 * 
	 * @param novo -
	 *            ObjetoTransacao
	 * @param velho -
	 *            ObjetoTransacao
	 */
	public Collection<TabelaLinhaColunaAlteracao> compareObjetoTransacao(ObjetoTransacao novo, ObjetoTransacao velho,String [] colecaoNomesAtributos) {
		
		Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes = new Vector();

		//Class classe = velho.getClass();
		Method metodo = null;
		String nomeMetodo;
		String[] atributosRegistro = null;
		OperacaoEfetuada operacaoGeral = null; 
		Collection usuariosAcao = null;
		
		if (novo != null) {
			atributosRegistro = novo.retornarAtributosSelecionadosRegistro();
			operacaoGeral = novo.getOperacaoEfetuada();
			usuariosAcao = novo.getUsuarioAcaoUsuarioHelp();
		} else {
			atributosRegistro = velho.retornarAtributosSelecionadosRegistro();
			operacaoGeral = velho.getOperacaoEfetuada();
			usuariosAcao = velho.getUsuarioAcaoUsuarioHelp();			
		}
		
		//Altera��o refeferente a CRC2511 - Registrar transa��o
		//para atualiza��a de um campo apenas, o CPF do cliente
		//em Efetuar Parcelamento de D�bitos
		//Anderson Italo/Francisco Nascimento
		//data:13/07/2009
		//anteriormente continha o c�digo do trecho comentado
		//if (atributosRegistro != null){
		//	colecaoNomesAtributos = atributosRegistro;
		//}
		if (atributosRegistro != null && 
				(colecaoNomesAtributos != null && colecaoNomesAtributos.length >= atributosRegistro.length) 
				|| colecaoNomesAtributos == null){

			colecaoNomesAtributos = atributosRegistro;
		}
		//fim altera��o
		
		for (int i = 0; i < colecaoNomesAtributos.length; i++) {
			
			nomeMetodo = "get";
			Object[] args = {colecaoNomesAtributos[i]};
			Class[] tipos = {String.class};
			try {
				Object retornoMetodoVelho = null;
				Object retornoMetodoNovo = null;
				// invocando o metodo do objeto velho para pegar o retorno
				if (velho != null) {
					metodo = velho.getClass().getMethod(nomeMetodo, tipos);
					retornoMetodoVelho = metodo.invoke(velho,args);			
					
					if (retornoMetodoVelho != null && retornoMetodoVelho
							.getClass().getName().contains("Enhancer")){
						Filtro filtro = velho.retornaFiltroRegistroOperacao();
						filtro.adicionarParametro(new ParametroSimples("id", velho.get("id")));
						Collection coll;
						try {
							coll = RepositorioUtilHBM.getInstancia().pesquisar(filtro, velho.getClass().getName());

							velho = (ObjetoTransacao) Util.retonarObjetoDeColecao(coll);
						} catch (ErroRepositorioException e) {
							System.out.println(e.getMessage());
						}
						
						retornoMetodoVelho = metodo.invoke(velho,args);
						
					}
				} 
				if (novo != null) {
					metodo = novo.getClass().getMethod(nomeMetodo, tipos);
					retornoMetodoNovo = metodo.invoke(novo, args);					
				}
				
				Object retorno = retornoMetodoVelho != null ? retornoMetodoVelho : retornoMetodoNovo; 
				// verifica se o retorno eh do tipo conhecido
				if ((retorno instanceof ObjetoGcom)
						|| (metodo.getReturnType().isPrimitive())
						|| (retorno instanceof Short)
						|| (retorno instanceof Byte)
						|| (retorno instanceof Character)
						|| (retorno instanceof Integer)
						|| (retorno instanceof Long)
						|| (retorno instanceof Double)
						|| (retorno instanceof Float)
						|| (retorno instanceof String)
						|| (retorno instanceof Boolean)
						|| (retorno instanceof BigDecimal)
						|| (retorno instanceof Date)
						|| (retorno instanceof Collection)) {

						Collection<TabelaLinhaColunaAlteracao> auxAlteracoes = new Vector<TabelaLinhaColunaAlteracao> ();
						verificarDiferencaAtributo(auxAlteracoes, colecaoNomesAtributos[i], novo, 
								retornoMetodoNovo, velho, retornoMetodoVelho,
								operacaoGeral, usuariosAcao, false);
						tabelaLinhaColunaAlteracoes.addAll(auxAlteracoes);
				} 	
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				// e.printStackTrace();
				// nunca entra aqui
				// excecao levantada quando o metodo invocado nao existe no
				// objeto passado
				// o metodo invocado foi pego da lista do objeto que se
				// deseja utilizar
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				// e.printStackTrace();
				// nunca entra aqui
				// excecao levantada quando o metodo invocado nao existe no
				// objeto passado
				// o metodo invocado foi pego da lista do objeto que se
				// deseja utilizar
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				// e.printStackTrace();
				// nunca entra aqui
				// excecao levantada quando o metodo invocado nao existe no
				// objeto passado
				// o metodo invocado foi pego da lista do objeto que se
				// deseja utilizar
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (ControladorException e) {
				e.printStackTrace();
			}
			
		}

		return tabelaLinhaColunaAlteracoes;
	}

	/**
	 * M�todo que verifica se existe diferenca entre os atributos, chama o
	 * criadaor de TabelaLinhaColunaAlteracao e adiciona na collecao passada.
	 * 
	 * @param nomeMetodo
	 * @param nomeAtributo Nome do atributo que ser� verificado
	 * @param novo Objeto novo que cont�m tal atributo
	 * @param retornoMetodoNovo Valor novo deste atributo
	 * @param velho Objeto velho que cont�m tal atributo
	 * @param retornoMetodoVelho Valor velho deste atributo
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ControladorException 
	 */
	private void verificarDiferencaAtributo(
			Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes,
			String nomeAtributo, ObjetoTransacao novo,
			Object retornoMetodoNovo, ObjetoTransacao velho,
			Object retornoMetodoVelho, OperacaoEfetuada operacaoEfetuada,
			Collection usuarios, boolean itemDeColecao) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, ControladorException {

		// if ocorre quando o retorno do metodo for um outro objeto do sistema e
		// forem alterados de um pra outro
		// exemplo quando vc tem um combo e vc tinha um objeto e selecionou
		// outro
		if (retornoMetodoVelho instanceof ObjetoTransacao
				|| retornoMetodoNovo instanceof ObjetoTransacao) {
			
			// Caso o objetoGcom n�o tenha o Annotation 'ControleAlteracao', 
			// � suficiente verificar se houve mudan�a de identificadores
			ObjetoTransacao gcomVelho = (ObjetoTransacao) retornoMetodoVelho;
			ObjetoTransacao gcomNovo = (ObjetoTransacao) retornoMetodoNovo;
			
			if (gcomNovo != null && gcomVelho != null) {
				
				gcomVelho = consultarObjetoCarregandoAtributo(gcomVelho);
				
				// pegar os ids e verificar se todos sao iguais
				// Caso o objeto tenha v�rios id (chave composta), o campo que ser� registrado
				// ser� a primeira chave, ent�o para definir qual o campo que se quer salvar
				// altere o m�todo retornaCamposChavePrimaria, definindo como o primeiro elemento
				// do array de keys, o campo que se queira deixar registrado.
				String[] keys = gcomVelho.retornaCamposChavePrimaria();
				for (int i = 0; keys != null && i < keys.length; i++) {
					String id = keys[i];
					String nomeMetodoKey = "get"
							+ id.substring(0, 1).toUpperCase()
							+ id.substring(1, id.length());

					Method metodoNovo = gcomNovo.getClass().getMethod(nomeMetodoKey, (Class[])null);
					Object retornoKeyNovo = metodoNovo.invoke(gcomNovo, (Object[])null);

					Method metodoVelho = gcomVelho.getClass().getMethod(nomeMetodoKey,(Class[]) null);
					Object retornoKeyVelho = metodoVelho.invoke(gcomVelho, (Object[])null);

					// Ambos diferentes de null => alteracao
					if (retornoKeyNovo != null && retornoKeyVelho != null){
						String descricaoVelho = consultarDescricao(gcomVelho);
						String descricaoNovo = consultarDescricao(gcomNovo);

						// Se os ids forem diferentes,primeiro registra-se o ID com o valor da descricao do objeto
						
						if (!retornoKeyVelho.equals(retornoKeyNovo)) {
							
							TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = gerarTabelaLinhaColunaAlteracao(nomeAtributo, 
									novo.getTipoAtributo(nomeAtributo),descricaoNovo, descricaoVelho);
							if (tabelaLinhaColunaAlteracao != null)
								tabelaLinhaColunaAlteracoes.add(tabelaLinhaColunaAlteracao);
						} 								
						
						// Verificando se os atributos deste atributo dever� ser anasalido (recursivamente)
						// quando o getdescricaoParaRegistraTRansacao retorna "" � pq deve entrar no objeto pra 
						// percorrer os campos
						if (gcomVelho.temControleAlteracao() 
//								&& (descricaoVelho.equals("") && descricaoNovo.equals(""))
								){

							// Entrando na classe deste atributo para analisar se houve altera��o em seus atributos
							Collection<TabelaLinhaColunaAlteracao> alteracoes = compareObjetoTransacao(gcomNovo, gcomVelho, null);
							if (alteracoes != null && !alteracoes.isEmpty()){
								
								TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = gerarTabelaLinhaColunaAlteracao(
										id, retornoMetodoNovo.getClass(),descricaoNovo, descricaoVelho);
								if (tabelaLinhaColunaAlteracao != null && 
									tabelaLinhaColunaAlteracoes.isEmpty()){
									alteracoes.add(tabelaLinhaColunaAlteracao);
								}
								
								tabelaLinhaColunaAlteracoes.addAll(alteracoes);
																	
							}
															
						} 
					}
				}
				// else ocorre quando o retono do metodo for um outro objeto do
				// sistema e forem alterados de um pra null ou de
				// null pra um
				// exemplo quando vc tem um pop up e vc setou pra null
				// ou vc tinha um null e vc escolheu um
			} else if ((retornoMetodoNovo != null && retornoMetodoVelho == null) ||	(retornoMetodoNovo == null && retornoMetodoVelho != null)) {

				// pegando os nomes dos ids
				String[] keys = null;
				if (gcomNovo != null){
					keys = gcomNovo.retornaCamposChavePrimaria();
				} else {
					keys = gcomVelho.retornaCamposChavePrimaria();
				}
				
				for (int i = 0; keys != null && i < keys.length; i++) {
					// pra cada id pega o metodo
					String id = keys[i];
									
					String descricaoVelho = null;
					String descricaoNovo = null;
					Class classe;
					
					if (gcomNovo != null) {
						descricaoNovo = consultarDescricao((ObjetoGcom)retornoMetodoNovo);
					} else {
						descricaoVelho = consultarDescricao((ObjetoGcom)retornoMetodoVelho);
					}
					String nomeCampo = "";
					if (itemDeColecao){
						nomeCampo = id;
						if (gcomNovo != null){
							classe = gcomNovo.getClass();
						} else {
							classe = gcomVelho.getClass();
						}

					} else {
						nomeCampo = nomeAtributo;
						if (novo != null){
							classe = novo.getClass();
						} else {
							classe = velho.getClass();
						}						
					}

					TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = gerarTabelaLinhaColunaAlteracao(nomeCampo, 
							classe,descricaoNovo, descricaoVelho);
					if (tabelaLinhaColunaAlteracao != null){
						tabelaLinhaColunaAlteracoes.add(tabelaLinhaColunaAlteracao);
						if ((gcomVelho != null && gcomVelho.temControleAlteracao()) || 
								(gcomNovo != null && gcomNovo.temControleAlteracao())){
							
							if (gcomVelho != null){
								Filtro filtro = gcomVelho.retornaFiltroRegistroOperacao();

								try {
									Collection coll = RepositorioUtilHBM.getInstancia().pesquisar(filtro, gcomVelho.getClass().getName());
									Iterator iter = coll.iterator();
									if (iter.hasNext()){
										gcomVelho = (ObjetoTransacao) iter.next();	
									}
									
								} catch (ErroRepositorioException e) {
									e.printStackTrace();
								}
								
							}							
							
							Collection<TabelaLinhaColunaAlteracao> alteracoes = compareObjetoTransacao(gcomNovo, gcomVelho, null);
							if (alteracoes != null && !alteracoes.isEmpty()){
								tabelaLinhaColunaAlteracoes.addAll(alteracoes);
							}
						}					
					}
					if ((gcomVelho != null && gcomVelho.temControleAlteracao()) || 
							(gcomNovo != null && gcomNovo.temControleAlteracao())){
						
						if (gcomVelho != null){
							gcomVelho = consultarObjetoCarregandoAtributo(gcomVelho);								
						}							
						
						Collection<TabelaLinhaColunaAlteracao> alteracoes = compareObjetoTransacao(gcomNovo, gcomVelho, null);
						if (alteracoes != null && !alteracoes.isEmpty()){
							tabelaLinhaColunaAlteracoes.addAll(alteracoes);
						}
					}					
					
				}
			}
			
		} else if (retornoMetodoNovo instanceof Collection || retornoMetodoVelho instanceof Collection){
			ControleAlteracaoColecao controle = ControleAlteracaoColecao.gerarControle(
				(Collection) retornoMetodoVelho, (Collection) retornoMetodoNovo);

			
			if (!controle.getAlteradas().isEmpty()){
				for (Iterator iter = controle.getAlteradas().keySet().iterator(); iter
						.hasNext();) {
					Object elementAntes = iter.next();
					Object elementDepois = controle.getAlteradas().get(elementAntes);
					Collection<TabelaLinhaColunaAlteracao> alteracoes = new Vector();
					verificarDiferencaAtributo(alteracoes, nomeAtributo, novo, elementDepois,
							velho, elementAntes, operacaoEfetuada, usuarios, true);
					if (alteracoes != null && !alteracoes.isEmpty()){
						salvarModificacao((ObjetoTransacao)elementDepois, operacaoEfetuada, usuarios, 
								alteracoes, AlteracaoTipo.ALTERACAO);															
					}
				} 
			}
			if (!controle.getAdicionadas().isEmpty()){
				for (Iterator iter = controle.getAdicionadas().iterator(); iter
						.hasNext();) {
					Object elementDepois = iter.next();
					Collection<TabelaLinhaColunaAlteracao> alteracoes = new Vector();
					verificarDiferencaAtributo(alteracoes, nomeAtributo, novo, elementDepois,
							velho, null, operacaoEfetuada, usuarios ,true);				
					if (alteracoes != null && !alteracoes.isEmpty()){
						salvarModificacao((ObjetoTransacao)elementDepois, operacaoEfetuada, usuarios, 
								alteracoes, AlteracaoTipo.INCLUSAO);															
					}
				} 
			}
			if (!controle.getRemovidas().isEmpty()){
				for (Iterator iter = controle.getRemovidas().iterator(); iter
						.hasNext();) {
					ObjetoTransacao elementAntes = (ObjetoTransacao) iter.next();
					Collection<TabelaLinhaColunaAlteracao> alteracoes = new Vector();
					verificarDiferencaAtributo(alteracoes, nomeAtributo, novo, null,
							velho, elementAntes, operacaoEfetuada, usuarios, true);
					
					elementAntes.setId2(novo.getId2());
					
					if (alteracoes != null && !alteracoes.isEmpty()){
						salvarModificacao(elementAntes, operacaoEfetuada, usuarios, 
								alteracoes, AlteracaoTipo.EXCLUSAO);															
					}
					
				} 
			}
		} else {
			if (retornoMetodoNovo != null && retornoMetodoVelho != null) {
				if (!retornoMetodoNovo.equals(retornoMetodoVelho)) {

					TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = gerarTabelaLinhaColunaAlteracao(nomeAtributo, 
							novo.getClass(),retornoMetodoNovo, retornoMetodoVelho);
					if (tabelaLinhaColunaAlteracao != null)
						tabelaLinhaColunaAlteracoes.add(tabelaLinhaColunaAlteracao);

				}
				// se o novo for diferente de null e velho for igual a null
			} else if ((retornoMetodoNovo != null && retornoMetodoVelho == null) ||	(retornoMetodoNovo == null && retornoMetodoVelho != null)) {
				Class classe = null;
				if (novo != null){
					classe = novo.getClass();
				} else {
					classe = velho.getClass();
				}
				
				TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = gerarTabelaLinhaColunaAlteracao(nomeAtributo, 
						classe, retornoMetodoNovo,	retornoMetodoVelho);
				if (tabelaLinhaColunaAlteracao != null){
					tabelaLinhaColunaAlteracoes.add(tabelaLinhaColunaAlteracao);
				}
			}
		}
	}
	
	public static ObjetoTransacao consultarObjetoCarregandoAtributo(ObjetoTransacao obj){
		Filtro filtro = obj.retornaFiltroRegistroOperacao();

		try {
			Collection coll = RepositorioUtilHBM.getInstancia().pesquisar(filtro, obj.getClass().getName());
			Iterator iter = coll.iterator();
			if (iter.hasNext()){
				obj = (ObjetoTransacao) iter.next();	
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}		
		return obj;
	}

	/**
	 * M�todo que cria uma TabelaLinhaColunaAlteracao
	 * 
	 * @param nomeAtributo
	 * @param classe
	 * @param valorNovo
	 * @param valorVelho
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private TabelaLinhaColunaAlteracao gerarTabelaLinhaColunaAlteracao(
			String nomeAtributo, Class classe,
			Object valorNovo, Object valorVelho) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		
		TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = null;
		// Dividir nomeAtributo pelo . e passar o ultimo valor para o proximo metodo
		int indiceUltimoPonto = nomeAtributo.lastIndexOf(".");
		if (indiceUltimoPonto != -1){
			nomeAtributo = nomeAtributo.substring(indiceUltimoPonto + 1);
		}
		String nomeColuna = HibernateUtil.getNameColumn(classe, nomeAtributo);
		
		if (valorVelho == null) {
			valorVelho = "";
		}
		if (valorNovo == null){
			valorNovo = "";
		}
		
		if (nomeColuna != null && !(valorVelho.equals("") && valorNovo.equals(""))) {

			TabelaColuna tabelaColuna = new TabelaColuna();
			tabelaColuna.setColuna(nomeColuna);
			tabelaColuna.setDescricaoColuna(nomeColuna);

			tabelaLinhaColunaAlteracao = new TabelaLinhaColunaAlteracao();
			tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
			tabelaLinhaColunaAlteracao.setConteudoColunaAnterior(valorVelho);
			tabelaLinhaColunaAlteracao.setConteudoColunaAtual(valorNovo);
			tabelaLinhaColunaAlteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
		}
		return tabelaLinhaColunaAlteracao;
	}

	/**
	 * M�todo que intercepta o save do hibernte utilizado pra registrar a
	 * operacao
	 * 
	 * @author Thiago Toscano
	 * @date 25/03/2006
	 * 
	 * @param arg0
	 *            objeto a ser salvo
	 * @param arg1
	 *            a chame primaria do objeto a ser salvo
	 * @param arg2
	 *            array de todos os valores do objeto
	 * @param arg3
	 *            array dos nomes de as propriedades do objeto
	 * @param arg4
	 *            array dos tipos das propriedades do objeto
	 * @return
	 * @throws CallbackException
	 */
	public boolean onSave(Object arg0, Serializable arg1, Object[] arg2,String[] arg3, Type[] arg4) throws CallbackException {

		translate(arg0, arg1, arg2, arg3, arg4);

		if (arg0 instanceof ObjetoTransacao) {
			ObjetoTransacao objetoTransacao = (ObjetoTransacao) arg0;

			if (objetoTransacao.getUsuarioAcaoUsuarioHelp() == null	|| objetoTransacao.getUsuarioAcaoUsuarioHelp().isEmpty()) {
				return true;// throw new
				// CallbackException("atencao.usuario.vazia");
			}
			if (objetoTransacao.getOperacaoEfetuada() == null) {
				return true;// throw new
				// CallbackException("atencao.operacaoEfetuada.vazia");
			}
			if (objetoTransacao.getOperacaoEfetuada().getOperacao() == null) {
				return true;// throw new
				// CallbackException("atencao.operacao.vazia");
			}
			
			registrarInclusao(arg0, arg3);
		}		

		return true;
	}
	
	private void registrarInclusao(Object obj, String[] arg){
		if (obj instanceof ObjetoTransacao){
			ObjetoTransacao objetoTransacao = (ObjetoTransacao) obj;
			
			// verifica se a classe possui controle de registrar transacao
			if (!objetoTransacao.temControleAlteracao()){
				return;
			}

			if (objetoTransacao.getIdAntigo() == null){
				
				try {
					
					String nomeTabela = HibernateUtil.getNameTable(objetoTransacao.getClass());
		
					FiltroTabela filtroTabela = new FiltroTabela();
					filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.NOME, nomeTabela));
		
					Collection coll = getControladorUtil().pesquisar(filtroTabela,Tabela.class.getSimpleName());
					Tabela tabela = null;
					if (coll != null && !coll.isEmpty()) {
						tabela = (Tabela) coll.iterator().next();
					} else {

						return;
						/*
						 * Foi acordado retirar a inclus�o autom�tica de tabelas e colunas n�o encontradas
						 * 					Francisco Nascimento, 05/03/08
						 * 						
						tabela = new Tabela();
						tabela.setNomeTabela(nomeTabela);
						tabela.setDescricao(nomeTabela);
						tabela.setUltimaAlteracao(new Date(System.currentTimeMillis()));
						this.getControladorUtil().inserir(tabela);
						 */
					}
					
					objetoTransacao.getOperacaoEfetuada().preencherDadosAdicionais(objetoTransacao);
					
					AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
					alteracaoTipo.setId(AlteracaoTipo.INCLUSAO);
		
					TabelaLinhaAlteracao tabelaLinhaAlteracao = new TabelaLinhaAlteracao();
					tabelaLinhaAlteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
					
					if (objetoTransacao.getId2() != null)
						tabelaLinhaAlteracao.setId2(objetoTransacao.getId2());
					
					tabelaLinhaAlteracao.setTabela(tabela);
					tabelaLinhaAlteracao.setAlteracaoTipo(alteracaoTipo);
					
					Collection<TabelaLinhaColunaAlteracao> collTabelaLinhaColunaAlteracao = 
						new ArrayList<TabelaLinhaColunaAlteracao>(); 
					
					// Adicionando uma linha com a descricao do objeto, caso esta descricao n�o seja vazia
					String[] keys = objetoTransacao.retornaCamposChavePrimaria();
					for (int i = 0; keys != null && i < keys.length; i++) {
						String id = keys[i];
						//String nomeMetodoKey = "get" + id.substring(0, 1).toUpperCase() + id.substring(1, id.length());

						//Method metodoNovo = objetoTransacao.getClass().getMethod(nomeMetodoKey, (Class[])null);
						//Object retornoKeyNovo = metodoNovo.invoke(objetoTransacao, (Object[])null);
					
						String descricaoNovo = consultarDescricao(objetoTransacao);
						
						if (!descricaoNovo.equals("")){
															
							TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = gerarTabelaLinhaColunaAlteracao(
									id, objetoTransacao.getTipoAtributo(id),descricaoNovo, "");
							if (tabelaLinhaColunaAlteracao != null)
								tabelaLinhaColunaAlteracao.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
							
								// Pesquisa a Tabelacoluna
								TabelaColuna tabelaColuna = gerarTabelaColuna(tabela, id, objetoTransacao);
								tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
							
								collTabelaLinhaColunaAlteracao.add(tabelaLinhaColunaAlteracao);
						}
						break;
					}		
		
					collTabelaLinhaColunaAlteracao.addAll(gerarTabelaLinhaColunaAlteracaoInclusaoOuExclusao(true, false, 
							objetoTransacao, tabelaLinhaAlteracao));
					// fim: adicionando descricao como alteracao
		
					Integer[] ids = getIds(objetoTransacao);
					if (ids != null && ids.length > 0) {
						tabelaLinhaAlteracao.setId1(ids[0]);
					}
					if (ids != null && ids.length > 1) {
						tabelaLinhaAlteracao.setId2(ids[1]);
					}
					
					if (objetoTransacao.getOperacaoEfetuada().getArgumentoValor() <= 0){
						Object objId = objetoTransacao.get("id");
						if (objId instanceof Integer){
							objetoTransacao.getOperacaoEfetuada().setArgumentoValor((Integer) objId);	
						} else {
						}
						 
					}
					
					this.getControladorTransacao().inserirOperacaoEfetuada(
							objetoTransacao.getUsuarioAcaoUsuarioHelp(),
							objetoTransacao.getOperacaoEfetuada(),
							tabelaLinhaAlteracao, collTabelaLinhaColunaAlteracao);
					this.salvarTabelaLinhaAlteracaoPrincipal(objetoTransacao.getOperacaoEfetuada(), objetoTransacao);
					objetoTransacao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
				} catch (Exception e) {
					throw new CallbackException("atencao.registrar.operacao", e);
				}
			} else {
				verificarObjetoAlterado(objetoTransacao,arg);	
			}
		}
	}
	

	/**
	 * M�todo que intercepta o alterar do hibernate utilizado para registrar a
	 * operacao
	 * 
	 * @author Administrador
	 * @date 25/03/2006
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @return
	 * @throws CallbackException
	 */
	public boolean onFlushDirty(Object arg0, Serializable arg1, Object[] arg2,
			Object[] arg3, String[] arg4, Type[] arg5) throws CallbackException {

		translate(arg0, arg1, arg2, arg4, arg5);

		if (arg0 instanceof ObjetoTransacao) {
			ObjetoTransacao novo = (ObjetoTransacao) arg0;

			if (novo.getUsuarioAcaoUsuarioHelp() == null || novo.getUsuarioAcaoUsuarioHelp().isEmpty()) {
				return true;// throw new
				// CallbackException("atencao.usuario.vazia");
			}
			if (novo.getOperacaoEfetuada() == null) {
				return true;// throw new
				// CallbackException("atencao.operacaoEfetuada.vazia");
			}
			if (novo.getOperacaoEfetuada().getOperacao() == null) {
				return true;// throw new
				// CallbackException("atencao.operacao.vazia");
			}
			verificarObjetoAlterado(novo,arg4);
		}

		return true;
	}

	/**
	 * M�todo que intercepta o delete do hibernte utilizado pra registrar a
	 * operacao
	 * 
	 * @author Thiago Toscano
	 * @date 25/03/2006
	 * 
	 * @param arg0
	 *            objeto a ser salvo
	 * @param arg1
	 *            a chame primaria do objeto a ser salvo
	 * @param arg2
	 *            array de todos os valores do objeto
	 * @param arg3
	 *            array dos nomes de as propriedades do objeto
	 * @param arg4
	 *            array dos tipos das propriedades do objeto
	 * @return
	 * @throws CallbackException
	 */
	public void onDelete(Object arg0, Serializable arg1, Object[] arg2,	String[] arg3, Type[] arg4) throws CallbackException {
		if (arg0 instanceof ObjetoTransacao) {
			ObjetoTransacao objetoTransacao = (ObjetoTransacao) arg0;

			if (objetoTransacao.getUsuarioAcaoUsuarioHelp() == null	|| objetoTransacao.getUsuarioAcaoUsuarioHelp().isEmpty()) {
				return;
				// throw new CallbackException("atencao.usuario.vazia");
			}
			if (objetoTransacao.getOperacaoEfetuada() == null) {
				return;
				// throw new
				// CallbackException("atencao.operacaoEfetuada.vazia");
			}
			if (objetoTransacao.getOperacaoEfetuada().getOperacao() == null) {
				return;
				// throw new CallbackException("atencao.operacao.vazia");
			}
			registrarExclusao(arg0);
		}
	}
	
	public void registrarExclusao(Object obj){
		if (obj instanceof ObjetoTransacao){
			
			ObjetoTransacao objetoTransacao = (ObjetoTransacao) obj;
			
			// verifica se a classe possui controle de registrar transacao
			if (!objetoTransacao.temControleAlteracao()){
				return;
			}			
			
			try {

				String nomeTabela = HibernateUtil.getNameTable(objetoTransacao.getClass());

				FiltroTabela filtroTabela = new FiltroTabela();
				filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.NOME, nomeTabela));

				Collection coll = getControladorUtil().pesquisar(filtroTabela,Tabela.class.getSimpleName());
				Tabela tabela = null;
				if (coll != null && !coll.isEmpty()) {
					tabela = (Tabela) coll.iterator().next();
				} else {

					return;
					/*
					 * Foi acordado retirar a inclus�o autom�tica de tabelas e colunas n�o encontradas
					 * 					Francisco Nascimento, 05/03/08
					 * 				
					tabela = new Tabela();
					tabela.setNomeTabela(nomeTabela);
					tabela.setDescricao(nomeTabela);
					tabela.setUltimaAlteracao(new Date(System.currentTimeMillis()));
					this.getControladorUtil().inserir(tabela);
					*/
				}

				objetoTransacao.getOperacaoEfetuada().preencherDadosAdicionais(objetoTransacao);
				
				AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
				alteracaoTipo.setId(AlteracaoTipo.EXCLUSAO);

				TabelaLinhaAlteracao tabelaLinhaAlteracao = new TabelaLinhaAlteracao();
				tabelaLinhaAlteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
				tabelaLinhaAlteracao.setTabela(tabela);
				tabelaLinhaAlteracao.setAlteracaoTipo(alteracaoTipo);

				Collection<TabelaLinhaColunaAlteracao> collTabelaLinhaColunaAlteracao = 
					new ArrayList<TabelaLinhaColunaAlteracao>();
				// Adicionando uma linha com a descricao do objeto, caso esta descricao n�o seja vazia
				String[] keys = objetoTransacao.retornaCamposChavePrimaria();
				for (int i = 0; keys != null && i < keys.length; i++) {
					String id = keys[i];
					
					//String nomeMetodoKey = "get"
						//	+ id.substring(0, 1).toUpperCase()
							//+ id.substring(1, id.length());

					//Method metodoNovo = objetoTransacao.getClass().getMethod(nomeMetodoKey, (Class[])null);
					//Object retornoKeyNovo = metodoNovo.invoke(objetoTransacao, (Object[])null);
				
					String descricaoNovo = consultarDescricao(objetoTransacao);
					
					if (!descricaoNovo.equals("")){
														
						TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = gerarTabelaLinhaColunaAlteracao(
								id, objetoTransacao.getTipoAtributo(id),"", descricaoNovo);
						if (tabelaLinhaColunaAlteracao != null)
							tabelaLinhaColunaAlteracao.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
						
							// Pesquisa a Tabelacoluna
							TabelaColuna tabelaColuna = gerarTabelaColuna(tabela, id, objetoTransacao);
							tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
						
							collTabelaLinhaColunaAlteracao.add(tabelaLinhaColunaAlteracao);
					}
					break;
				}		
	
				collTabelaLinhaColunaAlteracao.addAll(
						gerarTabelaLinhaColunaAlteracaoInclusaoOuExclusao(false, true, objetoTransacao, tabelaLinhaAlteracao));
				// fim: adicionando descricao como alteracao
				
				Integer[] ids = getIds(objetoTransacao);
				if (ids != null && ids.length > 0) {
					tabelaLinhaAlteracao.setId1(ids[0]);
				}
				if (ids != null && ids.length > 1) {
					tabelaLinhaAlteracao.setId2(ids[1]);
				}

				
				this.getControladorTransacao().inserirOperacaoEfetuada(
						objetoTransacao.getUsuarioAcaoUsuarioHelp(),
						objetoTransacao.getOperacaoEfetuada(),
						tabelaLinhaAlteracao, collTabelaLinhaColunaAlteracao);
				this.salvarTabelaLinhaAlteracaoPrincipal(objetoTransacao.getOperacaoEfetuada(), objetoTransacao);
			} catch (Exception e) {
				throw new CallbackException("atencao.registrar.operacao", e);
			}			
		}
	}

	private Integer[] getIds(ObjetoGcom objetoTransacao)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		
		Integer[] retorno = null;
		String[] arg = objetoTransacao.retornaCamposChavePrimaria();
		retorno = new Integer[arg.length];

		// pegando o primeiro id do objeto passado
		if (arg != null && arg.length > 0) {
			String id = arg[0];
			String nomeMetodoKey = "get" + id.substring(0, 1).toUpperCase()	+ id.substring(1, id.length());
			Method metodo = objetoTransacao.getClass().getMethod(nomeMetodoKey,(Class[])null);
			// pega o retonro do primeiro id
			Object retornoKey = metodo.invoke(objetoTransacao, (Object[])null);
			// se for interger � pq foi um id
			if (retornoKey instanceof Integer) {
				retorno[0] = (Integer) retornoKey;
			} else {
				// se nao foi integer eh pq ele tem um id composto e tem outro
				// objeto como id
				if (retornoKey instanceof ObjetoGcom) {
					ObjetoGcom objetoGcom = (ObjetoGcom) retornoKey;
					// pega os ids do objeto que � id do objeto passado
					String[] camposChavePrimaria = objetoGcom.retornaCamposChavePrimaria();
					if (camposChavePrimaria != null	&& camposChavePrimaria.length > 0) {
						String idCamposChavePrimaria = camposChavePrimaria[0];
						String nomeCamposChavePrimaria = "get"+ idCamposChavePrimaria.substring(0, 1).toUpperCase()
								+ idCamposChavePrimaria.substring(1,idCamposChavePrimaria.length());
						Method metodoCamposChavePrimaria = objetoGcom.getClass().getMethod(nomeCamposChavePrimaria,	(Class[])null);
						// invoca o id do objeto que � id do objeto passado
						Object retornoKeyCamposChavePrimaria = metodoCamposChavePrimaria.invoke(objetoGcom, (Object[])null);
						if (retornoKeyCamposChavePrimaria instanceof Integer) {
							retorno[0] = (Integer) retornoKeyCamposChavePrimaria;
						} else {
							retorno[0] = new Integer(0);
						}
					}
				} else {
					retorno[0] = new Integer(0);
				}
			}
		}

		// pegando o segundo id
		if (arg != null && arg.length > 1) {
			String id = arg[1];
			String nomeMetodoKey = "get" + id.substring(0, 1).toUpperCase()	+ id.substring(1, id.length());
			Method metodo = objetoTransacao.getClass().getMethod(nomeMetodoKey,	(Class[])null);
			Object retornoKey = metodo.invoke(objetoTransacao, (Object[])null);
			if (retornoKey instanceof Integer) {
				retorno[1] = (Integer) retornoKey;
			} else {
				// se nao foi integer eh pq ele tem um id composto e tem outro
				// objeto como id
				if (retornoKey instanceof ObjetoGcom) {
					ObjetoGcom objetoGcom = (ObjetoGcom) retornoKey;
					// pega os ids do objeto que � id do objeto passado
					String[] camposChavePrimaria = objetoGcom.retornaCamposChavePrimaria();
					
					if (camposChavePrimaria != null	&& camposChavePrimaria.length > 0) {
						String idCamposChavePrimaria = camposChavePrimaria[0];
						String nomeCamposChavePrimaria = "get"+ idCamposChavePrimaria.substring(0, 1).toUpperCase()+ idCamposChavePrimaria.substring(1,idCamposChavePrimaria.length());
						Method metodoCamposChavePrimaria = objetoGcom.getClass().getMethod(nomeCamposChavePrimaria,(Class[])null);
						// invoca o id do objeto que � id do objeto passado
						Object retornoKeyCamposChavePrimaria = metodoCamposChavePrimaria.invoke(objetoGcom, (Object[])null);
						
						if (retornoKeyCamposChavePrimaria instanceof Integer) {
							retorno[1] = (Integer) retornoKeyCamposChavePrimaria;
						} else {
							retorno[1] = new Integer(0);
						}
					}
				} else {
					retorno[1] = new Integer(0);
				}
			}
		}

		return retorno;
	}

	private Collection gerarTabelaLinhaColunaAlteracaoInclusaoOuExclusao(
			boolean insersao, boolean exclusao,
			ObjetoTransacao objetoTransacao,
			TabelaLinhaAlteracao tabelaLinhaAlteracao)
			throws ControladorException, NoSuchMethodException {
		
		Collection<TabelaLinhaColunaAlteracao> retorno = new Vector();

		String nomeTabela = HibernateUtil.getNameTable(objetoTransacao.getClass());

		FiltroTabela filtroTabela = new FiltroTabela();
		filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.NOME,nomeTabela));
		Collection coll = getControladorUtil().pesquisar(filtroTabela,Tabela.class.getSimpleName());
		Tabela tabela = null;
		if (coll != null && !coll.isEmpty()) {
			tabela = (Tabela) coll.iterator().next();
		} else {

			return null;
			/*
			 * Foi acordado retirar a inclus�o autom�tica de tabelas e colunas n�o encontradas
			 * 					Francisco Nascimento, 05/03/08
			 * 		
			tabela = new Tabela();
			tabela.setNomeTabela(nomeTabela);
			tabela.setDescricao(nomeTabela);
			tabela.setUltimaAlteracao(new Date(System.currentTimeMillis()));
			this.getControladorUtil().inserir(tabela);
			*/
		}

		Class classe = objetoTransacao.getClass();
		Method[] metodos = classe.getMethods();

		Method metodo;
		String nomeMetodo;
		String nomeAtributo;
		String[] atributosRegistro = objetoTransacao.retornarAtributosSelecionadosRegistro();
		
		for (int i = 0; i < metodos.length; i++) {
			metodo = metodos[i];
			nomeMetodo = metodo.getName();
			// se for get e nao for getClass e nao for getUltimaAlteracao
			if (nomeMetodo.startsWith("get") && !nomeMetodo.startsWith("getClass") && !nomeMetodo.startsWith("getUltimaAlteracao")) {
				
				nomeAtributo = nomeMetodo.substring(3, nomeMetodo.length());
				if (!nomeAtributo.equals("")){
					nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase()+ nomeAtributo.substring(1, nomeAtributo.length());	
				} else {
					continue;
				}				

				// verificando se houve selecao de atributos para registro
				boolean atributoEncontrado = false;
				if (atributosRegistro != null && nomeAtributo != null && !nomeAtributo.equals("")){
					for (int j = 0; j < atributosRegistro.length; j++) {
						if (nomeAtributo.equalsIgnoreCase(atributosRegistro[j])){
							atributoEncontrado = true;	
							break;
						}
					}
					if (!atributoEncontrado){
						continue; // by pass this attribute
					}
				}
				
				try {
					// invocando o metodo do objeto velho para pegar o retorno
					Object retornoMetodoVelho = metodo.invoke(objetoTransacao,(Object[])null);

					// verifica se o retorno eh do tipo conhecido
					if ((retornoMetodoVelho instanceof ObjetoGcom)
							|| (metodo.getReturnType().isPrimitive())
							|| (retornoMetodoVelho instanceof Short)
							|| (retornoMetodoVelho instanceof Byte)
							|| (retornoMetodoVelho instanceof Character)
							|| (retornoMetodoVelho instanceof Integer)
							|| (retornoMetodoVelho instanceof Long)
							|| (retornoMetodoVelho instanceof Double)
							|| (retornoMetodoVelho instanceof Float)
							|| (retornoMetodoVelho instanceof String)
							|| (retornoMetodoVelho instanceof Boolean)
							|| (retornoMetodoVelho instanceof BigDecimal)
							|| (retornoMetodoVelho instanceof Date)) {
						// invocando o metodo do objeto novo para pegar o
						// retorno
						Object retornoMetodoNovo = metodo.invoke(objetoTransacao, (Object[])null);
						TabelaColuna tabelaColuna = gerarTabelaColuna(tabela,nomeAtributo, objetoTransacao);
						
						if (retornoMetodoNovo != null && tabelaColuna != null) {

							Object retornoMetodo = "";
							
							if (retornoMetodoNovo instanceof ObjetoGcom) {
								ObjetoGcom objetoGcom = (ObjetoGcom) retornoMetodoNovo;
								Integer[] ids = getIds(objetoGcom);
								if (ids != null && ids.length > 0) {
									retornoMetodo = ids[0] + "";
								}
								if (ids != null && ids.length > 1) {
									retornoMetodo = retornoMetodo + "" + ids[1];
								}
								// pegando a descricao do objeto gcom
								// Para obtencao da descricao, � necess�rio se ter um  objeto do 
								// tipo ObjetoTransacao, pois ser� usado um m�todo definido nesta classe 
								// (getDescricaoParaRegistroTransacao) 
								if (objetoGcom instanceof ObjetoTransacao){
									retornoMetodo = consultarDescricao((ObjetoTransacao) objetoGcom);
//									ObjetoTransacao objTransNovo = (ObjetoTransacao) objetoGcom;
//									ObjetoTransacao objTransVelho = (ObjetoTransacao) retornoMetodoVelho;
//									if (retornoMetodo == null || retornoMetodo.equals("") || retornoMetodo.equals("null")){
//										tabelaLinhaColunaAlteracoes = compareObjetoTransacao(
//												objTransNovo, objTransVelho , objTransNovo.retornarAtributosSelecionadosRegistro());
//										retorno.addAll(tabelaLinhaColunaAlteracoes);
//									}
								}

							} else {
								retornoMetodo = retornoMetodoNovo;
							}
							
							if (retornoMetodo != null && !retornoMetodo.equals("")){
								TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = new TabelaLinhaColunaAlteracao();
								tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
								tabelaLinhaColunaAlteracao.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
								tabelaLinhaColunaAlteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
								tabelaLinhaColunaAlteracao.setConteudoColunaAtual(" ");
								tabelaLinhaColunaAlteracao.setConteudoColunaAnterior(" ");
								
								if (insersao) {
									tabelaLinhaColunaAlteracao.setConteudoColunaAtual(retornoMetodo);
								}

								if (exclusao) {
									tabelaLinhaColunaAlteracao.setConteudoColunaAnterior(retornoMetodo);
								}
								
								retorno.add(tabelaLinhaColunaAlteracao);								
							}
							
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					// e.printStackTrace();
					// nunca entra aqui
					// excecao levantada quando o metodo invocado nao existe no
					// objeto passado
					// o metodo invocado foi pego da lista do objeto que se
					// deseja utilizar
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					// e.printStackTrace();
					// nunca entra aqui
					// excecao levantada quando o metodo invocado nao existe no
					// objeto passado
					// o metodo invocado foi pego da lista do objeto que se
					// deseja utilizar
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					// e.printStackTrace();
					// nunca entra aqui
					// excecao levantada quando o metodo invocado nao existe no
					// objeto passado
					// o metodo invocado foi pego da lista do objeto que se
					// deseja utilizar
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return retorno;
	}

	/**
	 * M�todo que gera TabelaLinhaAlteracao a partir do nome do atributo de uma
	 * classe e seu retorno
	 * 
	 * @author Administrador
	 * @date 04/04/2006
	 * 
	 * @param nomeAtributo
	 * @param retonro
	 * @return
	 * @throws ControladorException
	 */
	private TabelaColuna gerarTabelaColuna(Tabela tabela, String nomeAtributo,ObjetoTransacao objetoTransacao) throws ControladorException {
		
		TabelaColuna retorno = null;

		String nomeColuna = HibernateUtil.getNameColumn(objetoTransacao.getClass(), nomeAtributo);
		
		if (nomeColuna != null) {
			FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
			filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.TABELA_ID, tabela.getId()));
			filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.COLUNA, nomeColuna));
			Collection coll = this.getControladorUtil().pesquisar(filtroTabelaColuna, TabelaColuna.class.getSimpleName());
			
			if (coll != null && !coll.isEmpty()) {
				retorno = (TabelaColuna) coll.iterator().next();
			} else {

				return null;
				/*
				 * Foi acordado retirar a inclus�o autom�tica de tabelas e colunas n�o encontradas
				 * 					Francisco Nascimento, 05/03/08
				 * 
				
				retorno = new TabelaColuna();
				retorno.setColuna(nomeColuna);
				retorno.setDescricaoColuna(nomeColuna);
				retorno.setTabela(tabela);
				retorno.setUltimaAlteracao(new Date(System.currentTimeMillis()));
				
				Integer idTabelaColuna = (Integer) this.getControladorUtil().inserir(retorno);
				retorno.setId(idTabelaColuna);
				// inserindo a ordem de exibicao desta tabela coluna
				Operacao operacao = objetoTransacao.getOperacaoEfetuada().getOperacao();
				
				OperacaoOrdemExibicao ordemColuna = new OperacaoOrdemExibicao();
				ordemColuna.setComp_id(new OperacaoOrdemExibicaoPK(new Integer(operacao.getId()),
					tabela.getId()));
				ordemColuna.setTabelaColuna(retorno);
				this.getControladorUtil().inserir(ordemColuna);
				
				 */
			}
		}

		return retorno;
	}

	public int[] findDirty(Object arg0, Serializable arg1, Object[] arg2,Object[] arg3, String[] arg4, Type[] arg5) {
		return null;
	}

	public void afterTransactionBegin(Transaction arg0) {
		// System.out.println("2");
	}

	public void afterTransactionCompletion(Transaction arg0) {
		// System.out.println("3");
	}

	public void beforeTransactionCompletion(Transaction arg0) {
		// System.out.println("4");
	}

	public Object getEntity(String arg0, Serializable arg1)	throws CallbackException {
		// System.out.println("5");
		return null;
	}

	public String getEntityName(Object arg0) throws CallbackException {
		// System.out.println("6");
		return null;
	}

	public Object instantiate(String arg0, EntityMode arg1, Serializable arg2)throws CallbackException {
		// System.out.println("7");
		return null;
	}

	public Boolean isTransient(Object arg0) {
		// System.out.println("8");
		return new Boolean(false);
	}

	public void onCollectionRecreate(Object arg0, Serializable arg1)throws CallbackException {
//		 System.out.println(">>>> onCollectionRecreate");
	}

	public void onCollectionRemove(Object arg0, Serializable arg1)throws CallbackException {
//		 System.out.println(">>>>> onCollectionRemove: " + arg1);
	}

	public void onCollectionUpdate(Object arg0, Serializable arg1)throws CallbackException {
//		 System.out.println(">>>>> onCollectionUpdate");
	}

	public boolean onLoad(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		translate(arg0, arg1, arg2, arg3, arg4);
		return true;
	}

	public String onPrepareStatement(String arg0) {
		if (arg0.toUpperCase().startsWith("INSERT".toUpperCase())) {
			 arg0 = arg0.toUpperCase().replace("INSERT INTO".toUpperCase(), "INSERT /*+ append */ INTO ".toUpperCase());
		}
		return arg0;
	}

	public void postFlush(Iterator arg0) throws CallbackException {
		// System.out.println("17");
	}

	public void preFlush(Iterator arg0) throws CallbackException {
		// System.out.println("preFlush");
	}

	/**
	 * Esta fun��o faz o translate dos dados que entram e saem da base O
	 * translate nada mais � do que um replace dos caracteres especiais por
	 * caracteres normais
	 * 
	 * @author Rodrigo Silveira
	 * @date 12/07/2006
	 * 
	 * @param arg0
	 *            entidade
	 * @param arg1
	 *            chave prim�ria
	 * @param arg2
	 *            valor das propriedades
	 * @param arg3
	 *            nomes das propriedades
	 * @param arg4
	 *            tipos
	 */
	private void translate(Object entidade, Serializable chavePrimaria,Object[] valorPropriedades, String[] nomesPropriedades, Type[] tipos) {

		for (int i = 0; i < valorPropriedades.length; i++) {
			Object valorPropriedade = valorPropriedades[i];

			if (valorPropriedade instanceof String) { // Pega cada propriedade
				// para verificar se �
				// String
				String valorPropriedadeString = (String) valorPropriedade;

				if (valorPropriedadeString != null
						&& !valorPropriedadeString.trim().equals("")
						&& !valorPropriedadeString
								.matches("[\\w&&[^��������������������������]]*")) { // Verifica
					// se
					// existe
					// algum
					// caracter
					// especial
					// na
					// String

					// Faz o replace para tirar os caracteres especiais da
					// String
					valorPropriedades[i] = ((String) valorPropriedade).trim()
							.replace('�', 'A').replace('�', 'O').replace('�',
									'A').replace('�', 'E').replace('�', 'I')
							.replace('�', 'U').replace('�', 'A').replace('�',
									'A').replace('�', 'E').replace('�', 'O')
							.replace('�', 'U').replace('�', 'C').replace('�',
									'A').replace('�', 'O').replace('�', 'A')
							.replace('�', 'E').replace('�', 'I').replace('�',
									'U').replace('�', 'A').replace('�', 'A')
							.replace('�', 'E').replace('�', 'O').replace('�',
									'U').replace('�', 'C'); // �������������

				}
			}

		}
	}

	
	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 21/08/2006
	 *
	 * @param colecaoObjetosAtualizados
	 * @param classMetadata
	 * @param alteracoes
	 * @param idOperacao
	 */
	public  void registrarTransacaoHQL(Collection colecaoObjetosAtualizados, ClassMetadata classMetadata, Map<String,Object> alteracoes, Integer idOperacao){
		
		String[] propriedades = classMetadata.getPropertyNames();
		Object[] valorPropriedades = alteracoes.values().toArray();
		
		valorPropriedades = translateRegistrarTransacaoHQL(valorPropriedades);
		
		if(colecaoObjetosAtualizados != null){
			for(Object objetoAtualizado : colecaoObjetosAtualizados){
				if(objetoAtualizado instanceof ObjetoTransacao){
					
					ObjetoTransacao objetoTransacao = (ObjetoTransacao) objetoAtualizado;
					

					//------------ REGISTRAR TRANSA��O ----------------
					Usuario usuarioLogado = Usuario.USUARIO_TESTE;
					RegistradorOperacao registradorOperacao = new RegistradorOperacao(idOperacao,new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				    Operacao operacaoRegistrarTransacao = new Operacao();
				    operacaoRegistrarTransacao.setId(idOperacao);
				    OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
				    operacaoEfetuada.setOperacao(operacaoRegistrarTransacao);
				    objetoTransacao.setOperacaoEfetuada(operacaoEfetuada);
				    objetoTransacao.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				    registradorOperacao.registrarOperacao(objetoTransacao);
				    //------------ REGISTRAR TRANSA��O ----------------					

				    //------------- ATUALIZAR DADOS DO OBJETO ALTERADO ---------------
				    if(propriedades != null && propriedades.length > 0){
						
						for (int i = 0; i < propriedades.length; i++) {
							
							if(alteracoes.containsKey(propriedades[i])){
								Class classe = objetoTransacao.getClass();
								String nomeAtributo = propriedades[i]; 
								String nomeMetodo;
								nomeMetodo = "set" + nomeAtributo.substring(0, 1).toUpperCase()+ nomeAtributo.substring(1, nomeAtributo.length()); 
								Method metodo;
								
								try {
									
									/*metodo = classe.getMethod(nomeMetodo, (Class[]) null);
									Object[] args = {alteracoes.get(propriedades[i])};
									metodo.invoke(objetoTransacao,args);
									*/
									Method[] metodos = classe.getMethods();

									if(metodos != null && metodos.length > 0){
										for (int j = 0; j < metodos.length; j++) {
											metodo = metodos[j];
											if(metodo.getName().equals(nomeMetodo)){
												Object[] args = {alteracoes.get(propriedades[i])};
												metodo.invoke(objetoTransacao,args);
												break;
											}
										}
									}
									
								} catch (SecurityException e) {
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								} 
							}
						}
					}
				    //------------- FIM ATUALIZAR DADOS DO OBJETO ALTERADO ---------------
				    
				    verificarObjetoAlterado(objetoTransacao,propriedades);
				    
				}
			}
		}
	}
	
	
	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 21/08/2006
	 *
	 * @param valorPropriedades
	 * @return
	 */
	private Object[] translateRegistrarTransacaoHQL(Object[] valorPropriedades) {

		for (int i = 0; i < valorPropriedades.length; i++) {
			Object valorPropriedade = valorPropriedades[i];

			if (valorPropriedade instanceof String) { // Pega cada propriedade
				String valorPropriedadeString = (String) valorPropriedade;

				if (valorPropriedadeString != null && !valorPropriedadeString.trim().equals("")	&& !valorPropriedadeString.matches("[\\w&&[^��������������������������]]*")) { 
					valorPropriedades[i] = ((String) valorPropriedade).trim()
							.replace('�', 'A').replace('�', 'O').replace('�',
									'A').replace('�', 'E').replace('�', 'I')
							.replace('�', 'U').replace('�', 'A').replace('�',
									'A').replace('�', 'E').replace('�', 'O')
							.replace('�', 'U').replace('�', 'C').replace('�',
									'A').replace('�', 'O').replace('�', 'A')
							.replace('�', 'E').replace('�', 'I').replace('�',
									'U').replace('�', 'A').replace('�', 'A')
							.replace('�', 'E').replace('�', 'O').replace('�',
									'U').replace('�', 'C'); // �������������
				}
			}
		}
		
		return valorPropriedades;
	}
		
	/**
	 * Consultar a descri��o de um objeto Gcom, caso a descri��o esteja nula, realizar�
	 * uma busca no banco pra obter a descri��o
	 * @param gcom
	 * @return
	 */
	public static String consultarDescricao(Object obj){
		String descricao = "";
		ObjetoTransacao objTrans = null;
		if (obj instanceof ObjetoTransacao){
			objTrans = (ObjetoTransacao) obj;
		} else {
			return "";
		}
		
		try {
			try {
				descricao = objTrans.getDescricaoParaRegistroTransacao();	
			} catch (LazyInitializationException lie){
				
				objTrans = consultarObjetoCarregandoAtributo(objTrans);
				
				descricao = objTrans.getDescricaoParaRegistroTransacao();
				return "";
			}
			
			
			// a descricao est� nula, ent�o vamos consultar no banco o objeto para obter a descricao
			// isto acontece nas situacoes que foi instanciado um objeto apenas com seu identificador
			if (descricao == null){
				// consultar no banco a descri��o
				if (objTrans instanceof ObjetoTransacao){
					try {
						Filtro filtro = objTrans.retornaFiltroRegistroOperacao();
						Collection coll = RepositorioUtilHBM.getInstancia().pesquisar(filtro, objTrans.getClass().getName());
						
						Object objConsultado = Util.retonarObjetoDeColecao(coll);
						if (objConsultado != null){
							ObjetoTransacao objTransacao = (ObjetoTransacao) objConsultado;
							descricao = objTransacao.getDescricaoParaRegistroTransacao();
//							metodoDescricao = objTransacao.getClass().getMethod(nomeMetodoDescricao, (Class[])null);
//							descricao = (String) metodoDescricao.invoke(objTransacao, (Object[])null);							
						}

					} catch (ErroRepositorioException e) {
						e.printStackTrace();

					} catch(Exception e){
						
					}
					
				}
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}		
		return descricao;
	}

	
	/**
	 * Salvar tabela linha alteracao principal, que � �nico para cada operacao.
	 * @param operacaoEfetuada
	 * @param objetoTransacao
	 */
	private void salvarTabelaLinhaAlteracaoPrincipal(OperacaoEfetuada operacaoEfetuada, ObjetoTransacao objetoTransacao){
		
		if (operacaoEfetuada != null && operacaoEfetuada.getId() != null
				&& operacaoEfetuada.getTabelaLinhaAlteracaoPrincipal() == null){
			TabelaLinhaAlteracao tabelaLinhaAlteracao = new TabelaLinhaAlteracao();
			tabelaLinhaAlteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
			
			String nomeTabela = HibernateUtil.getNameTable(objetoTransacao.getClass());
			FiltroTabela filtroTabela = new FiltroTabela();
			filtroTabela.adicionarParametro(new ParametroSimples(FiltroTabela.NOME,nomeTabela));

			Collection coll = null;
			try {
				coll = getControladorUtil().pesquisar(filtroTabela,Tabela.class.getSimpleName());
			} catch (ControladorException e) {
				e.printStackTrace();
			}
			Tabela tabela = null;
			
			if (coll != null && !coll.isEmpty()) {
				tabela = (Tabela) coll.iterator().next();
			}
			AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
			alteracaoTipo.setId(AlteracaoTipo.INCLUSAO);

			tabelaLinhaAlteracao.setTabela(tabela);
			tabelaLinhaAlteracao.setAlteracaoTipo(alteracaoTipo);
			tabelaLinhaAlteracao.setOperacaoEfetuada(operacaoEfetuada);

			tabelaLinhaAlteracao.setIndicadorPrincipal(TabelaLinhaAlteracao.INDICADOR_TABELA_LINHA_ALTERACAO_PRINCIPAL);	
			tabelaLinhaAlteracao.setId1(operacaoEfetuada.getIdObjetoPrincipal());
			
			tabelaLinhaAlteracao.setId2(objetoTransacao.getId2());
			
			try {
				this.getControladorUtil().inserir(tabelaLinhaAlteracao);
				operacaoEfetuada.setTabelaLinhaAlteracaoPrincipal(tabelaLinhaAlteracao);				
			} catch (ControladorException e) {
				e.printStackTrace();
			}			
		}
		
	}

}