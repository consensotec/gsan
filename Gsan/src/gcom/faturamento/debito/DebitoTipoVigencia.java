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
package gcom.faturamento.debito;import gcom.interceptor.ControleAlteracao;import gcom.interceptor.ObjetoTransacao;import gcom.seguranca.acesso.OperacaoEfetuada;import gcom.util.filtro.Filtro;import gcom.util.filtro.ParametroSimples;import java.math.BigDecimal;import java.util.Date;
/** @author Hibernate CodeGenerator *//** * Descri��o da classe *  * @author Hugo Leonardo * @date 16/04/2010 */@ControleAlteracao()
public class DebitoTipoVigencia extends ObjetoTransacao{	private static final long serialVersionUID = 1L;		public static final int ATRIBUTOS_DEBITO_TIPO_VIGENCIA_INSERIR = 1598; //Operacao.OPERACAO_INSERIR_DEBITO_TIPO_VIGENCIA	public static final int ATRIBUTOS_DEBITO_TIPO_VIGENCIA_ATUALIZAR = 1602; //Operacao.OPERACAO_ATUALIZAR_DEBITO_TIPO_VIGENCIA	public static final int ATRIBUTOS_DEBITO_TIPO_VIGENCIA_EXCLUIR = 1603; //Operacao.OPERACAO_EXCLUIR_DEBITO_TIPO_VIGENCIA	
	private Integer id;	/**	 * persistent field	 */	@ControleAlteracao(FiltroDebitoTipoVigencia.DEBITO_TIPO)	private DebitoTipo debitoTipo;		/**	 * persistent field	 */	@ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_TIPO_VIGENCIA_INSERIR,ATRIBUTOS_DEBITO_TIPO_VIGENCIA_ATUALIZAR,ATRIBUTOS_DEBITO_TIPO_VIGENCIA_EXCLUIR})	private Date dataVigenciaInicial;		/**	 * persistent field	 */	@ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_TIPO_VIGENCIA_INSERIR,ATRIBUTOS_DEBITO_TIPO_VIGENCIA_ATUALIZAR,ATRIBUTOS_DEBITO_TIPO_VIGENCIA_EXCLUIR})	private Date dataVigenciaFinal;		/**	 * persistent field	 */	@ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_TIPO_VIGENCIA_INSERIR,ATRIBUTOS_DEBITO_TIPO_VIGENCIA_ATUALIZAR,ATRIBUTOS_DEBITO_TIPO_VIGENCIA_EXCLUIR})	private BigDecimal valorDebito;		private OperacaoEfetuada operacaoEfetuada;		private Date ultimaAlteracao;	public String[] retornaCamposChavePrimaria(){		String[] retorno = new String[1];		retorno[0] = "id";		return retorno;	}
	public DebitoTipoVigencia() {
	}	public Date getDataVigenciaFinal() {		return dataVigenciaFinal;	}	public void setDataVigenciaFinal(Date dataVigenciaFinal) {		this.dataVigenciaFinal = dataVigenciaFinal;	}	public Date getDataVigenciaInicial() {		return dataVigenciaInicial;	}	public void setDataVigenciaInicial(Date dataVigenciaInicial) {		this.dataVigenciaInicial = dataVigenciaInicial;	}	public DebitoTipo getDebitoTipo() {		return debitoTipo;	}	public void setDebitoTipo(DebitoTipo debitoTipo) {		this.debitoTipo = debitoTipo;	}	public Integer getId() {		return id;	}	public void setId(Integer id) {		this.id = id;	}	public Date getUltimaAlteracao() {		return ultimaAlteracao;	}	public void setUltimaAlteracao(Date ultimaAlteracao) {		this.ultimaAlteracao = ultimaAlteracao;	}	public BigDecimal getValorDebito() {		return valorDebito;	}	public void setValorDebito(BigDecimal valorDebito) {		this.valorDebito = valorDebito;	}	public OperacaoEfetuada getOperacaoEfetuada() {		return operacaoEfetuada;	}	public void setOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada) {		this.operacaoEfetuada = operacaoEfetuada;	}		@Override	public String[] retornarAtributosInformacoesOperacaoEfetuada(){		String []atributos = {				"id", 				"debitoTipo.descricaoAbreviada", 				"valorDebito"};				return atributos;			}		@Override	public String[] retornarLabelsInformacoesOperacaoEfetuada(){		String []labels = {"Deb. Tipo Vig.", 				"Deb. Tipo",				"Valor Deb. Tipo Vig."				};			return labels;			}		@Override	public String getDescricaoParaRegistroTransacao() {		return getId().toString();	}		@Override	public Filtro retornaFiltroRegistroOperacao() {		Filtro filtro = retornaFiltro();				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoTipoVigencia.DEBITO_TIPO);				return filtro;	}		public Filtro retornaFiltro() {		FiltroDebitoTipoVigencia filtroDebitoTipoVigencia = new FiltroDebitoTipoVigencia();		filtroDebitoTipoVigencia.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoTipoVigencia.DEBITO_TIPO);		filtroDebitoTipoVigencia.adicionarParametro(new ParametroSimples(FiltroDebitoTipoVigencia.ID,				this.getId()));						return filtroDebitoTipoVigencia;	}	}
