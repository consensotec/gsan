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

package gcom.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoComando implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorComandoCriterio;

    /** persistent field */
    private Date dataPrevista;

    /** nullable persistent field */
    private Date dataHoraComando;

    /** nullable persistent field */
    private Date dataHoraRealizacao;

    /** nullable persistent field */
    private Integer quantidadeInclusoes;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    private Integer quantidadeItensIncluidos;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private String descricaoComunicacaoInterna;

    /** persistent field */
    private short indicadorSimulacao;

    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private Set negativacaoCriterios;
    
    /** persistent field */
    private Negativador negativador;
    
    /** persistent field */
    private NegativacaoComando comandoSimulacao;
    
    private Short indicadorBaixaRenda;
    
    private Short indicadorContaNomeCliente;
    
    private Short indicadorOrgaoPublico;
    
    private Short indicadorCpfCnpjValidado;
    
    public final static short SIMULACAO = 1;
    public final static short NAO_SIMULACAO = 2;
    
    private static int quantidadeImoveisJaIncluidos = 0;
    
    public static boolean continuarInclusaoImoveis(int quantidadeMaxima) {
    	if (quantidadeImoveisJaIncluidos <= quantidadeMaxima){
    		return true;
    	} else {
    		return false;
    	}
	}

    public static synchronized  void incrementarQuantidadeImoveisJaIncluidos(){
    	quantidadeImoveisJaIncluidos++;    	
    }
    
    public static synchronized  void resetQuantidadeImoveisJaIncluidos(){
    	quantidadeImoveisJaIncluidos = 0;    	
    }
    
    /** full constructor */
    public NegativacaoComando(Integer id, short indicadorComandoCriterio, Date dataPrevista, Date dataHoraComando, 
    		Date dataHoraRealizacao, Integer quantidadeInclusoes, BigDecimal valorDebito, Integer quantidadeItensIncluidos, 
    		Date ultimaAlteracao, String descricaoComunicacaoInterna, short indicadorSimulacao, Usuario usuario, 
    		Set negativacaoCriterios,NegativacaoComando comandoSimulacao) {
        this.id = id;
        this.indicadorComandoCriterio = indicadorComandoCriterio;
        this.dataPrevista = dataPrevista;
        this.dataHoraComando = dataHoraComando;
        this.dataHoraRealizacao = dataHoraRealizacao;
        this.quantidadeInclusoes = quantidadeInclusoes;
        this.valorDebito = valorDebito;
        this.quantidadeItensIncluidos = quantidadeItensIncluidos;
        this.ultimaAlteracao = ultimaAlteracao;
        this.descricaoComunicacaoInterna = descricaoComunicacaoInterna;
        this.indicadorSimulacao = indicadorSimulacao;
        this.usuario = usuario;
        this.negativacaoCriterios = negativacaoCriterios;
        this.comandoSimulacao = comandoSimulacao;
    }

    /** default constructor */
    public NegativacaoComando() {
    }

    /** minimal constructor */
    public NegativacaoComando(Integer id, short indicadorComandoCriterio, Date dataPrevista, short indicadorSimulacao, Usuario usuario, Set negativacaoCriterios) {
        this.id = id;
        this.indicadorComandoCriterio = indicadorComandoCriterio;
        this.dataPrevista = dataPrevista;
        this.indicadorSimulacao = indicadorSimulacao;
        this.usuario = usuario;
        this.negativacaoCriterios = negativacaoCriterios;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getIndicadorComandoCriterio() {
        return this.indicadorComandoCriterio;
    }

    public void setIndicadorComandoCriterio(short indicadorComandoCriterio) {
        this.indicadorComandoCriterio = indicadorComandoCriterio;
    }

    public Date getDataPrevista() {
        return this.dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getDataHoraComando() {
        return this.dataHoraComando;
    }

    public void setDataHoraComando(Date dataHoraComando) {
        this.dataHoraComando = dataHoraComando;
    }

    public Date getDataHoraRealizacao() {
        return this.dataHoraRealizacao;
    }

    public void setDataHoraRealizacao(Date dataHoraRealizacao) {
        this.dataHoraRealizacao = dataHoraRealizacao;
    }

    public Integer getQuantidadeInclusoes() {
        return this.quantidadeInclusoes;
    }

    public void setQuantidadeInclusoes(Integer quantidadeInclusoes) {
        this.quantidadeInclusoes = quantidadeInclusoes;
    }

    public BigDecimal getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Integer getQuantidadeItensIncluidos() {
        return this.quantidadeItensIncluidos;
    }

    public void setQuantidadeItensIncluidos(Integer quantidadeItensIncluidos) {
        this.quantidadeItensIncluidos = quantidadeItensIncluidos;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String getDescricaoComunicacaoInterna() {
        return this.descricaoComunicacaoInterna;
    }

    public void setDescricaoComunicacaoInterna(String descricaoComunicacaoInterna) {
        this.descricaoComunicacaoInterna = descricaoComunicacaoInterna;
    }

    public short getIndicadorSimulacao() {
        return this.indicadorSimulacao;
    }

    public void setIndicadorSimulacao(short indicadorSimulacao) {
        this.indicadorSimulacao = indicadorSimulacao;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set getNegativacaoCriterios() {
        return this.negativacaoCriterios;
    }

    public void setNegativacaoCriterios(Set negativacaoCriterios) {
        this.negativacaoCriterios = negativacaoCriterios;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Negativador getNegativador() {
		return negativador;
	}

	public void setNegativador(Negativador negativador) {
		this.negativador = negativador;
	}

	/**
	 * @return Retorna o campo idComandoSimulacao.
	 */
	public NegativacaoComando getComandoSimulacao() {
		return comandoSimulacao;
	}

	/**
	 * @param idComandoSimulacao O idComandoSimulacao a ser setado.
	 */
	public void setComandoSimulacao(NegativacaoComando comandoSimulacao) {
		this.comandoSimulacao = comandoSimulacao;
	}

	public Short getIndicadorBaixaRenda() {
		return indicadorBaixaRenda;
	}

	public void setIndicadorBaixaRenda(Short indicadorBaixaRenda) {
		this.indicadorBaixaRenda = indicadorBaixaRenda;
	}

	public Short getIndicadorContaNomeCliente() {
		return indicadorContaNomeCliente;
	}

	public void setIndicadorContaNomeCliente(Short indicadorContaNomeCliente) {
		this.indicadorContaNomeCliente = indicadorContaNomeCliente;
	}

	public Short getIndicadorOrgaoPublico() {
		return indicadorOrgaoPublico;
	}

	public void setIndicadorOrgaoPublico(
			Short indicadorOrgaoPublico) {
		this.indicadorOrgaoPublico = indicadorOrgaoPublico;
	}

	public Short getIndicadorCpfCnpjValidado() {
		return indicadorCpfCnpjValidado;
	}

	public void setIndicadorCpfCnpjValidado(Short indicadorCpfCnpjValidado) {
		this.indicadorCpfCnpjValidado = indicadorCpfCnpjValidado;
	}
}
