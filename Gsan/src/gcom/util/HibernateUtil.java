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
package gcom.util;

import gcom.arrecadacao.ArrecadacaoContabilParametros;
import gcom.arrecadacao.ArrecadacaoDadosDiarios;
import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.ArrecadadorMovimentoItem;
import gcom.arrecadacao.BandeiraCartao;
import gcom.arrecadacao.ContratoDemanda;
import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.arrecadacao.DebitoCarteiraMovimento;
import gcom.arrecadacao.DeducaoTipo;
import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.DevolucaoDadosDiarios;
import gcom.arrecadacao.DevolucaoHistorico;
import gcom.arrecadacao.DevolucaoSituacao;
import gcom.arrecadacao.GuiaDevolucao;
import gcom.arrecadacao.MetasArrecadacao;
import gcom.arrecadacao.MovimentoCartaoRejeita;
import gcom.arrecadacao.RecebimentoTipo;
import gcom.arrecadacao.RegistroCodigo;
import gcom.arrecadacao.ResumoArrecadacao;
import gcom.arrecadacao.aviso.AvisoAcerto;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimentoParcelamentoCliente;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoParcelamentoCliente;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoriaHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.arrecadacao.pagamento.GuiaPagamentoItemCategoria;
import gcom.arrecadacao.pagamento.GuiaPagamentoItemCategoriaHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoItemHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoParcelamentoCartao;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoCartaoDebito;
import gcom.arrecadacao.pagamento.PagamentoCartaoDebitoItem;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.arrecadacao.pagamento.SequenciaCartao;
import gcom.atendimentopublico.EspecificacaoPavimentacaoServicoTipo;
import gcom.atendimentopublico.EspecificacaoUnidadeCobranca;
import gcom.atendimentopublico.FiscalizarParametroCalculoDebito;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ResolucaoImagem;
import gcom.atendimentopublico.contratoadesao.ContratoAdesao;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacaoConsumoTipo;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacaoConsumoTipo;
import gcom.atendimentopublico.ordemservico.AnormalidadeComandoOSS;
import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoAcaoVisitaCampo;
import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoClienteFoneVisitaCampo;
import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoClienteVisitaCampo;
import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoVisitaCampo;
import gcom.atendimentopublico.ordemservico.ArquivoTextoVisitaCampo;
import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.BoletimOsConcluida;
import gcom.atendimentopublico.ordemservico.CapacidHidrComandoOSS;
import gcom.atendimentopublico.ordemservico.ClieFoneSeletivaVisitaCampo;
import gcom.atendimentopublico.ordemservico.ClieOsSeletivaVisitaCampo;
import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.ordemservico.CoordenadaPercursoEquipe;
import gcom.atendimentopublico.ordemservico.DataFiscalizacaoOsSeletiva;
import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.EquipeComponentes;
import gcom.atendimentopublico.ordemservico.EquipeEquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipo;
import gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva;
import gcom.atendimentopublico.ordemservico.FiscalizacaoFoto;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoAgua;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoEsgoto;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoHidrometroCapacidade;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoServicoACobrar;
import gcom.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gcom.atendimentopublico.ordemservico.FotoTipo;
import gcom.atendimentopublico.ordemservico.LigacaoSitComandoOSS;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.atendimentopublico.ordemservico.MensagemAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.MotivoRejeicao;
import gcom.atendimentopublico.ordemservico.OSAtividadeExecucaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSAtividadeMaterialProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSAtividadeProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSPriorizacaoTipo;
import gcom.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoAtividade;
import gcom.atendimentopublico.ordemservico.OrdemServicoBoletim;
import gcom.atendimentopublico.ordemservico.OrdemServicoFiscSit;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoMovimentoHistorico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.OrdemServicoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.OsAtividadeMaterialExecucao;
import gcom.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipe;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipeComponentes;
import gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.OsSeletivaVisitaCampo;
import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipoGrupo;
import gcom.atendimentopublico.ordemservico.ServicoTipoMaterial;
import gcom.atendimentopublico.ordemservico.ServicoTipoMotivoEncerramento;
import gcom.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.atendimentopublico.portal.QuestionarioSatisfacaoCliente;
import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno;
import gcom.atendimentopublico.registroatendimento.ArquivoProcedimentoOperacionalPadrao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncAcaoCobranca;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidade;
import gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupo;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacional;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.atendimentopublico.registroatendimento.RAReiteracaoFone;
import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora;
import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladoraFone;
import gcom.atendimentopublico.registroatendimento.RaEncerramentoComando;
import gcom.atendimentopublico.registroatendimento.RaEncerramentoComandoEspecificacoes;
import gcom.atendimentopublico.registroatendimento.RaEnderecoDescritivo;
import gcom.atendimentopublico.registroatendimento.RaMotivoReativacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorio;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.SolicitanteFone;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.VisualizacaoRegistroAtendimentoUrgencia;
import gcom.atualizacaocadastral.AreaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ArquivoTextoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.AtributoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.CepAtlzCadDM;
import gcom.atualizacaocadastral.ClienteAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ClienteFoneAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.DadosFinanceirosAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelFotoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelOcorrenciaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelSubcategoriaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.LogradouroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroBairroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroCepAtlzCadDM;
import gcom.atualizacaocadastral.MapaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.MensagemAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.OrdemServicoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ParametroQuadraAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ParametroTabelaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ResumoDadosFinanceirosAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.RetornoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.SituacaoTransmissaoAtualizacaoCadastralDM;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.FuncionalidadeSituacao;
import gcom.batch.Processo;
import gcom.batch.ProcessoFuncionalidade;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.batch.ProcessoTipo;
import gcom.batch.Relatorio;
import gcom.batch.RelatorioGerado;
import gcom.batch.UnidadeIniciada;
import gcom.batch.UnidadeProcessamento;
import gcom.batch.UnidadeSituacao;
import gcom.batch.auxiliarbatch.CobrancaDocumentoControleGeracao;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.ContaBraile;
import gcom.cadastro.ContaEmpresaSMS;
import gcom.cadastro.CpfTipo;
import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.EmailClienteAlterado;
import gcom.cadastro.EmpresaContratoCadastro;
import gcom.cadastro.EmpresaContratoCadastroAtributo;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.MensagemEmailFaturamentoCobranca;
import gcom.cadastro.MensagemEmailHistorico;
import gcom.cadastro.MensagemRetornoReceitaFederal;
import gcom.cadastro.MensagemSMSFaturamentoCobranca;
import gcom.cadastro.MensagemSMSHistorico;
import gcom.cadastro.MotivoRetiradaCobranca;
import gcom.cadastro.ParametroTabelaAtualizacaoCadastro;
import gcom.cadastro.ParametrosMSGSMSEmail;
import gcom.cadastro.SMSSequenciaEnvio;
import gcom.cadastro.SistemaAndroid;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.VersaoMobile;
import gcom.cadastro.VersaoSistemasAndroid;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCriticaTipo;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteContaAnterior;
import gcom.cadastro.cliente.ClienteContaHistorico;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteGuiaPagamento;
import gcom.cadastro.cliente.ClienteGuiaPagamentoHistorico;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.ClienteVirtual;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.dadocensitario.FonteDadosCensitario;
import gcom.cadastro.dadocensitario.IbgeSetorCensitario;
import gcom.cadastro.dadocensitario.IbgeSetorCensitarioDado;
import gcom.cadastro.dadocensitario.LocalidadeDadosCensitario;
import gcom.cadastro.dadocensitario.MunicipioDadosCensitario;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobranca;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.Despejo;
import gcom.cadastro.imovel.EloAnormalidade;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelCadastroOcorrencia;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelDoacao;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelEloAnormalidade;
import gcom.cadastro.imovel.ImovelEnderecoAnterior;
import gcom.cadastro.imovel.ImovelHistoricoPerfil;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfilCapacidadeHidrometro;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.ImovelSituacao;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSuprimido;
import gcom.cadastro.imovel.ImovelTipoCobertura;
import gcom.cadastro.imovel.ImovelTipoConstrucao;
import gcom.cadastro.imovel.ImovelTipoHabitacao;
import gcom.cadastro.imovel.ImovelTipoPropriedade;
import gcom.cadastro.imovel.ItemMovimentoProgramaEspecial;
import gcom.cadastro.imovel.MovimentoProgramaEspecial;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.imovel.PerfilAlteracaoMotivo;
import gcom.cadastro.imovel.PerfilAlteracaoTipo;
import gcom.cadastro.imovel.PiscinaVolumeFaixa;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.ReservatorioVolumeFaixa;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.VwImovelPrincipalCategoria;
import gcom.cadastro.localidade.AreaTipo;
import gcom.cadastro.localidade.CondicaoAbastecimentoAgua;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.GrauDificuldadeExecucao;
import gcom.cadastro.localidade.GrauIntermitencia;
import gcom.cadastro.localidade.GrauRiscoSegurancaFisica;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.LocalidadeClasse;
import gcom.cadastro.localidade.LocalidadePorte;
import gcom.cadastro.localidade.NivelPressao;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.QuadraPerfil;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.localidade.Zeis;
import gcom.cadastro.projeto.Projeto;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialCartaDebito;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.cobranca.*;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.PrestacaoItemContratoParcelamento;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.TipoRelacao;
import gcom.cobranca.parcelamento.PagamentoCartaoCreditoItem;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoPerfilDebitos;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.cobranca.parcelamento.ParcelamentoTipo;
import gcom.faturamento.ConsumoFaixaCategoria;
import gcom.faturamento.ConsumoFaixaLigacao;
import gcom.faturamento.ConsumoMinimoParametro;
import gcom.faturamento.ContaRevisaoFaixaValor;
import gcom.faturamento.DocumentoNaoEntregue;
import gcom.faturamento.ExtratoQuitacao;
import gcom.faturamento.ExtratoQuitacaoItem;
import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoContabilParametros;
import gcom.faturamento.FaturamentoDados;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoGrupoCanceladoHistorico;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FaturamentoImediatoAjuste;
import gcom.faturamento.FaturamentoSituacaoComando;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FaturamentoTipo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.HistogramaAguaEconomia;
import gcom.faturamento.HistogramaAguaEconomiaSemQuadra;
import gcom.faturamento.HistogramaAguaLigacao;
import gcom.faturamento.HistogramaAguaLigacaoSemQuadra;
import gcom.faturamento.HistogramaEsgotoEconomia;
import gcom.faturamento.HistogramaEsgotoEconomiaSemQuadra;
import gcom.faturamento.HistogramaEsgotoLigacao;
import gcom.faturamento.HistogramaEsgotoLigacaoSemQuadra;
import gcom.faturamento.ImpostoTipo;
import gcom.faturamento.ImpostoTipoAliquota;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.faturamento.MovimentoContaCategoriaConsumoFaixa;
import gcom.faturamento.MovimentoContaImpostoDeduzido;
import gcom.faturamento.MovimentoContaPrefaturada;
import gcom.faturamento.MovimentoContaPrefaturadaCategoria;
import gcom.faturamento.Prescricao;
import gcom.faturamento.QualidadeAgua;
import gcom.faturamento.QualidadeAguaPadrao;
import gcom.faturamento.ResumoFaturamentoSimulacao;
import gcom.faturamento.ResumoFaturamentoSimulacaoCredito;
import gcom.faturamento.ResumoFaturamentoSimulacaoDebito;
import gcom.faturamento.ResumoFaturamentoSituacaoEspecial;
import gcom.faturamento.ResumoFaturamentoSituacaoEspecialDetalhe;
import gcom.faturamento.TarifaTipoCalculo;
import gcom.faturamento.VencimentoAlternativo;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.autoinfracao.AutosInfracaoDebitoACobrar;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixaHistorico;
import gcom.faturamento.conta.ContaCategoriaHistorico;
import gcom.faturamento.conta.ContaComunicado;
import gcom.faturamento.conta.ContaComunicadoFaturamentoGrupo;
import gcom.faturamento.conta.ContaComunicadoQuadra;
import gcom.faturamento.conta.ContaComunicadoRota;
import gcom.faturamento.conta.ContaComunicadoSetor;
import gcom.faturamento.conta.ContaEmissao2Via;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.conta.ContaImpostosDeduzidosHistorico;
import gcom.faturamento.conta.ContaImpressao;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRetificacaoColuna;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.FaturaItemHistorico;
import gcom.faturamento.conta.MotivoNaoEntregaDocumento;
import gcom.faturamento.conta.Refaturamento;
import gcom.faturamento.contratodemanda.ContratoDemandaFaixaConsumo;
import gcom.faturamento.contratodemanda.ContratoDemandaImovel;
import gcom.faturamento.contratodemanda.ContratoDemandaMotivoEncerramento;
import gcom.faturamento.contratodemanda.ContratoDemandaSituacao;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarCategoria;
import gcom.faturamento.credito.CreditoARealizarCategoriaHistorico;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.credito.CreditoARealizarHistorico;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.credito.CreditoRealizadoCategoriaHistorico;
import gcom.faturamento.credito.CreditoRealizadoHistorico;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoACobrarCategoriaHistorico;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoCategoria;
import gcom.faturamento.debito.DebitoCobradoCategoriaHistorico;
import gcom.faturamento.debito.DebitoCobradoHistorico;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoFaixaValore;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.financeiro.ContaAReceberContabil;
import gcom.financeiro.ContaContabil;
import gcom.financeiro.DevedoresDuvidososContabilParametro;
import gcom.financeiro.DocumentosAReceberFaixaResumo;
import gcom.financeiro.DocumentosAReceberResumo;
import gcom.financeiro.FaixaDocumentosAReceber;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.LancamentoResumo;
import gcom.financeiro.LancamentoResumoConta;
import gcom.financeiro.LancamentoResumoContaHistorico;
import gcom.financeiro.LancamentoResumoValorTipo;
import gcom.financeiro.ParametrosDevedoresDuvidosos;
import gcom.financeiro.ParametrosDevedoresDuvidososItem;
import gcom.financeiro.ParametrosPerdasOrgaoPublico;
import gcom.financeiro.ParametrosPerdasSocietarias;
import gcom.financeiro.PerdasTipo;
import gcom.financeiro.ResumoDevedoresDuvidosos;
import gcom.financeiro.ResumoFaturamento;
import gcom.financeiro.ResumoReceita;
import gcom.financeiro.ValorConsumidoNaoFaturadoParametro;
import gcom.financeiro.ValorVolumesConsumidosNaoFaturado;
import gcom.financeiro.lancamento.LancamentoContabil;
import gcom.financeiro.lancamento.LancamentoContabilItem;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.financeiro.lancamento.LancamentoTipo;
import gcom.financeiro.lancamento.LancamentoTipoItem;
import gcom.gerencial.arrecadacao.GArrecadacaoForma;
import gcom.gerencial.arrecadacao.GArrecadador;
import gcom.gerencial.arrecadacao.GDevolucaoSituacao;
import gcom.gerencial.arrecadacao.UnResumoArrecadacao;
import gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto;
import gcom.gerencial.arrecadacao.UnResumoArrecadacaoCredito;
import gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutro;
import gcom.gerencial.arrecadacao.UnResumoArrecadacaoPorAno;
import gcom.gerencial.arrecadacao.pagamento.GEpocaPagamento;
import gcom.gerencial.arrecadacao.pagamento.GPagamentoSituacao;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.atendimentopublico.registroatendimento.GAtendimentoMotivoEncerramento;
import gcom.gerencial.atendimentopublico.registroatendimento.GMeioSolicitacao;
import gcom.gerencial.atendimentopublico.registroatendimento.GSolicitacaoTipo;
import gcom.gerencial.atendimentopublico.registroatendimento.GSolicitacaoTipoEspecificacao;
import gcom.gerencial.atendimentopublico.registroatendimento.UnResumoRegistroAtendimento;
import gcom.gerencial.atendimentopublico.registroatendimento.UnResumoRegistroAtendimentoPorAno;
import gcom.gerencial.cadastro.GEmpresa;
import gcom.gerencial.cadastro.Indicador;
import gcom.gerencial.cadastro.RgResumoLigacaoEconomia;
import gcom.gerencial.cadastro.UnResumoConsumoAgua;
import gcom.gerencial.cadastro.UnResumoIndicadorLigacaoEconomia;
import gcom.gerencial.cadastro.UnResumoLigacaoEconomia;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.geografico.GBairro;
import gcom.gerencial.cadastro.geografico.GMicrorregiao;
import gcom.gerencial.cadastro.geografico.GMunicipio;
import gcom.gerencial.cadastro.geografico.GRegiao;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GLocalidadePorte;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.cadastro.unidade.GUnidadeOrganizacional;
import gcom.gerencial.cobranca.FaixaValor;
import gcom.gerencial.cobranca.GDocumentoTipo;
import gcom.gerencial.cobranca.UnResumoIndicadoresCobranca;
import gcom.gerencial.cobranca.UnResumoParcelamento;
import gcom.gerencial.cobranca.UnResumoParcelamentoPorAno;
import gcom.gerencial.cobranca.UnResumoPendencia;
import gcom.gerencial.cobranca.UnResumoPendenciaSemQuadra;
import gcom.gerencial.faturamento.GConsumoTarifa;
import gcom.gerencial.faturamento.GFaturamentoGrupo;
import gcom.gerencial.faturamento.GImpostoTipo;
import gcom.gerencial.faturamento.UnResumoFaturamento;
import gcom.gerencial.faturamento.UnResumoIndicadoresFaturamento;
import gcom.gerencial.faturamento.UnResumoRefaturamento;
import gcom.gerencial.faturamento.credito.GCreditoOrigem;
import gcom.gerencial.faturamento.credito.GCreditoTipo;
import gcom.gerencial.faturamento.debito.GDebitoTipo;
import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItem;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import gcom.gerencial.micromedicao.GRota;
import gcom.gerencial.micromedicao.UnResumoColetaEsgoto;
import gcom.gerencial.micromedicao.UnResumoHidrometro;
import gcom.gerencial.micromedicao.UnResumoIndicadorDesempenhoMicromedicao;
import gcom.gerencial.micromedicao.UnResumoIndicadorDesempenhoMicromedicaoRef2010;
import gcom.gerencial.micromedicao.UnResumoInstalacaoHidrometro;
import gcom.gerencial.micromedicao.UnResumoInstalacaoHidrometroPorAno;
import gcom.gerencial.micromedicao.UnResumoLeituraAnormalidade;
import gcom.gerencial.micromedicao.UnResumoMeta;
import gcom.gerencial.micromedicao.UnResumoMetasAcumulado;
import gcom.gerencial.micromedicao.consumo.GConsumoTipo;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroCapacidade;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroClasseMetrologica;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroDiametro;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroLocalArmazenagem;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMarca;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMotivoBaixa;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroSituacao;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroTipo;
import gcom.gerencial.micromedicao.leitura.GLeituraSituacao;
import gcom.gerencial.micromedicao.medicao.GMedicaoTipo;
import gcom.gerencial.operacional.GDistritoOperacional;
import gcom.integracao.ServicoTerceiroAcompanhamentoServico;
import gcom.interceptor.Interceptador;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.ConsumoMinimoArea;
import gcom.micromedicao.ContratoEmpresaAditivo;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.ImovelTestesMedicaoConsumo;
import gcom.micromedicao.ItemContratoServicoTipo;
import gcom.micromedicao.ItemServico;
import gcom.micromedicao.ItemServicoContrato;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.MovimentoRoteiroEmpresa;
import gcom.micromedicao.MovimentoRoteiroEmpresaFoto;
import gcom.micromedicao.RateioTipo;
import gcom.micromedicao.ReleituraMobile;
import gcom.micromedicao.ResumoAnormalidadeLeitura;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RotaAtualizacaoSeq;
import gcom.micromedicao.RoteiroEmpresa;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.TelemetriaLog;
import gcom.micromedicao.TelemetriaLogErro;
import gcom.micromedicao.TelemetriaMov;
import gcom.micromedicao.TelemetriaMovReg;
import gcom.micromedicao.TelemetriaRetMot;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoHistoricoAnterior;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.consumo.ResumoAnormalidadeConsumo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroClassePressao;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroFaixaIdade;
import gcom.micromedicao.hidrometro.HidrometroFatorCorrecao;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroMotivoBaixa;
import gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentado;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraFaixaFalsa;
import gcom.micromedicao.leitura.LeituraFiscalizacao;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistoricoAnterior;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.mobile.execucaoordemservico.ArquivoTextoOSCobranca;
import gcom.mobile.execucaoordemservico.ArquivoTextoOSCobrancaCliente;
import gcom.mobile.execucaoordemservico.ArquivoTextoOSCobrancaItem;
import gcom.mobile.execucaoordemservico.ExecucaoOSCliente;
import gcom.mobile.execucaoordemservico.ExecucaoOSCorte;
import gcom.mobile.execucaoordemservico.ExecucaoOSFiscalizacao;
import gcom.mobile.execucaoordemservico.ExecucaoOSFoto;
import gcom.mobile.execucaoordemservico.ExecucaoOSOrdemServico;
import gcom.mobile.execucaoordemservico.ExecucaoOSSituacoesEncontradas;
import gcom.mobile.execucaoordemservico.ExecucaoOSVisita;
import gcom.mobile.execucaoordemservico.ParametrosArquivoTextoOSCobranca;
import gcom.mobile.execucaoordemservico.ParametrosArquivoTextoOSLocalidade;
import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.ProducaoAgua;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SetorFonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SistemaEsgotoTratamentoTipo;
import gcom.operacional.TipoCaptacao;
import gcom.operacional.ZonaAbastecimento;
import gcom.operacional.ZonaPressao;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
import gcom.seguranca.Atributo;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.ConsultarReceitaFederal;
import gcom.seguranca.FuncionalidadeAtributo;
import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.FuncionalidadeDependencia;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.GrupoAcesso;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.GrupoPermissaoEspecial;
import gcom.seguranca.acesso.Modulo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.OperacaoOrdemExibicao;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAlteracao;
import gcom.seguranca.acesso.usuario.UsuarioBanco;
import gcom.seguranca.acesso.usuario.UsuarioFavorito;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioGrupoRestricao;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.seguranca.acesso.usuario.UsuarioSenhaHistorico;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.seguranca.parametrosistema.ParametroTipo;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.SgbdTabela;
import gcom.seguranca.transacao.SgbdTabelaColuna;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastralSituacao;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaLinhaAlteracao;
import gcom.seguranca.transacao.TabelaLinhaColunaAlteracao;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.EntityKey;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.RootClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Classe respons�vel pela instancia��o do Hibernate e servi�os espec�ficos da
 * tecnologia
 * 
 * @author rodrigo
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private static SessionFactory sessionFactoryGerencial;
	
	private static SessionFactory sessionFactoryIntegracaoSAM;

	private static SessionFactory sessionFactoryIntegracaoIFS;

	private static SessionFactory sessionFactoryPentaho;
	
	private static Configuration configuration;
	
	private static Configuration configurationUser;

	private static Configuration configurationGerencial;
	
	private static Configuration configurationIFS;
	
	private static Configuration configurationPentaho;
		
	private static HashMap<Integer, Long> tempoSession = new HashMap<Integer, Long>();
	
	public static final Logger log;

	static { 
	      log = Logger.getLogger("GSAN_ENTIDADES_CONSULTAS");
	      log.debug(";ClasseChamadaN2;MetodoChamadaN2;ClasseChamadaN1;MetodoChamadaN1;NomeEntidade;QtdEntidadesConsultadas;TempoConsulta;OutrasEntidadesConsultadas");
	}

	public static void inicializarSessionFactoryIFS() {
		configurationIFS = new Configuration();
		configurationIFS.setProperty("hibernate.connection.datasource","java:/OracleIFSDS");

		sessionFactoryIntegracaoIFS = configurationIFS.buildSessionFactory();
	}

	public static void inicializarSessionFactoryPentaho() throws ServiceLocatorException {
		if (ServiceLocator.getResource("java:/PentahoDS") == null) {
			return;
		}

		configurationPentaho = new Configuration();
		configurationPentaho.setProperty("hibernate.connection.datasource","java:/PentahoDS");
		configurationPentaho.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");

		configurationPentaho.addClass(DadosFinanceirosAtualizacaoCadastralDM.class)
			.addClass(ResumoDadosFinanceirosAtualizacaoCadastralDM.class);

		sessionFactoryPentaho = configurationPentaho.buildSessionFactory();
	}
	
	public static void inicializarSessionFactory() {

		try {
			configuration = new Configuration();
			configurationGerencial = new Configuration();
			
			verificaBaseDadosTipo(); 
			
			configurationGerencial.addClass(UnResumoArrecadacao.class).
				addClass(UnResumoArrecadacaoAguaEsgoto.class).
				addClass(UnResumoArrecadacaoCredito.class).
				addClass(UnResumoArrecadacaoOutro.class).
				addClass(UnResumoFaturamento.class).
				addClass(UnResumoColetaEsgoto.class).
				addClass(UnResumoConsumoAgua.class).
				addClass(UnResumoLigacaoEconomia.class).
				addClass(RgResumoLigacaoEconomia.class).
				addClass(GEsferaPoder.class).
				addClass(GClienteTipo.class).
				addClass(GCategoria.class).
				addClass(GSubcategoria.class).
				addClass(GRegiao.class).
				addClass(GMicrorregiao.class).
				addClass(GMunicipio.class).
				addClass(GBairro.class).
				addClass(GEpocaPagamento.class).
				addClass(GDocumentoTipo.class).
				addClass(GLocalidade.class).
				addClass(GLocalidadePorte.class).
				addClass(GLigacaoAguaPerfil.class).
				addClass(GLigacaoAguaSituacao.class).
				addClass(GLigacaoEsgotoPerfil.class).
				addClass(GLigacaoEsgotoSituacao.class).
				addClass(GRota.class).
				addClass(GPagamentoSituacao.class).
				addClass(GGerenciaRegional.class).
				addClass(GUnidadeNegocio.class).
				addClass(GSetorComercial.class).
				addClass(GQuadra.class).
				addClass(GImovelPerfil.class).
				addClass(GConsumoTipo.class).
				addClass(GLancamentoItem.class).
				addClass(GLancamentoItemContabil.class).
				addClass(GCreditoOrigem.class).
				addClass(GFinanciamentoTipo.class).
				addClass(UnResumoRegistroAtendimento.class).
				addClass(UnResumoInstalacaoHidrometro.class).
				addClass(GMeioSolicitacao.class).
				addClass(GSolicitacaoTipo.class).
				addClass(GSolicitacaoTipoEspecificacao.class).
				addClass(UnResumoParcelamento.class).
				addClass(UnResumoLeituraAnormalidade.class).
				addClass(UnResumoHidrometro.class).
				addClass(GMedicaoTipo.class).
				addClass(GHidrometroMarca.class).
				addClass(GHidrometroCapacidade.class).
				addClass(GHidrometroDiametro.class).
				addClass(GHidrometroLocalArmazenagem.class).
				addClass(GHidrometroSituacao.class).
				addClass(GHidrometroTipo.class).
				addClass(UnResumoRefaturamento.class).
				addClass(GEmpresa.class).
				addClass(GLeituraSituacao.class).
				addClass(GArrecadacaoForma.class).
				addClass(GArrecadador.class).
				addClass(UnResumoMeta.class).
				addClass(UnResumoMetasAcumulado.class).
				addClass(GDebitoTipo.class).
				addClass(GCreditoTipo.class).
				addClass(GImpostoTipo.class).
				addClass(Indicador.class).
				addClass(GConsumoTarifa.class).
				addClass(GHidrometroClasseMetrologica.class).
				addClass(GHidrometroMotivoBaixa.class).
				addClass(GFaturamentoGrupo.class).
				addClass(GUnidadeOrganizacional.class).
				addClass(FaixaValor.class).
				addClass(UnResumoIndicadoresFaturamento.class).
				addClass(UnResumoIndicadorLigacaoEconomia.class).
				addClass(UnResumoPendencia.class).
				addClass(UnResumoPendenciaSemQuadra.class).
				addClass(UnResumoIndicadorDesempenhoMicromedicao.class).
				addClass(GDevolucaoSituacao.class).
				addClass(GAtendimentoMotivoEncerramento.class).
				addClass(GDistritoOperacional.class).
				addClass(UnResumoIndicadoresCobranca.class).
				addClass(UnResumoIndicadorDesempenhoMicromedicaoRef2010.class).
				addClass(UnResumoArrecadacaoPorAno.class).
				addClass(UnResumoRegistroAtendimentoPorAno.class).
				addClass(UnResumoInstalacaoHidrometroPorAno.class).
				addClass(UnResumoParcelamentoPorAno.class);
			
			/*.addClass(GArrecadacaoForma.class).addClass(GArrecadador.class).addClass(UnResumoMeta.class).addClass(UnResumoMetasAcumulado.class)
			 .addClass(GCreditoTipo.class).addClass(GDebitoTipo.class);*/

			sessionFactoryGerencial = configurationGerencial
					.buildSessionFactory();

			//-------------------Configura��o do servidor Gerencial------------------//	
		

			configuration
			// **********************************************/
					// CLASSES DO PACOTE gcom.atendimentopublico //
					// ********************************************//
					// gcom.atendimentopublico.ligacaoagua
					.addClass(CorteTipo.class).addClass(
							EmissaoOrdemCobrancaTipo.class).addClass(
							LigacaoAgua.class).addClass(
							LigacaoAguaDiametro.class).addClass(
							LigacaoAguaMaterial.class).addClass(
							LigacaoAguaPerfil.class).addClass(
							LigacaoAguaSituacao.class).addClass(
							SupressaoTipo.class)
							
					//gvom.atendimentopublico.contratoadesao
							.addClass(ContratoAdesao.class)							
							
					// gcom.atendimentopublico.ligacaoesgoto
					.addClass(LigacaoEsgoto.class).addClass(
							LigacaoEsgotoDiametro.class).addClass(
							LigacaoEsgotoMaterial.class).addClass(
							LigacaoEsgotoPerfil.class).addClass(
							LigacaoEsgotoSituacao.class)
					// gcom.atendimentopublico.registroatendimento
					.addClass(RegistroAtendimento.class).addClass(
							AgenciaReguladoraMotReclamacao.class).addClass(
							AgenciaReguladoraMotRetorno.class).addClass(
							AtendimentoMotivoEncerramento.class).addClass(
							AtendimentoRelacaoTipo.class).addClass(
							LocalOcorrencia.class).addClass(
							MeioSolicitacao.class).addClass(
							RaDadosAgenciaReguladora.class).addClass(
							RaDadosAgenciaReguladoraFone.class).addClass(
							RaEnderecoDescritivo.class).addClass(
							RaMotivoReativacao.class).addClass(
							RegistroAtendimentoSolicitante.class).addClass(
							RegistroAtendimentoUnidade.class).addClass(
							SolicitacaoTipo.class).addClass(
							SolicitacaoTipoEspecificacao.class).addClass(
							SolicitacaoTipoGrupo.class).addClass(
							SolicitanteFone.class).addClass(Tramite.class)
					.addClass(EspecificacaoImovelSituacao.class).addClass(
							EspecificacaoImovSitCriterio.class).addClass(
							EspecificacaoTipoValidacao.class)
				    .addClass(RaEncerramentoComando.class)
				    .addClass(RaEncerramentoComandoEspecificacoes.class)
				    .addClass(RegistroAtendimentoAnexo.class)
				    .addClass(SolicitacaoDocumentoObrigatorio.class)
				    .addClass(LocalidadeEspecificacaoUnidade.class)
				    .addClass(RegistroAtendimentoConta.class)
				    .addClass(RegistroAtendimentoPagamentoDuplicidade.class)
				    .addClass(AtendimentoMotivoEncAcaoCobranca.class)
				   
					// gcom.atendimentopublico.ordemservico
					.addClass(OrdemServicoMovimento.class).addClass(OrdemServicoMovimentoHistorico.class).addClass(OrdemServico.class).addClass(ServicoTipo.class)
					.addClass(SupressaoMotivo.class).addClass(Atividade.class)
					.addClass(Equipe.class).addClass(
							EquipamentosEspeciais.class).addClass(
							EquipeComponentes.class).addClass(
							EspecificacaoServicoTipo.class).addClass(
							FiscalizacaoColetiva.class)
					.addClass(Material.class).addClass(MaterialUnidade.class)
					.addClass(OrdemServicoAtividade.class).addClass(
							OrdemServicoProgramacao.class).addClass(
							OrdemServicoUnidade.class).addClass(
							OsAtividadeMaterialExecucao.class).addClass(
							OsAtividadePeriodoExecucao.class).addClass(
							OsExecucaoEquipe.class).addClass(
							OsExecucaoEquipeComponentes.class).addClass(
							OsProgramNaoEncerMotivo.class).addClass(
							OsReferidaRetornoTipo.class).addClass(
							ProgramacaoRoteiro.class).addClass(
							ServicoCobrancaValor.class).addClass(
							ServicoNaoCobrancaMotivo.class).addClass(
							ServicoPerfilTipo.class).addClass(
							ServicoTipoAtividade.class).addClass(
							ServicoTipoGrupo.class).addClass(
							ServicoTipoMaterial.class).addClass(
							ServicoTipoOperacao.class).addClass(
							ServicoTipoPrioridade.class).addClass(
							ServicoTipoReferencia.class).addClass(
							ServicoTipoSubgrupo.class).addClass(
							LocalidadeSolicTipoGrupo.class).addClass(
							FiscalizacaoSituacao.class).addClass(
							FiscalizacaoSituacaoAgua.class).addClass(
							FiscalizacaoSituacaoEsgoto.class).addClass(
							FiscalizacaoSituacaoHidrometroCapacidade.class)
					.addClass(FiscalizacaoSituacaoServicoACobrar.class)
					.addClass(OrdemServicoPavimento.class)
					.addClass(BoletimOsConcluida.class)
					.addClass(DataFiscalizacaoOsSeletiva.class)
					.addClass(LigacaoOrigem.class)
					.addClass(VisualizacaoRegistroAtendimentoUrgencia.class)
					.addClass(OrdemServicoFiscSit.class)
					.addClass(MotivoRejeicao.class)
					.addClass(ServicoTipoBoletim.class)
					.addClass(OrdemServicoBoletim.class)
					.addClass(ContaBraile.class)
					.addClass(EspecificacaoPavimentacaoServicoTipo.class)
					.addClass(EspecificacaoUnidadeCobranca.class)
					.addClass(RAReiteracao.class)
					.addClass(RAReiteracaoFone.class)
					.addClass(OSProgramacaoCalibragem.class)
					.addClass(OSPriorizacaoTipo.class).addClass(EquipeEquipamentosEspeciais.class)
					.addClass(ArquivoTextoAcompanhamentoServico.class)
					.addClass(OSAtividadeExecucaoAcompanhamentoServico.class)
					.addClass(OSAtividadeMaterialProgramacaoAcompanhamentoServico.class)
					.addClass(OrdemServicoSituacao.class)
					.addClass(OSAtividadeProgramacaoAcompanhamentoServico.class)
					.addClass(OSProgramacaoAcompanhamentoServico.class)
					.addClass(ServicoTipoMotivoEncerramento.class)
					.addClass(OsSeletivaVisitaCampo.class)
					.addClass(ArquivoTextoVisitaCampo.class)
					.addClass(ClieOsSeletivaVisitaCampo.class)
					.addClass(ClieFoneSeletivaVisitaCampo.class)
					.addClass(OrdemServicoFoto.class)
					.addClass(FotoSituacaoOrdemServico.class)
					.addClass(MensagemAcompanhamentoServico.class)
					.addClass(CoordenadaPercursoEquipe.class)
					.addClass(ArquivoTextoRetornoVisitaCampo.class)
					.addClass(ArquivoTextoRetornoAcaoVisitaCampo.class)
					.addClass(ArquivoTextoRetornoClienteVisitaCampo.class)
					.addClass(ArquivoTextoRetornoClienteFoneVisitaCampo.class)
					.addClass(ArquivoProcedimentoOperacionalPadrao.class)
					.addClass(FiscalizarParametroCalculoDebito.class)
					.addClass(FotoTipo.class)
					.addClass(FiscalizacaoFoto.class)
					.addClass(OcorrenciaOperacionalTipo.class)
					.addClass(OcorrenciaOperacionalMotivo.class)
					.addClass(OcorrenciaOperacional.class)
					.addClass(ResolucaoImagem.class)
					// *************************************//
					// CLASSES DO PACOTE gcom.cadastro //
					// *************************************//
					.addClass(VersaoMobile.class)
					.addClass(VersaoSistemasAndroid.class)
					.addClass(SistemaAndroid.class)
					.addClass(ParametrosMSGSMSEmail.class)
					.addClass(MensagemSMSFaturamentoCobranca.class)
					.addClass(MensagemEmailFaturamentoCobranca.class)
					.addClass(MensagemEmailHistorico.class)
					.addClass(SMSSequenciaEnvio.class)
					.addClass(MensagemSMSHistorico.class)
					.addClass(ContaEmpresaSMS.class)
					// gcom.cadastro.cliente
					.addClass(CpfTipo.class)
					.addClass(Cliente.class)
					.addClass(ClienteEndereco.class)
					.addClass(ImovelCadastroOcorrencia.class)
					.addClass(ImovelEloAnormalidade.class)
					.addClass(ClienteFone.class)
					.addClass(ClienteImovel.class)
					.addClass(ClienteRelacaoTipo.class)
					.addClass(ClienteImovelEconomia.class)
					.addClass(ClienteImovelFimRelacaoMotivo.class)
					.addClass(ClienteTipo.class)
					.addClass(FoneTipo.class)
					.addClass(ClienteConta.class)
					.addClass(ClienteContaHistorico.class)
					.addClass(ClienteContaAnterior.class)
					.addClass(OrgaoExpedidorRg.class)
					.addClass(PessoaSexo.class)
					.addClass(Profissao.class)
					.addClass(RamoAtividade.class)
					.addClass(EsferaPoder.class)
					.addClass(ClienteGuiaPagamento.class)
					.addClass(ClienteGuiaPagamentoHistorico.class)
					.addClass(SituacaoAtualizacaoCadastral.class)
					.addClass(ClienteAtualizacaoCadastral.class)
					.addClass(ClienteFoneAtualizacaoCadastral.class)
					// gcom.cadastro.dadocensitario
					.addClass(LocalidadeDadosCensitario.class).addClass(
							MunicipioDadosCensitario.class).addClass(
							IbgeSetorCensitarioDado.class).addClass(
							FonteDadosCensitario.class).addClass(
							IbgeSetorCensitario.class)
					// gcom.cadastro.empresa
					.addClass(Empresa.class)
					.addClass(EmpresaCobranca.class)
					.addClass(EmpresaCobrancaFaixa.class)
					// gcom.cadastro.endereco
					.addClass(LogradouroCep.class).addClass(Cep.class)
					.addClass(CepTipo.class).addClass(EnderecoReferencia.class)
					.addClass(EnderecoTipo.class).addClass(Logradouro.class)
					.addClass(LogradouroBairro.class).addClass(
							LogradouroTipo.class).addClass(
							LogradouroTitulo.class)
					// gcom.cadastro.funcionario
					.addClass(Funcionario.class).addClass(FuncionarioCargo.class)
					// gcom.cadastro.geografico
					.addClass(Bairro.class).addClass(Microrregiao.class)
					.addClass(Municipio.class).addClass(MunicipioFeriado.class)
					.addClass(Regiao.class).addClass(
							RegiaoDesenvolvimento.class).addClass(
							UnidadeFederacao.class).addClass(BairroArea.class)
					// gcom.cadastro.imovel
					.addClass(AreaConstruidaFaixa.class).addClass(
							CadastroOcorrencia.class).addClass(
							CategoriaTipo.class).addClass(Categoria.class)
					.addClass(Despejo.class).addClass(EloAnormalidade.class)
					.addClass(FonteAbastecimento.class).addClass(Imovel.class)
					.addClass(ImovelCobrancaSituacao.class).addClass(
							ImovelEconomia.class).addClass(
							ImovelEnderecoAnterior.class).addClass(
							ImovelPerfil.class).addClass(
							ImovelSubcategoria.class).addClass(ImovelRamoAtividade.class).addClass(
							PavimentoRua.class)
					.addClass(PavimentoCalcada.class).addClass(
							PiscinaVolumeFaixa.class).addClass(PocoTipo.class)
					.addClass(ReservatorioVolumeFaixa.class).addClass(
							Subcategoria.class)
					.addClass(ImovelContaEnvio.class).addClass(
							ImovelDoacao.class).addClass(
							EntidadeBeneficente.class).addClass(
							ImovelTipoHabitacao.class).addClass(
							ImovelTipoPropriedade.class).addClass(
							ImovelTipoConstrucao.class).addClass(
							ImovelTipoCobertura.class)
							.addClass(ImovelAtualizacaoCadastral.class)
							.addClass(ImovelSubcategoriaAtualizacaoCadastral.class)
							.addClass(ImovelProgramaEspecial.class)
							.addClass(ImovelSuprimido.class)
							.addClass(ImovelInscricaoAlterada.class)
							.addClass(MovimentoProgramaEspecial.class)
							.addClass(ItemMovimentoProgramaEspecial.class)
							.addClass(ImovelPerfilCapacidadeHidrometro.class)
							.addClass(ImovelHistoricoPerfil.class)
							.addClass(PerfilAlteracaoTipo.class)
							.addClass(PerfilAlteracaoMotivo.class)
					// gcom.cadastro.localidade
					.addClass(GerenciaRegional.class)
					.addClass(Localidade.class).addClass(LocalidadePorte.class)
					.addClass(LocalidadeClasse.class).addClass(Quadra.class)
					.addClass(QuadraPerfil.class)
					.addClass(SetorComercial.class).addClass(Zeis.class)
					.addClass(AreaTipo.class).addClass(UnidadeNegocio.class)
					.addClass(QuadraFace.class)
					// gcom.cadastro.sistemaparametro
					.addClass(NacionalFeriado.class).addClass(
							SistemaParametro.class).addClass(
							SistemaAlteracaoHistorico.class)
					// gcom.cadastro.tarifasocial
					.addClass(RendaTipo.class).addClass(
							TarifaSocialCartaoTipo.class).addClass(
							TarifaSocialExclusaoMotivo.class).addClass(
							TarifaSocialDadoEconomia.class).addClass(
							TarifaSocialRevisaoMotivo.class)
					// gcom.cadastro.unidade
					.addClass(UnidadeOrganizacional.class).addClass(
							UnidadeTipo.class)
					.addClass(ArquivoTextoAtualizacaoCadastral.class)
					
					.addClass(EmpresaContratoCadastro.class)
					.addClass(EmpresaContratoCadastroAtributo.class)
					.addClass(UnidadeOrganizacionalMunicipio.class)
					// gcom.cadastro.atualizacaocadastralsimplificado
					.addClass(AtualizacaoCadastralSimplificado.class)
					.addClass(AtualizacaoCadastralSimplificadoCritica.class)
					.addClass(AtualizacaoCadastralSimplificadoBinario.class)
					.addClass(AtualizacaoCadastralSimplificadoCriticaTipo.class)
					.addClass(AtualizacaoCadastralSimplificadoLinha.class)
					// gcom.cadastro.projeto
					.addClass(Projeto.class)
					.addClass(EmpresaContratoCobranca.class)
					// gcom.cadastro.descricaogenerica
					.addClass(DescricaoGenerica.class)			
					.addClass(MensagemRetornoReceitaFederal.class)
					.addClass(ClienteVirtual.class)
					.addClass(MotivoRetiradaCobranca.class)					
					.addClass(ParametroTabelaAtualizacaoCadastro.class)
					
					// *************************************//
					// CLASSES DO PACOTE gcom.atualizacaocadastral //
					// *************************************//
					.addClass(ImovelAtualizacaoCadastralDM.class)
					.addClass(ClienteAtualizacaoCadastralDM.class)
					.addClass(ClienteFoneAtualizacaoCadastralDM.class)
					.addClass(AreaAtualizacaoCadastralDM.class)
					.addClass(ArquivoTextoAtualizacaoCadastralDM.class)
					.addClass(HidrometroInstalacaoHistoricoAtualizacaoCadastralDM.class)
					.addClass(ImovelSubcategoriaAtualizacaoCadastralDM.class)
					.addClass(ParametroQuadraAtualizacaoCadastralDM.class)
					.addClass(ParametroTabelaAtualizacaoCadastralDM.class)
					.addClass(SituacaoTransmissaoAtualizacaoCadastralDM.class)
					.addClass(CepAtlzCadDM.class)
					.addClass(LogradouroAtlzCadDM.class)
					.addClass(LogradouroBairroAtlzCadDM.class)
					.addClass(LogradouroCepAtlzCadDM.class)
					.addClass(ImovelOcorrenciaAtualizacaoCadastralDM.class)
					.addClass(ImovelFotoAtualizacaoCadastralDM.class)
					.addClass(RetornoAtualizacaoCadastralDM.class)
					.addClass(AtributoAtualizacaoCadastralDM.class)
					.addClass(MensagemAtualizacaoCadastralDM.class)
					.addClass(MapaAtualizacaoCadastralDM.class)
					.addClass(OrdemServicoAtualizacaoCadastralDM.class)
					
					// *************************************//
					// CLASSES DO PACOTE gcom.cobranca //
					// *************************************//
					.addClass(CobrancaGrupo.class).addClass(
							CobrancaSituacao.class).addClass(
							CobrancaSituacaoHistorico.class).addClass(
							CobrancaSituacaoMotivo.class).addClass(
							CobrancaSituacaoTipo.class).addClass(
							ParcelamentoGrupo.class).addClass(
							CobrancaForma.class).addClass(
							IndicesAcrescimosImpontualidade.class).addClass(
							ResumoCobrancaSituacaoEspecial.class).addClass(
							CobrancaAcaoSituacao.class).addClass(
							CobrancaDebitoSituacao.class).addClass(
							ResumoCobrancaAcao.class).addClass(
							ParcelamentoFaixaValor.class).addClass(NegativacaoComando.class)
							.addClass(NegativacaoCriterio.class).addClass(NegativacaoCriterioClienteTipo.class)
							.addClass(NegativacaoCriterioCpfTipo.class).addClass(NegativacaoCriterioImovelPerfil.class)
							.addClass(NegativacaoCriterioSubcategoria.class).addClass(NegativacaoImovei.class).addClass(Negativador.class).addClass(NegativadorContrato.class)
							.addClass(NegativadorExclusaoMotivo.class).addClass(NegativadorMovimento.class).addClass(NegativadorMovimentoReg.class)
							.addClass(NegativadorMovimentoRegItem.class).addClass(NegativadorMovimentoRegRetMot.class).addClass(NegativadorRegistroTipo.class)
							.addClass(NegativadorRetornoMotivo.class).addClass(NegativCritCobrGrupo.class)
							.addClass(NegativCritElo.class).addClass(NegativCritGerReg.class)
							.addClass(NegativCritUndNeg.class)
							.addClass(ResumoNegativacao.class)
							.addClass(NegativadorResultadoSimulacao.class).addClass(UnidadeOrganizacionalTestemunha.class)
							.addClass(CriterioSituacaoCobranca.class)
							.addClass(CriterioSituacaoLigacaoAgua.class)
							.addClass(CriterioSituacaoLigacaoEsgoto.class)
							.addClass(NegativacaoCriterioLigacaoAgua.class)
							.addClass(NegativacaoCriterioLigacaoEsgoto.class)
							.addClass(EmpresaCobrancaConta.class)
							.addClass(EmpresaCobrancaContaPagamentos.class)
							.addClass(ComandoEmpresaCobrancaConta.class)
							.addClass(ComandoEmpresaCobrancaContaExtensao.class)
							.addClass(CobrancaSituacaoComando.class)
							.addClass(NegativadorMovimentoRegParcelamento.class)
							.addClass(ParcelamentoPagamentoCartaoCredito.class)
							.addClass(PagamentoCartaoCreditoItem.class)
							.addClass(DocumentosReceberFaixaDiasVencidos.class)
							.addClass(NegativCritNegRetMot.class)
							.addClass(ParcDesctoInativVista.class)
							.addClass(CobrancaAcaoOrdemServicoNaoAceitas.class)
							.addClass(UnidadeRepavimentadoraCustoPavimentoRua.class)
							.addClass(UnidadeRepavimentadoraCustoPavimentoCalcada.class)
							.addClass(CobrancaBoletimMedicao.class)
							.addClass(CobrancaBoletimDesconto.class)
							.addClass(CobrancaBoletimExecutado.class)
							.addClass(CobrancaBoletimSucesso.class)
							.addClass(ComandoEmpresaCobrancaContaGerencia.class)
							.addClass(ComandoEmpresaCobrancaContaImovelPerfil.class)
							.addClass(ComandoEmpresaCobrancaContaUnidadeNegocio.class)
							.addClass(CmdEmpresaCobrancaContaLigacaoAguaSituacao.class)
							.addClass(MotivoNaoAceitacaoEncerramentoOS.class)
							.addClass(ComandoOrdemSeletiva.class)
							.addClass(LigacaoSitComandoOSS.class)
							.addClass(AnormalidadeComandoOSS.class)
							.addClass(CapacidHidrComandoOSS.class)
							.addClass(EmpresaCobrancaContaPenalidade.class)
							.addClass(EmpresaContaCobrancaCancelada.class)
							.addClass(EmpresaCobrancaContaBoletimMedicao.class)
							.addClass(CobrancaBoletimContrato.class)
							.addClass(BoletimMedicaoJustificativaPenalidade.class)
							.addClass(ComandoEmpresaCobrancaContaSetorComercial.class)
							.addClass(ImovelRetiradaComando.class)
							.addClass(CertidaoNegativaDebito.class)
							.addClass(ComandoAtividadeImoveis.class)
							.addClass(DividaAtivaCriterio.class)
							.addClass(DividaAtivaCriterioClienteTipo.class)
							.addClass(DividaAtivaCriterioEsferaPoder.class)
							.addClass(DividaAtivaImovel.class)
							.addClass(DividaAtivaDebito.class)
							.addClass(DividaAtivaAmortizacaoTipo.class)
							.addClass(DividaAtivaAmortizacao.class)
							.addClass(DividaAtivaAnalitico.class)							.addClass(CobrancaAcaoGrupoContrato.class)
							.addClass(ColunasTextoSMSEmail.class)
							.addClass(NegativacaoComandoImovel.class)
							// *************************************//
							// CLASSES DO PACOTE gcom.cobranca.contratoparcelamento //
							// *************************************//
							
							.addClass(ContratoParcelamentoRD.class)
							.addClass(QuantidadePrestacoes.class)
							.addClass(TipoRelacao.class)
							.addClass(ContratoParcelamento.class)
							.addClass(ContratoParcelamentoCliente.class)
							.addClass(PrestacaoContratoParcelamento.class)
							.addClass(ContratoParcelamentoItem.class)
							.addClass(PrestacaoItemContratoParcelamento.class)
							.addClass(ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua.class)
							
							// *************************************//
							// FIM CLASSES DO PACOTE gcom.cobranca.contratoparcelamento //
							// *************************************//

//							.addClass(CobrancaBoletimMedicao.class)
//							.addClass(CobrancaBoletimDesconto.class)
//							.addClass(CobrancaBoletimExecutado.class)
//							.addClass(CobrancaBoletimSucesso.class)


					// *************************************//
					// CLASSES DO PACOTE gcom.faturamento //
					// *************************************//
					.addClass(QualidadeAgua.class).addClass(ImpostoTipo.class)
					.addClass(ImpostoTipoAliquota.class).addClass(
							FaturamentoGrupo.class).addClass(
							FaturamentoSituacaoTipo.class).addClass(
							FaturamentoAtividade.class).addClass(
							FaturamentoAtividadeCronograma.class).addClass(
							FaturamentoGrupoCronogramaMensal.class).addClass(
							FaturamentoImediatoAjuste.class).addClass(
							FaturamentoSituacaoMotivo.class).addClass(
							FaturamentoSituacaoHistorico.class).addClass(
							FaturamentoTipo.class).addClass(
							FaturamentoAtivCronRota.class).addClass(
							FaturamentoDados.class).addClass(
							ResumoFaturamentoSimulacao.class)
							.addClass(ResumoFaturamentoSimulacaoDebito.class)
							.addClass(ResumoFaturamentoSimulacaoCredito.class)
							.addClass(VencimentoAlternativo.class).addClass(
							ResumoFaturamentoSituacaoEspecial.class).addClass(
							ResumoFaturamentoSituacaoEspecialDetalhe.class).addClass(
							FaturamentoContabilParametros.class).addClass(
							GuiaPagamentoGeral.class).addClass(
							DocumentoNaoEntregue.class).addClass(
							HistogramaAguaEconomia.class).addClass(
							HistogramaAguaLigacao.class).addClass(
							HistogramaEsgotoEconomia.class).addClass(
							HistogramaEsgotoLigacao.class).addClass(
							QualidadeAguaPadrao.class)
							.addClass(FaturamentoSituacaoComando.class)
							.addClass(TarifaTipoCalculo.class)
							.addClass(GuiaPagamentoParcelamentoCartao.class)
							.addClass(MotivoInterferenciaTipo.class)
							.addClass(ExtratoQuitacao.class)
							.addClass(ExtratoQuitacaoItem.class)
							.addClass(Prescricao.class)
							.addClass(ConsumoMinimoParametro.class)
							.addClass(FaturamentoGrupoCanceladoHistorico.class)
							.addClass(HistogramaEsgotoEconomiaSemQuadra.class)

					// gcom.faturamento.conta ContaMensagem
					.addClass(ContaCategoriaConsumoFaixa.class)
					.addClass(Conta.class)
					.addClass(ContaCategoria.class)
					.addClass(MotivoNaoEntregaDocumento.class)
					.addClass(Refaturamento.class)
					.addClass(Fatura.class)
					.addClass(FaturaItem.class)
					.addClass(ContaHistorico.class)
					.addClass(ContaImpostosDeduzidos.class)
					.addClass(ContaMotivoCancelamento.class)
					.addClass(ContaMotivoInclusao.class)
					.addClass(ContaMotivoRetificacao.class)
					.addClass(ContaMotivoRevisao.class)
					.addClass(ContaGeral.class)
					.addClass(ContaImpressao.class)
					.addClass(ContaCategoriaConsumoFaixaHistorico.class)
					.addClass(ContaCategoriaHistorico.class)
					.addClass(ContaImpostosDeduzidosHistorico.class)
					.addClass(ContaTipo.class)
					.addClass(ContaMotivoRetificacaoColuna.class)
					.addClass(ContaEmissao2Via.class)
									
					// gcom.faturamento.debito
					.addClass(DebitoCobrado.class).addClass(DebitoTipo.class)
					.addClass(DebitoACobrar.class).addClass(
							DebitoACobrarCategoria.class).addClass(
							DebitoCobradoHistorico.class).addClass(
							DebitoCobradoCategoria.class).addClass(
							DebitoACobrarHistorico.class).addClass(
							DebitoCreditoSituacao.class).addClass(
							ContaMensagem.class).addClass(
							DebitoACobrarGeral.class).addClass(
							DebitoTipoVigencia.class)

					// gcom.faturamento.credito
					.addClass(CreditoRealizado.class).addClass(
							CreditoARealizar.class).addClass(
							CreditoARealizarCategoria.class).addClass(
							CreditoRealizadoHistorico.class).addClass(
							CreditoRealizadoCategoria.class).addClass(
							CreditoTipo.class).addClass(
							CreditoARealizarHistorico.class).addClass(
							CreditoOrigem.class).addClass(
							CreditoARealizarGeral.class)

					// gcom.faturamento.consumotarifa
					.addClass(ConsumoTarifa.class).addClass(
							ConsumoTarifaVigencia.class).addClass(
							ConsumoTarifaCategoria.class).addClass(
							ConsumoTarifaFaixa.class)
							
					// gcom.faturamento.debito
					.addClass(DebitoFaixaValore.class)
					
					// gcom.faturamento.autoinfracao
					.addClass(AutoInfracaoSituacao.class)
					.addClass(AutosInfracao.class)
					.addClass(AutosInfracaoDebitoACobrar.class)
					.addClass(FaturaItemHistorico.class)
					.addClass(HistogramaAguaEconomiaSemQuadra.class)
					.addClass(HistogramaAguaLigacaoSemQuadra.class)
					.addClass(HistogramaEsgotoLigacaoSemQuadra.class)
					.addClass(ContaComunicado.class)
					.addClass(ContaComunicadoQuadra.class)
					.addClass(ContaComunicadoRota.class)
					.addClass(ContaComunicadoSetor.class)
					.addClass(ContaComunicadoFaturamentoGrupo.class)	
					
					// *************************************//
					// CLASSES DO PACOTE gcom.micromedicao //
					// *************************************//
					.addClass(Rota.class).addClass(RateioTipo.class).addClass(
							ImovelTestesMedicaoConsumo.class)
					.addClass(ItemContratoServicoTipo.class)		
					// gcom.micromedicao.hidrometro
					.addClass(HidrometroCapacidade.class).addClass(
							Hidrometro.class).addClass(
							HidrometroMotivoBaixa.class).addClass(
							HidrometroClasseMetrologica.class).addClass(
							HidrometroMarca.class).addClass(
							HidrometroMovimentacao.class).addClass(
							HidrometroMotivoMovimentacao.class).addClass(
							HidrometroLocalArmazenagem.class).addClass(
							HidrometroSituacao.class).addClass(
							HidrometroDiametro.class)
							.addClass(HidrometroRelojoaria.class)
							.addClass(
							HidrometroInstalacaoHistorico.class).addClass(
							HidrometroLocalInstalacao.class).addClass(
							HidrometroTipo.class).addClass(
							HidrometroProtecao.class).addClass(
							HidrometroMovimentado.class).addClass(
							Leiturista.class).addClass(
							ArquivoTextoRoteiroEmpresa.class).addClass(
							RoteiroEmpresa.class).addClass(
							ServicoTipoCelular.class).addClass(
							MovimentoRoteiroEmpresa.class)
							.addClass(ItemServico.class)
							.addClass(ContratoEmpresaServico.class)
							.addClass(ItemServicoContrato.class)
							.addClass(RetornoControleHidrometro.class)
							.addClass(TelemetriaLog.class)
							.addClass(TelemetriaLogErro.class)
							.addClass(TelemetriaMov.class)
							.addClass(TelemetriaMovReg.class)
							.addClass(TelemetriaRetMot.class)
							.addClass(ContratoEmpresaAditivo.class)
							.addClass(HidrometroFatorCorrecao.class)
							.addClass(HidrometroClassePressao.class)
							.addClass(MovimentoRoteiroEmpresaFoto.class)
							.addClass(HidrometroFaixaIdade.class)

					// gcom.micromedicao.leitura
					.addClass(LeituraTipo.class)
					.addClass(LeituraSituacao.class).addClass(
							LeituraFaixaFalsa.class).addClass(
							LeituraAnormalidadeLeitura.class).addClass(
							LeituraAnormalidade.class).addClass(
							LeituraFiscalizacao.class).addClass(
							LeituraAnormalidadeConsumo.class)
					// gcom.micromedicao.medicao //
					.addClass(MedicaoHistorico.class).addClass(
							MedicaoTipo.class)
					// gcom.micromedicao.consumo //
					.addClass(ConsumoHistorico.class).addClass(
							ConsumoTipo.class).addClass(
							ConsumoAnormalidade.class).addClass(
							LigacaoTipo.class).addClass(
							ResumoAnormalidadeConsumo.class).addClass(
							ResumoAnormalidadeLeitura.class).addClass(
							ConsumoHistoricoAnterior.class).addClass(
							MedicaoHistoricoAnterior.class).addClass(
							SituacaoTransmissaoLeitura.class).addClass(
							ConsumoMinimoArea.class).addClass(
							ConsumoAnormalidadeAcao.class).addClass(
							RotaAtualizacaoSeq.class )                                    
							.addClass( ReleituraMobile.class )


					// ************************************//
					// CLASSES DO PACOTE gcom.financeiro //
					// ************************************//
					.addClass(LancamentoContabil.class).addClass(
							LancamentoResumo.class).addClass(
							LancamentoResumoValorTipo.class).addClass(
							LancamentoResumoConta.class).addClass(
							LancamentoResumoContaHistorico.class).addClass(
							FinanciamentoTipo.class).addClass(
							LancamentoContabilItem.class).addClass(
							ContaContabil.class).addClass(
							LancamentoOrigem.class).addClass(
							ResumoFaturamento.class).addClass(
							LancamentoItem.class).addClass(
							LancamentoItemContabil.class).addClass(
							LancamentoTipoItem.class).addClass(
							LancamentoTipo.class).addClass(
							DevedoresDuvidososContabilParametro.class).addClass(
							ContaAReceberContabil.class).addClass(
							ValorVolumesConsumidosNaoFaturado.class).addClass(
							DocumentosAReceberResumo.class).addClass(
							ResumoReceita.class).addClass(
							FaixaDocumentosAReceber.class).addClass(
							DocumentosAReceberFaixaResumo.class).addClass(
							PerdasTipo.class).addClass(
							ParametrosPerdasOrgaoPublico.class).addClass(
							ParametrosPerdasSocietarias.class).addClass(
							ValorConsumidoNaoFaturadoParametro.class)

					// ************************************//
					// CLASSES DO PACOTE gcom.arrecadacao //
					// ************************************//
					// gcom.arrecadacao.banco

					.addClass(ResumoArrecadacao.class).addClass(Banco.class)
					.addClass(Agencia.class)
					// gcom.arrecadacao.pagamento
					.addClass(Pagamento.class)
					.addClass(PagamentoSituacao.class).addClass(
							GuiaPagamento.class).addClass(
							GuiaPagamentoHistorico.class).addClass(
							GuiaPagamentoCategoriaHistorico.class)
					.addClass(PagamentoCartaoDebito.class)
					.addClass(PagamentoCartaoDebitoItem.class)
					.addClass(SequenciaCartao.class)
					.addClass(GuiaPagamentoItem.class)
					.addClass(GuiaPagamentoItemHistorico.class)
					.addClass(GuiaPagamentoItemCategoria.class)
					.addClass(GuiaPagamentoItemCategoriaHistorico.class)
					// gcom.arrecadacao.debito
					.addClass(DebitoAutomatico.class).addClass(
							DebitoAutomaticoRetornoCodigo.class).addClass(
							DebitoAutomaticoMovimento.class).addClass(
							GuiaPagamentoCategoria.class).addClass(
							MetasArrecadacao.class)
					.addClass(DebitoAutomaticoParcelamentoCliente.class)
                    .addClass(DebitoAutomaticoMovimentoParcelamentoCliente.class)
					.addClass(DevolucaoHistorico.class)
					.addClass(DevolucaoDadosDiarios.class)
					.addClass(DebitoCarteiraMovimento.class)
					.addClass(BandeiraCartao.class)
					// *************************************//
					// CLASSES DO PACOTE gcom.operacional //
					// *************************************//
					.addClass(Bacia.class).addClass(DistritoOperacional.class)
					.addClass(DivisaoEsgoto.class).addClass(
							SistemaAbastecimento.class).addClass(
							SistemaEsgoto.class).addClass(
							SistemaEsgotoTratamentoTipo.class).addClass(
							AbastecimentoProgramacao.class).addClass(
							ManutencaoProgramacao.class).addClass(
							SetorAbastecimento.class).addClass(
							ZonaAbastecimento.class)
					.addClass(ZonaPressao.class).addClass(ProducaoAgua.class)
					// ************************************//
					// CLASSES DO PACOTE gcom.seguranca //
					// ************************************//
					// gcom.seguranca.acesso
					.addClass(AlteracaoTipo.class).addClass(UsuarioTipo.class)
					.addClass(TabelaLinhaAlteracao.class).addClass(
							TabelaLinhaColunaAlteracao.class).addClass(
							TabelaColuna.class).addClass(Tabela.class)
					.addClass(UsuarioAcao.class)
					.addClass(UsuarioFavorito.class)
					.addClass(GrupoAcesso.class)
					.addClass(UsuarioSenhaHistorico.class)
					//gcom.seguranca.parametrosistema
					.addClass(ParametroSistema.class)
					.addClass(ParametroTipo.class)
					// gcom.seguranca.transacao
					.addClass(SgbdTabela.class)
					.addClass(SgbdTabelaColuna.class).addClass(
							UsuarioSituacao.class).addClass(
							UsuarioPermissaoEspecial.class).addClass(
							UsuarioAlteracao.class).addClass(
							UsuarioGrupoRestricao.class).addClass(
							UsuarioGrupo.class).addClass(
							UsuarioAbrangencia.class).addClass(Usuario.class)
					.addClass(UsuarioBanco.class)
					.addClass(ResolucaoDiretoria.class).addClass(
							CreditoRealizadoCategoriaHistorico.class).addClass(
							CreditoARealizarCategoriaHistorico.class).addClass(
							DebitoCobradoCategoriaHistorico.class).addClass(
							DebitoACobrarCategoriaHistorico.class).addClass(
							PermissaoEspecial.class).addClass(
							AvisoDeducoes.class).addClass(AvisoBancario.class)
					.addClass(AvisoAcerto.class).addClass(
							ArrecadadorMovimentoItem.class).addClass(
							ArrecadadorMovimento.class).addClass(
							ArrecadadorContratoTarifa.class).addClass(
							ParcelamentoTipo.class).addClass(
							ParcelamentoSituacao.class).addClass(
							ParcelamentoQuantidadeReparcelamento.class)
					.addClass(ParcelamentoQuantidadePrestacao.class).addClass(
							ParcelamentoPerfil.class).addClass(
							ParcelamentoItem.class).addClass(
							ParcelamentoDescontoInatividade.class).addClass(
							ParcelamentoDescontoAntiguidade.class).addClass(
							Parcelamento.class).addClass(DocumentoTipo.class)
					.addClass(DocumentoEmissaoForma.class).addClass(
							DevolucaoSituacao.class).addClass(Devolucao.class)
					.addClass(DeducaoTipo.class).addClass(GuiaDevolucao.class)
					.addClass(GrupoFuncionalidadeOperacao.class).addClass(
							Grupo.class).addClass(
							FuncionalidadeDependencia.class).addClass(
							Funcionalidade.class).addClass(
							ParcelamentoMotivoDesfazer.class).addClass(
							PagamentoHistorico.class).addClass(
							OperacaoEfetuada.class).addClass(Operacao.class)
					.addClass(OperacaoTipo.class)
					.addClass(OperacaoTabela.class).addClass(
							RegistroCodigo.class).addClass(
							ArrecadadorContrato.class).addClass(
							Arrecadador.class).addClass(ArrecadacaoForma.class)
					.addClass(CobrancaAcao.class).addClass(
							RotaAcaoCriterio.class).addClass(
							CobrancaAcaoAtividadeComando.class).addClass(
							CobrancaCriterioLinha.class).addClass(
							CobrancaCriterio.class).addClass(
							CobrancaAtividadeComandoRota.class).addClass(
							CobrancaAtividade.class).addClass(
							CobrancaAcaoCronograma.class).addClass(
							CobrancaAcaoAtividadeCronograma.class).addClass(
							Modulo.class).addClass(ContratoDemanda.class)
					.addClass(ContratoMotivoCancelamento.class).addClass(
							CobrancaGrupoCronogramaMes.class).addClass(
							CobrancaDocumentoItem.class).addClass(
							CobrancaDocumento.class).addClass(
							ImovelSituacaoTipo.class).addClass(
							ImovelSituacao.class).addClass(ContaBancaria.class)
					.addClass(ArrecadacaoDadosDiarios.class).addClass(
							ResumoPendencia.class).addClass(
							RecebimentoTipo.class).addClass(
							ArrecadacaoContabilParametros.class).addClass(
							MotivoCorte.class).addClass(
							UnidadeProcessamento.class).addClass(
							ProcessoIniciado.class).addClass(
							ProcessoSituacao.class).addClass(
							ProcessoFuncionalidade.class).addClass(
							FuncionalidadeIniciada.class).addClass(
							FuncionalidadeSituacao.class).addClass(
							Processo.class).addClass(ProcessoTipo.class)
					.addClass(UnidadeIniciada.class).addClass(
							RelatorioGerado.class).addClass(Relatorio.class)
					.addClass(UnidadeSituacao.class).addClass(
							RamalLocalInstalacao.class).addClass(
							ParametrosDevedoresDuvidosos.class).addClass(
							ParametrosDevedoresDuvidososItem.class).addClass(
							ResumoDevedoresDuvidosos.class).addClass(
							DbVersaoBase.class).addClass(
							EnvioEmail.class).addClass(
							ResumoCobrancaAcaoEventual.class).addClass(
							ConsumoFaixaLigacao.class).addClass(
							ConsumoFaixaCategoria.class).addClass(
							ContaRevisaoFaixaValor.class).addClass(
                            OperacaoOrdemExibicao.class).addClass(
                            LigacaoEsgotoDestinoDejetos.class).addClass(
                            LigacaoEsgotoCaixaInspecao.class).addClass(
                            LigacaoEsgotoDestinoAguasPluviais.class).addClass(
                            LigacaoEsgotoEsgotamento.class).
							addClass(LigacaoAguaSituacaoConsumoTipo.class).
							addClass(LigacaoEsgotoSituacaoConsumoTipo.class).
							addClass(FonteCaptacao.class).
 							addClass(SetorFonteCaptacao.class).
 							addClass(FuncionalidadeCategoria.class).
 							addClass(TabelaAtualizacaoCadastral.class).
 							addClass(TabelaAtualizacaoCadastralSituacao.class).
 							addClass(TabelaColunaAtualizacaoCadastral.class).
							addClass(CicloMeta.class).
							addClass(Atributo.class).
							addClass(AtributoGrupo.class).
							addClass(FuncionalidadeAtributo.class).
							addClass(CicloMetaGrupo.class).
							addClass(VwImovelPrincipalCategoria.class).
							addClass(MovimentoContaImpostoDeduzido.class).
							addClass(MovimentoContaCategoriaConsumoFaixa.class).
							addClass(MovimentoContaPrefaturadaCategoria.class).
							addClass(MovimentoContaPrefaturada.class).
							addClass(MotivoNaoGeracaoDocCobranca.class).
							addClass(ImovelNaoGerado.class).
							addClass(TipoCaptacao.class).
							addClass(CobrancaDocumentoImpressao.class).
							addClass(CobrancaDocumentoControleGeracao.class).
							addClass(GrauDificuldadeExecucao.class).
							addClass(GrauRiscoSegurancaFisica.class).
							addClass(NivelPressao.class).
							addClass(GrauIntermitencia.class).
							addClass(CondicaoAbastecimentoAgua.class).
							addClass(ArquivoTextoRoteiroEmpresaDivisao.class).
							addClass(MovimentoCartaoRejeita.class).
							addClass(EmailClienteAlterado.class).
							addClass(CobrancaAcaoAtividadeComandoFiscalizacaoSituacao.class).
							addClass(CobrancaDocumentoFisc.class).
							addClass(ControleLiberacaoPermissaoEspecial.class).
							addClass(SolicitacaoAcessoSituacao.class).
							addClass(SolicitacaoAcessoGrupo.class).
							addClass(SolicitacaoAcesso.class).
							addClass(NegativacaoCriterioSituacaoEspecialCobranca.class).
							addClass(NegativacaoCriterioSituacaoCobranca.class)
										.addClass(TarifaSocialCarta.class)
					.addClass(TarifaSocialCartaDebito.class)
					.addClass(TarifaSocialComandoCarta.class)
					.addClass(TarifaSocialMotivoCarta.class)
					.addClass(GrupoPermissaoEspecial.class)
					
					.addClass(ConsultarReceitaFederal.class)
					.addClass(ParcelamentoPerfilDebitos.class)
					
					// ************************************//
					// CLASSES DO PACOTE gcom.atendimentopublico.portal //
					// ************************************//
					
					.addClass(QuestionarioSatisfacaoCliente.class)
					.addClass(AcessoLojaVirtual.class)
					
					
					// ************************************//
					// FIM DAS CLASSES DO PACOTE gcom.atendimentopublico.portal //
					// ************************************//
					
					.addClass(ServicoTerceiroAcompanhamentoServico.class)
					
					
					// ***************************************************//
					// CLASSES DO PACOTE gcom.faturamento.contratodemanda //
					// ***************************************************//
					.addClass(ContratoDemandaImovel.class)
					.addClass(ContratoDemandaMotivoEncerramento.class)
					.addClass(ContratoDemandaSituacao.class)
					.addClass(ContratoDemandaFaixaConsumo.class)
					
					// ***************************************************//
					// CLASSES DO PACOTE gcom.mobile.execucaoordemservico //
					// ***************************************************//
					.addClass(ArquivoTextoOSCobranca.class)
					.addClass(ArquivoTextoOSCobrancaCliente.class)
					.addClass(ArquivoTextoOSCobrancaItem.class)
					.addClass(ExecucaoOSCliente.class)
					.addClass(ExecucaoOSCorte.class)
					.addClass(ExecucaoOSFiscalizacao.class)
					.addClass(ExecucaoOSFoto.class)
					.addClass(ExecucaoOSOrdemServico.class)
					.addClass(ExecucaoOSSituacoesEncontradas.class)
					.addClass(ExecucaoOSVisita.class)
					.addClass(ParametrosArquivoTextoOSCobranca.class)
					.addClass(ParametrosArquivoTextoOSLocalidade.class)
					;
							
							
			configuration.setInterceptor(Interceptador.getInstancia());
			sessionFactory = configuration.buildSessionFactory();

			inicializarSessionFactoryPentaho();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a SessionFactory");
		} catch (ServiceLocatorException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a SessionFactory");
		} finally {
			try {
				if (getDialect().toUpperCase().contains("ORACLE")) {
					HibernateUtil.inicializarSessionFactoryIFS();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retorna o valor de session
	 * 
	 * @return O valor de session
	 */
	public static Session getSession() {
		Session retorno = null;

		try {
			retorno = sessionFactory.openSession();
			//System.out.println("inicio:"+retorno.hashCode());
			tempoSession.put(retorno.hashCode(), System.currentTimeMillis());
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a Session");
		}

		return retorno;
	}

	/**
	 * Retorna o valor de session
	 * 
	 * @return O valor de session
	 */
	public static StatelessSession getStatelessSession() {
		StatelessSession retorno = null;

		try {
			retorno = sessionFactory.openStatelessSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a Session");
		}

		return retorno;
	}

	/**
	 * Retorna o valor de session
	 * 
	 * @return O valor de session
	 */
	public static StatelessSession getStatelessSessionGerencial() {
		StatelessSession retorno = null;

		try {
			retorno = sessionFactoryGerencial.openStatelessSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a Session");
		}

		return retorno;
	}

	/**
	 * Retorna o valor de session
	 * 
	 * @return O valor de session
	 */
	public static Session getSessionGerencial() {
		Session retorno = null;

		try {
			retorno = sessionFactoryGerencial.openSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException(
					"Hibernate - Erro ao criar a Session Gerencial");
		}

		return retorno;
	}

	/**
	 * Retorna a sess�o do Pentaho
	 * 
	 * @return sess�o
	 */
	public static Session getSessionPentaho() {
		try {
			if(sessionFactoryPentaho == null) {
				throw new SistemaException("Hibernate - Erro ao obter a Session Pentaho");
			}

			return sessionFactoryPentaho.openSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao obter a Session Pentaho");
		}
	}

	/**
	 * Fecha a session
	 * 
	 * @param session
	 *            Descri��o do par�metro
	 */
	public static void closeSession(Session session) {

		if (session != null) {
			try {

				//session.clear();

				
				Throwable t = new Throwable();
				StackTraceElement[] elements = t.getStackTrace();

				
					Long tempoInicialSession = tempoSession.get(session.hashCode());
					
					if (tempoInicialSession != null ) {
					
						
						Long tempoTotalSession = System.currentTimeMillis() - tempoInicialSession;
						
						String mensagem = loggerEntidadesPorConsulta(session, elements, tempoTotalSession);
						if (mensagem != null && !mensagem.trim().equals("")) {
							log.debug(mensagem);
						}
						
				
				
					}
				session.close();
				//session = null;
			} catch (HibernateException ex) {
				throw new SistemaException(
						"Hibernate - Erro ao fechar a Session");
			} catch (NullPointerException ex) {
				ex.printStackTrace();
				System.out.println("Nullpointer aqui");
				
			}

		}
	}


	private static String loggerEntidadesPorConsulta(Session session, StackTraceElement[] elements, long tempoTotalSession) {
		
		//String calleeMethod = elements[0].getMethodName();
		String callerMethodName = elements[1].getMethodName();
		String callerClassName = elements[1].getClassName();
		String callerMethodName2Level = elements[2].getMethodName();
		String callerClassName2Level = elements[2].getClassName();
		

	
		String log = "";
		Map<String, Integer> entidades = new HashMap<String, Integer>();

		for (Object a : session.getStatistics().getEntityKeys()) {

			entidades.put(((EntityKey) a).getEntityName(), session
					.getStatistics().getEntityCount());

		}

		Iterator iterator = entidades.keySet().iterator();
		while (iterator.hasNext()) {

			
			
			String nomeEntidade = (String) iterator.next();

			 
			if (log.trim().equals("")) {
				log += ";"+callerClassName2Level + ";" + callerMethodName2Level + ";";
				log += callerClassName + ";" + callerMethodName + ";";
				log += nomeEntidade + ";";
				log += entidades.get(nomeEntidade) + ";" + tempoTotalSession+"; ";
			} else {
				log += " "+nomeEntidade + " ";
				log += entidades.get(nomeEntidade); 
				
			}
			
			
			

		}
		
		tempoSession.remove(session.hashCode());
		
		return log;
	}

	/**
	 * Fecha a session
	 * 
	 * @param session
	 *            Descri��o do par�metro
	 */
	public static void closeSession(StatelessSession session) {

		if (session != null) {
			try {
				session.close();
			} catch (HibernateException ex) {
				throw new SistemaException(
						"Hibernate - Erro ao fechar a Session");
			}

		}
	}

	/**
	 * M�todo que obt�m o tamanho da propriedade da classe
	 * 
	 * @param mappedClass
	 *            Nome da classe
	 * @param propertyName
	 *            Nome da propriedade da classe
	 * @return O valor de columnSize
	 */
	public static int getColumnSize(Class mappedClass, String propertyName) {
		Configuration cfg = HibernateUtil.getConfig();
		PersistentClass pClass = cfg.getClassMapping(mappedClass.getName());
		Column col = null;
		Property hibProp = null;

		try {
			hibProp = pClass.getProperty(propertyName);

			Iterator it = hibProp.getColumnIterator();

			while (it.hasNext()) {
				col = (Column) hibProp.getColumnIterator().next();
				break;
			}

		} catch (MappingException ex) {
			throw new SistemaException("Hibernate - Erro no mapeamento");
		}

		return col.getLength();
	}

	/**
	 * M�todo que obt�m o nome da coluna no banco da propriedade passada Caso
	 * nao tenha, retorna null
	 * 
	 * @param mappedClass
	 *            Nome da classe
	 * @param propertyName
	 *            Nome da propriedade da classe
	 * @return nome da coluna
	 */
	public static String getNameColumn(Class mappedClass, String propertyName) {
		String retorno = null;
		Configuration cfg = HibernateUtil.getConfig();
		PersistentClass pClass = cfg.getClassMapping(mappedClass.getName());
		Column col = null;
		Property hibProp = null;

		try {
			hibProp = pClass.getProperty(propertyName);

			Iterator it = hibProp.getColumnIterator();

			while (it.hasNext()) {
				col = (Column) hibProp.getColumnIterator().next();
				break;
			}

			// retorno = col.getComment();
			// if (retorno == null || "".equals(retorno)) {
			if (col == null) {
				retorno = ConstantesDescricaoBanco.get(pClass.getTable()
						.getName()
						+ "." + propertyName);
			} else {
				retorno = ConstantesDescricaoBanco.get(pClass.getTable()
						.getName()
						+ "." + col.getName());
			}
			if (retorno == null && col != null) {

				retorno = col.getName();
			}

			if (col == null) {
				retorno = null;
			}
			// }

		} catch (MappingException ex) {
			try {

				hibProp = pClass.getIdentifierProperty();
				if (hibProp.getName().equalsIgnoreCase(propertyName)) {

					Iterator it = hibProp.getColumnIterator();

					while (it.hasNext()) {
						col = (Column) hibProp.getColumnIterator().next();
						break;
					}

					// retorno = col.getComment();
					// if (retorno == null || "".equals(retorno)) {
					// retorno = col.getName();
					// }

					retorno = ConstantesDescricaoBanco.get(pClass.getTable()
							.getName()
							+ "." + col.getName());
					if (retorno == null) {
						retorno = col.getName();
					}
				}

			} catch (MappingException eex) {
				eex.printStackTrace();
			}
		}

		return retorno;
	}

	/**
	 * M�todo que obt�m o nome da tabela da classe passada
	 * 
	 * @param mappedClass
	 *            Nome da classe
	 * @return O String nome da tablea
	 */
	public static String getNameTable(Class mappedClass) {
		Configuration cfg = HibernateUtil.getConfig();
		PersistentClass pClass = cfg.getClassMapping(mappedClass.getName());

		String retorno = pClass.getTable().getComment();
		if (retorno == null || "".equals(retorno)) {
			retorno = ConstantesDescricaoBanco.get(pClass.getTable().getName());
			if (retorno == null) {
				retorno = pClass.getTable().getName();
			}

		}

		return retorno;
	}
	
	/**
	 * Retorna a que classe est� mapeada a tabela passada 
	 * @param tableName caminho da tabela 
	 * @return caminho da classe 
	 */
	public static String getClassName(String tableName){
		Configuration cfg = HibernateUtil.getConfig();		
		if (cfg != null){
			Iterator iter = cfg.getClassMappings();
			while ( iter.hasNext() ) {
				PersistentClass classe = (PersistentClass) iter.next();
				if (classe.getTable().getName().equals(tableName)) {
					return classe.getClassName();
				}
			}			
		}
		return null;
	}

	/**
	 * Retorna o valor de config
	 * 
	 * @return O valor de config
	 */
	public static Configuration getConfig() {

		return configuration;
	}

	/**
	 * The main program for the HibernateUtil class
	 * 
	 * @param args
	 *            The command line arguments
	 */
	public static void main(String[] args) {

		getSession();

	}


	/**
	 * Retorna o valor de session
	 * 
	 * @return O valor de session
	 */
	public static StatelessSession getStatelessSessionIntegracaoSAM() {
		StatelessSession retorno = null;

		
			try {
				retorno = sessionFactoryIntegracaoSAM == null ? null : sessionFactoryIntegracaoSAM.openStatelessSession();
			} catch (HibernateException ex) {
				ex.printStackTrace();
				throw new SistemaException("Hibernate - Erro ao criar a Session IntegracaoSAM");
			}
		

		return retorno;
	}
	
	
	public static StatelessSession getStatelessSessionIntegracaoIFS() {
		StatelessSession retorno = null;

		
			try {
				retorno = sessionFactoryIntegracaoIFS == null ? null : sessionFactoryIntegracaoIFS.openStatelessSession();
			} catch (HibernateException ex) {
				ex.printStackTrace();
				throw new SistemaException("Hibernate - Erro ao criar a Session Integracao IFS");
			}
		

		return retorno;
	}
	
	/**
	 * Retorna o valor de session
	 * 
	 * @return O valor de session
	 */
	public static Session getSessionIntegracaoIFS() {
		Session retorno = null;

		
			try {
				retorno = sessionFactoryIntegracaoIFS == null ? null : sessionFactoryIntegracaoIFS.openSession();
			} catch (HibernateException ex) {
				ex.printStackTrace();
				throw new SistemaException(
						"Hibernate - Erro ao criar a Session Integracao IFS");
			}
		
		return retorno;
	}

	/**
	 * Retorna o valor de session
	 * 
	 * @return O valor de session
	 */
	public static Session getSessionIntegracaoSAM() {
		Session retorno = null;

		
			try {
				retorno = sessionFactoryIntegracaoSAM == null ? null : sessionFactoryIntegracaoSAM.openSession();
			} catch (HibernateException ex) {
				ex.printStackTrace();
				throw new SistemaException(
						"Hibernate - Erro ao criar a Session IntegracaoSAM");
			}
		
		return retorno;
	}
	
	public static String getDialect(){
		String retorno = "";
		retorno = configuration.getProperty("hibernate.dialect");		
		return retorno;
	}
	
	/**
	 * Seta as propriedades do configurationGerencial e do configuration de acordo com o arquivo xml de propriedades HibernateBaseProperties 
	 * 
	 * @author Paulo Diniz
	 * @date 27/11/2011
	 * 
	 * @return
	 */
	private static void verificaBaseDadosTipo() {
		try {
			File fXmlFile = new File("HibernateBaseProperties.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = (NodeList) doc.getElementsByTagName("properties");
			Node properties = nodeList.item(0);
			String base = "";
		    if (properties.getNodeType() == Node.ELEMENT_NODE) {
		    	
		    	base = Util.getTagValue("base", (Element) properties);
			   
		    }
			
			if (base != null && !base.equals("") && (base.equals("Oracle") || base.equals("Postgres"))) {
				
				String datasource = Util.getTagValue("connection-datasource", (Element) properties);
				String dialect = Util.getTagValue("dialect", (Element) properties);
				String release_mode = Util.getTagValue("connection-release-mode", (Element) properties);
		
				if( base.equals("Postgres")){
					configurationGerencial.setProperty("hibernate.connection.datasource", "java:/PostgresGerencialDS");

				} else {
					configurationGerencial.setProperty("hibernate.connection.datasource", datasource);
					
				}
				configurationGerencial.setProperty("hibernate.connection.release_mode", release_mode);
				configurationGerencial.setProperty("hibernate.dialect",dialect);
				
				configuration.setProperty("hibernate.connection.datasource", datasource);
				configuration.setProperty("hibernate.connection.release_mode", release_mode);
				configuration.setProperty("hibernate.dialect",dialect);
				
			}else{
				//� setado Postgres como Default
				configurationGerencial.setProperty("hibernate.connection.datasource","java:/PostgresGerencialDS");
				configurationGerencial.setProperty("hibernate.connection.release_mode", "after_transaction");
				configurationGerencial.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
				
				configuration.setProperty("hibernate.connection.datasource","java:/PostgresDS");
				configuration.setProperty("hibernate.connection.release_mode", "after_transaction");
				configuration.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
				
			}
		} catch (Exception e) {

			e.printStackTrace();
			
			//� setado Postgres como Default
			configurationGerencial.setProperty("hibernate.connection.datasource","java:/PostgresGerencialDS");
			configurationGerencial.setProperty("hibernate.connection.release_mode", "after_transaction");
			configurationGerencial.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
			
			configuration.setProperty("hibernate.connection.datasource","java:/PostgresDS");
			configuration.setProperty("hibernate.connection.release_mode", "after_transaction");
			configuration.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
		}
	}
	
	/**
	 * [RM6365] - 21/11/2011 - Bruno Barros - Grava��o das altera��es no banco de dados por usu�rio
	 * [UC1252] - Alterar Usu�rio Logado no Banco de Dados
	 * 
	 * [FS-0002] - Trocar o usu�rio da base
	 * 
	 * @author Th�lio Ara�jo
	 * @since 22/11/2011
	 * 
	 * @param idUsuario
	 * @param loginUsuario
	 * @return UsuarioBanco
	 * @throws ErroRepositorioException
	 */
	public static int alterarSessaoDaFabrica(String user, String password, String empresa) {
		int mensagemRetorno = 0;
		try {
			configurationUser = new Configuration();
			if(empresa.equals(SistemaParametro.EMPRESA_COMPESA)){
				configurationUser.setProperty("hibernate.connection.datasource","java:/OracleDS");
				configurationUser.setProperty("hibernate.connection.release_mode", "after_transaction");
				configurationUser.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle9Dialect");
			}else{
				configurationUser.setProperty("hibernate.connection.datasource","java:/PostgresDS");
				configurationUser.setProperty("hibernate.connection.release_mode", "after_transaction");
				configurationUser.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
			}
			configurationUser.setInterceptor(Interceptador.getInstancia());
			Iterator<?> classMappingsConfigurationIterator = configuration
					.getClassMappings();
			while (classMappingsConfigurationIterator.hasNext()) {
				RootClass classe = (RootClass) classMappingsConfigurationIterator
						.next();
				configurationUser.addClass(classe.getMappedClass());
			}
			configurationUser
					.setProperty("hibernate.connection.username", user);
			configurationUser.setProperty("hibernate.connection.password",
					password);
			
			
			sessionFactory = configurationUser.buildSessionFactory();
			mensagemRetorno = UsuarioBanco.MENSAGEM_SUCESSO;
		} catch (Exception ex) {
			ex.printStackTrace();
			mensagemRetorno = UsuarioBanco.MENSAGEM_ERRO;
			sessionFactory = configuration.buildSessionFactory();
		}
		return mensagemRetorno;
	}
	
	/**
	 * [RM6365] - 21/11/2011 - Bruno Barros - Grava��o das altera��es no banco de dados por usu�rio
	 * [UC1252] - Alterar Usu�rio Logado no Banco de Dados
	 * 
	 * [FS-0002] - Trocar o usu�rio da base
	 * 
	 * @author Th�lio Ara�jo
	 * @since 22/11/2011
	 * 
	 * @param idUsuario
	 * @param loginUsuario
	 * @return UsuarioBanco
	 * @throws ErroRepositorioException
	 */
	public static void alterarSessaoDaFabricaParaPadrao() {
		try {
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * [RM 4201] Bloquear vers�o batch para IP pr� cadastrado
	 * 
	 * M�todo que verifica se o ip do servidor possui
	 * autoriza��o para rodar batch
	 * 
	 * @author Raimundo Martins
	 * @date 31/07/2012 
	 * */
	
	/*public static boolean autorizadoBatch(){
		boolean retorno = false;	
		try{
			SistemaParametro sisp = (SistemaParametro) getSession().createCriteria(SistemaParametro.class).uniqueResult();
			InetAddress ip = InetAddress.getLocalHost();			
			if(ip.getHostAddress()!=null && sisp.getIpAutorizadoBatch() !=null &&
			  !ip.getHostAddress().trim().equals("") && !sisp.getIpAutorizadoBatch().trim().equals("") &&
			   ip.getHostAddress().equals(sisp.getIpAutorizadoBatch()))
				retorno = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return retorno;
	}*/
	
}
