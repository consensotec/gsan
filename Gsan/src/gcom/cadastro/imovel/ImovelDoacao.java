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
package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ImovelDoacao extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private BigDecimal valorDoacao;
	
	/** persistent field */
	private Date dataAdesao;
	
	/** nullable persistent field */
	private Date dataCancelamento;
	
	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private EntidadeBeneficente entidadeBeneficente;

	/** persistent field */
	private Usuario usuarioAdesao;

	/** persistent field */
	private Usuario usuarioCancelamento;

	/** persistent field */
	private Imovel imovel;
	
	/** persistent field */
	private Integer anoMesReferenciaInicial;
	
	/** persistent field */
	private Integer anoMesReferenciaFinal;

	/** full constructor */
	public ImovelDoacao(Imovel imovel, 
			            BigDecimal valorDoacao, 
			            EntidadeBeneficente entidadeBeneficente,
			            Date dataAdesao,
			            Usuario usuarioIdAdesao,
			            Date dataCancelamento,
			            Usuario usuarioIdCancelamento,
			            Date ultimaAlteracao) {
		this.valorDoacao = valorDoacao;
		this.dataAdesao = dataAdesao;
		this.dataCancelamento = dataCancelamento;
		this.ultimaAlteracao = ultimaAlteracao;
		this.entidadeBeneficente = entidadeBeneficente;
		this.usuarioAdesao = usuarioIdAdesao;
		this.usuarioCancelamento = usuarioIdCancelamento;
		this.imovel = imovel;
	}

	/** default constructor */
	public ImovelDoacao() {
	}

	/** minimal constructor */
	public ImovelDoacao(Imovel imovel,
			            BigDecimal valorDoacao,
			            EntidadeBeneficente entidadeBeneficente,
			            Date dataAdesao,
			            Usuario usuarioAdesao,
			            Date ultimaAlteracao) {
		this.valorDoacao = valorDoacao;
		this.dataAdesao = dataAdesao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.entidadeBeneficente = entidadeBeneficente;
		this.usuarioAdesao = usuarioAdesao;
		this.imovel = imovel;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorDoacao() {
		return this.valorDoacao;
	}
	
	public String getValorDoacaoAsString() {
		return Util.formataBigDecimal(this.valorDoacao, 2, true);
	}

	public void setValorDoacao(BigDecimal valorDoacao) {
		this.valorDoacao = valorDoacao;
	}

	public Date getDataAdesao() {
		return this.dataAdesao;
	}
	
	public String getDataAdesaoAsString() {
		return Util.formatarData(this.dataAdesao);
	}

	public void setDataAdesao(Date dataAdesao) {
		this.dataAdesao = dataAdesao;
	}

	public Date getDataCancelamento() {
		return this.dataCancelamento;
	}
	
	public String getDataCancelamentoAsString() {
		return Util.formatarData(this.dataCancelamento);
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public EntidadeBeneficente getEntidadeBeneficente() {
		return this.entidadeBeneficente;
	}

	public void setEntidadeBeneficente(EntidadeBeneficente entidadeBeneficente) {
		this.entidadeBeneficente = entidadeBeneficente;
	}

	public Usuario getUsuarioAdesao() {
		return this.usuarioAdesao;
	}

	public void setUsuarioAdesao(Usuario usuarioIdAdesao) {
		this.usuarioAdesao = usuarioIdAdesao;
	}

	public Usuario getUsuarioCancelamento() {
		return this.usuarioCancelamento;
	}

	public void setUsuarioCancelamento(Usuario usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
    public String[] retornaCamposChavePrimaria(){
 		String[] retorno = new String[1];
 		retorno[0] = "id";
 		return retorno;
 	}

	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}

	public void setAnoMesReferenciaFinal(Integer anoMesReferenciaFinal) {
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
	}

	public Integer getAnoMesReferenciaInicial() {
		return anoMesReferenciaInicial;
	}

	public void setAnoMesReferenciaInicial(Integer anoMesReferenciaInicial) {
		this.anoMesReferenciaInicial = anoMesReferenciaInicial;
	}

	public Filtro retornaFiltro(){
 		FiltroImovelDoacao filtroImovelDoacao = new FiltroImovelDoacao();
 		
 		filtroImovelDoacao.adicionarParametro(new ParametroSimples(FiltroImovelDoacao.ID, this.getId()));
 		filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("entidadeBeneficente");
 		filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("imovel");

 		return filtroImovelDoacao; 
 	}
}
