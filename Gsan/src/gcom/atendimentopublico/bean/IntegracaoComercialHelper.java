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
package gcom.atendimentopublico.bean;

import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class IntegracaoComercialHelper implements Serializable {
	private static final long serialVersionUID = 1L;

    private gcom.cadastro.imovel.Imovel imovel;
    
    private gcom.atendimentopublico.ligacaoagua.LigacaoAgua ligacaoAgua;
    
    private gcom.atendimentopublico.ordemservico.OrdemServico ordemServico;
    
    private LigacaoEsgoto ligacaoEsgoto;
    
    private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;
    
    private HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico;
    
    private DadosEfetuacaoCorteLigacaoAguaHelper dadosEfetuacaoCorteLigacaoAguaHelper;
    
    private boolean veioEncerrarOS;
    
    private String qtdParcelas;
    
    private Integer localArmazenagemHidrometro;
    
    private String matriculaImovel;
    
    private String situacaoHidrometroSubstituido;   
    
    private Usuario usuarioLogado;
    
	public IntegracaoComercialHelper(){}

	public gcom.cadastro.imovel.Imovel getImovel() {
		return imovel;
	}

	public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
		this.imovel = imovel;
	}

	public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IntegracaoComercialHelper)) {
            return false;
        }
        IntegracaoComercialHelper castOther = (IntegracaoComercialHelper) other;

        return this.getImovel().getId().equals(castOther.getImovel().getId());
    }

	public gcom.atendimentopublico.ligacaoagua.LigacaoAgua getLigacaoAgua() {
		return ligacaoAgua;
	}

	public void setLigacaoAgua(
			gcom.atendimentopublico.ligacaoagua.LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public gcom.atendimentopublico.ordemservico.OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(
			gcom.atendimentopublico.ordemservico.OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public LigacaoEsgoto getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}

	public void setLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	public boolean isVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(boolean veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	public HidrometroInstalacaoHistorico getHidrometroSubstituicaoHistorico() {
		return hidrometroSubstituicaoHistorico;
	}

	public void setHidrometroSubstituicaoHistorico(
			HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico) {
		this.hidrometroSubstituicaoHistorico = hidrometroSubstituicaoHistorico;
	}

	public Integer getLocalArmazenagemHidrometro() {
		return localArmazenagemHidrometro;
	}

	public void setLocalArmazenagemHidrometro(Integer localArmazenagemHidrometro) {
		this.localArmazenagemHidrometro = localArmazenagemHidrometro;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getSituacaoHidrometroSubstituido() {
		return situacaoHidrometroSubstituido;
	}

	public void setSituacaoHidrometroSubstituido(
			String situacaoHidrometroSubstituido) {
		this.situacaoHidrometroSubstituido = situacaoHidrometroSubstituido;
	}

	public DadosEfetuacaoCorteLigacaoAguaHelper getDadosEfetuacaoCorteLigacaoAguaHelper() {
		return dadosEfetuacaoCorteLigacaoAguaHelper;
	}

	public void setDadosEfetuacaoCorteLigacaoAguaHelper(
			DadosEfetuacaoCorteLigacaoAguaHelper dadosEfetuacaoCorteLigacaoAguaHelper) {
		this.dadosEfetuacaoCorteLigacaoAguaHelper = dadosEfetuacaoCorteLigacaoAguaHelper;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
