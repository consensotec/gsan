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
package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * [UC0721] - Distribuir dados do Registro de Movimento do Arrecadador da Ficha de Compensa��o
 * Autor: Vivianne Sousa
 * Data: 23/11/2007
 */
public class RegistroHelperCodigo5 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigo5() {
	}

	private String codigoBancoCompensacao;
    
    private String loteServico;
    
    private String codigoRegistro;
    
    private String usoExclusivo04;
    
    private String qtdeRegistrosLote;
    
    private String qtdeTITCobranca06;
    
    private String valorTITCarteira07;
    
    private String qtdeTITCobranca08;
    
    private String valorTITCarteira09;
    
    private String qtdeTITCobranca10;
    
    private String valorTITCarteira11;
    
    private String qtdeTITCobranca12;
    
    private String valorTITCarteira13;
    
    private String numeroAvisoLancamento;
    
    private String usoExclusivo15;

    public String getCodigoBancoCompensacao() {
        return codigoBancoCompensacao;
    }

    public void setCodigoBancoCompensacao(String codigoBancoCompensacao) {
        this.codigoBancoCompensacao = codigoBancoCompensacao;
    }

    public String getLoteServico() {
        return loteServico;
    }

    public void setLoteServico(String loteServico) {
        this.loteServico = loteServico;
    }

    public String getNumeroAvisoLancamento() {
        return numeroAvisoLancamento;
    }

    public void setNumeroAvisoLancamento(String numeroAvisoLancamento) {
        this.numeroAvisoLancamento = numeroAvisoLancamento;
    }

    public String getQtdeRegistrosLote() {
        return qtdeRegistrosLote;
    }

    public void setQtdeRegistrosLote(String qtdeRegistrosLote) {
        this.qtdeRegistrosLote = qtdeRegistrosLote;
    }

    public String getQtdeTITCobranca06() {
        return qtdeTITCobranca06;
    }

    public void setQtdeTITCobranca06(String qtdeTITCobranca06) {
        this.qtdeTITCobranca06 = qtdeTITCobranca06;
    }

    public String getQtdeTITCobranca08() {
        return qtdeTITCobranca08;
    }

    public void setQtdeTITCobranca08(String qtdeTITCobranca08) {
        this.qtdeTITCobranca08 = qtdeTITCobranca08;
    }

    public String getQtdeTITCobranca10() {
        return qtdeTITCobranca10;
    }

    public void setQtdeTITCobranca10(String qtdeTITCobranca10) {
        this.qtdeTITCobranca10 = qtdeTITCobranca10;
    }

    public String getQtdeTITCobranca12() {
        return qtdeTITCobranca12;
    }

    public void setQtdeTITCobranca12(String qtdeTITCobranca12) {
        this.qtdeTITCobranca12 = qtdeTITCobranca12;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getUsoExclusivo04() {
        return usoExclusivo04;
    }

    public void setUsoExclusivo04(String usoExclusivo04) {
        this.usoExclusivo04 = usoExclusivo04;
    }

    public String getUsoExclusivo15() {
        return usoExclusivo15;
    }

    public void setUsoExclusivo15(String usoExclusivo15) {
        this.usoExclusivo15 = usoExclusivo15;
    }

    public String getValorTITCarteira07() {
        return valorTITCarteira07;
    }

    public void setValorTITCarteira07(String valorTITCarteira07) {
        this.valorTITCarteira07 = valorTITCarteira07;
    }

    public String getValorTITCarteira09() {
        return valorTITCarteira09;
    }

    public void setValorTITCarteira09(String valorTITCarteira09) {
        this.valorTITCarteira09 = valorTITCarteira09;
    }

    public String getValorTITCarteira11() {
        return valorTITCarteira11;
    }

    public void setValorTITCarteira11(String valorTITCarteira11) {
        this.valorTITCarteira11 = valorTITCarteira11;
    }

    public String getValorTITCarteira13() {
        return valorTITCarteira13;
    }

    public void setValorTITCarteira13(String valorTITCarteira13) {
        this.valorTITCarteira13 = valorTITCarteira13;
    }

    
}
