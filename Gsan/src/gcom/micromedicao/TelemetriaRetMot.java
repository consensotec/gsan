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
import java.util.Date;

public class TelemetriaRetMot implements Serializable {
	private static final long serialVersionUID = 1L;

	// Constantes telemetria ret mot >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public final static Integer ID_QUANTIDADE_INVALIDA = 1;
    public final static Integer ID_DATA_ENVIO_INVALIDA = 2;
    public final static Integer ID_CODIGO_EMPRESA_INVALIDO = 3;
    public final static Integer ID_INSCRICAO_INVALIDA = 4;
    public final static Integer ID_LEITURA_INVALIDA = 5;
    public final static Integer ID_HIDROMETRO_INVALIDO = 6;
    public final static Integer ID_MEDICAO_TIPO_INVALIDO = 7;
    public final static Integer ID_DATA_LEITURA_INVALIDA = 8;
    public final static Integer ID_MATRICULA_INVALIDA = 9;
    public final static Integer ID_MATRICULA_INEXISTENTE = 10;
    public final static Integer ID_DATA_LEITURA_INTERVALO_INVALIDO = 11;
    public final static Integer ID_FATURAMENTO_GRUPO_INVALIDO = 12;
    public final static Integer ID_QUANTIDADE_DIFERENTE = 13;
    public final static Integer ID_LEITURA_DADOS_INCOMPLETOS = 14;
    public final static Integer ID_IMOVEL_SEM_HIDROMETRO_AGUA = 15;
    public final static Integer ID_IMOVEL_SEM_HIDROMETRO_POCO = 16;
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private Integer id;
    private String descricaoRetorno;
    private Short indicadorUso;
    private Short indicadorMovAceito;
    private Date ultimaAlteracao;

    public TelemetriaRetMot() {}

    public TelemetriaRetMot(Integer id) {
		super();
		this.id = id;
	}

	public TelemetriaRetMot(Integer id, String descricaoRetorno, Short indicadorUso,
			Short indicadorMovAceito, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.descricaoRetorno = descricaoRetorno;
		this.indicadorUso = indicadorUso;
		this.indicadorMovAceito = indicadorMovAceito;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDescricaoRetorno() {
		return descricaoRetorno;
	}

	public void setDescricaoRetorno(String descricaoRetorno) {
		this.descricaoRetorno = descricaoRetorno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorMovAceito() {
		return indicadorMovAceito;
	}

	public void setIndicadorMovAceito(Short indicadorMovAceito) {
		this.indicadorMovAceito = indicadorMovAceito;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
