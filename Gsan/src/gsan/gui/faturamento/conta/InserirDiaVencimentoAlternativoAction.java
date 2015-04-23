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

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.gui.faturamento.conta;

import gsan.atendimentopublico.portal.AcessoLojaVirtual;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroVencimentoAlternativo;
import gsan.faturamento.VencimentoAlternativo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirDiaVencimentoAlternativoAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		Collection<VencimentoAlternativo> vencimentosAlternativos = new ArrayList<VencimentoAlternativo>();
		VencimentoAlternativo vencimentoAlternativo = (VencimentoAlternativo) sessao.getAttribute("vencimentoAlternativo");
		
		boolean portal = false;
		if ( sessao.getAttribute("portal") != null &&
				sessao.getAttribute("portal").equals("ok") ) {
			
			portal = true;
			
			Integer matricula = (Integer) sessao.getAttribute("matricula");
			FiltroVencimentoAlternativo filtroVencimentoAlternativo = new FiltroVencimentoAlternativo(FiltroVencimentoAlternativo.DATA_IMPLANTACAO);
			
			filtroVencimentoAlternativo
					.adicionarParametro(new ParametroSimples(
							FiltroVencimentoAlternativo.IMOVEL_ID, matricula));
			
			vencimentosAlternativos = fachada.pesquisar(
					filtroVencimentoAlternativo, VencimentoAlternativo.class
							.getName());
	
			if (vencimentosAlternativos != null
					&& !vencimentosAlternativos.isEmpty()) {
	
				vencimentoAlternativo = (VencimentoAlternativo) Util
						.retonarObjetoDeColecao(vencimentosAlternativos);
			}
		}

		if(portal){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.INDICADOR_USUARIO_INTERNET, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
			
			usuarioLogado = 
					(Usuario) Util.retonarObjetoDeColecao(
				
			this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName()));
	
		}
		// Instância do formulário que está sendo utilizado
		InformarVencimentoAlternativoActionForm informarVencimentoAlternativoActionForm = (InformarVencimentoAlternativoActionForm) actionForm;

		Imovel imovel = (Imovel) sessao.getAttribute("imovel");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		
		if(vencimentoAlternativo == null || vencimentoAlternativo.equals(""))
		{
			FiltroVencimentoAlternativo filtroVencimentoAlternativo = new FiltroVencimentoAlternativo(FiltroVencimentoAlternativo.DATA_IMPLANTACAO);
			
			filtroVencimentoAlternativo
					.adicionarParametro(new ParametroSimples(
							FiltroVencimentoAlternativo.IMOVEL_ID, imovel
									.getId()));
	
			vencimentosAlternativos = fachada.pesquisar(
					filtroVencimentoAlternativo, VencimentoAlternativo.class
							.getName());
	
			if (vencimentosAlternativos != null
					&& !vencimentosAlternativos.isEmpty()) {
	
				vencimentoAlternativo = (VencimentoAlternativo) Util
						.retonarObjetoDeColecao(vencimentosAlternativos);
	
				Date dataVencimento = vencimentoAlternativo
						.getDataImplantacao();
	
				SimpleDateFormat dataFormato = new SimpleDateFormat(
						"dd/MM/yyyy");
				String dataVencimentoString = null;
				if (dataVencimento != null) {
					dataVencimentoString = dataFormato.format(dataVencimento);
				}
	
				informarVencimentoAlternativoActionForm
						.setDataAlteracaoVencimento(dataVencimentoString == null ? ""
								: "" + dataVencimentoString);
	
			}
		}else{
		
			Short numeroMesesMinimoVencimentoAlternativo = sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento();
		

			/*Calendar calendarVencimentoAlternativo = new GregorianCalendar();
			calendarVencimentoAlternativo.setTime(vencimentoAlternativo
					.getDataImplantacao());

			int mesVencimentoAlternativo = calendarVencimentoAlternativo
					.get(Calendar.MONTH);

			Calendar calendarAtual = new GregorianCalendar();

			int mesAtual = calendarAtual.get(Calendar.MONTH);*/
			
			//verifica  se usuario possue permissão especial para informar 
			//o vencimento alternativo antes do período válido
			boolean temPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido = fachada
				.verificarPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido(usuarioLogado);
			
			Date dataImplantacao = vencimentoAlternativo.getDataImplantacao();
			Date dataAtual = new Date();

			int diferencaMes = Util.dataDiff(dataAtual,dataImplantacao);

			if (diferencaMes < numeroMesesMinimoVencimentoAlternativo &&
					!temPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido) {

				if(portal){
					
					httpServletRequest.setAttribute("numeroMesesMinimo", numeroMesesMinimoVencimentoAlternativo +"");
					httpServletRequest.setAttribute("numeroMesesMinimoVencimentoAlternativoSuperiorPermitido", true);
					httpServletRequest.setAttribute("voltarServicos", true);
					
					if(sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_CAEMA)){
						return actionMapping.findForward("numeroMesesMinimoVencimentoAlternativoSuperiorPermitido");
					}else if(sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_CAER)){
						return actionMapping.findForward("numeroMesesMinimoVencimentoAlternativoSuperiorPermitidoCaer");
					}else if(sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_CAERN)){
						return actionMapping.findForward("numeroMesesMinimoVencimentoAlternativoSuperiorPermitidoCaern");
					}

					
				}else{
					
					throw new ActionServletException(
							"atencao.imovel.vencimento.alterado", null,
							numeroMesesMinimoVencimentoAlternativo.toString());
				}
				
			} else {

				vencimentoAlternativo.setDateExclusao(new Date());
				vencimentoAlternativo.setUltimaAlteracao(new Date());
				fachada.atualizar(vencimentoAlternativo);
			}
		}

		Short novoDiaVencimento = null;
		novoDiaVencimento = new Short(informarVencimentoAlternativoActionForm
				.getNovoDiaVencimento());
		
		//Short indicadorVencimentoMesSeguinte = null;
		//indicadorVencimentoMesSeguinte = null ;
		

		if (imovel != null && !imovel.equals("")) {
			imovel.setDiaVencimento(novoDiaVencimento);
			imovel.setUltimaAlteracao(new Date());

			//Alterado por Rômulo Aurélio Data: 24/08/2010
			//Analista: Ana Cristina
			//Alteração: Retirada do layout da funcionalidade o indicador “Vencimento é para mês seguinte: SIM ou NÃO”.
			
			FaturamentoGrupo faturamentoGrupo = fachada.recuperaGrupoFaturamentoDoImovel(imovel.getId());
			
			if(faturamentoGrupo.getDiaVencimento() > novoDiaVencimento){
				imovel.setIndicadorVencimentoMesSeguinte(ConstantesSistema.INDICADOR_USO_ATIVO);
			}else{
				imovel.setIndicadorVencimentoMesSeguinte(ConstantesSistema.INDICADOR_USO_DESATIVO);
			}
			fachada.atualizar(imovel);
		}

		VencimentoAlternativo vencimentoAlternativoInserir = new VencimentoAlternativo();

		if (imovel != null && !imovel.equals("")) {
			vencimentoAlternativoInserir.setImovel(imovel);
		}

		vencimentoAlternativoInserir.setDataImplantacao(new Date());
		vencimentoAlternativoInserir.setDateVencimento(novoDiaVencimento);
		vencimentoAlternativoInserir.setDateExclusao(null);
		vencimentoAlternativoInserir.setUltimaAlteracao(new Date());
		
				
    	// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_VENCIMENTO_ALTERNATIVO, vencimentoAlternativoInserir.getImovel().getId(),
				vencimentoAlternativoInserir.getImovel().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------
        
    		
		registradorOperacao.registrarOperacao(vencimentoAlternativoInserir);
		fachada.inserir(vencimentoAlternativoInserir);

		if (portal) {
			httpServletRequest.setAttribute("VencimentoAlteradoComSucesso", true);
			
			if(sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_CAEMA)){
				retorno = actionMapping.findForward("VencimentoAlteradoComSucesso");
				
			}else if(sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_CAER)) {
				retorno = actionMapping.findForward("VencimentoCaerAlteradoComSucesso");

			}else if(sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_CAERN)) {
				retorno = actionMapping.findForward("VencimentoCaernAlteradoComSucesso");
			}

		} else {
			sessao.removeAttribute("colecaoNovoDiaVencimento");
			sessao.removeAttribute("imovel");
			sessao.removeAttribute("vencimentoAlternativo");
			montarPaginaSucesso(httpServletRequest,
					"Vencimento Alternativo para o imóvel "
							+ informarVencimentoAlternativoActionForm.getIdImovel()
							+ " inserido com sucesso.",
					"Informar outro Vencimento Alternativo",
					"exibirInformarVencimentoAlternativoAction.do?menu=sim");
		}
		//Contabiliza os servicos utilizados
		if(httpServletRequest.getParameter("lojaVirtual") != null){
			String ip = httpServletRequest.getRemoteAddr(); 
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.ALTERAR_VENCIMENTO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
			
		}
		return retorno;

	}
	
}