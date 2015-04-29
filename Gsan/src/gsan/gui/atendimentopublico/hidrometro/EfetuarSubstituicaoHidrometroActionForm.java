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
package gsan.gui.atendimentopublico.hidrometro;

import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.cadastro.imovel.Imovel;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.HidrometroProtecao;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.util.Util;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Form utilizado no Efetuar Substitui��o de Hidr�metro
 * 
 * @author Ana Maria
 * @date 19/07/2006
 */
public class EfetuarSubstituicaoHidrometroActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idLocalArmazenagem;
	private String idOrdemServico;
	private String nomeOrdemServico;
	private String localInstalacao;
	private String numeroHidrometro;
	private String protecao;
	private String situacaoCavalete;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String clienteUsuario;
	private String cpfCnpjCliente;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String tipoMedicao;
	private String dataInstalacao;
	private String leituraInstalacao;
	private String numeroSelo;
	private String numeroHidrometroAtual;
	private String tipoMedicaoAtual;
	private String dataRetirada;
	private String numeroLeitura;
	private String situacaoHidrometro;
	private String localArmazenagemHidrometro;
	private String veioEncerrarOS;
	
//	Dados da Gera��o do D�bito
    private String idTipoDebito;
    private String descricaoTipoDebito;
    
    private String valorDebito;
    private String motivoNaoCobranca;
    private String percentualCobranca;
    private String quantidadeParcelas;
    private String valorParcelas;
    private String qtdeMaxParcelas;    
    
    /*
	 * Colocado por Raphael Rossiter em 20/04/2007
	 * [FS0013 - Altera��o de Valor]
	 */
    private String alteracaoValor;
    
    /*
     * Colocado por Vivianne Sousa em 05/12/2007
     */
    private String indicadorTrocaProtecao;
    private String indicadorTrocaRegistro;
    
    /*
     * Colocado por Hugo Fernando em 04/03/2010
     * */
    private String hidrometroExtraviado;
	
    
    private String indicadorSemMenu;
	
	public String getIndicadorSemMenu() {
		return indicadorSemMenu;
	}

	public void setIndicadorSemMenu(String indicadorSemMenu) {
		this.indicadorSemMenu = indicadorSemMenu;
	}

	/**
	 * @return Returns the hidrometroExtraviado.
	 */
	public String getHidrometroExtraviado() {
		return hidrometroExtraviado;
	}

	/**
	 * @param hidrometroExtraviado The hidrometroExtraviado to set.
	 */
	public void setHidrometroExtraviado(String hidrometroExtraviado) {
		this.hidrometroExtraviado = hidrometroExtraviado;
	}

	public String getIndicadorTrocaProtecao() {
        return indicadorTrocaProtecao;
    }

    public void setIndicadorTrocaProtecao(String indicadorTrocaProtecao) {
        this.indicadorTrocaProtecao = indicadorTrocaProtecao;
    }

    public String getIndicadorTrocaRegistro() {
        return indicadorTrocaRegistro;
    }

    public void setIndicadorTrocaRegistro(String indicadorTrocaRegistro) {
        this.indicadorTrocaRegistro = indicadorTrocaRegistro;
    }

    public String getQtdeMaxParcelas() {
		return qtdeMaxParcelas;
	}

	public void setQtdeMaxParcelas(String qtdeMaxParcelas) {
		this.qtdeMaxParcelas = qtdeMaxParcelas;
	}

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getMotivoNaoCobranca() {
		return motivoNaoCobranca;
	}

	public void setMotivoNaoCobranca(String motivoNaoCobranca) {
		this.motivoNaoCobranca = motivoNaoCobranca;
	}

	public String getPercentualCobranca() {
		return percentualCobranca;
	}

	public void setPercentualCobranca(String percentualCobranca) {
		this.percentualCobranca = percentualCobranca;
	}

	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getValorParcelas() {
		return valorParcelas;
	}

	public void setValorParcelas(String valorParcelas) {
		this.valorParcelas = valorParcelas;
	}

	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	/**
	 * @return Retorna o campo localInstalacao.
	 */
	public String getLocalInstalacao() {
		return localInstalacao;
	}

	/**
	 * @param localInstalacao a ser setado.
	 */
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}

	/**
	 * @return Retorna o campo protecao.
	 */
	public String getProtecao() {
		return protecao;
	}

	/**
	 * @param protecao a ser setado.
	 */
	public void setProtecao(String protecao) {
		this.protecao = protecao;
	}

	/**
	 * @return Retorna o campo tipoCavalete.
	 */
	public String getSituacaoCavalete() {
		return situacaoCavalete;
	}

	/**
	 * @param tipoCavalete a ser setado.
	 */
	public void setSituacaoCavalete(String situacaoCavalete) {
		this.situacaoCavalete = situacaoCavalete;
	}

	/**
	 * @return Retorna o campo clienteUsuario.
	 */
	public String getClienteUsuario() {
		return clienteUsuario;
	}

	/**
	 * @param clienteUsuario a ser setado.
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
	 * @param cpfCnpjCliente a ser setado.
	 */
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	/**
	 * @return Retorna o campo dataInstalacao.
	 */
	public String getDataInstalacao() {
		return dataInstalacao;
	}

	/**
	 * @param dataInstalacao a ser setado.
	 */
	public void setDataInstalacao(String dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	/**
	 * @return Retorna o campo idOrdemServico.
	 */
	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	/**
	 * @param idOrdemServico a ser setado.
	 */
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param matriculaImovel a ser setado.
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
	 * @param situacaoLigacaoAgua a ser setado.
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
	 * @param situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo nomeOrdemServico.
	 */
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}

	/**
	 * @param nomeOrdemServico a ser setado.
	 */
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}

	/**
	 * @return Retorna o campo tipoMedicao.
	 */
	public String getTipoMedicao() {
		return tipoMedicao;
	}

	/**
	 * @param tipoMedicao a ser setado.
	 */
	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	/**
	 * @return Retorna o campo hidrometro.
	 */
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	/**
	 * @param hidrometro a ser setado.
	 */
	public void setNumeroHidrometro(String codigoHidrometro) {
		this.numeroHidrometro = codigoHidrometro;
	}

	/**
	 * @return Retorna o campo leituraInstalacao.
	 */
	public String getLeituraInstalacao() {
		return leituraInstalacao;
	}

	/**
	 * @param leituraInstalacao a ser setado.
	 */
	public void setLeituraInstalacao(String leituraInstalacao) {
		this.leituraInstalacao = leituraInstalacao;
	}
	
	/**
	 * @return Retorna o campo numeroSelo.
	 */
	public String getNumeroSelo() {
		return numeroSelo;
	}

	/**
	 * @param numeroSelo a ser setado.
	 */
	public void setNumeroSelo(String numeroSelo) {
		this.numeroSelo = numeroSelo;
	}

	/**
	 * @return Retorna o campo numeroHidrometroAtual.
	 */
	
	public String getNumeroHidrometroAtual() {
		return numeroHidrometroAtual;
	}

	/**
	 * @param numeroHidrometroAtual a ser setado.
	 */
	public void setNumeroHidrometroAtual(String numeroHidrometroAtual) {
		this.numeroHidrometroAtual = numeroHidrometroAtual;
	}
	
	/**
	 * @return Retorna o campo tipoMedicaoAtual
	 */
		public String getTipoMedicaoAtual() {
		return tipoMedicaoAtual;
	}

	/**
	 * @param tipoMedicaoAtual a ser setado.
	 */
	public void setTipoMedicaoAtual(String tipoMedicaoAtual) {
		this.tipoMedicaoAtual = tipoMedicaoAtual;
	}
	
	/**
	 * @return Retorna o campo dataRetirada
	 */
	
	public String getDataRetirada() {
		return dataRetirada;
	}

	/**
	 * @param dataRetirada a ser setado.
	 */
	public void setDataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	
	/**
	 * @return Retorna o campo numeroLeitura.
	 */
		public String getNumeroLeitura() {
		return numeroLeitura;
	}

	/**
	 * @param numeroLeitura a ser setado.
	 */
	public void setNumeroLeitura(String numeroLeitura) {
		this.numeroLeitura = numeroLeitura;
	}

	/**
	 * @return Retorna o campo situacaoLeitura.
	 */
	
	public String getSituacaoHidrometro() {
		return situacaoHidrometro;
	}

	/**
	 * @param situacaoLeitura a ser setado.
	 */
	public void setSituacaoHidrometro(String situacaoHidrometro) {
		this.situacaoHidrometro = situacaoHidrometro;
	}
	
	public HidrometroInstalacaoHistorico setFormValues(HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		
		/*
		 * Campos obrigat�rios
		 */
		
		//data instala��o
		hidrometroInstalacaoHistorico.setDataInstalacao(Util.converteStringParaDate(getDataInstalacao()));
		
		if (tipoMedicao.equals(""+MedicaoTipo.POCO) || tipoMedicao.equals(""+MedicaoTipo.LIGACAO_ESGOTO)) {

		  Imovel imovel = new Imovel();
		  imovel.setId(new Integer(matriculaImovel));
						
		  hidrometroInstalacaoHistorico.setImovel(imovel);
		  hidrometroInstalacaoHistorico.setLigacaoAgua(null);
					
		} else if (tipoMedicao.equals(""+MedicaoTipo.LIGACAO_AGUA)) {

		  LigacaoAgua ligacaoAgua = new LigacaoAgua();
		  ligacaoAgua.setId(new Integer(matriculaImovel));
						
		  hidrometroInstalacaoHistorico.setLigacaoAgua(ligacaoAgua);
		  hidrometroInstalacaoHistorico.setImovel(null);
	    }
		//medi��o tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(Integer.parseInt(getTipoMedicao()));
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);
		
		// Caso seja liga��o de esgoto tratar como po�o
		if (getTipoMedicao().equals("3")) {
		medicaoTipo.setId(MedicaoTipo.POCO);
		} else {
		medicaoTipo.setId(Integer.parseInt(getTipoMedicao()));
		}
		
		
		//hidr�metro local instala��o
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer.parseInt(getLocalInstalacao()));		
		hidrometroInstalacaoHistorico.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
		
		//prote��o
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer.parseInt(getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);
		
		//leitura instala��o
		if(getLeituraInstalacao()!=null && !getLeituraInstalacao().trim().equals("")){
		  hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(Integer.parseInt(getLeituraInstalacao()));
		}else{
			hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(0);	
		}
		
		//cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short.parseShort(getSituacaoCavalete()));
		
		/*
		 * Campos opcionais 
		 */
		//leitura corte
		hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
		
		//leitura supress�o
		hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(null);
		
		//numero selo
		if (getNumeroSelo() != null && !getNumeroSelo().equals("")){
			hidrometroInstalacaoHistorico.setNumeroSelo(getNumeroSelo());
		} else {
			hidrometroInstalacaoHistorico.setNumeroSelo(null);
		}
		
		//tipo de rateio
		hidrometroInstalacaoHistorico.setRateioTipo(null);
		hidrometroInstalacaoHistorico.setDataImplantacaoSistema(new Date());

		//indicador instala��o substitui��o
		hidrometroInstalacaoHistorico.setIndicadorInstalcaoSubstituicao(new Short("1"));		
		
		//data �ltima altera��o
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
        
        if(getIndicadorTrocaProtecao()!= null){
            hidrometroInstalacaoHistorico.setIndicadorTrocaProtecao(new Short(getIndicadorTrocaProtecao()));
        }
        if(getIndicadorTrocaRegistro()!= null){
            hidrometroInstalacaoHistorico.setIndicadorTrocaRegistro(new Short(getIndicadorTrocaRegistro()));
        }
		
		return hidrometroInstalacaoHistorico;
	}

	public String getLocalArmazenagemHidrometro() {
		return localArmazenagemHidrometro;
	}

	public void setLocalArmazenagemHidrometro(String localArmazenagemHidrometro) {
		this.localArmazenagemHidrometro = localArmazenagemHidrometro;
	}

	public String getAlteracaoValor() {
		return alteracaoValor;
	}

	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}

	/**
	 * @return Retorna o campo idLocalArmazenagem.
	 */
	public String getIdLocalArmazenagem() {
		return idLocalArmazenagem;
	}

	/**
	 * @param idLocalArmazenagem O idLocalArmazenagem a ser setado.
	 */
	public void setIdLocalArmazenagem(String idLocalArmazenagem) {
		this.idLocalArmazenagem = idLocalArmazenagem;
	}
	
	
}
