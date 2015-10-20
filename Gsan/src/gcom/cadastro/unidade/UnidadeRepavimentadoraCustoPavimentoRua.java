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
* R�mulo Aur�lio de Melo Souza Filho
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
* Anderson Italo felinto de Lima
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
package gcom.cadastro.unidade;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Descri��o da classe Unidade Repavimentadora Custo Pavimento Rua
 * 
 * @author Hugo Leonardo
 * @date 20/12/2010
 */
@ControleAlteracao()
public class UnidadeRepavimentadoraCustoPavimentoRua extends ObjetoTransacao{
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR = 1734; //Operacao.OPERACAO_INSERIR_CUSTO_PAVIMENTO
	public static final int ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR = 1736; //Operacao.OPERACAO_ATUALIZAR_CUSTO_PAVIMENTO
	public static final int ATRIBUTOS_CUSTO_PAVIMENTO_EXCLUIR = 1737; //Operacao.OPERACAO_EXCLUIR_CUSTO_PAVIMENTO

	/** identifier field */
	private Integer id;
	
	/** identifier field */
	@ControleAlteracao(value=FiltroUnidadeRepavimentadoraCustoPavimentoRua.UNIDADE_REPAVIMENTADORA)
	private UnidadeOrganizacional unidadeRepavimentadora;

	/** persistent field */
	@ControleAlteracao(value=FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA)
	private gcom.cadastro.imovel.PavimentoRua pavimentoRua;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR,ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR,ATRIBUTOS_CUSTO_PAVIMENTO_EXCLUIR})
	private BigDecimal valorPavimento;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR,ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR,ATRIBUTOS_CUSTO_PAVIMENTO_EXCLUIR})
	private Date dataVigenciaInicial;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR,ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR,ATRIBUTOS_CUSTO_PAVIMENTO_EXCLUIR})
	private Date dataVigenciaFinal;
	
	/** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CUSTO_PAVIMENTO_INSERIR,ATRIBUTOS_CUSTO_PAVIMENTO_ATUALIZAR})
	private Date ultimaAlteracao;

	/** default constructor */
	public UnidadeRepavimentadoraCustoPavimentoRua() {
		
	}
	
    /** minimal constructor */
    public UnidadeRepavimentadoraCustoPavimentoRua(UnidadeOrganizacional unidadeRepavimentadora, PavimentoRua pavimentoRua, 
    		BigDecimal valorPavimento, Date dataVigenciaInicial, Date ultimaAlteracao) {
    	
        this.unidadeRepavimentadora = unidadeRepavimentadora;
        this.pavimentoRua = pavimentoRua;
        this.valorPavimento = valorPavimento;
        this.dataVigenciaInicial = dataVigenciaInicial;
        this.ultimaAlteracao = ultimaAlteracao;
    }

	public Date getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(Date dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public Date getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}

	public void setDataVigenciaInicial(Date dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	public void setUnidadeRepavimentadora(
			UnidadeOrganizacional unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public BigDecimal getValorPavimento() {
		return valorPavimento;
	}

	public void setValorPavimento(BigDecimal valorPavimento) {
		this.valorPavimento = valorPavimento;
	}
	
	public boolean isPodeAtualizar(){
		boolean retorno = true;
		
		if(this.getDataVigenciaFinal() != null 
				&& Util.compararData(this.getDataVigenciaFinal(), new Date()) == -1){
			
			retorno = false;
		}
		return retorno;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Unid. Repav. Custo Pav. Rua.", 
				"Pavimento Rua",
				"Valor Pavimento."
				};
			return labels;		
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"id", "pavimentoRua.descricao", "valorPavimento"};
		
		return atributos;		
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		
		return filtro;
	}
	
	public Filtro retornaFiltro() {
		FiltroUnidadeRepavimentadoraCustoPavimentoRua filtro = new FiltroUnidadeRepavimentadoraCustoPavimentoRua();

		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoRua.UNIDADE_REPAVIMENTADORA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA);
		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeRepavimentadoraCustoPavimentoRua.ID, this.getId()));
		
		return filtro;
	}
    
}
