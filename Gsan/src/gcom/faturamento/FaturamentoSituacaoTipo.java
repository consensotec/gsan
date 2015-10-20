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
package gcom.faturamento;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
/** @author Hibernate CodeGenerator */
public class FaturamentoSituacaoTipo extends ObjetoTransacao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorParalisacaoFaturamento;

    /** nullable persistent field */
    private Short indicadorParalisacaoLeitura;

    /** nullable persistent field */
    private Short indicadorUso;
    
    /** nullable persistent field */
    private Short indicadorValidoAgua;
    
    /** nullable persistent field */
    private Short indicadorValidoEsgoto;
    
    private Short indicadorInformarConsumo;
    
    private Short indicadorInformarVolume;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
   // private Short indicadorFaturamentoParalisacaoEsgoto;

    /** persistent field */
    private LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComLeitura;

    /** persistent field */
    private LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemLeitura;

    /** persistent field */
    private LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComLeitura;

    /** persistent field */
    private LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemLeitura;
    
    private Short indicadorParalisaFatEsgoto;
    
    private Short indicadorParalisaFatAgua;
    
    
    //  --CONSTANTES
    /**
     * Description of the Field
     */
    public final static Integer PARALISAR_FATURAMENTO_DE_ESGOTO = new Integer(12);

    public final static Integer FATURAMENTO_NORMAL = new Integer(0);
    
    public final static Integer FATURAR_NORMAL = new Integer(5);

    public final static Integer NITRATO = new Integer(9);
    
    public final static Short INDICADOR_PARALIZACAO_LEITURA_NAO_REALIZADA = new Short("1");
    
    public final static Integer PARALISAR_LEITURA_FATURAR_MEDIA = new Integer(2);
    
    public final static Integer PARALISAR_LEITURA_FATURAR_TAXA_MINIMA = new Integer(3);

    /** full constructor */
    public FaturamentoSituacaoTipo(String descricao, Short indicadorParalisacaoFaturamento, Short indicadorParalisacaoLeitura, Short indicadorUso, Date ultimaAlteracao, Short indicadorFaturamentoParalisacaoEsgoto, LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComLeitura, LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemLeitura, LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComLeitura, LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemLeitura) {
        this.descricao = descricao;
        this.indicadorParalisacaoFaturamento = indicadorParalisacaoFaturamento;
        this.indicadorParalisacaoLeitura = indicadorParalisacaoLeitura;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        //this.indicadorFaturamentoParalisacaoEsgoto = indicadorFaturamentoParalisacaoEsgoto;
        this.leituraAnormalidadeLeituraComLeitura = leituraAnormalidadeLeituraComLeitura;
        this.leituraAnormalidadeLeituraSemLeitura = leituraAnormalidadeLeituraSemLeitura;
        this.leituraAnormalidadeConsumoComLeitura = leituraAnormalidadeConsumoComLeitura;
        this.leituraAnormalidadeConsumoSemLeitura = leituraAnormalidadeConsumoSemLeitura;
    }

    /** default constructor */
    public FaturamentoSituacaoTipo() {
    }

    /** minimal constructor */
    public FaturamentoSituacaoTipo(LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComLeitura, LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemLeitura, LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComLeitura, LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemLeitura) {
        this.leituraAnormalidadeLeituraComLeitura = leituraAnormalidadeLeituraComLeitura;
        this.leituraAnormalidadeLeituraSemLeitura = leituraAnormalidadeLeituraSemLeitura;
        this.leituraAnormalidadeConsumoComLeitura = leituraAnormalidadeConsumoComLeitura;
        this.leituraAnormalidadeConsumoSemLeitura = leituraAnormalidadeConsumoSemLeitura;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Short getIndicadorParalisacaoFaturamento() {
        return this.indicadorParalisacaoFaturamento;
    }

    public void setIndicadorParalisacaoFaturamento(Short indicadorParalisacaoFaturamento) {
        this.indicadorParalisacaoFaturamento = indicadorParalisacaoFaturamento;
    }

    public Short getIndicadorParalisacaoLeitura() {
        return this.indicadorParalisacaoLeitura;
    }

    public void setIndicadorParalisacaoLeitura(Short indicadorParalisacaoLeitura) {
        this.indicadorParalisacaoLeitura = indicadorParalisacaoLeitura;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public LeituraAnormalidadeLeitura getLeituraAnormalidadeLeituraComLeitura() {
        return this.leituraAnormalidadeLeituraComLeitura;
    }

    public void setLeituraAnormalidadeLeituraComLeitura(LeituraAnormalidadeLeitura leituraAnormalidadeLeituraComLeitura) {
        this.leituraAnormalidadeLeituraComLeitura = leituraAnormalidadeLeituraComLeitura;
    }

    public LeituraAnormalidadeLeitura getLeituraAnormalidadeLeituraSemLeitura() {
        return this.leituraAnormalidadeLeituraSemLeitura;
    }

    public void setLeituraAnormalidadeLeituraSemLeitura(LeituraAnormalidadeLeitura leituraAnormalidadeLeituraSemLeitura) {
        this.leituraAnormalidadeLeituraSemLeitura = leituraAnormalidadeLeituraSemLeitura;
    }

    public LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumoComLeitura() {
        return this.leituraAnormalidadeConsumoComLeitura;
    }

    public void setLeituraAnormalidadeConsumoComLeitura(LeituraAnormalidadeConsumo leituraAnormalidadeConsumoComLeitura) {
        this.leituraAnormalidadeConsumoComLeitura = leituraAnormalidadeConsumoComLeitura;
    }

    public LeituraAnormalidadeConsumo getLeituraAnormalidadeConsumoSemLeitura() {
        return this.leituraAnormalidadeConsumoSemLeitura;
    }

    public void setLeituraAnormalidadeConsumoSemLeitura(LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemLeitura) {
        this.leituraAnormalidadeConsumoSemLeitura = leituraAnormalidadeConsumoSemLeitura;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Short getIndicadorValidoAgua() {
		return indicadorValidoAgua;
	}

	public void setIndicadorValidoAgua(Short indicadorValidoAgua) {
		this.indicadorValidoAgua = indicadorValidoAgua;
	}

	public Short getIndicadorValidoEsgoto() {
		return indicadorValidoEsgoto;
	}

	public void setIndicadorValidoEsgoto(Short indicadorValidoEsgoto) {
		this.indicadorValidoEsgoto = indicadorValidoEsgoto;
	}

	public Short getIndicadorInformarConsumo() {
		return indicadorInformarConsumo;
	}

	public void setIndicadorInformarConsumo(Short indicadorInformarConsumo) {
		this.indicadorInformarConsumo = indicadorInformarConsumo;
	}

	public Short getIndicadorInformarVolume() {
		return indicadorInformarVolume;
	}

	public void setIndicadorInformarVolume(Short indicadorInformarVolume) {
		this.indicadorInformarVolume = indicadorInformarVolume;
	}
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []atributos = {"descricao"};
		return atributos;	

	}

	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []atributos = {"Descricao"};
		return atributos;
	}	
	@Override
	public Filtro retornaFiltro() {
		FiltroFaturamentoSituacaoTipo filtro = new FiltroFaturamentoSituacaoTipo();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoTipo.ID,this.getId()));
		
		return filtro;
	}
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		return filtro;
	}	

	public Short getIndicadorParalisaFatEsgoto() {
		return indicadorParalisaFatEsgoto;
	}

	public void setIndicadorParalisaFatEsgoto(Short indicadorParalisaFatEsgoto) {
		this.indicadorParalisaFatEsgoto = indicadorParalisaFatEsgoto;
	}

	public Short getIndicadorParalisaFatAgua() {
		return indicadorParalisaFatAgua;
	}

	public void setIndicadorParalisaFatAgua(Short indicadorParalisaFatAgua) {
		this.indicadorParalisaFatAgua = indicadorParalisaFatAgua;
	}
	
	
}
