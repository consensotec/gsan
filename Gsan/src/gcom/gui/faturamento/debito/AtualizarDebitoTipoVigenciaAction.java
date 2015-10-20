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
package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.FiltroDebitoTipoVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
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
 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 16/04/2010
 */
public class AtualizarDebitoTipoVigenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		AtualizarDebitoTipoVigenciaActionForm form = (AtualizarDebitoTipoVigenciaActionForm) actionForm;

		//Recebe os Parametros da sessao.
		Date timeStampDB = (Date) sessao.getAttribute("timeStamp");
		
	//	String idDebitoTipoVigencia1 = (String) sessao.getAttribute("idDebitoTipoVigencia");
		
		DebitoTipoVigencia debitoTipoVigencia = (DebitoTipoVigencia) sessao.getAttribute("debitoTipoVigencia");
		
	//	form.setIdDebitoTipoVigencia(idDebitoTipoVigencia1);

		String descricaoDebitoTipo = form.getNomeDebitoTipo();

		String debitoTipoSTR = form.getDebitoTipo();

		String valorServico = form.getValorDebito();

		debitoTipoVigencia.setValorDebito(Util.formatarMoedaRealparaBigDecimal(valorServico));

		// Seta no Objeto os dados do form

		DebitoTipo debitoTipo = new DebitoTipo();

		debitoTipo.setId(new Integer(debitoTipoSTR));

		debitoTipoVigencia.setDebitoTipo(debitoTipo);
		
		debitoTipoVigencia.setUltimaAlteracao(new Date());
		
		//
		// Verificar Atualiza��o realizada por outro usu�rio
		//
		String idDebitoTipoVigencia = debitoTipoVigencia.getId().toString();
		
		FiltroDebitoTipoVigencia filtroDebitoTipoVigencia = new FiltroDebitoTipoVigencia();
		filtroDebitoTipoVigencia.adicionarParametro(new ParametroSimples(FiltroDebitoTipoVigencia.ID, idDebitoTipoVigencia));
		
		//  Recupera Debito Tipo Servi�o
		Collection colecaoDebitoTipoVigencia = fachada.pesquisar(filtroDebitoTipoVigencia, DebitoTipoVigencia.class.getName());
		
		DebitoTipoVigencia debitoTipoVigenciaBD = (DebitoTipoVigencia) colecaoDebitoTipoVigencia.iterator().next();
		
		if ( !Util.formatarData(debitoTipoVigenciaBD.getDataVigenciaInicial()).equals(form.getDataVigenciaInicial() ) || 
				!Util.formatarData(debitoTipoVigenciaBD.getDataVigenciaFinal()).equals(form.getDataVigenciaFinal()) ) {
			
			// Vig�ncia do valor do servi�o
			//[FS0007] � Validar data da vig�ncia inicial
			if (form.getDataVigenciaInicial() != null && !form.getDataVigenciaInicial().equals("")){
				
				if (!Util.validarDiaMesAno(form.getDataVigenciaInicial())) {
					
					debitoTipoVigencia.setDataVigenciaInicial(Util.converteStringParaDate(form.getDataVigenciaInicial()));
					//[FS0008] � Validar data da vig�ncia final
					if (!Util.validarDiaMesAno(form.getDataVigenciaFinal())) {
						
						debitoTipoVigencia.setDataVigenciaFinal(Util.converteStringParaDate(form.getDataVigenciaFinal()));
						
						if(Util.compararData(debitoTipoVigencia.getDataVigenciaInicial(),debitoTipoVigencia.getDataVigenciaFinal()) == 1){
							throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
						}
					}else{
						throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
					}			
				}else{
					throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
				}
			}else{
				
				debitoTipoVigencia.setDataVigenciaInicial(null);
				debitoTipoVigencia.setDataVigenciaFinal(null);
				
			}
			// FS0008 - Verificar pr�-exist�ncia de vig�ncia para o d�bito tipo
			Fachada.getInstancia().verificarExistenciaVigenciaDebito(form.getDataVigenciaInicial(), 
					form.getDataVigenciaFinal(), new Integer(form.getDebitoTipo()), new Integer("1"));
		
		
		}	
		
		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_DEBITO_TIPO_VIGENCIA, debitoTipoVigencia.getDebitoTipo().getId(),
				debitoTipoVigencia.getDebitoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSA��O ----------------
		
		registradorOperacao.registrarOperacao(debitoTipoVigencia);
		
		
		if(timeStampDB.compareTo(debitoTipoVigenciaBD.getUltimaAlteracao()) != 0){
			throw new ActionServletException("atencao.atualizacao.timestamp");
		}
		
		fachada.atualizar(debitoTipoVigencia);

		montarPaginaSucesso(httpServletRequest, "O Debito Tipo Vigencia "
				+ descricaoDebitoTipo + " atualizado com sucesso.",
				"Realizar outra Manuten��o Debito Tipo Vigencia",
				"exibirFiltrarDebitoTipoVigenciaAction.do?menu=sim");

		return retorno;
	}
}
