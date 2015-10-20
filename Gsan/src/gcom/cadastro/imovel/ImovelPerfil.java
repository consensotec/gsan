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
package gcom.cadastro.imovel;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
@ControleAlteracao()

public class ImovelPerfil extends ObjetoTransacao{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int  OPERACAO_IMOVEL_PERFIL_INSERIR = 1687;
	public static final int  OPERACAO_IMOVEL_PERFIL_ATUALIZAR = 1702;
	public static final int OPERACAO_IMOVEL_PERFIL_REMOVER = 1698;	

	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})	
    private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER,
	                                   Operacao.REATIVAR_IMOVEIS_EXCLUIDO})	
    private String descricao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
    private Short indicadorUso;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
    private Date ultimaAlteracao;
    
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
    private Short indicadorGeracaoAutomatica;
    
    /**
	 * @since 19/09/2007
	 */
	private String descricaoComId;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorCorporativo;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorGerarDadosLeitura;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorBloquearRetificacao;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorGrandeConsumidor;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorPerfilTelemedido;
	
	
	/**
	 * @since 15/12/2009
	 */
	private Subcategoria subcategoria;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorInserirManterPerfil;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorBloqueaDadosSocial;
	
	/**
	 * @since 17/05/2010
	 */
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorGeraDebitoSegundaViaConta;	
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorAcrescimoImpontualidade;
	
	 
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Short indicadorNegativacaoDoCliente;
	
	@ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
	private Integer consumoMinimo;
	
    public Short getIndicadorBloqueaDadosSocial() {
		return indicadorBloqueaDadosSocial;
	}

	public void setIndicadorBloqueaDadosSocial(Short indicadorBloqueaDadosSocial) {
		this.indicadorBloqueaDadosSocial = indicadorBloqueaDadosSocial;
	}

	public Short getIndicadorInserirManterPerfil() {
		return indicadorInserirManterPerfil;
	}

	public void setIndicadorInserirManterPerfil(Short indicadorInserirManterPerfil) {
		this.indicadorInserirManterPerfil = indicadorInserirManterPerfil;
	}

	/**
     * Description of the Field
     */
    public final static Integer NORMAL = new Integer(5);

    public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	/**
     * Description of the Field
     */
    public final static Integer GRANDE = new Integer(1);

    /**
     * Description of the Field
     */
    public final static Integer TARIFA_SOCIAL = new Integer(4);
    
    /**
     * Description of the Field
     */
    public final static Integer GRANDE_NO_MES = new Integer(2);
    
    public final static Integer CORPORATIVO = new Integer(3);
    
    public final static Integer GRANDE_TELEMEDIDO = new Integer(7);
    
    public final static Integer VIVA_AGUA = new Integer(6);
    
    public final static Integer CORPORATIVO_TELEMED = new Integer(8);
    
    public final static Integer CADASTRO_PROVISORIO = new Integer(9);
    
    public final static Integer GRANDE_CLIENTE = new Integer(12);
    
    public final static Integer CLIENTE_CORPORATIVO = new Integer(13);
    
    /**
     * Description of the Field
     */
    public final static Short SIM = new Short((short)1);
    
    /**
     * Description of the Field
     */
    public final static Short NAO = new Short((short)2);    
    
    @ControleAlteracao(funcionalidade={OPERACAO_IMOVEL_PERFIL_INSERIR, OPERACAO_IMOVEL_PERFIL_ATUALIZAR, OPERACAO_IMOVEL_PERFIL_REMOVER})
    private PermissaoEspecial permissaoEspecial;
    
    /**
     * full constructor
     * 
     * @param descricao
     *            Descri��o do par�metro
     * @param indicadorUso
     *            Descri��o do par�metro
     * @param ultimaAlteracao
     *            Descri��o do par�metro
     */
    public ImovelPerfil(String descricao, Short indicadorUso,
            Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * constructor
     */
    public ImovelPerfil(String descricao, Short indicadorUso,
            Date ultimaAlteracao, Short indicadorGeracaoAutomatica) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
    }

    /**
     * default constructor
     */
    public ImovelPerfil() {
    }
    
    public ImovelPerfil(Integer id){
    	this.id = id;
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de descricao
     * 
     * @return O valor de descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Seta o valor de descricao
     * 
     * @param descricao
     *            O novo valor de descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     */
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    /**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @return Descri��o do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	/**
	 * @return Returns the indicadorGeracaoAutomatica.
	 */
	public Short getIndicadorGeracaoAutomatica() {
		return indicadorGeracaoAutomatica;
	}

	/**
	 * @param indicadorGeracaoAutomatica The indicadorGeracaoAutomatica to set.
	 */
	public void setIndicadorGeracaoAutomatica(Short indicadorGeracaoAutomatica) {
		this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
	}
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroImovelPerfil();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,
				this.getId()));		
		return filtro;
	}
	
	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 19/09/2007
	 *
	 * @return
	 */
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}

	/**
	 * @return Retorna o campo indicadorGerarDadosLeitura.
	 */
	public Short getIndicadorGerarDadosLeitura() {
		return indicadorGerarDadosLeitura;
	}

	/**
	 * @param indicadorGerarDadosLeitura O indicadorGerarDadosLeitura a ser setado.
	 */
	public void setIndicadorGerarDadosLeitura(Short indicadorGerarDadosLeitura) {
		this.indicadorGerarDadosLeitura = indicadorGerarDadosLeitura;
	}

	/**
	 * @return Retorna o campo indicadorBloquearRetificacao.
	 */
	public Short getIndicadorBloquearRetificacao() {
		return indicadorBloquearRetificacao;
	}

	/**
	 * @param indicadorBloquearRetificacao O indicadorBloquearRetificacao a ser setado.
	 */
	public void setIndicadorBloquearRetificacao(Short indicadorBloquearRetificacao) {
		this.indicadorBloquearRetificacao = indicadorBloquearRetificacao;
	}

	public Short getIndicadorGrandeConsumidor() {
		return indicadorGrandeConsumidor;
	}

	public void setIndicadorGrandeConsumidor(Short indicadorGrandeConsumidor) {
		this.indicadorGrandeConsumidor = indicadorGrandeConsumidor;
	}

	public Short getIndicadorGeraDebitoSegundaViaConta() {
		return indicadorGeraDebitoSegundaViaConta;
	}

	public void setIndicadorGeraDebitoSegundaViaConta(
			Short indicadorGeraDebitoSegundaViaConta) {
		this.indicadorGeraDebitoSegundaViaConta = indicadorGeraDebitoSegundaViaConta;
	}
	
	public Short getIndicadorCorporativo() {
		return indicadorCorporativo;
	}

	public void setIndicadorCorporativo(Short indicadorCorporativo) {
		this.indicadorCorporativo = indicadorCorporativo;
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = { "descricao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao"};
		return labels;		
	}

	public Short getIndicadorAcrescimoImpontualidade() {
		return indicadorAcrescimoImpontualidade;
	}

	public void setIndicadorAcrescimoImpontualidade(
			Short indicadorAcrescimoImpontualidade) {
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
	}

	public Short getIndicadorNegativacaoDoCliente() {
		return indicadorNegativacaoDoCliente;
	}

	public void setIndicadorNegativacaoDoCliente(Short indicadorNegativacaoDoCliente) {
		this.indicadorNegativacaoDoCliente = indicadorNegativacaoDoCliente;
	}

	public Short getIndicadorPerfilTelemedido() {
		return indicadorPerfilTelemedido;
	}

	public void setIndicadorPerfilTelemedido(Short indicadorPerfilTelemedido) {
		this.indicadorPerfilTelemedido = indicadorPerfilTelemedido;
	}

	public Integer getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public PermissaoEspecial getPermissaoEspecial() {
		return permissaoEspecial;
	}

	public void setPermissaoEspecial(PermissaoEspecial permissaoEspecial) {
		this.permissaoEspecial = permissaoEspecial;
	}
}
