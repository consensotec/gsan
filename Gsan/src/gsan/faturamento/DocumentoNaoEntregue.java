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
package gsan.faturamento;

import gsan.cobranca.FiltroDocumentoCobranca;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DocumentoNaoEntregue extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataTentativaEntrega;

    /** persistent field */
    private short numeroTentativa;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gsan.cadastro.cliente.Cliente cliente;

    /** persistent field */
    private gsan.faturamento.conta.Fatura fatura;

    /** persistent field */
    private gsan.cobranca.CobrancaDocumento cobrancaDocumento;

    /** persistent field */
    private gsan.cobranca.DocumentoTipo documentoTipo;

    /** persistent field */
    private gsan.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento;

    /** persistent field */
    private gsan.faturamento.GuiaPagamentoGeral guiaPagamentoGeral;

    /** persistent field */
    private gsan.faturamento.conta.ContaGeral contaGeral;

    /** full constructor */
    public DocumentoNaoEntregue(Integer id, Date dataTentativaEntrega, short numeroTentativa, Date ultimaAlteracao, gsan.cadastro.cliente.Cliente cliente, gsan.faturamento.conta.Fatura fatura, gsan.cobranca.CobrancaDocumento cobrancaDocumento, gsan.cobranca.DocumentoTipo documentoTipo, gsan.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento, gsan.faturamento.GuiaPagamentoGeral guiaPagamentoGeral, gsan.faturamento.conta.ContaGeral contaGeral) {
        this.id = id;
        this.dataTentativaEntrega = dataTentativaEntrega;
        this.numeroTentativa = numeroTentativa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.fatura = fatura;
        this.cobrancaDocumento = cobrancaDocumento;
        this.documentoTipo = documentoTipo;
        this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.contaGeral = contaGeral;
    }

    /** default constructor */
    public DocumentoNaoEntregue() {
    }

	public gsan.cadastro.cliente.Cliente getCliente() {
		return cliente;
	}

	public void setCliente(gsan.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public gsan.cobranca.CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(
			gsan.cobranca.CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public gsan.faturamento.conta.ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(gsan.faturamento.conta.ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public Date getDataTentativaEntrega() {
		return dataTentativaEntrega;
	}

	public void setDataTentativaEntrega(Date dataTentativaEntrega) {
		this.dataTentativaEntrega = dataTentativaEntrega;
	}

	public gsan.cobranca.DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(gsan.cobranca.DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public gsan.faturamento.conta.Fatura getFatura() {
		return fatura;
	}

	public void setFatura(gsan.faturamento.conta.Fatura fatura) {
		this.fatura = fatura;
	}

	public gsan.faturamento.GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(
			gsan.faturamento.GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gsan.faturamento.conta.MotivoNaoEntregaDocumento getMotivoNaoEntregaDocumento() {
		return motivoNaoEntregaDocumento;
	}

	public void setMotivoNaoEntregaDocumento(
			gsan.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}

	public short getNumeroTentativa() {
		return numeroTentativa;
	}

	public void setNumeroTentativa(short numeroTentativa) {
		this.numeroTentativa = numeroTentativa;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	   public String toString() {	        return new ToStringBuilder(this)	            .append("id", getId())	            .toString();	    }	    		public String[] retornaCamposChavePrimaria(){			String[] retorno = new String[1];			retorno[0] = "id";			return retorno;		}			    public Filtro retornaFiltro(){	    	FiltroDocumentoCobranca filtroDocumentoNaoEntregue = new FiltroDocumentoCobranca();	    	filtroDocumentoNaoEntregue.adicionarParametro(new ParametroSimples(FiltroDocumentoCobranca.ID,this.getId()));	    	filtroDocumentoNaoEntregue.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");						return filtroDocumentoNaoEntregue;		}

}
