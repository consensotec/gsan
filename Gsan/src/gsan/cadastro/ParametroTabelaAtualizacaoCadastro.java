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
package gsan.cadastro;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gsan.cadastro.localidade.Localidade;
import gsan.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

public class ParametroTabelaAtualizacaoCadastro implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Localidade localidadeInicial;
	
	private Localidade localidadeFinal;
	
	private Integer codigoSetorComercialInicial;
	
	private Integer codigoSetorComercialFinal;
	
	private Integer numeroQuadraInicial;
	
	private Integer numeroQuadraFinal;
	
	private Date ultimaAlteracao;
	
	private Usuario usuario;
	
	private Empresa empresa;

	private Integer codigoRotaInicial;
	
	private Integer codigoRotaFinal;
	
	private Imovel imovel;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	
	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public gsan.seguranca.acesso.usuario.Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(gsan.seguranca.acesso.usuario.Usuario usuario) {
		this.usuario = usuario;
	}
	
	public gsan.cadastro.empresa.Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(gsan.cadastro.empresa.Empresa empresa) {
		this.empresa = empresa;
	}
	
	
	public Integer getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(Integer codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	public Integer getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(Integer codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public ParametroTabelaAtualizacaoCadastro(){}
	
	public ParametroTabelaAtualizacaoCadastro( 
			Localidade localidadeInicial, 
			Localidade localidadeFinal, 
			Integer codigoSetorComercialInicial, 
			Integer codigoSetorComercialFinal,
			Integer numeroQuadraInicial, 
			Integer numeroQuadraFinal,
			Date ultimaAlteracao, 
			Usuario usuario, 
			Empresa  empresa) {
		
		this.localidadeInicial = localidadeInicial;
		this.localidadeFinal = localidadeFinal;
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
		this.numeroQuadraFinal = numeroQuadraFinal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.usuario = usuario;
		this.empresa = empresa;
	
	}
	
	
	public void montarParametroTabelaAtualizacaoCadastro(
		ImovelGeracaoTabelasTemporariasCadastroHelper helper){
		
		if(helper.getFirma() != null && !helper.getFirma().equals("")){
			Empresa empresa = new Empresa();
			empresa.setId(new Integer(helper.getFirma()));
			
			this.setEmpresa(empresa);
		}
		
		if(helper.getUsuario() != null){
			this.setUsuario(helper.getUsuario());
		}
		
		if(helper.getMatricula() != null){
			
			Imovel imovel = new Imovel();
			imovel.setId(new Integer(helper.getMatricula()));
			
			this.setImovel(imovel);
		}
		
		if(helper.getLocalidadeInicial() != null && 
			!helper.getLocalidadeInicial().equals("")){
			
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(helper.getLocalidadeInicial()));
			
			this.setLocalidadeInicial(localidade);
		}

		if(helper.getLocalidadeFinal() != null && 
			!helper.getLocalidadeFinal().equals("")){
			
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(helper.getLocalidadeFinal()));
			
			this.setLocalidadeFinal(localidade);
			
		}
		
		if(helper.getCodigoSetorComercialInicial() != null && 
			!helper.getCodigoSetorComercialInicial().equals("")){
			
			this.setCodigoSetorComercialInicial(new Integer(helper.getCodigoSetorComercialInicial()));
		}
		
		if(helper.getCodigoSetorComercialFinal() != null && 
			!helper.getCodigoSetorComercialFinal().equals("")){
			
			this.setCodigoSetorComercialFinal(new Integer(helper.getCodigoSetorComercialFinal()));
		}
		
		if(helper.getQuadraInicial() != null && !helper.getQuadraInicial().equals("")){
			this.setNumeroQuadraInicial(new Integer(helper.getQuadraInicial()));
		}
		
		if(helper.getQuadraFinal() != null && !helper.getQuadraInicial().equals("")){
			this.setNumeroQuadraFinal(new Integer(helper.getQuadraFinal()));
		}
		
		if(helper.getRotaInicial() != null && !helper.getRotaInicial().equals("")){
			this.setCodigoRotaInicial(new Integer(helper.getRotaInicial()));
		}
		
		if(helper.getRotaFinal() != null && !helper.getRotaFinal().equals("")){
			this.setCodigoRotaFinal(new Integer(helper.getRotaFinal()));
		}	
		
	}

   
    
}
