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
* Thiago Silva Toscano de Brito
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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.NegativacaoImovei;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegItem;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorMovimentoRegItem;
import gcom.spcserasa.bean.ExcluirNegativacaoOnLineHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Thiago Silva Toscano de Brito,Vivianne Sousa
 * @date 22/12/2007,13/12/2011
 */
public class ExibirExcluirNegativacaoOnLineAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirExcluirNegativacaoOnLine");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		ExcluirNegativacaoOnLineActionForm form = (ExcluirNegativacaoOnLineActionForm) actionForm;
		form.setDataHoje(Util.formatarData(new Date()));
		form.setDataEnvio(Util.formatarData(new Date()));

		Collection<Negativador> colecaoNegativador = pesquisarColecaoNegativador();
		form.setCollNegativador(colecaoNegativador);
		sessao.setAttribute("collNegativador", colecaoNegativador);

		String idImovel =  form.getIdImovel();
		
		// Verifica se o id do imovel não está cadastrado
		if (form.getNegativador() != null && !form.getNegativador().trim().equals("")) {
			Collection<NegativadorExclusaoMotivo> colecaoNegativadorExclusaoMotivo = 
					pesquisarColecaoNegativadorExclusaoMotivo(fachada, form);
			form.setCollMotivoExclusao(colecaoNegativadorExclusaoMotivo);
		}

		// Verifica se o id do imovel não está cadastrado
		if (idImovel != null && !idImovel.trim().equals("")) {
			// Filtro para descobrir id do Imovel
			Collection<Imovel> colecaoImovel = pesquisarImovel(fachada, idImovel);

			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				form.setIdImovel(""); 
				form.setInscricaoImovel( "IMOVEL INEXISTENTE" );
				httpServletRequest.setAttribute("existeImovel","exception");
				httpServletRequest.setAttribute("nomeCampo","idImovel");
				limparForm(form);
				limparSessao(sessao);
			}else{
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

				if (!form.getIdImovel().equals(form.getIdImovelAnterior())  || 
						!form.getNegativador().equals(form.getNegativadorAnterior()) ) {

					limparForm(form);
					limparSessao(sessao);

					form.setNegativadorAnterior(form.getNegativador());
					form.setIdImovelAnterior(imovel.getId().toString());
					form.setIdImovel(imovel.getId().toString());
					String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId()); 
					form.setInscricaoImovel(inscricaoImovel);
					httpServletRequest.setAttribute("existeImovel","true");
					httpServletRequest.setAttribute("nomeCampo","idImovel");
	
					// caso tenha passado o negativador
					if (form.getNegativador() != null && !form.getNegativador().equals("")) {
						
						// caso nao tenha negativacao para o negativador selecionado
						Negativador negativador = pesquisarNegativador(fachada, form);
						if (negativador == null) {
							limparSessao(sessao);
							throw new ActionServletException("atencao.negativador.nao.selecionado");
						}
						
						Collection<NegativacaoImovei> colecaoNegativacao = fachada.
								pesquisarImovelNegativado(imovel,negativador);
						
						if (colecaoNegativacao == null || colecaoNegativacao.isEmpty()) {
							String[] parametros = {negativador.getCliente().getNome()};
							limparSessao(sessao);
							throw new ActionServletException("atencao.nao.ha.negativacao.para.imovel.selecionado",null,parametros);
						}
						//..................................................................................................................................						
						//alteração - 05/03/2008 [início]
						//..................................................................................................................................									
						Collection<NegativadorMovimentoReg> coll = fachada.pesquisarNegativadorMovimentoRegInclusao(imovel, negativador);
											     
						if (coll == null || coll.size() == 0) {
							String[] parametros = {negativador.getCliente().getNome()};																
							limparSessao(sessao);
							throw new ActionServletException("atencao.negativacao_para_imovel_sem.retorno_ou_nao_aceita",null,parametros);
						}else {
						// caso tenha carregue
//							NegativadorMovimentoReg negativadorMovimentoReg = fachada.pesquisarNegativadorMovimentoRegInclusao(imovel, negativador);
							//RM6364 - Alteração para negativação por período
							//alterado por Vivianne Sousa - 12/12/2011
							Collection<ExcluirNegativacaoOnLineHelper> colecaoHelper = new ArrayList<ExcluirNegativacaoOnLineHelper>();
							Collection<NegativadorMovimentoReg> colecaoNegativadorMovimentoReg = fachada.
									pesquisarNegativadorMovimentoRegInclusao(imovel, negativador);
							Iterator<NegativadorMovimentoReg> iterReg = colecaoNegativadorMovimentoReg.iterator();
							
							while (iterReg.hasNext()) {
								NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) iterReg.next();
								ExcluirNegativacaoOnLineHelper helper = new ExcluirNegativacaoOnLineHelper();
								
								if (negativadorMovimentoReg == null ) {
									String[] parametros = {negativador.getCliente().getNome()};
									limparSessao(sessao);
									throw new ActionServletException("atencao.nao.ha.negativacao.para.imovel.selecionado",null,parametros);
								} 
								helper.setNegativadorMovimentoReg(negativadorMovimentoReg);
								helper.setCliente(negativadorMovimentoReg.getCliente());
								
								//pesquisar NegativadorMovimentoRegItem de conta
								Collection<NegativadorMovimentoRegItem> itensConta = obterItensConta(fachada, negativadorMovimentoReg);
								helper.setItensConta(itensConta);
								//pesquisar NegativadorMovimentoRegItem de guia de pagamento
								Collection<NegativadorMovimentoRegItem> itensGuiaPagamento = obterItensGuiaPagamento(fachada, negativadorMovimentoReg);
								helper.setItensGuiaPagamento(itensGuiaPagamento);
								
//								FiltroNegativacaoImoveis fnii = new FiltroNegativacaoImoveis();
//								fnii.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.IMOVEL_ID,imovel.getId()));
//								fnii.adicionarParametro(new ParametroSimples(FiltroNegativacaoImoveis.NEGATIVACAO_COMANDO_ID,negativadorMovimentoReg.getNegativadorMovimento().getNegativacaoComando().getId()));
//								NegativacaoImovei ni = (NegativacaoImovei) Util.retonarObjetoDeColecao(fachada.pesquisar(fnii, NegativacaoImovei.class.getName()));
								//RM6364 - Alteração para negativação por período
								//alterado por Vivianne Sousa - 12/12/2011
								NegativacaoImovei ni = fachada.obterNegativacaoImoveis(negativador,
									negativadorMovimentoReg.getNegativadorMovimento(),imovel,negativadorMovimentoReg.getCliente());
								String situacaoNegativacao = "Não Confirmado";
								if (ni != null && ni.getDataConfirmacao() != null) {
									situacaoNegativacao =  "Confirmado";
								} 
								helper.setSituacaoNegativacao(situacaoNegativacao);
								colecaoHelper.add(helper);
								
								//RM6364 - Alteração para negativação por período
								//alterado por Vivianne Sousa - 12/12/2011
//								sessao.setAttribute("negativadorMovimentoReg", negativadorMovimentoReg);
//								sessao.setAttribute("imovel", imovel);
//								sessao.setAttribute("itensConta", itensConta);
//								sessao.setAttribute("itensGuiaPagamento", itensGuiaPagamento);
//								form.setDataEnvio(Util.formatarData(negativadorMovimentoReg.getNegativadorMovimento().getDataEnvio()));
							}
							sessao.setAttribute("imovel", imovel);
							sessao.setAttribute("colecaoHelper", colecaoHelper);
						}

					} else {
						limparSessao(sessao);
						throw new ActionServletException("atencao.negativador.nao.selecionado");
					}
				}	
			}
		} else {
			limparForm(form);
			limparSessao(sessao);
		}

		if (Util.verificarNaoVazio(form.getIdUsuario())) {
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, form.getIdUsuario()));
			Collection<Usuario> coll = Fachada.getInstancia().pesquisar(filtroUsuario,Usuario.class.getName());
			if (coll != null && !coll.isEmpty()) {
				// O usuário foi encontrado
				form.setNomeUsuario(((Usuario) ((List) coll).get(0)).getNomeUsuario());
				sessao.setAttribute("nomeUsuario", form.getNomeUsuario());
				form.setUsuarioNaoEncontrada("false");
			} else {
				form.setUsuarioNaoEncontrada("true");
				form.setNomeUsuario("");
			}
		} else {
			form.setNomeUsuario("");
		}

		form.setDataExclusao(Util.formatarData(new Date()));
		
		return retorno;
	}

	private Collection<Negativador> pesquisarColecaoNegativador() {
		FiltroNegativador fn = new FiltroNegativador();
		fn.adicionarCaminhoParaCarregamentoEntidade("cliente");
		String[] ordem = {"cliente.nome"};
		fn.setCampoOrderBy(ordem);
		Collection<Negativador> colecaoNegativador = Fachada.getInstancia().pesquisar(fn,Negativador.class.getName());
		return colecaoNegativador;
	}

	private Collection<NegativadorExclusaoMotivo> pesquisarColecaoNegativadorExclusaoMotivo(Fachada fachada,
			ExcluirNegativacaoOnLineActionForm form) {
		FiltroNegativadorExclusaoMotivo fnem = new FiltroNegativadorExclusaoMotivo();
		fnem.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.NEGATIVADOR_ID,form.getNegativador()));
		fnem.setCampoOrderBy("codigoExclusaoMotivo");
		Collection<NegativadorExclusaoMotivo> colecaoNegativadorExclusaoMotivo = fachada.pesquisar(fnem, NegativadorExclusaoMotivo.class.getName());
		return colecaoNegativadorExclusaoMotivo;
	}

	private Negativador pesquisarNegativador(Fachada fachada, ExcluirNegativacaoOnLineActionForm form) {
		FiltroNegativador fn;
		fn = new FiltroNegativador();
		fn.adicionarParametro(new ParametroSimples(FiltroNegativador.ID,form.getNegativador()));
		fn.adicionarCaminhoParaCarregamentoEntidade("cliente");
		Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(fachada.pesquisar(fn, Negativador.class.getName()));
		return negativador;
	}

	private Collection<NegativadorMovimentoRegItem> obterItensGuiaPagamento(Fachada fachada,
			NegativadorMovimentoReg negativadorMovimentoReg) {
		FiltroNegativadorMovimentoRegItem fnmri = new FiltroNegativadorMovimentoRegItem();
		fnmri.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,negativadorMovimentoReg.getId()));
		fnmri.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.DOCUMENTO_TIPO_ID,DocumentoTipo.GUIA_PAGAMENTO));
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.CONTA_GERAL_CONTA_HISTORICO);
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.CONTA_GERAL_CONTA);
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO);
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_HISTORICO);
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.COBRANCA_DEBITO_SITUACAO);
		Collection<NegativadorMovimentoRegItem> itensGuiaPagamento = fachada.pesquisar(fnmri, NegativadorMovimentoRegItem.class.getName());
		return itensGuiaPagamento;
	}

	private Collection<NegativadorMovimentoRegItem> obterItensConta(Fachada fachada,
			NegativadorMovimentoReg negativadorMovimentoReg) {
		FiltroNegativadorMovimentoRegItem fnmri = new FiltroNegativadorMovimentoRegItem();
		fnmri.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,negativadorMovimentoReg.getId()));
		fnmri.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.DOCUMENTO_TIPO_ID,DocumentoTipo.CONTA));
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.CONTA_GERAL_CONTA_HISTORICO);
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.CONTA_GERAL_CONTA);
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO);
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_HISTORICO);
		fnmri.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegItem.COBRANCA_DEBITO_SITUACAO);
		Collection<NegativadorMovimentoRegItem> itensConta = fachada.pesquisar(fnmri, NegativadorMovimentoRegItem.class.getName());
		return itensConta;
	}

	private Collection<Imovel> pesquisarImovel(Fachada fachada, String idImovel) {
		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CEP);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.UNIDADE_FEDERACAO);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.ENDERECO_REFERENCIA);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TIPO);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.COBRANCA_SITUACAO);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TITULO);

		Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		return colecaoImovel;
	}

	private void limparForm(ExcluirNegativacaoOnLineActionForm form) {		
		form.setMotivoExclusao("");
		form.setDataExclusao("");
		form.setUsuarioNaoEncontrada("");
		form.setNomeUsuario("");
	}

	private void limparSessao(HttpSession sessao) {
		sessao.setAttribute("negativadorMovimentoReg", null);
		sessao.setAttribute("imovel", null);
		sessao.setAttribute("itensConta", null);
		sessao.setAttribute("itensGuiaPagamento", null);
		sessao.setAttribute("situacaoNegativacao", null);
		sessao.setAttribute("colecaoHelper", null);
	}
}