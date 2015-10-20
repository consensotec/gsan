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
public class RegistroHelperCodigo1 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigo1() {
	}
    
    private String codigoBancoCompensacao;

    private String loteServico;
    
    private String codigoRegistro;
    
    private String tipoOperacao;
    
    private String tipoServico;
    
    private String formaLancamento;
    
    private String numeroVersaoLayoutArquivo;
    
    private String usoExclusivo08;
    
    private String tipoInscricaoEmpresa;
    
    private String numeroInscricaoEmpresa;
    
    private String codigoConvenioBanco;
    
    private String agenciaMantedoraConta;
    
    private String digitoVerificadorAgencia;
    
    private String numeroContaCorrente;
    
    private String digitoVerificadorConta;
    
    private String digitoVerificadorAgConta;
    
    private String nomeEmpresa;
    
    private String mensagem1;
    
    private String mensagem2;
    
    private String numeroRemessaRetorno;
    
    private String dataGravacaoRemessaRetorno;
    
    private String dataCredito;

    private String usoExclusivo23;

    public String getAgenciaMantedoraConta() {
        return agenciaMantedoraConta;
    }

    public void setAgenciaMantedoraConta(String agenciaMantedoraConta) {
        this.agenciaMantedoraConta = agenciaMantedoraConta;
    }

    public String getCodigoBancoCompensacao() {
        return codigoBancoCompensacao;
    }

    public void setCodigoBancoCompensacao(String codigoBancoCompensacao) {
        this.codigoBancoCompensacao = codigoBancoCompensacao;
    }

    public String getCodigoConvenioBanco() {
        return codigoConvenioBanco;
    }

    public void setCodigoConvenioBanco(String codigoConvenioBanco) {
        this.codigoConvenioBanco = codigoConvenioBanco;
    }

    public String getDataCredito() {
        return dataCredito;
    }

    public void setDataCredito(String dataCredito) {
        this.dataCredito = dataCredito;
    }

    public String getDataGravacaoRemessaRetorno() {
        return dataGravacaoRemessaRetorno;
    }

    public void setDataGravacaoRemessaRetorno(String dataGravacaoRemessaRetorno) {
        this.dataGravacaoRemessaRetorno = dataGravacaoRemessaRetorno;
    }

    public String getDigitoVerificadorAgConta() {
        return digitoVerificadorAgConta;
    }

    public void setDigitoVerificadorAgConta(String digitoVerificadorAgConta) {
        this.digitoVerificadorAgConta = digitoVerificadorAgConta;
    }

    public String getDigitoVerificadorAgencia() {
        return digitoVerificadorAgencia;
    }

    public void setDigitoVerificadorAgencia(String digitoVerificadorAgencia) {
        this.digitoVerificadorAgencia = digitoVerificadorAgencia;
    }

    public String getDigitoVerificadorConta() {
        return digitoVerificadorConta;
    }

    public void setDigitoVerificadorConta(String digitoVerificadorConta) {
        this.digitoVerificadorConta = digitoVerificadorConta;
    }

    public String getFormaLancamento() {
        return formaLancamento;
    }

    public void setFormaLancamento(String formaLancamento) {
        this.formaLancamento = formaLancamento;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getLoteServico() {
        return loteServico;
    }

    public void setLoteServico(String loteServico) {
        this.loteServico = loteServico;
    }

    public String getMensagem1() {
        return mensagem1;
    }

    public void setMensagem1(String mensagem1) {
        this.mensagem1 = mensagem1;
    }

    public String getMensagem2() {
        return mensagem2;
    }

    public void setMensagem2(String mensagem2) {
        this.mensagem2 = mensagem2;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNumeroContaCorrente() {
        return numeroContaCorrente;
    }

    public void setNumeroContaCorrente(String numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
    }

    public String getNumeroInscricaoEmpresa() {
        return numeroInscricaoEmpresa;
    }

    public void setNumeroInscricaoEmpresa(String numeroInscricaoEmpresa) {
        this.numeroInscricaoEmpresa = numeroInscricaoEmpresa;
    }

    public String getNumeroRemessaRetorno() {
        return numeroRemessaRetorno;
    }

    public void setNumeroRemessaRetorno(String numeroRemessaRetorno) {
        this.numeroRemessaRetorno = numeroRemessaRetorno;
    }

    public String getNumeroVersaoLayoutArquivo() {
        return numeroVersaoLayoutArquivo;
    }

    public void setNumeroVersaoLayoutArquivo(String numeroVersaoLayoutArquivo) {
        this.numeroVersaoLayoutArquivo = numeroVersaoLayoutArquivo;
    }

    public String getTipoInscricaoEmpresa() {
        return tipoInscricaoEmpresa;
    }

    public void setTipoInscricaoEmpresa(String tipoInscricaoEmpresa) {
        this.tipoInscricaoEmpresa = tipoInscricaoEmpresa;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getUsoExclusivo08() {
        return usoExclusivo08;
    }

    public void setUsoExclusivo08(String usoExclusivo08) {
        this.usoExclusivo08 = usoExclusivo08;
    }

    public String getUsoExclusivo23() {
        return usoExclusivo23;
    }

    public void setUsoExclusivo23(String usoExclusivo23) {
        this.usoExclusivo23 = usoExclusivo23;
    }
    
	}
