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
package gcom.gui.micromedicao;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir Leiturista
 * 
 * @author Thiago Ten�rio
 * @date 22/07/2007
 */
public class ConsultarArquivoTextoLeituraActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	//private String codigo;
	
	private String empresaID;
	
	private String grupoFaturamentoID;
	
	private String mesAno;
	
	private String situaTransmLeitura;
	
	private String[] idsRegistros;
	
	private String leituristaID;
	
	private String idSituacaoLeitura;
	
	private String idLocalidade;
	
	private String nomeLocalidade;
	
	private String motivoFinalizacao;
	
	private String tipoMedicao;
	
	private String finalizar;
	
	private String indicadorAgenteComercial;
	
	//Atributo FK na tabela de arquivo_texto_roteiro_empresa que referencia um servico tipo celular na
	//tabela servico_tipo_celular
	private String servicoTipoCelular;
	
	private String contaImpressa;	
	
	public String getContaImpressa() {
		return contaImpressa;
	}

	public void setContaImpressa(String contaImpressa) {
		this.contaImpressa = contaImpressa;
	}

	public String getServicoTipoCelular() {
		return servicoTipoCelular;
	}
	
	public void setServicoTipoCelular(String servicoTipoCelular) {
		this.servicoTipoCelular = servicoTipoCelular;
	}
	
	public String getIdSituacaoLeitura() {
		return idSituacaoLeitura;
	}
	
	public void setIdSituacaoLeitura(String idSituacaoLeitura) {
		this.idSituacaoLeitura = idSituacaoLeitura;
	}
	
	public String getEmpresaID() {
		return empresaID;
	}
	
	public void setEmpresaID(String empresaID) {
		this.empresaID = empresaID;
	}
	
	public String getGrupoFaturamentoID() {
		return grupoFaturamentoID;
	}
	
	public void setGrupoFaturamentoID(String grupoFaturamentoID) {
		this.grupoFaturamentoID = grupoFaturamentoID;
	}
	
	public String getMesAno() {
		return mesAno;
	}
	
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	
	public String getSituaTransmLeitura() {
		return situaTransmLeitura;
	}
	
	public void setSituaTransmLeitura(String situaTransmLeitura) {
		this.situaTransmLeitura = situaTransmLeitura;
	}
	
	/**
	 * @return Returns the idsRegistros.
	 */
	public String[] getIdsRegistros() {
		return idsRegistros;
	}
	
	/**
	 * @param idsRegistros The idsRegistros to set.
	 */
	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}
	
	/**
	 * @return Returns the leituristaID.
	 */
	public String getLeituristaID() {
		return leituristaID;
	}
	
	/**
	 * @param leituristaID The leituristaID to set.
	 */
	public void setLeituristaID(String leituristaID) {
		this.leituristaID = leituristaID;
	}
	
	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		super.reset(arg0, arg1);
		
		this.empresaID="";
		
		this.grupoFaturamentoID="";
		
		this.mesAno="";
		
		this.situaTransmLeitura="";
		
		this.idsRegistros=new String[0];
		
		this.leituristaID="";
		
		this.idSituacaoLeitura="";
		
	}

	public String getMotivoFinalizacao() {
		return motivoFinalizacao;
	}

	public void setMotivoFinalizacao(String motivoFinalizacao) {
		this.motivoFinalizacao = motivoFinalizacao;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getFinalizar() {
		return finalizar;
	}

	public void setFinalizar(String finalizar) {
		this.finalizar = finalizar;
	}

	public String getIndicadorAgenteComercial() {
		return indicadorAgenteComercial;
	}

	public void setIndicadorAgenteComercial(String indicadorAgenteComercial) {
		this.indicadorAgenteComercial = indicadorAgenteComercial;
	}
	
	
}