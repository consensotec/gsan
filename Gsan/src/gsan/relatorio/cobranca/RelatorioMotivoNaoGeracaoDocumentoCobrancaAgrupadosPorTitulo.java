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
* Anderson Italo Felinto de Lima
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
package gsan.relatorio.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaAcaoAtividadeComando;
import gsan.cobranca.CobrancaAcaoAtividadeCronograma;
import gsan.cobranca.FiltroImovelNaoGerado;
import gsan.cobranca.ImovelNaoGerado;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.cobranca.MotivoNaoGeracaoDocumentoActionForm;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesIn;

/**
 * Descri��o da classe
 * Classe respons�vel pelo processamento dos
 * parametros informados e consequente 
 * montagem dos registros exibidos posteriormente
 * pelo relat�rio
 * 
 * @author Anderson Italo
 * @date 26/11/2009
 */
public class RelatorioMotivoNaoGeracaoDocumentoCobrancaAgrupadosPorTitulo extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioMotivoNaoGeracaoDocumentoCobrancaAgrupadosPorTitulo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FILTRAR_DOCUMENTO_COBRANCA);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		 
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		Integer indicadorSintetico =  (Integer)getParametro("sintetico");
		Collection<CobrancaAcaoAtividadeComando> colCobrancaAcaoAtividadeComando = (Collection) getParametro("colCobrancaAcaoAtividadeComando");
		MotivoNaoGeracaoDocumentoActionForm form = (MotivoNaoGeracaoDocumentoActionForm)getParametro("form");
		
		Integer gerencia = null;
		Integer unidade = null;
		Integer localidade = null;
		Integer setorComercial = null;
		Integer quadra = null;
		
		if(form.getGerenciaRegional() != null && !"-1".equals(form.getGerenciaRegional()))
			gerencia = new Integer(form.getGerenciaRegional());
		if(form.getUnidadeNegocio() != null && !"-1".equals(form.getUnidadeNegocio()))
			unidade = new Integer(form.getUnidadeNegocio());
		if(form.getIdLocalidade() != null && !"".equals(form.getIdLocalidade()))
			localidade = new Integer(form.getIdLocalidade());
		if(form.getIdSetorComercial() != null && !"".equals(form.getIdSetorComercial()))
			setorComercial = new Integer(form.getIdSetorComercial());
		if(form.getIdQuadra() != null && !"".equals(form.getIdQuadra()))
			quadra = new Integer(form.getIdQuadra());
		
		if(gerencia != null){
			if(localidade != null){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidade));
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, gerencia));
				
				if(unidade != null){
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_UNIDADE_NEGOCIO, unidade));
				}
				
				Collection colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				
				if(Util.isVazioOrNulo(colLocalidade)){
					throw new ActionServletException("atencao.localidade_invalida");
				}
			}
		}
		
		byte[] retorno = null;
		
		if (indicadorSintetico.intValue() == 1){
				
			Integer totalImoveisComando = 0;
			for(CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando : colCobrancaAcaoAtividadeComando){
				totalImoveisComando += pesquisaTotalImoveisComandoEventual(fachada, cobrancaAcaoAtividadeComando,form);
			}
			
			FiltroImovelNaoGerado filtroImovelNaoGerado = new FiltroImovelNaoGerado();
			filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
			filtroImovelNaoGerado.setCampoOrderBy(FiltroImovelNaoGerado.ID_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
			
			if(gerencia != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.GERENCIA_REGIONAL_ID, gerencia));
			}
			if(unidade != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.UNIDADE_NEGOCIO_ID, unidade));
			}
			if(localidade != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.LOCALIDADE_ID, localidade));
			}
			if(setorComercial != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.SETOR_COMERCIAL_ID, setorComercial));
			}
			if(quadra != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.QUADRA_ID, quadra));
			}
			
			//seta os pametros
			parametros.put("imoveisComando", totalImoveisComando);
			
			Integer totalImoveisGeraramDocumento = fachada.pesquisarQuantidadeImoveisPorGrupoCobrancaAgrupadosTitulo(gerencia, unidade, localidade, setorComercial, quadra);
			
			parametros.put("imoveisGeraramDocumento", totalImoveisGeraramDocumento);
			
			Collection colecaoImovelNaoGerado = fachada.pesquisar(filtroImovelNaoGerado, ImovelNaoGerado.class.getName());
			
			parametros.put("imoveisNaoGeraramDocumento", colecaoImovelNaoGerado.size());
			
			List<RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean> beans = new ArrayList<RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean>();
			
			if (colecaoImovelNaoGerado != null && !colecaoImovelNaoGerado.isEmpty()){
				/*5.5.	Descri��o do Motivo: (a partir da tabela MOTIVO_NAO_GERACAO_DOCUMENTO 
				 * com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do comando)
				 * 8.5.	Descri��o do Motivo: (a partir da tabela MOTIVO_NAO_GERACAO_DOCUMENTO 
				 * com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do comando)*/
				
				RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean();
				int quantidadeImoveisPorMotivo = 0;
				ImovelNaoGerado imovelNaoGeradoAnterior = null;
				
				
				/* 5.6.	Caso tenha sido escolhida a op��o sint�tico:
				 * 8.6.	Caso tenha sido escolhida a op��o sint�tico
				 * 	5.7.1.	Matr�cula dos Im�veis n�o gerados no comando (a 
				 * 	partir da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do Comando)
				 * 	8.7.1.	Matr�cula dos Im�veis n�o gerados no comando (a partir da 
				 * tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do Comando)*/
				for (Iterator colecaoImovelNaoGeradoIterator = colecaoImovelNaoGerado
						.iterator(); colecaoImovelNaoGeradoIterator.hasNext();) {
					ImovelNaoGerado imovelNaoGerado = (ImovelNaoGerado) colecaoImovelNaoGeradoIterator.next();
				
					if (imovelNaoGeradoAnterior != null){
						if (imovelNaoGerado.getMotivoNaoGeracaoDocCobranca().getId().intValue() != imovelNaoGeradoAnterior
								.getMotivoNaoGeracaoDocCobranca().getId().intValue()){
							
							bean.setQuantidadeImoveisPorMotivo(quantidadeImoveisPorMotivo);
							beans.add(bean);
							bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean();
							quantidadeImoveisPorMotivo = 0;
						}
					}
					
					quantidadeImoveisPorMotivo++;
					
					bean.setDescricaoMotivo(imovelNaoGerado.getMotivoNaoGeracaoDocCobranca().getDescricao());
					
					imovelNaoGeradoAnterior = imovelNaoGerado; 
					
					if (!colecaoImovelNaoGeradoIterator.hasNext()){
						bean.setQuantidadeImoveisPorMotivo(quantidadeImoveisPorMotivo);
						beans.add(bean);
					}
				}
			}else{
				RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean();
				beans.add(bean);
			}
			
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA_SINTETICO,
					parametros, new RelatorioDataSource(beans), tipoRelatorio);
			
		}else{
			
			List<RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean> beans = new ArrayList<RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean>();
			
			Integer totalImoveisComando = 0;
			for(CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando : colCobrancaAcaoAtividadeComando){
				totalImoveisComando += pesquisaTotalImoveisComandoEventual(fachada, cobrancaAcaoAtividadeComando,form);
			}
			
			FiltroImovelNaoGerado filtroImovelNaoGerado = new FiltroImovelNaoGerado();
			filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
			filtroImovelNaoGerado.setCampoOrderBy(FiltroImovelNaoGerado.ID_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
			
			if(gerencia != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.GERENCIA_REGIONAL_ID, gerencia));
			}
			if(unidade != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.UNIDADE_NEGOCIO_ID, unidade));
			}
			if(localidade != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.LOCALIDADE_ID, localidade));
			}
			if(setorComercial != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.SETOR_COMERCIAL_ID, setorComercial));
			}
			if(quadra != null){
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.QUADRA_ID, quadra));
			}
			
			//seta os pametros
			parametros.put("imoveisComando", totalImoveisComando);
			
			Integer totalImoveisGeraramDocumento = fachada.pesquisarQuantidadeImoveisPorGrupoCobrancaAgrupadosTitulo(gerencia, unidade, localidade, setorComercial, quadra);
			
			parametros.put("imoveisGeraramDocumento", totalImoveisGeraramDocumento);
				
			Collection colecaoImovelNaoGerado = fachada.pesquisar(filtroImovelNaoGerado, ImovelNaoGerado.class.getName());
			
			parametros.put("imoveisNaoGeraramDocumento", colecaoImovelNaoGerado.size());
			
			if (colecaoImovelNaoGerado != null && !colecaoImovelNaoGerado.isEmpty()){
				/*5.5.	Descri��o do Motivo: (a partir da tabela MOTIVO_NAO_GERACAO_DOCUMENTO 
				 * com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do comando)
				 * 8.5.	Descri��o do Motivo: (a partir da tabela MOTIVO_NAO_GERACAO_DOCUMENTO 
				 * com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do comando)*/
				
				RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean();
				int quantidadeImoveisPorMotivo = 0;
				int i = 0;
				ImovelNaoGerado imovelNaoGeradoAnterior = null;
				
				
				/* 5.6.	Caso tenha sido escolhida a op��o sint�tico:
				 * 8.6.	Caso tenha sido escolhida a op��o sint�tico
				 * 	5.7.1.	Matr�cula dos Im�veis n�o gerados no comando (a 
				 * 	partir da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do Comando)
				 * 	8.7.1.	Matr�cula dos Im�veis n�o gerados no comando (a partir da 
				 * tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do Comando)*/
				for (Iterator colecaoImovelNaoGeradoIterator = colecaoImovelNaoGerado
						.iterator(); colecaoImovelNaoGeradoIterator.hasNext();) {
					ImovelNaoGerado imovelNaoGerado = (ImovelNaoGerado) colecaoImovelNaoGeradoIterator.next();
				
					if (imovelNaoGeradoAnterior != null){
						if (imovelNaoGerado.getMotivoNaoGeracaoDocCobranca().getId().intValue() != imovelNaoGeradoAnterior
								.getMotivoNaoGeracaoDocCobranca().getId()){
							
							bean.setQuantidadeImoveisPorMotivo(quantidadeImoveisPorMotivo);
							beans.add(bean);
							bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean();
							quantidadeImoveisPorMotivo = 0;
						}
					}
					
					quantidadeImoveisPorMotivo++;
					i++;
					
					switch (i) {
					case 1:
						if (bean.getMatricula1() != null){
							bean.setMatricula1(bean.getMatricula1() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula1(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 2:
						if (bean.getMatricula2() != null){
							bean.setMatricula2(bean.getMatricula2() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula2(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 3:
						if (bean.getMatricula3() != null){
							bean.setMatricula3(bean.getMatricula3() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula3(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 4:
						if (bean.getMatricula4() != null){
							bean.setMatricula4(bean.getMatricula4() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula4(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 5:
						if (bean.getMatricula5() != null){
							bean.setMatricula5(bean.getMatricula5() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula5(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 6:
						if (bean.getMatricula6() != null){
							bean.setMatricula6(bean.getMatricula6() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula6(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 7:
						if (bean.getMatricula7() != null){
							bean.setMatricula7(bean.getMatricula7() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula7(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 8:
						if (bean.getMatricula8() != null){
							bean.setMatricula8(bean.getMatricula8() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula8(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 9:
						if (bean.getMatricula9() != null){
							bean.setMatricula9(bean.getMatricula9() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula9(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 10:
						if (bean.getMatricula10() != null){
							bean.setMatricula10(bean.getMatricula10() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula10(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					default:
						break;
					}
					
					bean.setDescricaoMotivo(imovelNaoGerado.getMotivoNaoGeracaoDocCobranca().getDescricao());
					
					imovelNaoGeradoAnterior = imovelNaoGerado; 
					
					if (i == 10){
						i=0;
					}
					
					if (!colecaoImovelNaoGeradoIterator.hasNext()){
						bean.setQuantidadeImoveisPorMotivo(quantidadeImoveisPorMotivo);
						beans.add(bean);
					}
				}
			}else{
				RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean();
				beans.add(bean);
			}
			
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA_ANALITICO,
					parametros, new RelatorioDataSource(beans), tipoRelatorio);
		}
		
		return retorno;
	}

	
	private Integer pesquisaTotalImoveisComandoEventual(Fachada fachada, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, MotivoNaoGeracaoDocumentoActionForm form) {
		
		Integer totalImoveisRetorno = 0;
		if (cobrancaAcaoAtividadeComando.getCliente() != null || cobrancaAcaoAtividadeComando.getSuperior() != null){
			
			//1.1.1.	Caso o Cliente Superior esteja preenchido
			if (cobrancaAcaoAtividadeComando.getSuperior() != null){
				
				/*1.1.1.2.	O sistema seleciona os im�veis para cada um dos clientes 
				 * subordinados e para o pr�prio Cliente Superior(a partir da tabela 
				 * CLIENTE_IMOVEL com CLIE_ID=c�digo de cada cliente subordinado, acrescido 
				 * dos im�veis com CLIE_ID = C�digo do Cliente Superior)*/
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.CLIENTE_RESPONSAVEL_ID, cobrancaAcaoAtividadeComando.getSuperior().getId()));
				
				Collection colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
				Collection idsClientes = new ArrayList();
				idsClientes.add(cobrancaAcaoAtividadeComando.getSuperior().getId());
				
				for (Iterator colecaoClienteIterator = colecaoClientes.iterator(); colecaoClienteIterator
						.hasNext();) {
					Cliente cliente = (Cliente) colecaoClienteIterator.next();
					idsClientes.add(cliente.getId());
				}
				
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimplesIn(
						FiltroClienteImovel.CLIENTE_ID, idsClientes));
				
				Collection colecaoClienteImoveis = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
				totalImoveisRetorno = colecaoClienteImoveis.size();
				
			}
			
			//1.1.2.	Caso o Cliente esteja preenchido
			if (cobrancaAcaoAtividadeComando.getCliente() != null){
				/*1.1.2.1.	O sistema seleciona os im�veis relacionados com o cliente 
				 * informado (a partir da tabela CLIENTE_IMOVEL com CLIE_ID=c�digo do cliente 
				 * e CRTP_ID=id do tipo de rela��o do cliente com o im�vel (se preenchida)*/
				
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_ID, cobrancaAcaoAtividadeComando.getCliente().getId()));
				
				if(cobrancaAcaoAtividadeComando.getClienteRelacaoTipo() != null){
					filtroClienteImovel.adicionarParametro(new ParametroSimples(
							FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, cobrancaAcaoAtividadeComando.getClienteRelacaoTipo().getId()));
				}
				
				Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
				totalImoveisRetorno = colecaoClienteImovel.size();
			}
			
		}else{
			
			/*8.2.	Im�veis no Comando: total de im�veis do Comando Eventual 
			 * [SB0003 - Obter im�veis do Comando Eventual]
			 * 1.2.1.	A partir da lista de rotas do comando ( tabela COBRANCA_ATIVIDADE_COMAND_ROTA 
			 * com CACM_ID = CACM_ID do comando em quest�o)
			 * 1.2.2.	Para cada rota da lista, o sistema: 
			 * 1.2.3.	Seleciona os im�veis das quadras pertencentes � rota( IMOV_ID com IMOV_ICEXCLUSAO = 2 
			 * e QDRA_ID = QDRA_ID da tabela QUDRA com ROTA_ID = ROTA_ID de cada rota da lista de rotas;*/
			
			
			Integer gerencia = null;
			Integer unidade = null;
			Integer localidade = null;
			Integer setorComercial = null;
			Integer quadra = null;
			
			if(form.getGerenciaRegional() != null && !"-1".equals(form.getGerenciaRegional()))
				gerencia = new Integer(form.getGerenciaRegional());
			if(form.getUnidadeNegocio() != null && !"-1".equals(form.getUnidadeNegocio()))
				unidade = new Integer(form.getUnidadeNegocio());
			if(form.getIdLocalidade() != null && !"".equals(form.getIdLocalidade()))
				localidade = new Integer(form.getIdLocalidade());
			if(form.getIdSetorComercial() != null && !"".equals(form.getIdSetorComercial()))
				setorComercial = new Integer(form.getIdSetorComercial());
			if(form.getIdQuadra() != null && !"".equals(form.getIdQuadra()))
				quadra = new Integer(form.getIdQuadra());
				
			
			totalImoveisRetorno = fachada.pesquisarQuantidadeImoveisPorComandoEventual(cobrancaAcaoAtividadeComando.getId(),
					gerencia,unidade,localidade,setorComercial,quadra);
		}
		return totalImoveisRetorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMotivoNaoGeracaoDocumentoCobranca", this);
	}

}
