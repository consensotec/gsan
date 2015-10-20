/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cobranca.parcelamento;

import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class ParcelamentoPerfilDebitos extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal percentualDescontoAVista;

    /** nullable persistent field */
    private BigDecimal percentualDescontoParcelado;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private ParcelamentoPerfil parcelamentoPerfil;
    
    private DebitoTipo debitoTipo;

    
    private String percentualDescontoAVistaFormatado;
    private String percentualDescontoParceladoFormatado;
    
    
    public ParcelamentoPerfilDebitos(){
    	
    }
    
	public ParcelamentoPerfilDebitos(Integer id,
			BigDecimal percentualDescontoAVista,
			BigDecimal percentualDescontoParcelado, Date ultimaAlteracao,
			ParcelamentoPerfil parcelamentoPerfil, DebitoTipo debitoTipo) {
		super();
		this.id = id;
		this.percentualDescontoAVista = percentualDescontoAVista;
		this.percentualDescontoParcelado = percentualDescontoParcelado;
		this.ultimaAlteracao = ultimaAlteracao;
		this.parcelamentoPerfil = parcelamentoPerfil;
		this.debitoTipo = debitoTipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPercentualDescontoAVista() {
		return percentualDescontoAVista;
	}

	public void setPercentualDescontoAVista(BigDecimal percentualDescontoAVista) {
		this.percentualDescontoAVista = percentualDescontoAVista;
	}

	public BigDecimal getPercentualDescontoParcelado() {
		return percentualDescontoParcelado;
	}

	public void setPercentualDescontoParcelado(
			BigDecimal percentualDescontoParcelado) {
		this.percentualDescontoParcelado = percentualDescontoParcelado;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ParcelamentoPerfil getParcelamentoPerfil() {
		return parcelamentoPerfil;
	}

	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	public String getPercentualDescontoAVistaFormatado() {
		if (getPercentualDescontoAVista() != null ){
			percentualDescontoAVistaFormatado = Util.formatarMoedaReal(getPercentualDescontoAVista());
		}
		
		return percentualDescontoAVistaFormatado;
	}

	public void setPercentualDescontoAVistaFormatado(
			String percentualDescontoAVistaFormatado) {
		this.percentualDescontoAVistaFormatado = percentualDescontoAVistaFormatado;
	}

	public String getPercentualDescontoParceladoFormatado() {
		if (getPercentualDescontoParcelado() != null ){
			percentualDescontoParceladoFormatado = Util.formatarMoedaReal(getPercentualDescontoParcelado());
		}
		return percentualDescontoParceladoFormatado;
	}

	public void setPercentualDescontoParceladoFormatado(
			String percentualDescontoParceladoFormatado) {
		this.percentualDescontoParceladoFormatado = percentualDescontoParceladoFormatado;
	}

}
