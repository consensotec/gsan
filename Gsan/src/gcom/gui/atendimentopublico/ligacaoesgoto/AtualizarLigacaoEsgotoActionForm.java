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
* R�mulo Aur�lio de Melo Souza Filho
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
package gcom.gui.atendimentopublico.ligacaoesgoto;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class AtualizarLigacaoEsgotoActionForm extends ActionForm{
	/**
	 * @since 16/05/2007
	 */
	private static final long serialVersionUID = 1L;
	//Ordem de Servi�o
	private String idOrdemServico;
	private String nomeOrdemServico;
	
	//Im�vel
    private String matriculaImovel;
    private String inscricaoImovel;
    private String clienteUsuario;
    private String cpfCnpjCliente;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;
    
    //Dados da Liga��o
    private String diametroLigacao;
    private String dataLigacao;
    private String materialLigacao;
    private String perfilLigacao;
    private String percentualColeta;
    private String percentualEsgoto;
    private String indicadorCaixaGordura;
    private String indicadorLigacao;
    private String veioEncerrarOS;
    // Controle
    private String redirect = "false";
    private Date dataConcorrencia;
    
    /*
	 * Colocado por R�mulo Aurelio em 02/08/2008
	 * [Permissao Especial atualizar ligacao de esgoto sem ra]
	 */
    
    private String idImovel;
    
    private String idLigacaoOrigem;
    
    private String permissaoAtualizarLigacaoEsgotosemRA;
    
    private String limiteConsumoAlternativo;
    
    private String percentualAlternativoEsgoto;
    
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
	 * @return Retorna o campo dataLigacao.
	 */
	public String getDataLigacao() {
		return dataLigacao;
	}
	/**
	 * @param dataLigacao O dataLigacao a ser setado.
	 */
	public void setDataLigacao(String dataLigacao) {
		this.dataLigacao = dataLigacao;
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
	 * @param diametroLigacao O diametroLigacao a ser setado.
	 */
	public void setDiametroLigacao(String diametroLigacao) {
		this.diametroLigacao = diametroLigacao;
	}
	/**
	 * @return Retorna o campo materialLigacao.
	 */
	public String getMaterialLigacao() {
		return materialLigacao;
	}
	/**
	 * @param materialLigacao O materialLigacao a ser setado.
	 */
	public void setMaterialLigacao(String materialLigacao) {
		this.materialLigacao = materialLigacao;
	}
	/**
	 * @return Retorna o campo perfilLigacao.
	 */
	public String getPerfilLigacao() {
		return perfilLigacao;
	}
	/**
	 * @param perfilLigacao O perfilLigacao a ser setado.
	 */
	public void setPerfilLigacao(String perfilLigacao) {
		this.perfilLigacao = perfilLigacao;
	}
	/**
	 * @return Retorna o campo percentualColeta.
	 */
	public String getPercentualColeta() {
		return percentualColeta;
	}
	/**
	 * @param percentualColeta O percentualColeta a ser setado.
	 */
	public void setPercentualColeta(String percentualColeta) {
		this.percentualColeta = percentualColeta;
	}
	/**
	 * @return Retorna o campo percentualEsgoto.
	 */
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}
	/**
	 * @param percentualEsgoto O percentualEsgoto a ser setado.
	 */
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}
	/**
	 * @return Retorna o campo diametroLigacao.
	 */
	public String getDiametroLigacao() {
		return diametroLigacao;
	}
	
	/**
	 * Reseta dados do im�vel do action form 
	 *
	 * @author Leonardo Regis
	 * @date 21/07/2006
	 *
	 * @param ligacaoEsgotoActionForm
	 */
	public void resetImovel() {
		// Im�vel
		this.setMatriculaImovel("");
		this.setInscricaoImovel("");
		this.setClienteUsuario("");
		this.setCpfCnpjCliente("");
		this.setSituacaoLigacaoAgua("");
		this.setSituacaoLigacaoEsgoto("");
	}
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
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
	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}
	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}
	public Date getDataConcorrencia() {
		return dataConcorrencia;
	}
	public void setDataConcorrencia(Date dataConcorrencia) {
		this.dataConcorrencia = dataConcorrencia;
	}
	/**
	 * @return Retorna o campo indicadorCaixaGordura.
	 */
	public String getIndicadorCaixaGordura() {
		return indicadorCaixaGordura;
	}
	/**
	 * @param indicadorCaixaGordura O indicadorCaixaGordura a ser setado.
	 */
	public void setIndicadorCaixaGordura(String indicadorCaixaGordura) {
		this.indicadorCaixaGordura = indicadorCaixaGordura;
	}
	public String getPermissaoAtualizarLigacaoEsgotosemRA() {
		return permissaoAtualizarLigacaoEsgotosemRA;
	}
	public void setPermissaoAtualizarLigacaoEsgotosemRA(
			String permissaoAtualizarLigacaoEsgotosemRA) {
		this.permissaoAtualizarLigacaoEsgotosemRA = permissaoAtualizarLigacaoEsgotosemRA;
	}
	public String getIndicadorLigacao() {
		return indicadorLigacao;
	}
	public void setIndicadorLigacao(String indicadorLigacao) {
		this.indicadorLigacao = indicadorLigacao;
	}
	public String getLimiteConsumoAlternativo() {
		return limiteConsumoAlternativo;
	}
	public void setLimiteConsumoAlternativo(String limiteConsumoAlternativo) {
		this.limiteConsumoAlternativo = limiteConsumoAlternativo;
	}
	public String getPercentualAlternativoEsgoto() {
		return percentualAlternativoEsgoto;
	}
	public void setPercentualAlternativoEsgoto(String percentualAlternativoEsgoto) {
		this.percentualAlternativoEsgoto = percentualAlternativoEsgoto;
	}
}
