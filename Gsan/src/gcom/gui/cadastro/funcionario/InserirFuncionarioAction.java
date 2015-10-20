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
package gcom.gui.cadastro.funcionario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author R�mulo Aurelio
 * @date 04/04/2007
 */
public class InserirFuncionarioAction extends GcomAction{
	
	/**
	 * Este caso de uso permite a inclus�o de um novo Funcion�rio
	 * 
	 * [UC0842] Inserir Funcion�rio
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 04/04/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirFuncionarioActionForm form = (InserirFuncionarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		//CARREGANDO O OBJETO FUNCION�RIO
		Funcionario funcionario = this.getFuncionario(form, fachada);
		
		//INSERINDO FUNCION�RIO
		fachada.inserirFuncionario(funcionario, usuarioLogado);
		
		montarPaginaSucesso(httpServletRequest, "Funcionario de matricula "
				+ funcionario.getId() + " inserida com sucesso.",
				"Inserir outro Funcionario",
				"exibirInserirFuncionarioAction.do?menu=sim",
				"exibirAtualizarFuncionarioAction.do?idFuncionario="
				+ funcionario.getId() , "Atualizar Funcionario inserido");
				
	
		return retorno;
	}


	/**
	 * [UC0842] Inserir Funcion�rio
	 *
	 * Carregando os dados do funcion�rio a partir do que foi informado no formul�rio
	 *
	 * @author Raphael Rossiter
	 * @date 17/06/2009
	 *
	 * @param form
	 * @param fachada
	 * @return Funcionario
	 */
	private Funcionario getFuncionario(InserirFuncionarioActionForm form, Fachada fachada){
		
		Funcionario funcionario= new Funcionario();
		
		//MATR�CULA
		funcionario.setId(new Integer(form.getMatricula()));
		
		//NOME
		funcionario.setNome(form.getNome());
		
		//CPF
		if(form.getNumeroCpf() != null && !form.getNumeroCpf().equals("")){
			funcionario.setNumeroCpf(form.getNumeroCpf());
		}
		
		//DATA DE NASCIMENTO
		if(form.getDataNascimento() != null && !form.getDataNascimento().equals("")){
			funcionario.setDataNascimento(Util.converteStringParaDate(form.getDataNascimento()));
		}
		
		//CARGO
		String idFuncionarioCargo = form.getFuncionarioCargo();
		
		if (idFuncionarioCargo != null && !idFuncionarioCargo.trim().equals(
			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			FuncionarioCargo funcionarioCargo = new FuncionarioCargo();

			funcionarioCargo.setId(new Integer(idFuncionarioCargo));

			funcionario.setFuncionarioCargo(funcionarioCargo);

		}
		
		//EMPRESA
		String idEmpresa = form.getEmpresa();

		if (idEmpresa != null && !idEmpresa.trim().equals(
			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			Empresa empresa = new Empresa();

			empresa.setId(new Integer(idEmpresa));

			funcionario.setEmpresa(empresa);

		}
		
		//UNIDADE ORGANIZACIONAL
		String idUnidade = form.getIdUnidade();
		
		FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

    	filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
    	FiltroUnidadeOrganizacional.ID, idUnidade));
		
		Collection<UnidadeOrganizacional> colecaoUnidadeEmpresa = fachada.pesquisar(filtroUnidadeEmpresa,
		UnidadeOrganizacional.class.getName());

		if(colecaoUnidadeEmpresa== null || colecaoUnidadeEmpresa.isEmpty()){
			throw new ActionServletException("atencao.unidade.organizacional.inexistente");
		} 
		
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		unidadeOrganizacional.setId(new Integer(idUnidade));
		
		funcionario.setUnidadeOrganizacional(unidadeOrganizacional);
		
		//�LTIMA ALTERA��O
		funcionario.setUltimaAlteracao(new Date());
		
		return funcionario;

	}
}
