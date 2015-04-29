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
* Ivan S�rgio Virginio da Silva J�nior
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
package gsan.relatorio.atendimentopublico;

import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.batch.Relatorio;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteFone;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.projeto.FiltroProjeto;
import gsan.cadastro.projeto.Projeto;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.atendimentopublico.ordemservico.ImovelEmissaoOrdensSeletivasActionForm;
import gsan.micromedicao.hidrometro.FiltroHidrometro;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroProtecao;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.micromedicao.medicao.FiltroMedicaoHistorico;
import gsan.micromedicao.medicao.MedicaoHistorico;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.ZipUtil;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 
 * [UC0711] - Emitir Ordem de Servico Seletiva
 * 
 * @author Ivan S�rgio, Raphael Rossiter
 * @date 06/11/2007, 16/04/2009
 */
public class RelatorioEmitirOrdemServicoSeletiva extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioEmitirOrdemServicoSeletiva
	 */
	public RelatorioEmitirOrdemServicoSeletiva(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA);
	}
	
	@Deprecated
	public RelatorioEmitirOrdemServicoSeletiva() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		/*
		 * 				 =====ATEN��O====
		 * 
		 * ALTERA��ES FEITAS NO RELATORIO LEMBRAR DE VERIFICAR 
		 * A NECESSIDADE DE ALTERAR NO OUTRO TIPO DE RELATORIO.
		 * 
		 */
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		ImovelEmissaoOrdensSeletivasHelper helper = gerarObjetoHelper();
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Usuario usuarioLogado = (Usuario)getParametro("usuarioLogado");
		
		Fachada fachada = Fachada.getInstancia();
		
		//Recupera o AnoMesFaturamento de Sistema Parametro
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		// Subtrai 1 mes do ano/mes faturamento para pegar sempre o mes fechado
		String anoMesFaturamento = Util.subtraiAteSeisMesesAnoMesReferencia(
		sistemaParametro.getAnoMesFaturamento(), 1).toString();
		
		//[UC0711] Filtro para Emissao de Ordens Seletivas
		List colecaoDadosRelatorio = (List) fachada.filtrarImovelEmissaoOrdensSeletivas(helper);
		
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		byte[] retorno = null;
		
		// bean do relatorio
		RelatorioEmitirOrdemServicoSeletivaBean relatorioEmitirOrdemServicoSeletivaBean = null;
		
		// dados para o relatorio
		if (colecaoDadosRelatorio != null && !colecaoDadosRelatorio.isEmpty()) {
			
			//adicionado por Vivianne Sousa - 27/06/2011 - analista:Claudio Lira
			//[SB0001]-Gerar Comando. 
			Integer idComandoOrdemSeletiva = fachada.gerarComando(helper,colecaoDadosRelatorio.size(),usuarioLogado);
			
				Integer idEmpresa = null;
				Integer idOrdemServico = null;
				Integer idImovel = null;
				Imovel imovel = null;
				Cliente cliente = null;
				ClienteFone clienteFone = null;
				boolean achou = false;
				Collection colecaoEconomiasCategoria = null;
				Categoria entCategoria = null;
	
				Hidrometro hidrometro = null;
				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
				
				ServicoTipo servTipo = new ServicoTipo();
				int constante = 0;
				
				StringBuilder documentoTxt = new StringBuilder();
				
				if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)) {
					constante = ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO;
				}else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO)){
					constante = ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO;
				}else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_INSPECAO_ANORMALIDADE)){
					constante = ServicoTipo.TIPO_INSPECAO_ANORMALIDADE;
				}else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_SUBSTITUICAO_COM_REMOCAO)){
						constante = ServicoTipo.TIPO_SUBSTITUICAO_COM_REMOCAO;	
				}else {
					constante = ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO;
				}
				servTipo =  pesquisaServicoTipo(constante, fachada);
				
				idEmpresa = Util.converterStringParaInteger(helper.getFirma());
				
				/*************************************************************
				 * Faz o controle da ordem de impressao das p�ginas
				 *************************************************************/
				Integer quantidadePaginas = colecaoDadosRelatorio.size();
				int indiceColuna1 = 0;
				int indiceColuna2 = ((Double) Math.ceil(Double.parseDouble(""+quantidadePaginas) / 2)).intValue(); // +1
				int indice = 0;
				/*************************************************************/
				
				for (int i = 0; i < colecaoDadosRelatorio.size(); i++) {
					/********************************************************
					 * Recupera o Objeto de acordo com o modulo
					 ********************************************************/
					if (i % 2 == 0) {
						// Usa o Indice da Coluna 1
						indice = indiceColuna1;
					}else {
						// Usa o Indice da Coluna 2
						indice = indiceColuna2;
					}
					/********************************************************/
					Integer[] dadosImovel = (Integer[]) colecaoDadosRelatorio.get(indice); 
					
					idImovel = dadosImovel[0];
					Integer idAnormalidade = dadosImovel[1];

					
					String idProjeto = (String) getParametro("idProjeto");
					
					// [UC0430] - Gerar Ordem de Servico
					idOrdemServico = geraOrdemServico(servTipo, 
							idEmpresa, 
							idImovel, 
							idProjeto,
							idComandoOrdemSeletiva,
							idAnormalidade);
					
					/**
					 * Gera o Arquivo TXT das Ordens Seletivas
					 */
					// Numero de Ordem
					documentoTxt.append(Util.completaStringComEspacoAEsquerda(
							Util.adicionarZerosEsquedaNumero(9, idOrdemServico.toString()), 9));
					
					// Data de Emissao
					String dataAtual = Util.formatarData(new Date());
					documentoTxt.append(Util.completaString(dataAtual, 10));
					
					// Data de Validade
					String dataValidade = "";
					Date data = Util.adicionarNumeroDiasDeUmaData(new Date(), 90);
					// Adiciona 0 ao dia se necessario
					if (Util.getDiaMes(data) < 10) {
						dataValidade = "0" + Util.getDiaMes(data) + "/";
					}else {
						dataValidade = Util.getDiaMes(data) + "/";
					}
					
					// Adiciona 0 ao mes se necessario
					if (Util.getMes(data) < 10) {
						dataValidade += "0" + Util.getMes(data) + "/" + Util.getAno(data);
					}else {
						dataValidade += Util.getMes(data) + "/" + Util.getAno(data);
					}
					
					documentoTxt.append(Util.completaString(dataValidade, 10));
					
					// Matricula do Imovel
					documentoTxt.append(Util.completaStringComEspacoAEsquerda(
							Util.adicionarZerosEsquedaNumero(9, idImovel.toString()), 9));
					
					// Inscricao
					//imovel = new Imovel();
					imovel = fachada.pesquisarImovel(idImovel);
					documentoTxt.append(Util.completaString(imovel.getInscricaoFormatada(), 20));
					
					/**
					 *  Dados do Cliente
					 */
					cliente = new Cliente();
					cliente = fachada.consultarClienteUsuarioImovel(imovel);
					
					// Numero Telefone
					String telefone = "";
					if (!cliente.getClienteFones().isEmpty()) {
						Iterator iClienteFone = cliente.getClienteFones().iterator();
						clienteFone = new ClienteFone();
						
						while (iClienteFone.hasNext() & !achou) {
							clienteFone = (ClienteFone) iClienteFone.next();
							
							if (clienteFone.getIndicadorTelefonePadrao().equals(ClienteFone.INDICADOR_FONE_PADRAO)) {
								telefone = clienteFone.getTelefone();
								achou = true;
							}
						}
					}
					documentoTxt.append(Util.completaString(telefone, 9));
					
					// Nome do Cliente
					documentoTxt.append(Util.completaString(cliente.getNome(), 40));
					
					// CPF/CNPJ do Cliente
					String cpfCnpj = "";
					if (cliente.getCpf() != null) {
						cpfCnpj = Util.completaStringComEspacoAEsquerda(cliente.getCpfFormatado(), 19);
					}else if (cliente.getCnpj() != null) {
						cpfCnpj = Util.completaStringComEspacoAEsquerda(cliente.getCnpjFormatado(), 19);
					}else {
						cpfCnpj = Util.completaStringComEspacoAEsquerda("", 19);
					}
					
					documentoTxt.append(cpfCnpj);
					
					// Quantidade de Economias por Categoria
					colecaoEconomiasCategoria = fachada.obterQuantidadeEconomiasCategoria(imovel);
					
					if (colecaoEconomiasCategoria != null && !colecaoEconomiasCategoria.isEmpty()) {
						Iterator icolecaoEconomiasCategoria = colecaoEconomiasCategoria.iterator();
						entCategoria = new Categoria();
						int x = 0;
						
						while ( (icolecaoEconomiasCategoria.hasNext()) || (x < 4)) {
							if (icolecaoEconomiasCategoria.hasNext()) {
								entCategoria = (Categoria) icolecaoEconomiasCategoria.next();
								documentoTxt.append(Util.completaStringComEspacoAEsquerda(
										Util.adicionarZerosEsquedaNumero(4, entCategoria.
												getQuantidadeEconomiasCategoria().toString()), 4));
								
								//quantidadeEconomias += entCategoria.getQuantidadeEconomiasCategoria();
							}else {
								documentoTxt.append("0000");
							}
							
							x++;
						}
						
						//documentoTxt.append(Util.completaStringComEspacoAEsquerda(""+quantidadeEconomias, 4));
						//quantidadeEconomias = 0;
					}else {
						documentoTxt.append("0000000000000000");
					}
					
					// Perfil do Imovel
					documentoTxt.append(Util.completaString(imovel.getImovelPerfil().getDescricao(), 15));
					
					// Endereco Abreviado do Imovel
					String enderecoAbreviado = "";
					enderecoAbreviado = fachada.pesquisarEndereco(idImovel);
					documentoTxt.append(Util.completaString(enderecoAbreviado, 55));
					
					// Situacao de Esgoto
					documentoTxt.append(Util.completaString(imovel.getLigacaoEsgotoSituacao().getDescricao(), 10));
					
					// Consumo Minimo
					String consumoMinimo = "";
					if (imovel.getLigacaoEsgoto() != null) {
						if (imovel.getLigacaoEsgoto().getConsumoMinimo() != null) {
							consumoMinimo = ""+imovel.getLigacaoEsgoto().getConsumoMinimo();
						}
					}
					documentoTxt.append(Util.completaString(
							Util.adicionarZerosEsquedaNumero(5, consumoMinimo), 5));
					
					// Situacao de Agua
					documentoTxt.append(Util.completaString(imovel.getLigacaoAguaSituacao().getDescricao(), 15));
					
					/**
					 * Caso o Tipo de Ordem seja SUBSTITUICAO OU REMOCAO
					 */
					if ( servTipo.getConstanteFuncionalidadeTipoServico().intValue() == 
							ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO ||
							servTipo.getConstanteFuncionalidadeTipoServico().intValue() == 
								ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO ) {
						
						LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
						if (ligacaoAgua.getHidrometroInstalacaoHistorico() != null) {
							//hidrometro = new Hidrometro();
							hidrometro = ligacaoAgua.getHidrometroInstalacaoHistorico().getHidrometro();
							
							Integer idHidrometro = hidrometro.getId();
							
							// Cria o Filtro para Hidrometro
							FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
							filtroHidrometro.adicionarParametro(new ParametroSimples(
									FiltroHidrometro.ID, idHidrometro));
							filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
							filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
							filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroDiametro");
							
							Collection<Hidrometro> colecaoHidrometro = 
								fachada.pesquisar(filtroHidrometro, Hidrometro.class.getName());
							
							Iterator iColecaoHidrometro = colecaoHidrometro.iterator();
							Hidrometro dadosHidrometro = (Hidrometro) iColecaoHidrometro.next();
							
							// Marca Hidrometro
							documentoTxt.append(Util.completaString(dadosHidrometro.getHidrometroMarca().
									getDescricao(), 15));
							
							// Capacidade Hidrometro
							documentoTxt.append(Util.completaString(dadosHidrometro.getHidrometroCapacidade().
									getDescricaoAbreviada(), 6));
							
							// Numero do Hidrometro
							documentoTxt.append(Util.completaString(hidrometro.getNumero(), 10));
							
							// Diametro do Hidrometro
							documentoTxt.append(Util.completaString(dadosHidrometro.getHidrometroDiametro().
									getDescricaoAbreviada(), 5));
							
							// Local de Instalacao do Hidrometro
							hidrometroInstalacaoHistorico = ligacaoAgua.getHidrometroInstalacaoHistorico();
							documentoTxt.append(Util.completaString(hidrometroInstalacaoHistorico.
									getHidrometroLocalInstalacao().getDescricaoAbreviada(), 5));
							
							// Leitura Hidrometro
							FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
							filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
									FiltroMedicaoHistorico.LIGACAO_AGUA_ID, ligacaoAgua.getId()));
							
							if (helper.getTipoMedicao().equals(MedicaoTipo.LIGACAO_AGUA.toString())) {
								filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
										FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.LIGACAO_AGUA));
							}else if (helper.getTipoMedicao().equals(MedicaoTipo.POCO.toString())) {
								filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
										FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.POCO));
							}
							
							filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
									FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO, anoMesFaturamento));
							
							filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
							
							Collection<MedicaoHistorico> colecaoMedicaoHistorico = 
								fachada.pesquisar(filtroMedicaoHistorico, MedicaoHistorico.class.getName());
							
							MedicaoHistorico dadosMedicaoHistorico = null;
							if (colecaoMedicaoHistorico != null && !colecaoMedicaoHistorico.isEmpty()) {
								Iterator iColecaoMedicaoHistorico = colecaoMedicaoHistorico.iterator();
								dadosMedicaoHistorico = (MedicaoHistorico) iColecaoMedicaoHistorico.next();
								
								documentoTxt.append(Util.completaStringComEspacoAEsquerda(
										Util.adicionarZerosEsquedaNumero(7, ""+dadosMedicaoHistorico.
										getLeituraAtualFaturamento()), 7));
							}else {
								documentoTxt.append(Util.completaStringComEspacoAEsquerda(
										Util.adicionarZerosEsquedaNumero(7, ""), 7));
							}
							
							// Data de Instalacao do Hidrometro
							String dataInstalacao = "";
							if (hidrometroInstalacaoHistorico.getDataInstalacao() != null) {
								dataInstalacao = hidrometroInstalacaoHistorico.getDataInstalacao().toString();
								dataInstalacao = dataInstalacao.substring(8, 10) + "/" +
												 dataInstalacao.substring(5, 7) + "/" +
												 dataInstalacao.substring(0, 4);
							}
							documentoTxt.append(Util.completaString(dataInstalacao, 10));
							
							// Protecao Hidrometro
							String protecao = "";
							HidrometroProtecao hidrometroProtecao = 
								hidrometroInstalacaoHistorico.getHidrometroProtecao();
							if (hidrometroProtecao.getId() != null) {
								protecao = hidrometroProtecao.getDescricaoAbreviada();
							}
							documentoTxt.append(Util.completaString(protecao, 6));
							
							// Cavalete Hidrometro
							if (hidrometroInstalacaoHistorico.getIndicadorExistenciaCavalete().
									equals(HidrometroInstalacaoHistorico.INDICADOR_CAVALETE_SIM.shortValue())) {
								documentoTxt.append("SIM");
							}else if (hidrometroInstalacaoHistorico.getIndicadorExistenciaCavalete().
									equals(HidrometroInstalacaoHistorico.INDICADOR_CAVALETE_NAO.shortValue())) {
								documentoTxt.append("NAO");
							}
							
							// Anormalidade
							String descricaoAnormalidade = "";
							if (dadosMedicaoHistorico != null) {
								if (dadosMedicaoHistorico.getLeituraAnormalidadeFaturamento() != null) {
									LeituraAnormalidade leitura = dadosMedicaoHistorico.getLeituraAnormalidadeFaturamento();
									descricaoAnormalidade = leitura.getDescricao();
								}
							}
							documentoTxt.append(Util.completaString(descricaoAnormalidade, 25));
						}
					}
					
					// Nome da Firma
					FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
					filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, helper.getFirma()));
					filtroEmpresa.adicionarParametro(new ParametroSimples(
							FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
					
					Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
					Iterator iColecaoEmpresa = colecaoEmpresa.iterator();
					Empresa empresa = (Empresa) iColecaoEmpresa.next();
					
					documentoTxt.append(Util.completaString(empresa.getDescricaoAbreviada(), 10));
					
					//CRC4641 - adicionado por Vivianne Sousa - 22/06/2010
					//analista: Ana Cristina
					if(sistemaParametro.getCodigoEmpresaFebraban().
							equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)){
						//adiciona codigo da rota e sequ�ncia da rota
						documentoTxt.append(Util.completaStringComEspacoAEsquerda(imovel.getQuadra().getRota().getCodigo().toString(), 5));
						documentoTxt.append(Util.completaStringComEspacoAEsquerda(imovel.getNumeroSequencialRota().toString(), 5));	
					}
					
					// Adiciona o Numero da Pagina
					//documentoTxt.append("|" + (indice + 1) + "/" + quantidadePaginas);
					
					documentoTxt.append(System.getProperty("line.separator"));
					
					// Preenche o BEAN do Relatorio
					relatorioEmitirOrdemServicoSeletivaBean = new RelatorioEmitirOrdemServicoSeletivaBean();
					relatorioEmitirOrdemServicoSeletivaBean.setDescricaoTipoServico(helper.getTipoOrdem());
					relatorioEmitirOrdemServicoSeletivaBean.setCodigoElo(helper.getElo());
					relatorioEmitirOrdemServicoSeletivaBean.setNomeElo(helper.getNomeElo());
					relatorioEmitirOrdemServicoSeletivaBean.setIdLocalidade(helper.getLocalidadeInicial());
					relatorioEmitirOrdemServicoSeletivaBean.setNomeLocalidade(helper.getNomeLocalidadeInicial());
					relatorioEmitirOrdemServicoSeletivaBean.setCodigoSetorComercial(helper.getCodigoSetorComercialInicial());
					relatorioEmitirOrdemServicoSeletivaBean.setNomeSetorComercial(helper.getCodigoSetorComercialInicial());
					relatorioEmitirOrdemServicoSeletivaBean.setQuadra(helper.getQuadraInicial());
					relatorioEmitirOrdemServicoSeletivaBean.setIdFirma(helper.getFirma());
					relatorioEmitirOrdemServicoSeletivaBean.setNomeFirma(helper.getNomeFirma());
					relatorioEmitirOrdemServicoSeletivaBean.setInscricao(imovel.getInscricaoFormatada());
					relatorioEmitirOrdemServicoSeletivaBean.setMatriculaImovel(""+imovel.getId());
					relatorioEmitirOrdemServicoSeletivaBean.setEndereco(enderecoAbreviado);
					relatorioEmitirOrdemServicoSeletivaBean.setNumeroOrdem(""+idOrdemServico);
					
					if(idProjeto!=null && !idProjeto.equals("")){
						FiltroProjeto filtroProjeto = new FiltroProjeto();
						
						filtroProjeto.adicionarParametro(new ParametroSimples(FiltroProjeto.ID,idProjeto));
						
						Collection projetos = fachada.pesquisar(filtroProjeto,Projeto.class.getName());
						
						Projeto projeto = (Projeto) Util.retonarObjetoDeColecao(projetos);
						
						if(projeto!=null){
							relatorioEmitirOrdemServicoSeletivaBean.setNomeProjeto(projeto.getId()+" - "+ projeto.getNome());
						}
					}
					
					relatorioBeans.add(relatorioEmitirOrdemServicoSeletivaBean);
					
					/**
					 * Incrementa o Indice de acordo com o Modulo
					 */
					if (i % 2 == 0) {
						// Incrementa o indice da Coluna 1
						indiceColuna1++;
					}else {
						// Incrementa o indice da Coluna 2
						indiceColuna2++;
					}
				}
				
				// Ordena a Lista de acordo com a inscricao do Imovel
				relatorioBeans = this.ordenaOrdemServicoInscricao(relatorioBeans);
				
				// Gera o Arquivo TXT
				if (documentoTxt != null && documentoTxt.length() > 0) {
					try {
						gerarArquivoTxt(
								documentoTxt, helper.getTipoOrdem(), helper.getElo(),
								helper.getLocalidadeInicial(), helper.getLocalidadeFinal(),
								helper.getCodigoSetorComercialInicial(), helper.getCodigoSetorComercialFinal());
					}catch (Exception e) {
						e.printStackTrace();
						throw new TarefaException("Erro ao gerar arquivo.");
					}
				}
				
				// Par�metros do relat�rio
				Map parametros = new HashMap();
				
				parametros.put("imagem", sistemaParametro.getImagemRelatorio());
				
				RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

				// exporta o relat�rio em pdf e retorna o array de bytes
				retorno = gerarRelatorio(
						ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA,
						parametros, ds, tipoFormatoRelatorio);
				
				// ------------------------------------
				// Grava o relat�rio no sistema
				try {
					persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA,
							idFuncionalidadeIniciada);
				} catch (ControladorException e) {
					e.printStackTrace();
					throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
				}
				// ------------------------------------
				// retorna o relat�rio gerado
		}else {

			throw new ActionServletException("atencao.ordem_servico_ja_emitida");
		
		}
		
			//Par�metros do relat�rio
			Map parametros = new HashMap();
			
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());

			RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

			// exporta o relat�rio em pdf e retorna o array de bytes
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA,
					parametros, ds, tipoFormatoRelatorio);
			
			// ------------------------------------
			// Grava o relat�rio no sistema
			try {
				persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
			}
		
		
		return retorno;
	}

	private ImovelEmissaoOrdensSeletivasHelper gerarObjetoHelper() {
		ImovelEmissaoOrdensSeletivasHelper helper = new ImovelEmissaoOrdensSeletivasHelper();
		
		// PAR�METROS
		helper.setTipoOrdem((String) getParametro("tipoOrdem"));
		helper.setFirma((String) getParametro("firma"));
		helper.setNomeFirma((String) getParametro("nomeFirma"));
		helper.setQuantidadeMaxima((String) getParametro("quantidadeMaxima"));
		helper.setIdImovel((String) getParametro("idImovel"));
		helper.setElo((String) getParametro("elo"));
		helper.setNomeElo((String) getParametro("nomeElo"));
		helper.setGerenciaRegional((String) getParametro("gerenciaRegional"));
		helper.setNomeGerenciaRegional((String) getParametro("nomeGerenciaRegional"));
		helper.setUnidadeNegocio((String) getParametro("unidadeNegocio"));
		helper.setNomeUnidadeNegocio((String) getParametro("nomeUnidadeNegocio"));
		helper.setLocalidadeInicial((String) getParametro("localidadeInicial"));
		helper.setNomeLocalidadeInicial((String) getParametro("nomeLocalidadeInicial"));
		helper.setLocalidadeFinal((String) getParametro("localidadeFinal"));
		helper.setNomeLocalidadeFinal((String) getParametro("nomeLocalidadeFinal"));
		helper.setSetorComercialInicial((String) getParametro("setorComercialInicial"));
		helper.setCodigoSetorComercialInicial((String) getParametro("codigoSetorComercialInicial"));
		helper.setSetorComercialFinal((String) getParametro("setorComercialFinal"));
		helper.setCodigoSetorComercialFinal((String) getParametro("codigoSetorComercialFinal"));
		helper.setQuadraInicial((String) getParametro("idQuadraInicial"));
		helper.setQuadraFinal((String) getParametro("idQuadraFinal"));
		helper.setRotaInicial((String) getParametro("rotaInicial"));
		helper.setRotaFinal((String) getParametro("rotaFinal"));
		helper.setRotaSequenciaInicial((String) getParametro("rotaSequenciaInicial"));
		helper.setRotaSequenciaFinal((String) getParametro("rotaSequenciaFinal"));
		helper.setLogradouro((String) getParametro("logradouro"));
		helper.setDescricaoLogradouro((String) getParametro("descricaoLogradouro"));
		helper.setTipoEmissao((String) getParametro("sugestao"));
		helper.setDescricaoComando((String)getParametro("descricaoComando"));

		// CARACTER�STICAS
		helper.setPerfilImovel((String) getParametro("perfilImovel"));
		helper.setCategoria((String) getParametro("categoria"));
		helper.setSubCategoria((String) getParametro("subCategoria"));
		helper.setQuantidadeEconomiasInicial((String) getParametro("intervaloQuantidadeEconomiasInicial"));
		helper.setQuantidadeEconomiasFinal((String) getParametro("intervaloQuantidadeEconomiasFinal"));
		helper.setQuantidadeDocumentosInicial((String) getParametro("intervaloQuantidadeDocumentosInicial"));
		helper.setQuantidadeDocumentosFinal((String) getParametro("intervaloQuantidadeDocumentosFinal"));
		helper.setNumeroMoradoresInicial((String) getParametro("intervaloNumeroMoradoresInicial"));
		helper.setNumeroMoradoresFinal((String) getParametro("intervaloNumeroMoradoresFinal"));
		helper.setAreaConstruidaInicial((String) getParametro("intervaloAreaConstruidaInicial"));
		helper.setAreaConstruidaFinal((String) getParametro("intervaloAreaConstruidaFinal"));
		helper.setImovelCondominio((String) getParametro("imovelCondominio"));
		helper.setMediaImovel((String) getParametro("mediaImovel"));
		helper.setConsumoPorEconomia((String) getParametro("consumoPorEconomia"));
		helper.setConsumoPorEconomiaFinal((String) getParametro("consumoPorEconomiaFinal"));
		helper.setTipoMedicao((String) getParametro("tipoMedicao"));
		helper.setSituacaoLigacaoAgua((String[])getParametro("situacaoLigacaoAgua"));
		
		// HIDR�METRO
		helper.setCapacidadeHidrometro((String[]) getParametro("capacidade"));
		helper.setMarcaHidrometro((String) getParametro("marca"));
		helper.setAnormalidadeHidrometro((String[]) getParametro("anormalidadeHidrometro"));
		helper.setNumeroOcorrenciasAnormalidade((String) getParametro("numeroOcorrenciasConsecutivas"));
		//helper.setMesAnoInstalacaoInicialHidrometro((String) getParametro("mesAnoInstalacao"));
		helper.setMesAnoInstalacaoInicialHidrometro((String) getParametro("mesAnoInstalacaoInicial"));
		helper.setMesAnoInstalacaoFinalHidrometro((String) getParametro("mesAnoInstalacaoFinal"));
		
		if(getParametro("localInstalacao") != null){
			helper.setLocalInstalacaoHidrometro((String) getParametro("localInstalacao"));
		}
		
		
		return helper;
	}
	
	private Integer geraOrdemServico(ServicoTipo tipoServico, 
		Integer idEmpresa, 
		Integer idImovel, 
		String idProjeto, 
		Integer idComandoOrdemSeletiva,
		Integer idAnormalidadeLeitura) {
		
		OrdemServico ordemServico = null;
		ServicoTipo servicoTipo = null;
		Empresa empresa = null;
		Integer retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		// [UC0430] - Gerar Ordem de Servico
		
		ordemServico = new OrdemServico();
		ordemServico.setServicoTipo(tipoServico);
		
		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		ordemServico.setImovel(imovel);
		
		if(idProjeto!=null && !idProjeto.equals("")){
			Projeto projeto = new Projeto();
			
			Integer id = new Integer(idProjeto);
			
			projeto.setId(id);
			
			ordemServico.setProjeto(projeto);
		}
		
		if(idComandoOrdemSeletiva != null){
			ComandoOrdemSeletiva comandoOrdemSeletiva = new ComandoOrdemSeletiva();
			comandoOrdemSeletiva.setId(idComandoOrdemSeletiva);
			ordemServico.setComandoOrdemSeletiva(comandoOrdemSeletiva);
		}
		
		if(idAnormalidadeLeitura != null){
			LeituraAnormalidade leituraAnormalidade = new LeituraAnormalidade();
			leituraAnormalidade.setId(idAnormalidadeLeitura);
			
			ordemServico.setLeituraAnormalidade(leituraAnormalidade);
		}

		
		// Alterado por Victor Cisneiros - 05/11/2008
		// Verificar se a empresa � a principal
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new TarefaException("atencao.empresa_nao_encontra", idEmpresa.toString());
		}
		
		empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
		
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		
		// Caso a empresa seja principal (indicadorEmpresaPrincipal = 1) 
		// obter a unidade a partir da unidade que representa a localidade do imovel
		if (empresa.getIndicadorEmpresaPrincipal() != null && 
			empresa.getIndicadorEmpresaPrincipal().equals(Empresa.INDICADOR_EMPRESA_PRINCIPAL)) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			Collection pesquisaImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			if (pesquisaImovel == null || pesquisaImovel.isEmpty()) {
				throw new TarefaException("Imovel com id " + idImovel + "nao encontrado");
			}
			
			Imovel im = (Imovel) Util.retonarObjetoDeColecao(pesquisaImovel);
			
			Integer idLocalidade = im.getLocalidade().getId();
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.EMPRESA, idEmpresa));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_LOCALIDADE, idLocalidade));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				
				//[FS0011]-Verificar exist�ncia de mais de uma unidade correspondente � empresa
				if(colecaoUnidadeOrganizacional.size() > 1){
					throw new TarefaException("atencao.unidade_organizacional_dupla_correspondente_empresa");
				}
				
				unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				
			}else {
				String dEmpresa = empresa.getId().toString() + (empresa.getDescricao() != null ? " - " + empresa.getDescricao() : "");
				
				throw new TarefaException("atencao.unidade_organizacional_nao_encontrada_empresa", dEmpresa);
			}
			
		} else {
			
			// Recupera a Unidade Organizacional da Empresa
			unidadeOrganizacional.setEmpresa(empresa);
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.EMPRESA, empresa));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = 
				fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				
				//[FS0011]-Verificar exist�ncia de mais de uma unidade correspondente � empresa
				if(colecaoUnidadeOrganizacional.size() > 1){
					throw new TarefaException("atencao.unidade_organizacional_dupla_correspondente_empresa");
				}
				
				Iterator iColecaoUnidadeOrganizacional = colecaoUnidadeOrganizacional.iterator();
				unidadeOrganizacional = (UnidadeOrganizacional) iColecaoUnidadeOrganizacional.next();
			}else {
				String dEmpresa = empresa.getId().toString() + (empresa.getDescricao() != null ? " - " + empresa.getDescricao() : "");
				
				throw new TarefaException("atencao.unidade_organizacional_nao_encontrada_empresa", dEmpresa);
			}
		}
		
		retorno = fachada.gerarOrdemServicoSeletiva(
				ordemServico, unidadeOrganizacional, imovel, Usuario.USUARIO_BATCH);
		
		return retorno;
	}
	
	private void gerarArquivoTxt(
			StringBuilder txtArquivo,
			String tipoOrdem,
			String idElo,
			String idLocalidadeIncial,
			String idLocalidadeFinal,
			String codigoSetorComercialInicial,
			String codigoSetorComercialFinal) throws IOException, FileNotFoundException, Exception {
		
		if (txtArquivo != null && txtArquivo.length() != 0) {
			String nomeZip = "ORDEM_DE_" + tipoOrdem + "_DE_HIDROMETRO_";
			
			if (idElo != null && !idElo.equals("")) {
				nomeZip += idElo + "_" + Util.formatarDataComHora(new Date()).replace("/", "_").replace(":", "_");
			}else if (idLocalidadeIncial != null && !idLocalidadeIncial.equals("") &&
					idLocalidadeFinal != null && !idLocalidadeFinal.equals("") &&
					codigoSetorComercialInicial != null && !codigoSetorComercialInicial.equals("") &&
					codigoSetorComercialFinal != null && !codigoSetorComercialFinal.equals("")) {
				nomeZip += idLocalidadeIncial + "_" + idLocalidadeFinal + "_" +
						   codigoSetorComercialInicial + "_" + codigoSetorComercialFinal + "_" +
						   Util.formatarDataComHora(new Date()).replace("/", "_").replace(":", "_");
			}else if (idLocalidadeIncial != null && !idLocalidadeIncial.equals("") && 
					idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
				nomeZip += idLocalidadeIncial + "_" + idLocalidadeFinal + "_" +
							Util.formatarDataComHora(new Date()).replace("/", "_").replace(":", "_");
			}else {
				nomeZip += Util.formatarDataComHora(new Date()).replace("/", "_").replace(":", "_");
			}
			nomeZip = nomeZip.replace(" ", "_");
			
			txtArquivo.append("\u0004");
			
			try {
				// criar o arquivo zip
				File compactado = new File(nomeZip + ".zip"); // nomeZip
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(compactado));

				File leitura = new File(nomeZip + ".txt");
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leitura.getAbsolutePath())));
				out.write(txtArquivo.toString());
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				zos.close();
				leitura.delete();
			} catch (IOException e) {
				throw new IOException();
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
	}
	
	/**
	 * Ordena a Ordem de Servico por Inscricao
	 * 
	 * @param listaOrdemServico
	 * @return
	 */
	public List ordenaOrdemServicoInscricao(List listaOrdemServico) {
		Collections.sort((List) listaOrdemServico, new Comparator() {
			public int compare(Object a, Object b) {
				String inscricao1 = ((RelatorioEmitirOrdemServicoSeletivaBean) a).getInscricao().replace(".", "");
				String inscricao2 = ((RelatorioEmitirOrdemServicoSeletivaBean) b).getInscricao().replace(".", "");
				
				if (inscricao1 == null || inscricao1.equals("")) {
					return -1;
				} else {
					return inscricao1.compareTo(inscricao2);
				}
			}
		});
		
		return listaOrdemServico;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada
				.getInstancia()
				.filtrarImovelEmissaoOrdensSeletivasCount(gerarObjetoHelper());
		
		if(retorno==0){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioEmitirOrdemServicoSeletiva", this);
	}
	
	/**
	 * Retorna o servico tipo passando o numero da constante
	 * 
	 * @author Arthur Carvalho
	 * @date 16/04/2010
	 * 
	 * @param numeroConstanteServicoTipo
	 * @param fachada
	 * @return
	 */
	public ServicoTipo pesquisaServicoTipo(int numeroConstanteServicoTipo, Fachada fachada){
		ServicoTipo servicoTipo = null;
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro( new ParametroSimples(
				FiltroServicoTipo.CONSTANTE_FUNCIONALIDADE_TIPO_SERVICO, numeroConstanteServicoTipo) );
		
		Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName() );
		
		servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
		
		return servicoTipo;
		
	}
}
