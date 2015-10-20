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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacional;
import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesAplicacao;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
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
 * [UC1530] Inserir Registro de Atendimento Simplificado
 * 
 * @author Mariana Victor
 * @date 10/07/2013
 */
public class InserirRegistroAtendimentoSimplificadoAction extends GcomAction {
	
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InserirRegistroAtendimentoSimplificadoActionForm form = (InserirRegistroAtendimentoSimplificadoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		Integer tipoAtendimento = new Integer(form.getTipoAtendimento());
		
		this.validarCampos(form, fachada);
		
		//[IT0015] Pesquisar Cliente com CPF
		Cliente cliente = null;
		if (form.getNumeroCpf() != null
				&& !form.getNumeroCpf().trim().equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.CPF, form.getNumeroCpf()));
			filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO, ConstantesSistema.SIM));
			Collection<Cliente> colecaoCliente = fachada.pesquisar(
				filtroCliente, Cliente.class.getName());
			
			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
			}

			//[SB0005] Atualizar RG do Cliente
			if (cliente != null && cliente.getId() != null) {
				this.atualizarCliente(form, fachada, cliente);
			}
		}
		
		// 3.1.	Caso o tipo de atendimento seja "Operacional"
		if (tipoAtendimento.compareTo(ConstantesSistema.ATENDIMENTO_OPERACIONAL) == 0) {
			
			if (form.getIdOcorrenciaOperacional() == null
					|| form.getIdOcorrenciaOperacional().trim().equals("")) {
				//[SB0008] Chamar Funcionalidade Inserir RA
//				if (cliente != null && cliente.getId() != null) {
//					sessao.setAttribute("clienteRASimplificado", cliente);
//					sessao.removeAttribute("nomeSolicitante");
//				} else {
//					sessao.removeAttribute("clienteRASimplificado");
//					sessao.setAttribute("nomeSolicitante", form.getNome());
//				}
				
				sessao.setAttribute("dataAtendRASimplificado", form.getDataAtendimento());
				sessao.setAttribute("horaAtendRASimplificado", form.getHoraAtendimento());
				
				retorno = actionMapping.findForward("exibirInserirRegistroAtendimento");
				
			} else {
				Integer idRAInserido = this.inserirRegistroAtendimentoOperacional(usuario, form, fachada);
				this.inserirUnidadeRegistroAtendimento(usuario, fachada, idRAInserido);
				this.inserirSolicitanteRegistroAtendimento(form, sessao, fachada, idRAInserido, cliente);
				this.inserirRegistroAtendimentoEncerramento(usuario, form, sessao, fachada, idRAInserido, cliente);
				
				montarPaginaSucesso(httpServletRequest,
						"Registro Atendimento Simplificado de código " + idRAInserido + " inserido com sucesso",
						"Inserir outro Registro Atendimento Simplificado",
						"exibirInserirRegistroAtendimentoSimplificadoAction.do?menu=sim",
						"gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do?idRegistroAtendimento=" + idRAInserido,
						"Imprimir RA");
			}
			
		// 3.2.	Caso o tipo de atendimento seja "Comercial"
		} else if (tipoAtendimento.compareTo(ConstantesSistema.ATENDIMENTO_COMERCIAL) == 0) {
			
			//[SB0008] Chamar Funcionalidade Inserir RA
//			if (cliente != null && cliente.getId() != null) {
//				sessao.setAttribute("clienteRASimplificado", cliente);
//				sessao.removeAttribute("nomeSolicitante");
//			} else {
//				sessao.removeAttribute("clienteRASimplificado");
//				sessao.setAttribute("nomeSolicitante", form.getNome());
//			}
			
			sessao.setAttribute("dataAtendRASimplificado", form.getDataAtendimento());
			sessao.setAttribute("horaAtendRASimplificado", form.getHoraAtendimento());
			
			retorno = actionMapping.findForward("exibirInserirRegistroAtendimento");
			
		// 3.3.	Caso o tipo de atendimento seja "Reiteração"
		} else if (tipoAtendimento.compareTo(ConstantesSistema.ATENDIMENTO_REITERACAO) == 0) {
			
			Integer idRAInserido = this.inserirRegistroAtendimentoReiteracao(usuario, form, fachada, cliente);
			this.inserirUnidadeRegistroAtendimento(usuario, fachada, idRAInserido);
			this.inserirSolicitanteRegistroAtendimento(form, sessao, fachada, idRAInserido, cliente);
			this.inserirRegistroAtendimentoEncerramento(usuario, form, sessao, fachada, idRAInserido, cliente);

			montarPaginaSucesso(httpServletRequest,
					"Registro Atendimento Simplificado de código " + idRAInserido + " reiterado com sucesso",
					"Inserir outro Registro Atendimento Simplificado",
					"exibirInserirRegistroAtendimentoSimplificadoAction.do?menu=sim",
					"gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do?idRegistroAtendimento=" + idRAInserido,
					"Imprimir RA");
			
		// 3.4.	Caso o tipo de atendimento seja "Informação"
		} else {
			Integer idRAInserido = this.inserirRegistroAtendimentoInformacao(usuario, form, fachada);
			this.inserirUnidadeRegistroAtendimento(usuario, fachada, idRAInserido);
			this.inserirSolicitanteRegistroAtendimento(form, sessao, fachada, idRAInserido, cliente);
			this.inserirRegistroAtendimentoEncerramento(usuario, form, sessao, fachada, idRAInserido, cliente);
			
			montarPaginaSucesso(httpServletRequest,
					"Registro Atendimento Simplificado de código " + idRAInserido + " inserido com sucesso",
					"Inserir outro Registro Atendimento Simplificado",
					"exibirInserirRegistroAtendimentoSimplificadoAction.do?menu=sim",
					"gerarRelatorioRegistroAtendimentoViaClienteConsultarAction.do?idRegistroAtendimento=" + idRAInserido,
					"Imprimir RA");
		}

		return retorno;

	}
	
	/**
	 * [IT0013] Inserir Registro Atendimento Simplificado Reiteração
	 * */
	private Integer inserirRegistroAtendimentoReiteracao(Usuario usuario,
			InserirRegistroAtendimentoSimplificadoActionForm form, Fachada fachada, Cliente cliente ) {
		
		RegistroAtendimento registroAtendimento = null;
		
		RAReiteracao raReiteracao = new RAReiteracao();
		
		if ( form.getNumeroRA() != null && !form.getNumeroRA().equals( "" ) ){
			registroAtendimento = fachada.obterDadosRegistroAtendimento(Integer.parseInt(form.getNumeroRA())).getRegistroAtendimento();
		} else if ( form.getNumeroProtocolo() != null && form.getNumeroProtocolo().equals( "" ) ) {
			FiltroRegistroAtendimentoSolicitante filtro = new FiltroRegistroAtendimentoSolicitante();
			
			filtro.adicionarParametro( new ParametroSimples( FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_PROTOCOLO, form.getNumeroProtocolo() ) );
			filtro.adicionarCaminhoParaCarregamentoEntidade( "registroAtendimento" );
			Collection<RegistroAtendimentoSolicitante> colRAS = fachada.pesquisar( filtro , RegistroAtendimentoSolicitante.class.getName() );
			
			registroAtendimento = ( ( RegistroAtendimentoSolicitante )Util.retonarObjetoDeColecao( colRAS ) ).getRegistroAtendimento();
		}
		
		raReiteracao.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
		raReiteracao.setCliente(cliente);

		raReiteracao.setRegistroAtendimento(registroAtendimento);
		raReiteracao.setObservacao(form.getObservacao());
		
		if (form.getNumeroProtocolo() != null && !form.getNumeroProtocolo().trim().equals("")) {
			registroAtendimento.setObservacao(form.getObservacao() + " - Nº do protocolo: " + form.getNumeroProtocolo());
			raReiteracao.setNumeroProtocoloAtendimento( form.getNumeroProtocolo() );
		} else {
			String numeroProtocolo = fachada.pesquisarNumeroProtocoloRegistroAtendimento(
				Integer.valueOf(form.getNumeroRA()));
			registroAtendimento.setObservacao(form.getObservacao() + " - Nº do protocolo: " + numeroProtocolo);
			raReiteracao.setNumeroProtocoloAtendimento( numeroProtocolo );
		}		
		
		try{
			//gera a ordem de serviço
			fachada.reiterarRegistroAtendimento(registroAtendimento, usuario, raReiteracao,cliente != null ? cliente.getClienteFones() : null);
		} catch ( FachadaException e ){
			registroAtendimento.setObservacao( registroAtendimento.getObservacao() + "\nRA nao reiterada, motivo: " + ConstantesAplicacao.get( e.getMessage() ) );
			fachada.atualizar( registroAtendimento );
		} 
		
		
		MeioSolicitacao meioSolicitacao = new MeioSolicitacao();
		meioSolicitacao.setId(MeioSolicitacao.INTERNO);
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
		solicitacaoTipoEspecificacao.setId(SolicitacaoTipoEspecificacao.SIMPLIFICADO_REITERACAO);

		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
		atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);
		
		registroAtendimento.setIndicadorAtendimentoOnline(ConstantesSistema.SIM);
		registroAtendimento.setRegistroAtendimento(Util.converteStringParaDateHora(
			form.getDataAtendimento() + " " + form.getHoraAtendimento() + ":00"));
		registroAtendimento.setMeioSolicitacao(meioSolicitacao);
		registroAtendimento.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
		registroAtendimento.setDataPrevistaOriginal(new Date());
		registroAtendimento.setDataPrevistaAtual(new Date());
		registroAtendimento.setDataEncerramento(Util.converteStringParaDateHora(
			form.getDataAtendimento() + " " + form.getHoraAtendimento() + ":00"));
		registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);

		registroAtendimento.setDescricaoLocalOcorrencia("RA simplificada de Reiteracao");
		registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
		registroAtendimento.setUltimaAlteracao(new Date());
		registroAtendimento.setUnidadeAtual(usuario.getUnidadeOrganizacional());		
		
		registroAtendimento.setId( (Integer) fachada.inserir(registroAtendimento) );
		
		return registroAtendimento.getId();
	}
	
	/**
	 * [IT0014] Inserir Registro Atendimento Simplificado Informação
	 * */
	private Integer inserirRegistroAtendimentoInformacao(Usuario usuario,
			InserirRegistroAtendimentoSimplificadoActionForm form, Fachada fachada) {
		MeioSolicitacao meioSolicitacao = new MeioSolicitacao();
		meioSolicitacao.setId(MeioSolicitacao.INTERNO);
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
		solicitacaoTipoEspecificacao.setId(SolicitacaoTipoEspecificacao.SIMPLIFICADO_INFORMACAO);

		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
		atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);
		
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setIndicadorAtendimentoOnline(ConstantesSistema.SIM);
		registroAtendimento.setRegistroAtendimento(Util.converteStringParaDateHora(
			form.getDataAtendimento() + " " + form.getHoraAtendimento() + ":00"));
		registroAtendimento.setMeioSolicitacao(meioSolicitacao);
		registroAtendimento.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
		registroAtendimento.setDataPrevistaOriginal(new Date());
		registroAtendimento.setDataPrevistaAtual(new Date());
		registroAtendimento.setDataEncerramento(Util.converteStringParaDateHora(
			form.getDataAtendimento() + " " + form.getHoraAtendimento() + ":00"));
		registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		registroAtendimento.setObservacao(form.getDescricaoInformacao());;
		registroAtendimento.setDescricaoLocalOcorrencia("RA simplificada de Informacao");
		registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
		registroAtendimento.setUltimaAlteracao(new Date());
		registroAtendimento.setUnidadeAtual(usuario.getUnidadeOrganizacional());
		
		return (Integer) fachada.inserir(registroAtendimento);
	}
	
	/**
	 * [IT0010] Inserir Registro Atendimento Simplificado Operacional
	 * */
	private Integer inserirRegistroAtendimentoOperacional(Usuario usuario,
			InserirRegistroAtendimentoSimplificadoActionForm form, Fachada fachada) {
		MeioSolicitacao meioSolicitacao = new MeioSolicitacao();
		meioSolicitacao.setId(MeioSolicitacao.INTERNO);
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
		solicitacaoTipoEspecificacao.setId(SolicitacaoTipoEspecificacao.SIMPLIFICADO_OPERACIONAL);

		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
		atendimentoMotivoEncerramento.setId(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);

		OcorrenciaOperacional ocorrenciaOperacional = new OcorrenciaOperacional();
		ocorrenciaOperacional.setId(Integer.valueOf(form.getIdOcorrenciaOperacional()));
		
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setIndicadorAtendimentoOnline(ConstantesSistema.SIM);
		registroAtendimento.setRegistroAtendimento(Util.converteStringParaDateHora(
			form.getDataAtendimento() + " " + form.getHoraAtendimento() + ":00"));
		registroAtendimento.setMeioSolicitacao(meioSolicitacao);
		registroAtendimento.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
		registroAtendimento.setDataPrevistaOriginal(new Date());
		registroAtendimento.setDataPrevistaAtual(new Date());
		registroAtendimento.setDataEncerramento(Util.converteStringParaDateHora(
			form.getDataAtendimento() + " " + form.getHoraAtendimento() + ":00"));
		registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		
		Object[] dadosOcorrencia = fachada.pesquisarDadosOcorrenciaOperacional(
			Integer.valueOf(form.getIdOcorrenciaOperacional()));
		if (dadosOcorrencia[0] != null) {
			registroAtendimento.setObservacao((String) dadosOcorrencia[0]);
		}
		if (dadosOcorrencia[1] != null) {
			BairroArea bairroArea = new BairroArea();
			bairroArea.setId((Integer) dadosOcorrencia[1]);
			registroAtendimento.setBairroArea(bairroArea);
		} 
		if (dadosOcorrencia[2] != null) {
			Localidade localidade = new Localidade();
			localidade.setId((Integer) dadosOcorrencia[2]);
			registroAtendimento.setLocalidade(localidade);
		} 
		if (dadosOcorrencia[3] != null) {
			registroAtendimento.setDescricaoLocalOcorrencia((String) dadosOcorrencia[3]);
		} 
		
		registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
		registroAtendimento.setUltimaAlteracao(new Date());
		registroAtendimento.setUnidadeAtual(usuario.getUnidadeOrganizacional());
		registroAtendimento.setOcorrenciaOperacional(ocorrenciaOperacional);
		
		return (Integer) fachada.inserir(registroAtendimento);
	}
	
	/**
	 * [IT0018] Inserir Unidade do Registro Atendimento
	 * */
	private void inserirUnidadeRegistroAtendimento(Usuario usuario, 
			Fachada fachada, Integer idRAInserido) {
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setId(idRAInserido);
		
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
		
		RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
		registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
		registroAtendimentoUnidade.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
		registroAtendimentoUnidade.setUsuario(usuario);
		registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
		registroAtendimentoUnidade.setUltimaAlteracao(new Date());
		
		fachada.inserir(registroAtendimentoUnidade);
	}
	
	/**
	 * [IT0019] Inserir Solicitante do Registro Atendimento
	 * */
	private void inserirSolicitanteRegistroAtendimento(
			InserirRegistroAtendimentoSimplificadoActionForm form, HttpSession sessao,
			Fachada fachada, Integer idRAInserido, Cliente cliente) {
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setId(idRAInserido);
		
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = new RegistroAtendimentoSolicitante();
		registroAtendimentoSolicitante.setRegistroAtendimento(registroAtendimento);
		if (cliente != null && cliente.getId() != null) {
			registroAtendimentoSolicitante.setCliente(cliente);
		}
		if (form.getNome() != null 
				&& !form.getNome().trim().equals("")) {
			registroAtendimentoSolicitante.setSolicitante(form.getNome());
		}
		registroAtendimentoSolicitante.setIndicadorSolicitantePrincipal(ConstantesSistema.SIM);
		registroAtendimentoSolicitante.setUltimaAlteracao(new Date());
		
		Integer tipoAtendimento = new Integer(form.getTipoAtendimento());
		
		if (tipoAtendimento.compareTo(ConstantesSistema.ATENDIMENTO_REITERACAO) == 0) {			
			String numeroProtocolo = fachada.pesquisarNumeroProtocoloRegistroAtendimento(
					Integer.valueOf(form.getNumeroRA()));			
			registroAtendimentoSolicitante.setNumeroProtocoloAtendimento( numeroProtocolo );		
		} else {
			registroAtendimentoSolicitante.setNumeroProtocoloAtendimento(
				sessao.getAttribute("protocoloAtendimento").toString());
		}
		registroAtendimentoSolicitante.setIndicadorEnvioEmailPesquisa(ConstantesSistema.NAO);

		if (form.getNumeroCpf() != null
				&& !form.getNumeroCpf().trim().equals("")) {
			registroAtendimentoSolicitante.setNumeroCpf(
				form.getNumeroCpf());
		}
		if (form.getNumeroRg() != null
				&& !form.getNumeroRg().trim().equals("")) {
			registroAtendimentoSolicitante.setNumeroRg(
				form.getNumeroRg());
		}
		if (form.getIdOrgaoExpedidor() != null
				&& !form.getIdOrgaoExpedidor().trim().equals("")
				&& !form.getIdOrgaoExpedidor().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
			orgaoExpedidorRg.setId(Integer.valueOf(form.getIdOrgaoExpedidor()));
			registroAtendimentoSolicitante.setOrgaoExpedidorRg(
				orgaoExpedidorRg);
		}
		if (form.getIdUnidadeFederacao() != null
				&& !form.getIdUnidadeFederacao().trim().equals("")
				&& !form.getIdUnidadeFederacao().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
			unidadeFederacao.setId(Integer.valueOf(form.getIdUnidadeFederacao()));
			registroAtendimentoSolicitante.setUnidadeFederacao(
				unidadeFederacao);
		}
		
		fachada.inserir(registroAtendimentoSolicitante);
	}
	
	/**
	 * 
	 * */
	private void inserirRegistroAtendimentoEncerramento(Usuario usuario, 
			InserirRegistroAtendimentoSimplificadoActionForm form, HttpSession sessao,
			Fachada fachada, Integer idRAInserido, Cliente cliente) {
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setId(idRAInserido);
		
		RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
		registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
		registroAtendimentoUnidade.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
		registroAtendimentoUnidade.setUsuario(usuario);
		registroAtendimentoUnidade.setUltimaAlteracao(new Date());
		
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
		
		registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
	}

	/**
	 * [SB0005] Atualizar RG do Cliente
	 * */
	private void atualizarCliente(InserirRegistroAtendimentoSimplificadoActionForm form, Fachada fachada, Cliente cliente) {
		if ((cliente.getRg() == null
			    || cliente.getRg().trim().equals(""))
			  && form.getNumeroRg() != null
			  && !form.getNumeroRg().trim().equals("")
			  && form.getIdOrgaoExpedidor() != null
			  && !form.getIdOrgaoExpedidor().trim().equals("")
			  && !form.getIdOrgaoExpedidor().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")
			  && form.getIdUnidadeFederacao() != null
			  && !form.getIdUnidadeFederacao().trim().equals("")
			  && !form.getIdUnidadeFederacao().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			
			cliente.setRg(form.getNumeroRg());
			
			OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
			orgaoExpedidorRg.setId(Integer.valueOf(form.getIdOrgaoExpedidor()));
			cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
			
			UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
			unidadeFederacao.setId(Integer.valueOf(form.getIdUnidadeFederacao()));
			cliente.setUnidadeFederacao(unidadeFederacao);
			
			fachada.atualizar(cliente);
		}
	}
	
	/**
	 * [FE0006] Verificar preenchimento dos campos
	 * [FE0001] Validar CPF
	 * [FE0002] Verificar preenchimento do documento
	 * [FE0005] Verificar preenchimento dos dados RA
	 * [FE0009] Verificar existência RA para o Protocolo
	 * [FE0004] Verificar existência do registro de atendimento
	 * 
	 * */
	private void validarCampos(InserirRegistroAtendimentoSimplificadoActionForm form, Fachada fachada){

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Integer tipoAtendimento = new Integer(form.getTipoAtendimento());
		
		boolean cpfInformado = false;
		if (form.getNumeroCpf() != null 
				&& !form.getNumeroCpf().trim().equals("")) {
			cpfInformado = true;
		}
		boolean rgInformado = false;
		boolean orgaoExpInformado = false;
		boolean ufInformado = false;
		if (form.getNumeroRg() != null 
				&& !form.getNumeroRg().trim().equals("")) {
			rgInformado = true;
		}
		if (form.getIdOrgaoExpedidor() != null 
				&& !form.getIdOrgaoExpedidor().trim().equals("")
				&& !form.getIdOrgaoExpedidor().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			orgaoExpInformado = true;
		}
		if (form.getIdUnidadeFederacao() != null 
				&& !form.getIdUnidadeFederacao().trim().equals("")
				&& !form.getIdUnidadeFederacao().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			ufInformado = true;
		}
		
		// [FE0006] Verificar preenchimento dos campos
		if (form.getNome() == null
				|| form.getNome().trim().equals("")) {
			
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
				null, "Nome do Solicitante");
		}
		
		// [FE0001] Validar CPF
		if (cpfInformado 
				&& !Util.validacaoCPF(form.getNumeroCpf())) {
			throw new ActionServletException(
				"atencao.invalido.cpf", null, "Número do CPF");
		}
		
		if (!rgInformado
				&& (orgaoExpInformado || ufInformado)) {
			throw new ActionServletException(
				"atencao.campo_selecionado.obrigatorio", null, "RG");
		}
		
		if (!orgaoExpInformado
				&& (rgInformado || ufInformado)) {
			throw new ActionServletException(
				"atencao.campo_selecionado.obrigatorio", null, "Órgão Expedidor");
		}
		
		if (!ufInformado
				&& (rgInformado || orgaoExpInformado)) {
			throw new ActionServletException(
				"atencao.campo_selecionado.obrigatorio", null, "Unidade Federação");
		}
		
		
		
		// [FE0002] Verificar preenchimento do documento
		// 3.1.	Caso o tipo de atendimento seja "Operacional"
		if (tipoAtendimento.compareTo(ConstantesSistema.ATENDIMENTO_OPERACIONAL) == 0) {
			if (sistemaParametro.getIndicadorDocObrAtendimentoOperacional() != null
					&& sistemaParametro.getIndicadorDocObrAtendimentoOperacional().compareTo(
						ConstantesSistema.SIM) == 0
					&& !cpfInformado) {
				
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
					null, "o documento CPF");
			}
			
		// 3.2.	Caso o tipo de atendimento seja "Comercial"
		} else if (tipoAtendimento.compareTo(ConstantesSistema.ATENDIMENTO_COMERCIAL) == 0) {
			if (sistemaParametro.getIndicadorDocObrAtendimentoComercial() != null
					&& sistemaParametro.getIndicadorDocObrAtendimentoComercial().compareTo(
						ConstantesSistema.SIM) == 0
					&& !cpfInformado) {
				
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
					null, "o documento CPF");
			}
			
		// 3.3.	Caso o tipo de atendimento seja "Reiteração"
		} else if (tipoAtendimento.compareTo(ConstantesSistema.ATENDIMENTO_REITERACAO) == 0) {
			if (sistemaParametro.getIndicadorDocObrReiteracao() != null
					&& sistemaParametro.getIndicadorDocObrReiteracao().compareTo(
						ConstantesSistema.SIM) == 0
					&& !cpfInformado) {
				
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
					null, "o documento CPF");
			}
			
			// [FE0005] Verificar preenchimento dos dados RA
			if ((form.getNumeroProtocolo() == null
					|| form.getNumeroProtocolo().trim().equals(""))
				&& (form.getNumeroRA() == null
						|| form.getNumeroRA().trim().equals(""))) {
				
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
					null, "Registro de Atendimento para Reiteração");
			}
			if (form.getObservacao() == null || form.getObservacao().trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
					null, "Observação");
			}
			
			// [FE0004] Verificar existência do registro de atendimento
			if (form.getNumeroProtocolo() != null && !form.getNumeroProtocolo().trim().equals("")
					&& !fachada.pesquisarRegistroAtendimentoNumeroProtocolo(form.getNumeroProtocolo())) {
				throw new ActionServletException("atencao.numero_protocolo.nao_valido");
			}
			
			// [FE0004] Verificar existência do registro de atendimento
			if (form.getNumeroRA() != null && !form.getNumeroRA().trim().equals("")){
				String numeroProtocolo = fachada.pesquisarNumeroProtocoloRegistroAtendimento(
					Integer.valueOf(form.getNumeroRA()));
				
				if (numeroProtocolo == null || numeroProtocolo.trim().equals("")) {
					throw new ActionServletException("atencao.pesquisa_inexistente", 
						null, "Registro de Atendimento");
				}
			}
			
		// 3.4.	Caso o tipo de atendimento seja "Informação"
		} else {
			if (sistemaParametro.getIndicadorDocObrInformacao() != null
					&& sistemaParametro.getIndicadorDocObrInformacao().compareTo(
						ConstantesSistema.SIM) == 0
					&& !cpfInformado) {
				
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
					null, "Documento CPF");
			}
			
			if ( form.getIdLocalidadeInfo() == null || form.getIdLocalidadeInfo().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"") ){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
						null, "a Localidade para abertura da RA");
			}
			
			
			if (form.getDescricaoInformacao() == null || form.getDescricaoInformacao().trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
					null, "Descrição da Informação");
			}
		}
	}
}
