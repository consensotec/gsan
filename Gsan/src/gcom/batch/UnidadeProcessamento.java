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
package gcom.batch;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UnidadeProcessamento implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int ROTA = 1;

	public static final int CONTA = 2;

	public static final int FATURAMENTO_ATIVIDADE_CRONOGRAMA_ROTA = 3;

	public static final int RELATORIO = 4;

	public static final int FUNCIONALIDADE = 5;

	public static final int LOCALIDADE = 6;

	public static final int COBRANCA_GRUPO_CRONOGRAMA_MES = 7;

	public static final int SETOR_COMERCIAL = 8;

	public static final int HIDROMETRO_MARCA = 9;

	public static final int COB_ACAO_ATIV_CRONOG = 10;

	public static final int COB_ACAO_ATIV_COMAND = 11;
	
	public static final int RA_ENCERRAMENTO_COM = 12;
	
	public static final int NEGATIVADOR_MOVIMENTO_REG = 14;
	
	public static final int COMANDO_EMPRESA_COBRANCA_CONTA = 15;
	
	public static final int COMANDO_EMPRESA_COBRANCA_CONTA_EXTENSAO = 16;
	
	public static final int UNIDADE_NEGOCIO = 17;
	
	public static final int HIDROMETRO = 18;
	
	public static final int EMPRESA = 19;
	
	public static final int QUADRA = 20;
	
	public static final int LINHA = 23;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoUnidadeProcessamento;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private short indicadorUso;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** persistent field */
	private Set unidadesIniciadas;

	/** persistent field */
	private Set processosFuncionalidades;

	/** full constructor */
	public UnidadeProcessamento(String descricaoUnidadeProcessamento,
			Date ultimaAlteracao, short indicadorUso,
			String descricaoAbreviada, Set unidadesIniciadas,
			Set processosFuncionalidades) {
		this.descricaoUnidadeProcessamento = descricaoUnidadeProcessamento;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorUso = indicadorUso;
		this.descricaoAbreviada = descricaoAbreviada;
		this.unidadesIniciadas = unidadesIniciadas;
		this.processosFuncionalidades = processosFuncionalidades;
	}

	/** default constructor */
	public UnidadeProcessamento() {
	}

	/** minimal constructor */
	public UnidadeProcessamento(short indicadorUso, Set unidadesIniciadas,
			Set processosFuncionalidades) {
		this.indicadorUso = indicadorUso;
		this.unidadesIniciadas = unidadesIniciadas;
		this.processosFuncionalidades = processosFuncionalidades;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoUnidadeProcessamento() {
		return this.descricaoUnidadeProcessamento;
	}

	public void setDescricaoUnidadeProcessamento(
			String descricaoUnidadeProcessamento) {
		this.descricaoUnidadeProcessamento = descricaoUnidadeProcessamento;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Set getUnidadesIniciadas() {
		return this.unidadesIniciadas;
	}

	public void setUnidadesIniciadas(Set unidadesIniciadas) {
		this.unidadesIniciadas = unidadesIniciadas;
	}

	public Set getProcessosFuncionalidades() {
		return this.processosFuncionalidades;
	}

	public void setProcessosFuncionalidades(Set processosFuncionalidades) {
		this.processosFuncionalidades = processosFuncionalidades;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
