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
package gcom.gerencial.faturamento.bean;

import java.util.ArrayList;

/**
 * Classe respons�vel por ajudar o caso de uso [UC0275] Gerar Resumo das Ligacoes/Economias 
 *
 * @author Thiago Toscano
 * @date 20/04/2006
 */
public class ResumoFaturamentoSituacaoEspecialHelper {

	private Integer idImovel;
	private Integer idGerenciaRegional;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idEsfera;

	private Integer idEspecialFaturamento;
	private Integer idMotivoSituacaoEspecialFatauramento;
	private Integer anoMesInicioSituacaoEspecial;
	private Integer anoMesFinalSituacaoEspecial;
	private int quantidadeImovel = 1;
	private Integer anoMesReferenciaFaturamento;

	private ArrayList<Integer> idsImoveis;
	

	public boolean equals(Object obj) {

		if (obj instanceof ResumoFaturamentoSituacaoEspecialHelper) {
			ResumoFaturamentoSituacaoEspecialHelper irleh = (ResumoFaturamentoSituacaoEspecialHelper) obj;

			if (irleh.getIdGerenciaRegional() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdGerenciaRegional().equals(this.getIdGerenciaRegional())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdGerenciaRegional() != null) {
				return false;
			}

			if (irleh.getIdLocalidade() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdLocalidade().equals(this.getIdLocalidade())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdLocalidade() != null) {
				return false;
			}

			if (irleh.getIdSetorComercial() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdSetorComercial().equals(this.getIdSetorComercial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSetorComercial() != null) {
				return false;
			}

			if (irleh.getIdRota() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdRota().equals(this.getIdRota())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdRota() != null) {
				return false;
			}

			if (irleh.getIdQuadra() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdQuadra().equals(this.getIdQuadra())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdQuadra() != null) {
				return false;
			}

			if (irleh.getCodigoSetorComercial() != null) {
				// se os atributos forem diferentes
				if (!irleh.getCodigoSetorComercial().equals(this.getCodigoSetorComercial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getCodigoSetorComercial() != null) {
				return false;
			}

			if (irleh.getNumeroQuadra() != null) {
				// se os atributos forem diferentes
				if (!irleh.getNumeroQuadra().equals(this.getNumeroQuadra())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getNumeroQuadra() != null) {
				return false;
			}

			if (irleh.getIdPerfilImovel() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdPerfilImovel().equals(this.getIdPerfilImovel())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdPerfilImovel() != null) {
				return false;
			}

			if (irleh.getIdSituacaoLigacaoAgua() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdSituacaoLigacaoAgua().equals(this.getIdSituacaoLigacaoAgua())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSituacaoLigacaoAgua() != null) {
				return false;
			}

			if (irleh.getIdSituacaoLigacaoEsgoto() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdSituacaoLigacaoEsgoto().equals(this.getIdSituacaoLigacaoEsgoto())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSituacaoLigacaoEsgoto() != null) {
				return false;
			}

			if (irleh.getIdCategoria() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdCategoria().equals(this.getIdCategoria())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdCategoria() != null) {
				return false;
			}

			if (irleh.getIdEsfera() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdEsfera().equals(this.getIdEsfera())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdEsfera() != null) {
				return false;
			}

			if (irleh.getIdEspecialFaturamento() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdEspecialFaturamento().equals(this.getIdEspecialFaturamento())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdEspecialFaturamento() != null) {
				return false;
			}

			if (irleh.getIdMotivoSituacaoEspecialFatauramento() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdMotivoSituacaoEspecialFatauramento().equals(this.getIdMotivoSituacaoEspecialFatauramento())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdMotivoSituacaoEspecialFatauramento() != null) {
				return false;
			}

			if (irleh.getAnoMesInicioSituacaoEspecial() != null) {
				// se os atributos forem diferentes
				if (!irleh.getAnoMesInicioSituacaoEspecial().equals(this.getAnoMesInicioSituacaoEspecial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getAnoMesInicioSituacaoEspecial() != null) {
				return false;
			}

			if (irleh.getAnoMesFinalSituacaoEspecial() != null) {
				// se os atributos forem diferentes
				if (!irleh.getAnoMesFinalSituacaoEspecial().equals(this.getAnoMesFinalSituacaoEspecial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getAnoMesFinalSituacaoEspecial() != null) {
				return false;
			}
		} else {
			// se o objeto passado nao for do tipo ImovelResumoLigacaoEconomiaHelper 
			return false;
		}

		// todos os parametros sao iguais
		return true;
	}
	
	public int hashCode() {
		
		String retorno =  
		
		this.getIdGerenciaRegional() + "sdf" +
		this.getIdLocalidade() + "sdf" +
		this.getIdSetorComercial() + "sdf" +
		this.getIdRota() + "sdf" +
		this.getIdQuadra() + "sdf" +
		this.getCodigoSetorComercial() + "sdf" +
		this.getNumeroQuadra() + "sdf" +
		this.getIdPerfilImovel() + "sdf" +
		this.getIdSituacaoLigacaoAgua() + "sdf" +
		this.getIdSituacaoLigacaoEsgoto() + "sdf" +
		this.getIdCategoria() + "sdf" +
		this.getIdEsfera() + "sdf" +
		this.getIdEspecialFaturamento() + "sdf" +
		this.getIdMotivoSituacaoEspecialFatauramento() + "sdf" +
		this.getAnoMesFinalSituacaoEspecial() + "sdf" +
		this.getAnoMesInicioSituacaoEspecial() + "sdf";

		return retorno.hashCode();
	}
	/**
	 * @return Retorna o campo anoMesFinalSituacaoEspecial.
	 */
	public Integer getAnoMesFinalSituacaoEspecial() {
		return anoMesFinalSituacaoEspecial;
	}

	/**
	 * @param anoMesFinalSituacaoEspecial O anoMesFinalSituacaoEspecial a ser setado.
	 */
	public void setAnoMesFinalSituacaoEspecial(Integer anoMesFinalSituacaoEspecial) {
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
	}

	/**
	 * @return Retorna o campo anoMesInicioSituacaoEspecial.
	 */
	public Integer getAnoMesInicioSituacaoEspecial() {
		return anoMesInicioSituacaoEspecial;
	}

	/**
	 * @param anoMesInicioSituacaoEspecial O anoMesInicioSituacaoEspecial a ser setado.
	 */
	public void setAnoMesInicioSituacaoEspecial(Integer anoMesInicioSituacaoEspecial) {
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo idCategoria.
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return Retorna o campo idEsfera.
	 */
	public Integer getIdEsfera() {
		return idEsfera;
	}

	/**
	 * @param idEsfera O idEsfera a ser setado.
	 */
	public void setIdEsfera(Integer idEsfera) {
		this.idEsfera = idEsfera;
	}

	/**
	 * @return Retorna o campo idEspecialFaturamento.
	 */
	public Integer getIdEspecialFaturamento() {
		return idEspecialFaturamento;
	}

	/**
	 * @param idEspecialFaturamento O idEspecialFaturamento a ser setado.
	 */
	public void setIdEspecialFaturamento(Integer idEspecialFaturamento) {
		this.idEspecialFaturamento = idEspecialFaturamento;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idMotivoSituacaoEspecialFatauramento.
	 */
	public Integer getIdMotivoSituacaoEspecialFatauramento() {
		return idMotivoSituacaoEspecialFatauramento;
	}

	/**
	 * @param idMotivoSituacaoEspecialFatauramento O idMotivoSituacaoEspecialFatauramento a ser setado.
	 */
	public void setIdMotivoSituacaoEspecialFatauramento(
			Integer idMotivoSituacaoEspecialFatauramento) {
		this.idMotivoSituacaoEspecialFatauramento = idMotivoSituacaoEspecialFatauramento;
	}

	/**
	 * @return Retorna o campo idPerfilImovel.
	 */
	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}

	/**
	 * @param idPerfilImovel O idPerfilImovel a ser setado.
	 */
	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	/**
	 * @return Retorna o campo idQuadra.
	 */
	public Integer getIdQuadra() {
		return idQuadra;
	}

	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	/**
	 * @return Retorna o campo idRota.
	 */
	public Integer getIdRota() {
		return idRota;
	}

	/**
	 * @param idRota O idRota a ser setado.
	 */
	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo idSituacaoLigacaoAgua.
	 */
	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	/**
	 * @param idSituacaoLigacaoAgua O idSituacaoLigacaoAgua a ser setado.
	 */
	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo idSituacaoLigacaoEsgoto.
	 */
	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	/**
	 * @param idSituacaoLigacaoEsgoto O idSituacaoLigacaoEsgoto a ser setado.
	 */
	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo quantidadeImovel.
	 */
	public int getQuantidadeImovel() {
		return quantidadeImovel;
	}

	/**
	 * @param quantidadeImovel O quantidadeImovel a ser setado.
	 */
	public void setQuantidadeImovel(int quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}


	public ResumoFaturamentoSituacaoEspecialHelper(Integer idImovel, Integer idGerenciaRegional, 
			Integer idLocalidade, Integer idSetorComercial, Integer idRota, Integer idQuadra, 
			Integer codigoSetorComercial, Integer numeroQuadra, Integer idPerfilImovel, 
			Integer idSituacaoLigacaoAgua, Integer idSituacaoLigacaoEsgoto, Integer idEsfera, 
			Integer idEspecialFaturamento, Integer idMotivoSituacaoEspecialFatauramento, 
			Integer anoMesInicioSituacaoEspecial, Integer anoMesFinalSituacaoEspecial, Integer anoMesReferenciaFaturamento) {
		super();
		this.idImovel = idImovel;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		if (idEsfera != null && idEsfera.intValue() != 0)
			this.idEsfera = idEsfera;
		this.idEspecialFaturamento = idEspecialFaturamento;
		this.idMotivoSituacaoEspecialFatauramento = idMotivoSituacaoEspecialFatauramento;
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
		this.anoMesReferenciaFaturamento = anoMesReferenciaFaturamento;
	}

	public ArrayList<Integer> getIdsImoveis() {
		return idsImoveis;
	}

	public void setIdsImoveis(ArrayList<Integer> idsImoveis) {
		this.idsImoveis = idsImoveis;
	}

	public Integer getAnoMesReferenciaFaturamento() {
		return anoMesReferenciaFaturamento;
	}

	public void setAnoMesReferenciaFaturamento(Integer anoMesReferenciaFaturamento) {
		this.anoMesReferenciaFaturamento = anoMesReferenciaFaturamento;
	}
	
}