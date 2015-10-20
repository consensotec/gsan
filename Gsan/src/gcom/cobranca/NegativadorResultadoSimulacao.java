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
* Thiago Silva Toscano de Brito
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

package gcom.cobranca;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.spcserasa.FiltroNegativadorMovimentoReg;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class NegativadorResultadoSimulacao extends ObjetoTransacao implements Serializable {

	public Filtro retornaFiltro() {
		FiltroNegativadorMovimentoReg filtroNegativadorExclusaoMotivo = new FiltroNegativadorMovimentoReg();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.ID,this.getId()));		
		return filtroNegativadorExclusaoMotivo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    private String numeroCpf;

    /** nullable persistent field */
    private String numeroCnpj;

    /** persistent field */
    private Date ultimaAlteracao;
    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private NegativacaoComando negativacaoComando;
    
    private Integer quantidadeItensIncluidos;

    /** full constructor */
	public NegativadorResultadoSimulacao(Integer id, BigDecimal valorDebito, String numeroCpf, String numeroCnpj, Date ultimaAlteracao, Imovel imovel, NegativacaoComando negativacaoComando) {
		this.id = id;
		this.valorDebito = valorDebito;
		this.numeroCpf = numeroCpf;
		this.numeroCnpj = numeroCnpj;
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovel = imovel;
		this.negativacaoComando = negativacaoComando;
	}

    /** default constructor */
    public NegativadorResultadoSimulacao() {
    }

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo imovel.
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo negativacaoComando.
	 */
	public NegativacaoComando getNegativacaoComando() {
		return negativacaoComando;
	}

	/**
	 * @param negativacaoComando O negativacaoComando a ser setado.
	 */
	public void setNegativacaoComando(NegativacaoComando negativacaoComando) {
		this.negativacaoComando = negativacaoComando;
	}

	/**
	 * @return Retorna o campo numeroCnpj.
	 */
	public String getNumeroCnpj() {
		return numeroCnpj;
	}

	/**
	 * @param numeroCnpj O numeroCnpj a ser setado.
	 */
	public void setNumeroCnpj(String numeroCnpj) {
		this.numeroCnpj = numeroCnpj;
	}

	/**
	 * @return Retorna o campo numeroCpf.
	 */
	public String getNumeroCpf() {
		return numeroCpf;
	}

	/**
	 * @param numeroCpf O numeroCpf a ser setado.
	 */
	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	/**
	 * @return Retorna o campo quantidadeItensIncluidos.
	 */
	public Integer getQuantidadeItensIncluidos() {
		return quantidadeItensIncluidos;
	}

	/**
	 * @param quantidadeItensIncluidos O quantidadeItensIncluidos a ser setado.
	 */
	public void setQuantidadeItensIncluidos(Integer quantidadeItensIncluidos) {
		this.quantidadeItensIncluidos = quantidadeItensIncluidos;
	}


}
