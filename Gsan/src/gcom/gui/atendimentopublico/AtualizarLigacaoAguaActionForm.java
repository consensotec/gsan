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
package gcom.gui.atendimentopublico;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AtualizarLigacaoAguaActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	//Ordem de Servi�o
	private String idOrdemServico;
	private String nomeOrdemServico;
	private String servicoTipo;

	//Im�vel
    private String matriculaImovel;
    private String inscricaoImovel;
    private String clienteUsuario;
    private String cpfCnpjCliente;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;
    
    //Dados da Liga��o
    private String dataLigacao;
    private String diametroLigacao;
    private String materialLigacao;
    private String perfilLigacao;
    private String ramalLocalInstalacao;
    
    private String motivoCorte;
    private String tipoCorte;    
    private String numSeloCorte;
    private String leituraCorte;

    private String motivoSupressao;
    private String tipoSupressao;
    private String numSeloSupressao;
    private String leituraSupressao;
    
    private String aceitaLacre;
    private String numeroLacre;
    
    //Dados Valida��o
    private String habilitaCorte		= "true";
    private String habilitaSupressao 	= "true";
    
    private String veioEncerrarOS;
    private Date dataConcorrencia;
    
    /*private String ligacaoPosicao;
    private String ligacaoAguaAbastecimentoAlternativo;
    private String ligacaoAguaSituacaoAbastecimento;*/
    
    /*
	 * Colocado por R�mulo Au?elio em 02/08/2008
	 * [Permissao Especial efetuar ligacao de agua sem ra]
	 */
    
    private String idImovel;
    
    private String idLigacaoOrigem;
    
    private String permissaoAlterarOSsemRA;
    
    
    
    public Date getDataConcorrencia() {
		return dataConcorrencia;
	}

	public void setDataConcorrencia(Date dataConcorrencia) {
		this.dataConcorrencia = dataConcorrencia;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    
    	idOrdemServico = null;
    	nomeOrdemServico = null;
    	servicoTipo = null;

    	//Im�vel
        matriculaImovel = null;
        inscricaoImovel = null;
        clienteUsuario = null;
        cpfCnpjCliente = null;
        situacaoLigacaoAgua = null;
        situacaoLigacaoEsgoto = null;
        
        //Dados da Liga��o
        dataLigacao = null;
        diametroLigacao = null;
        materialLigacao = null;
        perfilLigacao = null;
        
        motivoCorte = null;
        tipoCorte = null;
        numSeloCorte = null;
        leituraCorte = null;

        motivoSupressao = null;
        tipoSupressao = null;
        numSeloSupressao = null;
        leituraSupressao = null;
    }
    
	
	
	public String getDataLigacao() {
		return dataLigacao;
	}

	public void setDataLigacao(String dataLigacao) {
		this.dataLigacao = dataLigacao;
	}

	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}
	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}
	/**
	 * @return Retorna o campo clienteUsuario.
	 */
	public String getClienteUsuario() {
		return clienteUsuario;
	}
	/**
	 * @param clienteUsuario O clienteUsuario a ser setado.
	 */
	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}
	/**
	 * @return Retorna o campo cpfCnpjCliente.
	 */
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	/**
	 * @param cpfCnpjCliente O cpfCnpjCliente a ser setado.
	 */
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	
	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	/**
	 * @param matriculaImovel O matriculaImovel a ser setado.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	/**
	 * @param situacaoLigacaoAgua O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	/**
	 * @param situacaoLigacaoEsgoto O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
  
	public String getDiametroLigacao() {
		return diametroLigacao;
	}

	public void setDiametroLigacao(String diametroLigacao) {
		this.diametroLigacao = diametroLigacao;
	}

	public String getMaterialLigacao() {
		return materialLigacao;
	}
	public void setMaterialLigacao(String materialLigacao) {
		this.materialLigacao = materialLigacao;
	}

	public String getPerfilLigacao() {
		return perfilLigacao;
	}
	public void setPerfilLigacao(String perfilLigacao) {
		this.perfilLigacao = perfilLigacao;
	}
    
	public String getNumSeloCorte() {
		return numSeloCorte;
	}
	public void setNumSeloCorte(String numSeloCorte) {
		this.numSeloCorte = numSeloCorte;
	}
	public String getNumSeloSupressao() {
		return numSeloSupressao;
	}
	public void setNumSeloSupressao(String numSeloSupressao) {
		this.numSeloSupressao = numSeloSupressao;
	}
	public String getMotivoCorte() {
		return motivoCorte;
	}
	public void setMotivoCorte(String motivoCorte) {
		this.motivoCorte = motivoCorte;
	}
	public String getMotivoSupressao() {
		return motivoSupressao;
	}
	public void setMotivoSupressao(String motivoSupressao) {
		this.motivoSupressao = motivoSupressao;
	}
	public String getTipoCorte() {
		return tipoCorte;
	}
	public void setTipoCorte(String tipoCorte) {
		this.tipoCorte = tipoCorte;
	}
	public String getTipoSupressao() {
		return tipoSupressao;
	}
	public void setTipoSupressao(String tipoSupressao) {
		this.tipoSupressao = tipoSupressao;
	}

	public String getHabilitaCorte() {
		return habilitaCorte;
	}
	public void setHabilitaCorte(String habilitaCorte) {
		this.habilitaCorte = habilitaCorte;
	}
	public String getHabilitaSupressao() {
		return habilitaSupressao;
	}
	public void setHabilitaSupressao(String habilitaSupressao) {
		this.habilitaSupressao = habilitaSupressao;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}
	public String getServicoTipo() {
		return servicoTipo;
	}
	public void setServicoTipo(String servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	public String getLeituraCorte() {
		return leituraCorte;
	}
	public void setLeituraCorte(String leituraCorte) {
		this.leituraCorte = leituraCorte;
	}
	public String getLeituraSupressao() {
		return leituraSupressao;
	}
	public void setLeituraSupressao(String leituraSupressao) {
		this.leituraSupressao = leituraSupressao;
	}

	public String getRamalLocalInstalacao() {
		return ramalLocalInstalacao;
	}

	public void setRamalLocalInstalacao(String ramalLocalInstalacao) {
		this.ramalLocalInstalacao = ramalLocalInstalacao;
	}

	public String getAceitaLacre() {
		return aceitaLacre;
	}

	public void setAceitaLacre(String aceitaLacre) {
		this.aceitaLacre = aceitaLacre;
	}

	public String getNumeroLacre() {
		return numeroLacre;
	}

	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdLigacaoOrigem() {
		return idLigacaoOrigem;
	}

	public void setIdLigacaoOrigem(String idLigacaoOrigem) {
		this.idLigacaoOrigem = idLigacaoOrigem;
	}

	public String getPermissaoAlterarOSsemRA() {
		return permissaoAlterarOSsemRA;
	}

	public void setPermissaoAlterarOSsemRA(String permissaoAlterarOSsemRA) {
		this.permissaoAlterarOSsemRA = permissaoAlterarOSsemRA;
	}

}