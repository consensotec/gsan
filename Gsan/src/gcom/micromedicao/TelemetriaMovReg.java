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

import gcom.cadastro.imovel.Imovel;
import gcom.micromedicao.medicao.MedicaoTipo;

import java.io.Serializable;
import java.util.Date;

public class TelemetriaMovReg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Date dataLeitura;
	
	private String inscricao;
	
	private Integer leitura;
	
	private String numeroHidrometro;
	
	private Short indicadorProcessado;
	
	private TelemetriaMov telemetriaMov;
	
	private Imovel imovel;
	
	private MedicaoTipo medicaoTipo;
	
	private Date ultimaAlteracao;
	
	private byte[] descricaoErro;
	
	public TelemetriaMovReg(){}

	public TelemetriaMovReg(Integer id, Date dataLeitura, String inscricao, Integer leitura, String numeroHidrometro,
			Short indicadorProcessado, TelemetriaMov telemetriaMov, Imovel imovel, MedicaoTipo medicaoTipo,
			Date ultimaAlteracao) {
		super();
		this.id = id;
		this.dataLeitura = dataLeitura;
		this.inscricao = inscricao;
		this.leitura = leitura;
		this.numeroHidrometro = numeroHidrometro;
		this.indicadorProcessado = indicadorProcessado;
		this.telemetriaMov = telemetriaMov;
		this.imovel = imovel;
		this.medicaoTipo = medicaoTipo;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Date getDataLeitura() {
		return dataLeitura;
	}

	public void setDataLeitura(Date dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Short getIndicadorProcessado() {
		return indicadorProcessado;
	}

	public void setIndicadorProcessado(Short indicadorProcessado) {
		this.indicadorProcessado = indicadorProcessado;
	}

	public Integer getLeitura() {
		return leitura;
	}

	public void setLeitura(Integer leitura) {
		this.leitura = leitura;
	}

	public MedicaoTipo getMedicaoTipo() {
		return medicaoTipo;
	}

	public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public TelemetriaMov getTelemetriaMov() {
		return telemetriaMov;
	}

	public void setTelemetriaMov(TelemetriaMov telemetriaMov) {
		this.telemetriaMov = telemetriaMov;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public byte[] getDescricaoErro() {
		return descricaoErro;
	}

	public void setDescricaoErro(byte[] descricaoErro) {
		this.descricaoErro = descricaoErro;
	}
}
