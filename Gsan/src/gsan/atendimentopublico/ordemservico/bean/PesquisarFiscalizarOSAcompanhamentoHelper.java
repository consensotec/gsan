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
package gsan.atendimentopublico.ordemservico.bean;

import gsan.util.ConstantesSistema;

import java.util.Date;
import java.util.Set;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa de ordem de servico
 *
 * @author Rafael Pinto
 * @date 17/08/2006
 */
public class PesquisarFiscalizarOSAcompanhamentoHelper {
	
	private static final long serialVersionUID = 1L;
	
	private Integer numeroOS = null;
	private Integer documentoCobranca = null;
	private Integer matriculaImovel = null;
	
	private Date dataAtendimentoInicial = null;
	private Date dataAtendimentoFinal = null;
	private Date dataGeracaoInicial = null;
	private Date dataGeracaoFinal = null;
	private Date dataEncerramentoInicial = null;
	private Date dataEncerramentoFinal = null;	
	
	
	private Integer municipio = null;
	private Integer bairro = null;
	private Integer areaBairro = null;
	private Integer logradouro = null;
	
	private Integer criterioSelecao = null;
		
	private Set<?> colecaoIdsOS = null;
	
	private int numeroPaginasPesquisa = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
	
	
	public PesquisarFiscalizarOSAcompanhamentoHelper() {
	}

	public Integer getAreaBairro() {
		return areaBairro;
	}

	public void setAreaBairro(Integer areaBairro) {
		this.areaBairro = areaBairro;
	}

	public Integer getBairro() {
		return bairro;
	}

	public void setBairro(Integer bairro) {
		this.bairro = bairro;
	}

	public Date getDataAtendimentoFinal() {
		return dataAtendimentoFinal;
	}	

	

	public void setDataAtendimentoFinal(Date dataAtendimentoFinal) {
		this.dataAtendimentoFinal = dataAtendimentoFinal;
	}

	public Date getDataAtendimentoInicial() {
		return dataAtendimentoInicial;
	}

	public void setDataAtendimentoInicial(Date dataAtendimentoInicial) {
		this.dataAtendimentoInicial = dataAtendimentoInicial;
	}

	public Date getDataEncerramentoFinal() {
		return dataEncerramentoFinal;
	}

	public void setDataEncerramentoFinal(Date dataEncerramentoFinal) {
		this.dataEncerramentoFinal = dataEncerramentoFinal;
	}

	public Date getDataEncerramentoInicial() {
		return dataEncerramentoInicial;
	}

	public void setDataEncerramentoInicial(Date dataEncerramentoInicial) {
		this.dataEncerramentoInicial = dataEncerramentoInicial;
	}

	public Date getDataGeracaoFinal() {
		return dataGeracaoFinal;
	}

	public void setDataGeracaoFinal(Date dataGeracaoFinal) {
		this.dataGeracaoFinal = dataGeracaoFinal;
	}

	public Date getDataGeracaoInicial() {
		return dataGeracaoInicial;
	}

	public void setDataGeracaoInicial(Date dataGeracaoInicial) {
		this.dataGeracaoInicial = dataGeracaoInicial;
	}

	public Integer getDocumentoCobranca() {
		return documentoCobranca;
	}

	public void setDocumentoCobranca(Integer documentoCobranca) {
		this.documentoCobranca = documentoCobranca;
	}
	
	public Integer getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Integer logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}

	
	public Set<?> getColecaoIdsOS() {
		return colecaoIdsOS;
	}

	public void setColecaoIdsOS(Set<?> colecaoIdsOS) {
		this.colecaoIdsOS = colecaoIdsOS;
	}

	public int getNumeroPaginasPesquisa() {
		return numeroPaginasPesquisa;
	}

	public void setNumeroPaginasPesquisa(int numeroPaginasPesquisa) {
		this.numeroPaginasPesquisa = numeroPaginasPesquisa;
	}

	public Integer getCriterioSelecao() {
		return criterioSelecao;
	}

	public void setCriterioSelecao(Integer criterioSelecao) {
		this.criterioSelecao = criterioSelecao;
	}
	
	public Integer getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
	}

	
		
}
