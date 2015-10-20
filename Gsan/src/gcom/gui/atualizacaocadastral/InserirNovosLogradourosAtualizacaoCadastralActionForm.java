/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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

package gcom.gui.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

public class InserirNovosLogradourosAtualizacaoCadastralActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	private String idLocalidade;
	private String nomeLocalidade;
	private String[] idsRegistros;
	private String idLogradouroSubstituto;
	
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
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
	public String[] getIdsRegistros() {
		return idsRegistros;
	}
	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}
	public String getIdLogradouroSubstituto() {
		return idLogradouroSubstituto;
	}
	public void setIdLogradouroSubstituto(String idLogradouroSubstituto) {
		this.idLogradouroSubstituto = idLogradouroSubstituto;
	} 
}
