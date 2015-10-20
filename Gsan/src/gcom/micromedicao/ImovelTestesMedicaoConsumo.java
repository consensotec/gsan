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
package gcom.micromedicao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class ImovelTestesMedicaoConsumo implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * identifier field
     */
    private Integer id;

    /**
     * persistent field
     */
    private Integer idLigacaoAguaSituacao;

    /**
     * persistent field
     */
    private Integer idLigacaoEsgotoSituacao;

    /**
     * persistent field
     */
    private Integer indicadorHidrometroAgua;

    /**
     * persistent field
     */
    private Integer indicadorHidrometroPoco;

    /**
     * persistent field
     */
    private int quantidadeEconomias;

    /**
     * nullable persistent field
     */
    private Integer numeroLeituraAnteriorAgua;

    /**
     * nullable persistent field
     */
    private Integer numeroLeituraAnteriorPoco;

    /**
     * nullable persistent field
     */
    private Integer numeroLeituraAnteriorInformadaAgua;

    /**
     * nullable persistent field
     */
    private Integer numeroLeituraAnteriorInformadaPoco;

    /**
     * nullable persistent field
     */
    private Integer idLeituraSituacaoAnteriorAgua;

    /**
     * nullable persistent field
     */
    private Integer idLeituraSituacaoAnteriorPoco;

    /**
     * nullable persistent field
     */
    private Integer numeroLeituraAtualAgua;

    /**
     * nullable persistent field
     */
    private Integer numeroLeituraAtualPoco;

    /**
     * nullable persistent field
     */
    private Integer idLeituraSituacaoAtualAgua;

    /**
     * nullable persistent field
     */
    private Integer idLeituraSituacaoAtualPoco;

    /**
     * nullable persistent field
     */
    private Integer idLeituraAnormalidadeAgua;

    /**
     * nullable persistent field
     */
    private Integer idLeituraAnormalidadePoco;

    /**
     * nullable persistent field
     */
    private Integer idFaturamentoSituacaoTipo;

    /**
     * nullable persistent field
     */
    private Integer numeroMedioConsumoHidrometroAgua;

    /**
     * nullable persistent field
     */
    private Integer numeroMedioConsumoHidrometroPoco;

    /**
     * nullable persistent field
     */
    private Integer numeroMedioConsumoImovel;

    /**
     * nullable persistent field
     */
    private Integer numeroFaixaIncialAgua;

    /**
     * nullable persistent field
     */
    private Integer numeroFaixaFinalAgua;

    /**
     * nullable persistent field
     */
    private Integer numeroFaixaInicialPoco;

    /**
     * nullable persistent field
     */
    private Integer numeroFaixaFinalPoco;

    /**
     * nullable persistent field
     */
    private Integer numeroConsumoMinimoAgua;

    /**
     * nullable persistent field
     */
    private Integer numeroConsumoMinimoEsgoto;

    /**
     * nullable persistent field
     */
    private Integer idPoco;

    /**
     * nullable persistent field
     */
    private Short numeroDigitosLeituraAgua;

    /**
     * nullable persistent field
     */
    private Short numeroDigitosLeituraPoco;

    /**
     * nullable persistent field
     */
    private Integer idHidrometroAgua;

    /**
     * nullable persistent field
     */
    private Integer idHidrometroPoco;

    /**
     * nullable persistent field
     */
    private BigDecimal percentualColetaLigacaoEsgoto;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;

    /**
     * full constructor
     * 
     * @param id
     *            Descri��o do par�metro
     * @param idLigacaoAguaSituacao
     *            Descri��o do par�metro
     * @param idLigacaoEsgotoSituacao
     *            Descri��o do par�metro
     * @param indicadorHidrometroAgua
     *            Descri��o do par�metro
     * @param indicadorHidrometroPoco
     *            Descri��o do par�metro
     * @param quantidadeEconomias
     *            Descri��o do par�metro
     * @param numeroLeituraAnteriorAgua
     *            Descri��o do par�metro
     * @param numeroLeituraAnteriorPoco
     *            Descri��o do par�metro
     * @param numeroLeituraAnteriorInformadaAgua
     *            Descri��o do par�metro
     * @param numeroLeituraAnteriorInformadaPoco
     *            Descri��o do par�metro
     * @param idLeituraSituacaoAnteriorAgua
     *            Descri��o do par�metro
     * @param idLeituraSituacaoAnteriorPoco
     *            Descri��o do par�metro
     * @param numeroLeituraAtualAgua
     *            Descri��o do par�metro
     * @param numeroLeituraAtualPoco
     *            Descri��o do par�metro
     * @param idLeituraSituacaoAtualAgua
     *            Descri��o do par�metro
     * @param idLeituraSituacaoAtualPoco
     *            Descri��o do par�metro
     * @param idLeituraAnormalidadeAgua
     *            Descri��o do par�metro
     * @param idLeituraAnormalidadePoco
     *            Descri��o do par�metro
     * @param idFaturamentoSituacaoTipo
     *            Descri��o do par�metro
     * @param numeroMedioConsumoHidrometroAgua
     *            Descri��o do par�metro
     * @param numeroMedioConsumoHidrometroPoco
     *            Descri��o do par�metro
     * @param numeroMedioConsumoImovel
     *            Descri��o do par�metro
     * @param numeroFaixaIncialAgua
     *            Descri��o do par�metro
     * @param numeroFaixaFinalAgua
     *            Descri��o do par�metro
     * @param numeroFaixaInicialPoco
     *            Descri��o do par�metro
     * @param numeroFaixaFinalPoco
     *            Descri��o do par�metro
     * @param numeroConsumoMinimoAgua
     *            Descri��o do par�metro
     * @param numeroConsumoMinimoEsgoto
     *            Descri��o do par�metro
     * @param idPoco
     *            Descri��o do par�metro
     * @param numeroDigitosLeituraAgua
     *            Descri��o do par�metro
     * @param numeroDigitosLeituraPoco
     *            Descri��o do par�metro
     * @param idHidrometroAgua
     *            Descri��o do par�metro
     * @param idHidrometroPoco
     *            Descri��o do par�metro
     * @param percentualColetaLigacaoEsgoto
     *            Descri��o do par�metro
     * @param ultimaAlteracao
     *            Descri��o do par�metro
     */
    public ImovelTestesMedicaoConsumo(Integer id,
            Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao,
            Integer indicadorHidrometroAgua, Integer indicadorHidrometroPoco,
            int quantidadeEconomias, Integer numeroLeituraAnteriorAgua,
            Integer numeroLeituraAnteriorPoco,
            Integer numeroLeituraAnteriorInformadaAgua,
            Integer numeroLeituraAnteriorInformadaPoco,
            Integer idLeituraSituacaoAnteriorAgua,
            Integer idLeituraSituacaoAnteriorPoco,
            Integer numeroLeituraAtualAgua, Integer numeroLeituraAtualPoco,
            Integer idLeituraSituacaoAtualAgua,
            Integer idLeituraSituacaoAtualPoco,
            Integer idLeituraAnormalidadeAgua,
            Integer idLeituraAnormalidadePoco,
            Integer idFaturamentoSituacaoTipo,
            Integer numeroMedioConsumoHidrometroAgua,
            Integer numeroMedioConsumoHidrometroPoco,
            Integer numeroMedioConsumoImovel, Integer numeroFaixaIncialAgua,
            Integer numeroFaixaFinalAgua, Integer numeroFaixaInicialPoco,
            Integer numeroFaixaFinalPoco, Integer numeroConsumoMinimoAgua,
            Integer numeroConsumoMinimoEsgoto, Integer idPoco,
            Short numeroDigitosLeituraAgua, Short numeroDigitosLeituraPoco,
            Integer idHidrometroAgua, Integer idHidrometroPoco,
            BigDecimal percentualColetaLigacaoEsgoto, Date ultimaAlteracao) {
        this.id = id;
        this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
        this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
        this.indicadorHidrometroAgua = indicadorHidrometroAgua;
        this.indicadorHidrometroPoco = indicadorHidrometroPoco;
        this.quantidadeEconomias = quantidadeEconomias;
        this.numeroLeituraAnteriorAgua = numeroLeituraAnteriorAgua;
        this.numeroLeituraAnteriorPoco = numeroLeituraAnteriorPoco;
        this.numeroLeituraAnteriorInformadaAgua = numeroLeituraAnteriorInformadaAgua;
        this.numeroLeituraAnteriorInformadaPoco = numeroLeituraAnteriorInformadaPoco;
        this.idLeituraSituacaoAnteriorAgua = idLeituraSituacaoAnteriorAgua;
        this.idLeituraSituacaoAnteriorPoco = idLeituraSituacaoAnteriorPoco;
        this.numeroLeituraAtualAgua = numeroLeituraAtualAgua;
        this.numeroLeituraAtualPoco = numeroLeituraAtualPoco;
        this.idLeituraSituacaoAtualAgua = idLeituraSituacaoAtualAgua;
        this.idLeituraSituacaoAtualPoco = idLeituraSituacaoAtualPoco;
        this.idLeituraAnormalidadeAgua = idLeituraAnormalidadeAgua;
        this.idLeituraAnormalidadePoco = idLeituraAnormalidadePoco;
        this.idFaturamentoSituacaoTipo = idFaturamentoSituacaoTipo;
        this.numeroMedioConsumoHidrometroAgua = numeroMedioConsumoHidrometroAgua;
        this.numeroMedioConsumoHidrometroPoco = numeroMedioConsumoHidrometroPoco;
        this.numeroMedioConsumoImovel = numeroMedioConsumoImovel;
        this.numeroFaixaIncialAgua = numeroFaixaIncialAgua;
        this.numeroFaixaFinalAgua = numeroFaixaFinalAgua;
        this.numeroFaixaInicialPoco = numeroFaixaInicialPoco;
        this.numeroFaixaFinalPoco = numeroFaixaFinalPoco;
        this.numeroConsumoMinimoAgua = numeroConsumoMinimoAgua;
        this.numeroConsumoMinimoEsgoto = numeroConsumoMinimoEsgoto;
        this.idPoco = idPoco;
        this.numeroDigitosLeituraAgua = numeroDigitosLeituraAgua;
        this.numeroDigitosLeituraPoco = numeroDigitosLeituraPoco;
        this.idHidrometroAgua = idHidrometroAgua;
        this.idHidrometroPoco = idHidrometroPoco;
        this.percentualColetaLigacaoEsgoto = percentualColetaLigacaoEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * Construtor da classe ImovelTestesMedicaoConsumo
     * 
     * @param id
     *            Descri��o do par�metro
     * @param idLigacaoAguaSituacao
     *            Descri��o do par�metro
     * @param indicadorHidrometroAgua
     *            Descri��o do par�metro
     * @param numeroLeituraAnteriorAgua
     *            Descri��o do par�metro
     * @param numeroLeituraAnteriorInformadaAgua
     *            Descri��o do par�metro
     * @param idLeituraSituacaoAnteriorAgua
     *            Descri��o do par�metro
     * @param numeroLeituraAtualAgua
     *            Descri��o do par�metro
     * @param idLeituraSituacaoAtualAgua
     *            Descri��o do par�metro
     * @param idLeituraAnormalidadeAgua
     *            Descri��o do par�metro
     * @param idFaturamentoSituacaoTipo
     *            Descri��o do par�metro
     * @param numeroConsumoMinimoAgua
     *            Descri��o do par�metro
     * @param numeroDigitosLeituraAgua
     *            Descri��o do par�metro
     * @param idHidrometroAgua
     *            Descri��o do par�metro
     */
    public ImovelTestesMedicaoConsumo(Integer id,
            Integer idLigacaoAguaSituacao, Integer indicadorHidrometroAgua,
            Integer numeroLeituraAnteriorAgua,
            Integer numeroLeituraAnteriorInformadaAgua,
            Integer idLeituraSituacaoAnteriorAgua,
            Integer numeroLeituraAtualAgua, Integer idLeituraSituacaoAtualAgua,
            Integer idLeituraAnormalidadeAgua,
            Integer idFaturamentoSituacaoTipo, Integer numeroConsumoMinimoAgua,
            Short numeroDigitosLeituraAgua, Integer idHidrometroAgua) {
        this.id = id;
        this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
        this.indicadorHidrometroAgua = indicadorHidrometroAgua;
        this.numeroLeituraAnteriorAgua = numeroLeituraAnteriorAgua;
        this.numeroLeituraAnteriorInformadaAgua = numeroLeituraAnteriorInformadaAgua;
        this.idLeituraSituacaoAnteriorAgua = idLeituraSituacaoAnteriorAgua;
        this.numeroLeituraAtualAgua = numeroLeituraAtualAgua;
        this.idLeituraSituacaoAtualAgua = idLeituraSituacaoAtualAgua;
        this.idLeituraAnormalidadeAgua = idLeituraAnormalidadeAgua;
        this.idFaturamentoSituacaoTipo = idFaturamentoSituacaoTipo;
        this.numeroConsumoMinimoAgua = numeroConsumoMinimoAgua;
        this.numeroDigitosLeituraAgua = numeroDigitosLeituraAgua;
        this.idHidrometroAgua = idHidrometroAgua;
    }

    /**
     * Construtor da classe ImovelTestesMedicaoConsumo
     * 
     * @param id
     *            Descri��o do par�metro
     * @param indicadorHidrometroPoco
     *            Descri��o do par�metro
     * @param numeroLeituraAnteriorPoco
     *            Descri��o do par�metro
     * @param numeroLeituraAnteriorInformadaPoco
     *            Descri��o do par�metro
     * @param idLeituraSituacaoAnteriorPoco
     *            Descri��o do par�metro
     * @param numeroLeituraAtualPoco
     *            Descri��o do par�metro
     * @param idLeituraSituacaoAtualPoco
     *            Descri��o do par�metro
     * @param idLeituraAnormalidadePoco
     *            Descri��o do par�metro
     * @param idFaturamentoSituacaoTipo
     *            Descri��o do par�metro
     * @param idPoco
     *            Descri��o do par�metro
     * @param numeroDigitosLeituraPoco
     *            Descri��o do par�metro
     * @param idHidrometroPoco
     *            Descri��o do par�metro
     */
    public ImovelTestesMedicaoConsumo(Integer id,
            Integer indicadorHidrometroPoco, Integer numeroLeituraAnteriorPoco,
            Integer numeroLeituraAnteriorInformadaPoco,
            Integer idLeituraSituacaoAnteriorPoco,
            Integer numeroLeituraAtualPoco, Integer idLeituraSituacaoAtualPoco,
            Integer idLeituraAnormalidadePoco,
            Integer idFaturamentoSituacaoTipo, Integer idPoco,
            Short numeroDigitosLeituraPoco, Integer idHidrometroPoco) {
        this.id = id;
        this.indicadorHidrometroPoco = indicadorHidrometroPoco;
        this.numeroLeituraAnteriorPoco = numeroLeituraAnteriorPoco;
        this.numeroLeituraAnteriorInformadaPoco = numeroLeituraAnteriorInformadaPoco;
        this.idLeituraSituacaoAnteriorPoco = idLeituraSituacaoAnteriorPoco;
        this.numeroLeituraAtualPoco = numeroLeituraAtualPoco;
        this.idLeituraSituacaoAtualPoco = idLeituraSituacaoAtualPoco;
        this.idLeituraAnormalidadePoco = idLeituraAnormalidadePoco;
        this.idFaturamentoSituacaoTipo = idFaturamentoSituacaoTipo;
        this.idPoco = idPoco;
        this.numeroDigitosLeituraPoco = numeroDigitosLeituraPoco;
        this.idHidrometroPoco = idHidrometroPoco;
    }

    /**
     * Construtor da classe ImovelTestesMedicaoConsumo
     * 
     * @param id
     *            Descri��o do par�metro
     * @param idLigacaoEsgotoSituacao
     *            Descri��o do par�metro
     * @param numeroConsumoMinimoEsgoto
     *            Descri��o do par�metro
     * @param percentualColetaLigacaoEsgoto
     *            Descri��o do par�metro
     */
    public ImovelTestesMedicaoConsumo(Integer id,
            Integer idLigacaoEsgotoSituacao, Integer numeroConsumoMinimoEsgoto,
            BigDecimal percentualColetaLigacaoEsgoto) {
        this.id = id;
        this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
        this.numeroConsumoMinimoEsgoto = numeroConsumoMinimoEsgoto;
        this.percentualColetaLigacaoEsgoto = percentualColetaLigacaoEsgoto;
    }

    /**
     * default constructor
     */
    public ImovelTestesMedicaoConsumo() {
    }

    /**
     * minimal constructor
     * 
     * @param id
     *            Descri��o do par�metro
     * @param idLigacaoAguaSituacao
     *            Descri��o do par�metro
     * @param idLigacaoEsgotoSituacao
     *            Descri��o do par�metro
     * @param indicadorHidrometroAgua
     *            Descri��o do par�metro
     * @param indicadorHidrometroPoco
     *            Descri��o do par�metro
     * @param quantidadeEconomias
     *            Descri��o do par�metro
     */
    public ImovelTestesMedicaoConsumo(Integer id,
            Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao,
            Integer indicadorHidrometroAgua, Integer indicadorHidrometroPoco,
            int quantidadeEconomias) {
        this.id = id;
        this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
        this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
        this.indicadorHidrometroAgua = indicadorHidrometroAgua;
        this.indicadorHidrometroPoco = indicadorHidrometroPoco;
        this.quantidadeEconomias = quantidadeEconomias;
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de idLigacaoAguaSituacao
     * 
     * @return O valor de idLigacaoAguaSituacao
     */
    public Integer getIdLigacaoAguaSituacao() {
        return this.idLigacaoAguaSituacao;
    }

    /**
     * Seta o valor de idLigacaoAguaSituacao
     * 
     * @param idLigacaoAguaSituacao
     *            O novo valor de idLigacaoAguaSituacao
     */
    public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
        this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
    }

    /**
     * Retorna o valor de idLigacaoEsgotoSituacao
     * 
     * @return O valor de idLigacaoEsgotoSituacao
     */
    public Integer getIdLigacaoEsgotoSituacao() {
        return this.idLigacaoEsgotoSituacao;
    }

    /**
     * Seta o valor de idLigacaoEsgotoSituacao
     * 
     * @param idLigacaoEsgotoSituacao
     *            O novo valor de idLigacaoEsgotoSituacao
     */
    public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
        this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
    }

    /**
     * Retorna o valor de indicadorHidrometroAgua
     * 
     * @return O valor de indicadorHidrometroAgua
     */
    public Integer getIndicadorHidrometroAgua() {
        return this.indicadorHidrometroAgua;
    }

    /**
     * Seta o valor de indicadorHidrometroAgua
     * 
     * @param indicadorHidrometroAgua
     *            O novo valor de indicadorHidrometroAgua
     */
    public void setIndicadorHidrometroAgua(Integer indicadorHidrometroAgua) {
        this.indicadorHidrometroAgua = indicadorHidrometroAgua;
    }

    /**
     * Retorna o valor de indicadorHidrometroPoco
     * 
     * @return O valor de indicadorHidrometroPoco
     */
    public Integer getIndicadorHidrometroPoco() {
        return this.indicadorHidrometroPoco;
    }

    /**
     * Seta o valor de indicadorHidrometroPoco
     * 
     * @param indicadorHidrometroPoco
     *            O novo valor de indicadorHidrometroPoco
     */
    public void setIndicadorHidrometroPoco(Integer indicadorHidrometroPoco) {
        this.indicadorHidrometroPoco = indicadorHidrometroPoco;
    }

    /**
     * Retorna o valor de quantidadeEconomias
     * 
     * @return O valor de quantidadeEconomias
     */
    public int getQuantidadeEconomias() {
        return this.quantidadeEconomias;
    }

    /**
     * Seta o valor de quantidadeEconomias
     * 
     * @param quantidadeEconomias
     *            O novo valor de quantidadeEconomias
     */
    public void setQuantidadeEconomias(int quantidadeEconomias) {
        this.quantidadeEconomias = quantidadeEconomias;
    }

    /**
     * Retorna o valor de numeroLeituraAnteriorAgua
     * 
     * @return O valor de numeroLeituraAnteriorAgua
     */
    public Integer getNumeroLeituraAnteriorAgua() {
        return this.numeroLeituraAnteriorAgua;
    }

    /**
     * Seta o valor de numeroLeituraAnteriorAgua
     * 
     * @param numeroLeituraAnteriorAgua
     *            O novo valor de numeroLeituraAnteriorAgua
     */
    public void setNumeroLeituraAnteriorAgua(Integer numeroLeituraAnteriorAgua) {
        this.numeroLeituraAnteriorAgua = numeroLeituraAnteriorAgua;
    }

    /**
     * Retorna o valor de numeroLeituraAnteriorPoco
     * 
     * @return O valor de numeroLeituraAnteriorPoco
     */
    public Integer getNumeroLeituraAnteriorPoco() {
        return this.numeroLeituraAnteriorPoco;
    }

    /**
     * Seta o valor de numeroLeituraAnteriorPoco
     * 
     * @param numeroLeituraAnteriorPoco
     *            O novo valor de numeroLeituraAnteriorPoco
     */
    public void setNumeroLeituraAnteriorPoco(Integer numeroLeituraAnteriorPoco) {
        this.numeroLeituraAnteriorPoco = numeroLeituraAnteriorPoco;
    }

    /**
     * Retorna o valor de numeroLeituraAnteriorInformadaAgua
     * 
     * @return O valor de numeroLeituraAnteriorInformadaAgua
     */
    public Integer getNumeroLeituraAnteriorInformadaAgua() {
        return this.numeroLeituraAnteriorInformadaAgua;
    }

    /**
     * Seta o valor de numeroLeituraAnteriorInformadaAgua
     * 
     * @param numeroLeituraAnteriorInformadaAgua
     *            O novo valor de numeroLeituraAnteriorInformadaAgua
     */
    public void setNumeroLeituraAnteriorInformadaAgua(
            Integer numeroLeituraAnteriorInformadaAgua) {
        this.numeroLeituraAnteriorInformadaAgua = numeroLeituraAnteriorInformadaAgua;
    }

    /**
     * Retorna o valor de numeroLeituraAnteriorInformadaPoco
     * 
     * @return O valor de numeroLeituraAnteriorInformadaPoco
     */
    public Integer getNumeroLeituraAnteriorInformadaPoco() {
        return this.numeroLeituraAnteriorInformadaPoco;
    }

    /**
     * Seta o valor de numeroLeituraAnteriorInformadaPoco
     * 
     * @param numeroLeituraAnteriorInformadaPoco
     *            O novo valor de numeroLeituraAnteriorInformadaPoco
     */
    public void setNumeroLeituraAnteriorInformadaPoco(
            Integer numeroLeituraAnteriorInformadaPoco) {
        this.numeroLeituraAnteriorInformadaPoco = numeroLeituraAnteriorInformadaPoco;
    }

    /**
     * Retorna o valor de idLeituraSituacaoAnteriorAgua
     * 
     * @return O valor de idLeituraSituacaoAnteriorAgua
     */
    public Integer getIdLeituraSituacaoAnteriorAgua() {
        return this.idLeituraSituacaoAnteriorAgua;
    }

    /**
     * Seta o valor de idLeituraSituacaoAnteriorAgua
     * 
     * @param idLeituraSituacaoAnteriorAgua
     *            O novo valor de idLeituraSituacaoAnteriorAgua
     */
    public void setIdLeituraSituacaoAnteriorAgua(
            Integer idLeituraSituacaoAnteriorAgua) {
        this.idLeituraSituacaoAnteriorAgua = idLeituraSituacaoAnteriorAgua;
    }

    /**
     * Retorna o valor de idLeituraSituacaoAnteriorPoco
     * 
     * @return O valor de idLeituraSituacaoAnteriorPoco
     */
    public Integer getIdLeituraSituacaoAnteriorPoco() {
        return this.idLeituraSituacaoAnteriorPoco;
    }

    /**
     * Seta o valor de idLeituraSituacaoAnteriorPoco
     * 
     * @param idLeituraSituacaoAnteriorPoco
     *            O novo valor de idLeituraSituacaoAnteriorPoco
     */
    public void setIdLeituraSituacaoAnteriorPoco(
            Integer idLeituraSituacaoAnteriorPoco) {
        this.idLeituraSituacaoAnteriorPoco = idLeituraSituacaoAnteriorPoco;
    }

    /**
     * Retorna o valor de numeroLeituraAtualAgua
     * 
     * @return O valor de numeroLeituraAtualAgua
     */
    public Integer getNumeroLeituraAtualAgua() {
        return this.numeroLeituraAtualAgua;
    }

    /**
     * Seta o valor de numeroLeituraAtualAgua
     * 
     * @param numeroLeituraAtualAgua
     *            O novo valor de numeroLeituraAtualAgua
     */
    public void setNumeroLeituraAtualAgua(Integer numeroLeituraAtualAgua) {
        this.numeroLeituraAtualAgua = numeroLeituraAtualAgua;
    }

    /**
     * Retorna o valor de numeroLeituraAtualPoco
     * 
     * @return O valor de numeroLeituraAtualPoco
     */
    public Integer getNumeroLeituraAtualPoco() {
        return this.numeroLeituraAtualPoco;
    }

    /**
     * Seta o valor de numeroLeituraAtualPoco
     * 
     * @param numeroLeituraAtualPoco
     *            O novo valor de numeroLeituraAtualPoco
     */
    public void setNumeroLeituraAtualPoco(Integer numeroLeituraAtualPoco) {
        this.numeroLeituraAtualPoco = numeroLeituraAtualPoco;
    }

    /**
     * Retorna o valor de idLeituraSituacaoAtualAgua
     * 
     * @return O valor de idLeituraSituacaoAtualAgua
     */
    public Integer getIdLeituraSituacaoAtualAgua() {
        return this.idLeituraSituacaoAtualAgua;
    }

    /**
     * Seta o valor de idLeituraSituacaoAtualAgua
     * 
     * @param idLeituraSituacaoAtualAgua
     *            O novo valor de idLeituraSituacaoAtualAgua
     */
    public void setIdLeituraSituacaoAtualAgua(Integer idLeituraSituacaoAtualAgua) {
        this.idLeituraSituacaoAtualAgua = idLeituraSituacaoAtualAgua;
    }

    /**
     * Retorna o valor de idLeituraSituacaoAtualPoco
     * 
     * @return O valor de idLeituraSituacaoAtualPoco
     */
    public Integer getIdLeituraSituacaoAtualPoco() {
        return this.idLeituraSituacaoAtualPoco;
    }

    /**
     * Seta o valor de idLeituraSituacaoAtualPoco
     * 
     * @param idLeituraSituacaoAtualPoco
     *            O novo valor de idLeituraSituacaoAtualPoco
     */
    public void setIdLeituraSituacaoAtualPoco(Integer idLeituraSituacaoAtualPoco) {
        this.idLeituraSituacaoAtualPoco = idLeituraSituacaoAtualPoco;
    }

    /**
     * Retorna o valor de idLeituraAnormalidadeAgua
     * 
     * @return O valor de idLeituraAnormalidadeAgua
     */
    public Integer getIdLeituraAnormalidadeAgua() {
        return this.idLeituraAnormalidadeAgua;
    }

    /**
     * Seta o valor de idLeituraAnormalidadeAgua
     * 
     * @param idLeituraAnormalidadeAgua
     *            O novo valor de idLeituraAnormalidadeAgua
     */
    public void setIdLeituraAnormalidadeAgua(Integer idLeituraAnormalidadeAgua) {
        this.idLeituraAnormalidadeAgua = idLeituraAnormalidadeAgua;
    }

    /**
     * Retorna o valor de idLeituraAnormalidadePoco
     * 
     * @return O valor de idLeituraAnormalidadePoco
     */
    public Integer getIdLeituraAnormalidadePoco() {
        return this.idLeituraAnormalidadePoco;
    }

    /**
     * Seta o valor de idLeituraAnormalidadePoco
     * 
     * @param idLeituraAnormalidadePoco
     *            O novo valor de idLeituraAnormalidadePoco
     */
    public void setIdLeituraAnormalidadePoco(Integer idLeituraAnormalidadePoco) {
        this.idLeituraAnormalidadePoco = idLeituraAnormalidadePoco;
    }

    /**
     * Retorna o valor de idFaturamentoSituacaoTipo
     * 
     * @return O valor de idFaturamentoSituacaoTipo
     */
    public Integer getIdFaturamentoSituacaoTipo() {
        return this.idFaturamentoSituacaoTipo;
    }

    /**
     * Seta o valor de idFaturamentoSituacaoTipo
     * 
     * @param idFaturamentoSituacaoTipo
     *            O novo valor de idFaturamentoSituacaoTipo
     */
    public void setIdFaturamentoSituacaoTipo(Integer idFaturamentoSituacaoTipo) {
        this.idFaturamentoSituacaoTipo = idFaturamentoSituacaoTipo;
    }

    /**
     * Retorna o valor de numeroMedioConsumoHidrometroAgua
     * 
     * @return O valor de numeroMedioConsumoHidrometroAgua
     */
    public Integer getNumeroMedioConsumoHidrometroAgua() {
        return this.numeroMedioConsumoHidrometroAgua;
    }

    /**
     * Seta o valor de numeroMedioConsumoHidrometroAgua
     * 
     * @param numeroMedioConsumoHidrometroAgua
     *            O novo valor de numeroMedioConsumoHidrometroAgua
     */
    public void setNumeroMedioConsumoHidrometroAgua(
            Integer numeroMedioConsumoHidrometroAgua) {
        this.numeroMedioConsumoHidrometroAgua = numeroMedioConsumoHidrometroAgua;
    }

    /**
     * Retorna o valor de numeroMedioConsumoHidrometroPoco
     * 
     * @return O valor de numeroMedioConsumoHidrometroPoco
     */
    public Integer getNumeroMedioConsumoHidrometroPoco() {
        return this.numeroMedioConsumoHidrometroPoco;
    }

    /**
     * Seta o valor de numeroMedioConsumoHidrometroPoco
     * 
     * @param numeroMedioConsumoHidrometroPoco
     *            O novo valor de numeroMedioConsumoHidrometroPoco
     */
    public void setNumeroMedioConsumoHidrometroPoco(
            Integer numeroMedioConsumoHidrometroPoco) {
        this.numeroMedioConsumoHidrometroPoco = numeroMedioConsumoHidrometroPoco;
    }

    /**
     * Retorna o valor de numeroMedioConsumoImovel
     * 
     * @return O valor de numeroMedioConsumoImovel
     */
    public Integer getNumeroMedioConsumoImovel() {
        return this.numeroMedioConsumoImovel;
    }

    /**
     * Seta o valor de numeroMedioConsumoImovel
     * 
     * @param numeroMedioConsumoImovel
     *            O novo valor de numeroMedioConsumoImovel
     */
    public void setNumeroMedioConsumoImovel(Integer numeroMedioConsumoImovel) {
        this.numeroMedioConsumoImovel = numeroMedioConsumoImovel;
    }

    /**
     * Retorna o valor de numeroFaixaIncialAgua
     * 
     * @return O valor de numeroFaixaIncialAgua
     */
    public Integer getNumeroFaixaIncialAgua() {
        return this.numeroFaixaIncialAgua;
    }

    /**
     * Seta o valor de numeroFaixaIncialAgua
     * 
     * @param numeroFaixaIncialAgua
     *            O novo valor de numeroFaixaIncialAgua
     */
    public void setNumeroFaixaIncialAgua(Integer numeroFaixaIncialAgua) {
        this.numeroFaixaIncialAgua = numeroFaixaIncialAgua;
    }

    /**
     * Retorna o valor de numeroFaixaFinalAgua
     * 
     * @return O valor de numeroFaixaFinalAgua
     */
    public Integer getNumeroFaixaFinalAgua() {
        return this.numeroFaixaFinalAgua;
    }

    /**
     * Seta o valor de numeroFaixaFinalAgua
     * 
     * @param numeroFaixaFinalAgua
     *            O novo valor de numeroFaixaFinalAgua
     */
    public void setNumeroFaixaFinalAgua(Integer numeroFaixaFinalAgua) {
        this.numeroFaixaFinalAgua = numeroFaixaFinalAgua;
    }

    /**
     * Retorna o valor de numeroFaixaInicialPoco
     * 
     * @return O valor de numeroFaixaInicialPoco
     */
    public Integer getNumeroFaixaInicialPoco() {
        return this.numeroFaixaInicialPoco;
    }

    /**
     * Seta o valor de numeroFaixaInicialPoco
     * 
     * @param numeroFaixaInicialPoco
     *            O novo valor de numeroFaixaInicialPoco
     */
    public void setNumeroFaixaInicialPoco(Integer numeroFaixaInicialPoco) {
        this.numeroFaixaInicialPoco = numeroFaixaInicialPoco;
    }

    /**
     * Retorna o valor de numeroFaixaFinalPoco
     * 
     * @return O valor de numeroFaixaFinalPoco
     */
    public Integer getNumeroFaixaFinalPoco() {
        return this.numeroFaixaFinalPoco;
    }

    /**
     * Seta o valor de numeroFaixaFinalPoco
     * 
     * @param numeroFaixaFinalPoco
     *            O novo valor de numeroFaixaFinalPoco
     */
    public void setNumeroFaixaFinalPoco(Integer numeroFaixaFinalPoco) {
        this.numeroFaixaFinalPoco = numeroFaixaFinalPoco;
    }

    /**
     * Retorna o valor de numeroConsumoMinimoAgua
     * 
     * @return O valor de numeroConsumoMinimoAgua
     */
    public Integer getNumeroConsumoMinimoAgua() {
        return this.numeroConsumoMinimoAgua;
    }

    /**
     * Seta o valor de numeroConsumoMinimoAgua
     * 
     * @param numeroConsumoMinimoAgua
     *            O novo valor de numeroConsumoMinimoAgua
     */
    public void setNumeroConsumoMinimoAgua(Integer numeroConsumoMinimoAgua) {
        this.numeroConsumoMinimoAgua = numeroConsumoMinimoAgua;
    }

    /**
     * Retorna o valor de numeroConsumoMinimoEsgoto
     * 
     * @return O valor de numeroConsumoMinimoEsgoto
     */
    public Integer getNumeroConsumoMinimoEsgoto() {
        return this.numeroConsumoMinimoEsgoto;
    }

    /**
     * Seta o valor de numeroConsumoMinimoEsgoto
     * 
     * @param numeroConsumoMinimoEsgoto
     *            O novo valor de numeroConsumoMinimoEsgoto
     */
    public void setNumeroConsumoMinimoEsgoto(Integer numeroConsumoMinimoEsgoto) {
        this.numeroConsumoMinimoEsgoto = numeroConsumoMinimoEsgoto;
    }

    /**
     * Retorna o valor de idPoco
     * 
     * @return O valor de idPoco
     */
    public Integer getIdPoco() {
        return this.idPoco;
    }

    /**
     * Seta o valor de idPoco
     * 
     * @param idPoco
     *            O novo valor de idPoco
     */
    public void setIdPoco(Integer idPoco) {
        this.idPoco = idPoco;
    }

    /**
     * Retorna o valor de numeroDigitosLeituraAgua
     * 
     * @return O valor de numeroDigitosLeituraAgua
     */
    public Short getNumeroDigitosLeituraAgua() {
        return this.numeroDigitosLeituraAgua;
    }

    /**
     * Seta o valor de numeroDigitosLeituraAgua
     * 
     * @param numeroDigitosLeituraAgua
     *            O novo valor de numeroDigitosLeituraAgua
     */
    public void setNumeroDigitosLeituraAgua(Short numeroDigitosLeituraAgua) {
        this.numeroDigitosLeituraAgua = numeroDigitosLeituraAgua;
    }

    /**
     * Retorna o valor de numeroDigitosLeituraPoco
     * 
     * @return O valor de numeroDigitosLeituraPoco
     */
    public Short getNumeroDigitosLeituraPoco() {
        return this.numeroDigitosLeituraPoco;
    }

    /**
     * Seta o valor de numeroDigitosLeituraPoco
     * 
     * @param numeroDigitosLeituraPoco
     *            O novo valor de numeroDigitosLeituraPoco
     */
    public void setNumeroDigitosLeituraPoco(Short numeroDigitosLeituraPoco) {
        this.numeroDigitosLeituraPoco = numeroDigitosLeituraPoco;
    }

    /**
     * Retorna o valor de idHidrometroAgua
     * 
     * @return O valor de idHidrometroAgua
     */
    public Integer getIdHidrometroAgua() {
        return this.idHidrometroAgua;
    }

    /**
     * Seta o valor de idHidrometroAgua
     * 
     * @param idHidrometroAgua
     *            O novo valor de idHidrometroAgua
     */
    public void setIdHidrometroAgua(Integer idHidrometroAgua) {
        this.idHidrometroAgua = idHidrometroAgua;
    }

    /**
     * Retorna o valor de idHidrometroPoco
     * 
     * @return O valor de idHidrometroPoco
     */
    public Integer getIdHidrometroPoco() {
        return this.idHidrometroPoco;
    }

    /**
     * Seta o valor de idHidrometroPoco
     * 
     * @param idHidrometroPoco
     *            O novo valor de idHidrometroPoco
     */
    public void setIdHidrometroPoco(Integer idHidrometroPoco) {
        this.idHidrometroPoco = idHidrometroPoco;
    }

    /**
     * Retorna o valor de percentualColetaLigacaoEsgoto
     * 
     * @return O valor de percentualColetaLigacaoEsgoto
     */
    public BigDecimal getPercentualColetaLigacaoEsgoto() {
        return this.percentualColetaLigacaoEsgoto;
    }

    /**
     * Seta o valor de percentualColetaLigacaoEsgoto
     * 
     * @param percentualColetaLigacaoEsgoto
     *            O novo valor de percentualColetaLigacaoEsgoto
     */
    public void setPercentualColetaLigacaoEsgoto(
            BigDecimal percentualColetaLigacaoEsgoto) {
        this.percentualColetaLigacaoEsgoto = percentualColetaLigacaoEsgoto;
    }

    /**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     */
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    /**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @return Descri��o do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}
