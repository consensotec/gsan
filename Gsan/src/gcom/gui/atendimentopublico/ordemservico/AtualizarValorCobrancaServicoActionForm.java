/**
 * 
 */
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
package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descri��o da classe
 * 
 * @author Administrador
 * @date 31/10/2006
 */
public class AtualizarValorCobrancaServicoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String tipoServico;
	private String nomeTipoServico;
	private String perfilImovel;
	private String indicadorMedido;
	private String capacidadeHidrometro;
	private String indicadorGeracaoDebito;
	private String valorServico;
	private String idServicoCobrancaValor;
	
	private String idCategoria;
	private String idSubCategoria;
	
	private String indicativoTipoSevicoEconomias;
	
	private String dataVigenciaInicial;
	private String dataVigenciaFinal;
	
	private String quantidadeEconomiasInicial;
	private String quantidadeEconomiasFinal;

	/**
	 * @return Retorna o campo idServicoCobrancaValor.
	 */
	public String getIdServicoCobrancaValor() {
		return idServicoCobrancaValor;
	}

	/**
	 * @param idServicoCobrancaValor O idServicoCobrancaValor a ser setado.
	 */
	public void setIdServicoCobrancaValor(String idServicoCobrancaValor) {
		this.idServicoCobrancaValor = idServicoCobrancaValor;
	}

	public String getValorServico() {
		return valorServico;
	}

	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}

	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	public String getIndicadorMedido() {
		return indicadorMedido;
	}

	public void setIndicadorMedido(String indicadorMedido) {
		this.indicadorMedido = indicadorMedido;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getNomeTipoServico() {
		return nomeTipoServico;
	}

	public void setNomeTipoServico(String nomeTipoServico) {
		this.nomeTipoServico = nomeTipoServico;
	}

	public String getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(String dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public String getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}

	public void setDataVigenciaInicial(String dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(String idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public String getIndicativoTipoSevicoEconomias() {
		return indicativoTipoSevicoEconomias;
	}

	public void setIndicativoTipoSevicoEconomias(
			String indicativoTipoSevicoEconomias) {
		this.indicativoTipoSevicoEconomias = indicativoTipoSevicoEconomias;
	}

	public String getQuantidadeEconomiasFinal() {
		return quantidadeEconomiasFinal;
	}

	public void setQuantidadeEconomiasFinal(String quantidadeEconomiasFinal) {
		this.quantidadeEconomiasFinal = quantidadeEconomiasFinal;
	}

	public String getQuantidadeEconomiasInicial() {
		return quantidadeEconomiasInicial;
	}

	public void setQuantidadeEconomiasInicial(String quantidadeEconomiasInicial) {
		this.quantidadeEconomiasInicial = quantidadeEconomiasInicial;
	}

	public String getIndicadorGeracaoDebito() {
		return indicadorGeracaoDebito;
	}

	public void setIndicadorGeracaoDebito(String indicadorGeracaoDebito) {
		this.indicadorGeracaoDebito = indicadorGeracaoDebito;
	}
	
}