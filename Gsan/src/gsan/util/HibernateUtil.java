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
package gsan.util;


import gsan.arrecadacao.ArrecadacaoContabilParametros;
import gsan.arrecadacao.ArrecadacaoDadosDiarios;
import gsan.arrecadacao.ArrecadacaoForma;
import gsan.arrecadacao.Arrecadador;
import gsan.arrecadacao.ArrecadadorContrato;
import gsan.arrecadacao.ArrecadadorContratoTarifa;
import gsan.arrecadacao.ArrecadadorMovimento;
import gsan.arrecadacao.ArrecadadorMovimentoItem;
import gsan.arrecadacao.ContratoDemanda;
import gsan.arrecadacao.ContratoMotivoCancelamento;
import gsan.arrecadacao.DebitoCarteiraMovimento;
import gsan.arrecadacao.DeducaoTipo;
import gsan.arrecadacao.Devolucao;
import gsan.arrecadacao.DevolucaoDadosDiarios;
import gsan.arrecadacao.DevolucaoHistorico;
import gsan.arrecadacao.DevolucaoSituacao;
import gsan.arrecadacao.GuiaDevolucao;
import gsan.arrecadacao.MetasArrecadacao;
import gsan.arrecadacao.MovimentoCartaoRejeita;
import gsan.arrecadacao.RecebimentoTipo;
import gsan.arrecadacao.RegistroCodigo;
import gsan.arrecadacao.ResumoArrecadacao;
import gsan.arrecadacao.aviso.AvisoAcerto;
import gsan.arrecadacao.aviso.AvisoBancario;
import gsan.arrecadacao.aviso.AvisoDeducoes;
import gsan.arrecadacao.banco.Agencia;
import gsan.arrecadacao.banco.Banco;
import gsan.arrecadacao.banco.ContaBancaria;
import gsan.arrecadacao.debitoautomatico.DebitoAutomatico;
import gsan.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gsan.arrecadacao.debitoautomatico.DebitoAutomaticoMovimentoParcelamentoCliente;
import gsan.arrecadacao.debitoautomatico.DebitoAutomaticoParcelamentoCliente;
import gsan.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo;
import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gsan.arrecadacao.pagamento.GuiaPagamentoCategoriaHistorico;
import gsan.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gsan.arrecadacao.pagamento.GuiaPagamentoItem;
import gsan.arrecadacao.pagamento.GuiaPagamentoItemCategoria;
import gsan.arrecadacao.pagamento.GuiaPagamentoItemCategoriaHistorico;
import gsan.arrecadacao.pagamento.GuiaPagamentoItemHistorico;
import gsan.arrecadacao.pagamento.GuiaPagamentoParcelamentoCartao;
import gsan.arrecadacao.pagamento.Pagamento;
import gsan.arrecadacao.pagamento.PagamentoCartaoDebito;
import gsan.arrecadacao.pagamento.PagamentoCartaoDebitoItem;
import gsan.arrecadacao.pagamento.PagamentoHistorico;
import gsan.arrecadacao.pagamento.PagamentoSituacao;
import gsan.arrecadacao.pagamento.SequenciaCartao;
import gsan.atendimentopublico.EspecificacaoPavimentacaoServicoTipo;
import gsan.atendimentopublico.EspecificacaoUnidadeCobranca;
import gsan.atendimentopublico.FiscalizarParametroCalculoDebito;
import gsan.atendimentopublico.LigacaoOrigem;
import gsan.atendimentopublico.ResolucaoImagem;
import gsan.atendimentopublico.contratoadesao.ContratoAdesao;
import gsan.atendimentopublico.ligacaoagua.CorteTipo;
import gsan.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo;
import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacaoConsumoTipo;
import gsan.atendimentopublico.ligacaoagua.MotivoCorte;
import gsan.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gsan.atendimentopublico.ligacaoagua.SupressaoTipo;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacaoConsumoTipo;
import gsan.atendimentopublico.ordemservico.AnormalidadeComandoOSS;
import gsan.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.ArquivoTextoRetornoAcaoVisitaCampo;
import gsan.atendimentopublico.ordemservico.ArquivoTextoRetornoClienteFoneVisitaCampo;
import gsan.atendimentopublico.ordemservico.ArquivoTextoRetornoClienteVisitaCampo;
import gsan.atendimentopublico.ordemservico.ArquivoTextoRetornoVisitaCampo;
import gsan.atendimentopublico.ordemservico.ArquivoTextoVisitaCampo;
import gsan.atendimentopublico.ordemservico.Atividade;
import gsan.atendimentopublico.ordemservico.BoletimOsConcluida;
import gsan.atendimentopublico.ordemservico.CapacidHidrComandoOSS;
import gsan.atendimentopublico.ordemservico.ClieFoneSeletivaVisitaCampo;
import gsan.atendimentopublico.ordemservico.ClieOsSeletivaVisitaCampo;
import gsan.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gsan.atendimentopublico.ordemservico.CoordenadaPercursoEquipe;
import gsan.atendimentopublico.ordemservico.DataFiscalizacaoOsSeletiva;
import gsan.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gsan.atendimentopublico.ordemservico.Equipe;
import gsan.atendimentopublico.ordemservico.EquipeComponentes;
import gsan.atendimentopublico.ordemservico.EquipeEquipamentosEspeciais;
import gsan.atendimentopublico.ordemservico.EspecificacaoServicoTipo;
import gsan.atendimentopublico.ordemservico.FiscalizacaoColetiva;
import gsan.atendimentopublico.ordemservico.FiscalizacaoFoto;
import gsan.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gsan.atendimentopublico.ordemservico.FiscalizacaoSituacaoAgua;
import gsan.atendimentopublico.ordemservico.FiscalizacaoSituacaoEsgoto;
import gsan.atendimentopublico.ordemservico.FiscalizacaoSituacaoHidrometroCapacidade;
import gsan.atendimentopublico.ordemservico.FiscalizacaoSituacaoServicoACobrar;
import gsan.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gsan.atendimentopublico.ordemservico.FotoTipo;
import gsan.atendimentopublico.ordemservico.LigacaoSitComandoOSS;
import gsan.atendimentopublico.ordemservico.Material;
import gsan.atendimentopublico.ordemservico.MaterialUnidade;
import gsan.atendimentopublico.ordemservico.MensagemAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.MotivoRejeicao;
import gsan.atendimentopublico.ordemservico.OSAtividadeExecucaoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.OSAtividadeMaterialProgramacaoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.OSAtividadeProgramacaoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.OSPriorizacaoTipo;
import gsan.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico;
import gsan.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServicoAtividade;
import gsan.atendimentopublico.ordemservico.OrdemServicoBoletim;
import gsan.atendimentopublico.ordemservico.OrdemServicoFiscSit;
import gsan.atendimentopublico.ordemservico.OrdemServicoFoto;
import gsan.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gsan.atendimentopublico.ordemservico.OrdemServicoMovimentoHistorico;
import gsan.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gsan.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gsan.atendimentopublico.ordemservico.OrdemServicoSituacao;
import gsan.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gsan.atendimentopublico.ordemservico.OsAtividadeMaterialExecucao;
import gsan.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao;
import gsan.atendimentopublico.ordemservico.OsExecucaoEquipe;
import gsan.atendimentopublico.ordemservico.OsExecucaoEquipeComponentes;
import gsan.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo;
import gsan.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gsan.atendimentopublico.ordemservico.OsSeletivaVisitaCampo;
import gsan.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gsan.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gsan.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gsan.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gsan.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gsan.atendimentopublico.ordemservico.ServicoTipoGrupo;
import gsan.atendimentopublico.ordemservico.ServicoTipoMaterial;
import gsan.atendimentopublico.ordemservico.ServicoTipoMotivoEncerramento;
import gsan.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gsan.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gsan.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gsan.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gsan.atendimentopublico.ordemservico.SupressaoMotivo;
import gsan.atendimentopublico.portal.AcessoLojaVirtual;
import gsan.atendimentopublico.portal.QuestionarioSatisfacaoCliente;
import gsan.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao;
import gsan.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno;
import gsan.atendimentopublico.registroatendimento.ArquivoProcedimentoOperacionalPadrao;
import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncAcaoCobranca;
import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gsan.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;
import gsan.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gsan.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gsan.atendimentopublico.registroatendimento.LocalOcorrencia;
import gsan.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidade;
import gsan.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupo;
import gsan.atendimentopublico.registroatendimento.MeioSolicitacao;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacional;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gsan.atendimentopublico.registroatendimento.RAReiteracao;
import gsan.atendimentopublico.registroatendimento.RAReiteracaoFone;
import gsan.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora;
import gsan.atendimentopublico.registroatendimento.RaDadosAgenciaReguladoraFone;
import gsan.atendimentopublico.registroatendimento.RaEncerramentoComando;
import gsan.atendimentopublico.registroatendimento.RaEncerramentoComandoEspecificacoes;
import gsan.atendimentopublico.registroatendimento.RaEnderecoDescritivo;
import gsan.atendimentopublico.registroatendimento.RaMotivoReativacao;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gsan.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorio;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gsan.atendimentopublico.registroatendimento.SolicitanteFone;
import gsan.atendimentopublico.registroatendimento.Tramite;
import gsan.atendimentopublico.registroatendimento.VisualizacaoRegistroAtendimentoUrgencia;
import gsan.atualizacaocadastral.AreaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ArquivoTextoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.AtributoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.CepAtlzCadDM;
import gsan.atualizacaocadastral.ClienteAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ClienteFoneAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ImovelFotoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ImovelOcorrenciaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ImovelSubcategoriaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.LogradouroAtlzCadDM;
import gsan.atualizacaocadastral.LogradouroBairroAtlzCadDM;
import gsan.atualizacaocadastral.LogradouroCepAtlzCadDM;
import gsan.atualizacaocadastral.MapaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.MensagemAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ParametroQuadraAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ParametroTabelaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.RetornoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.SituacaoTransmissaoAtualizacaoCadastralDM;
import gsan.batch.FuncionalidadeIniciada;
import gsan.batch.FuncionalidadeSituacao;
import gsan.batch.Processo;
import gsan.batch.ProcessoFuncionalidade;
import gsan.batch.ProcessoIniciado;
import gsan.batch.ProcessoSituacao;
import gsan.batch.ProcessoTipo;
import gsan.batch.Relatorio;
import gsan.batch.RelatorioGerado;
import gsan.batch.UnidadeIniciada;
import gsan.batch.UnidadeProcessamento;
import gsan.batch.UnidadeSituacao;
import gsan.batch.auxiliarbatch.CobrancaDocumentoControleGeracao;
import gsan.cadastro.ArquivoTextoAtualizacaoCadastral;
import gsan.cadastro.ContaBraile;
import gsan.cadastro.ContaEmpresaSMS;
import gsan.cadastro.CpfTipo;
import gsan.cadastro.DbVersaoBase;
import gsan.cadastro.EmailClienteAlterado;
import gsan.cadastro.EmpresaContratoCadastro;
import gsan.cadastro.EmpresaContratoCadastroAtributo;
import gsan.cadastro.EnvioEmail;
import gsan.cadastro.MensagemEmailFaturamentoCobranca;
import gsan.cadastro.MensagemEmailHistorico;
import gsan.cadastro.MensagemRetornoReceitaFederal;
import gsan.cadastro.MensagemSMSFaturamentoCobranca;
import gsan.cadastro.MensagemSMSHistorico;
import gsan.cadastro.MotivoRetiradaCobranca;
import gsan.cadastro.ParametroTabelaAtualizacaoCadastro;
import gsan.cadastro.ParametrosMSGSMSEmail;
import gsan.cadastro.SMSSequenciaEnvio;
import gsan.cadastro.SistemaAndroid;
import gsan.cadastro.SituacaoAtualizacaoCadastral;
import gsan.cadastro.VersaoMobile;
import gsan.cadastro.VersaoSistemasAndroid;
import gsan.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gsan.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gsan.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gsan.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCriticaTipo;
import gsan.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteAtualizacaoCadastral;
import gsan.cadastro.cliente.ClienteConta;
import gsan.cadastro.cliente.ClienteContaHistorico;
import gsan.cadastro.cliente.ClienteEndereco;
import gsan.cadastro.cliente.ClienteFone;
import gsan.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gsan.cadastro.cliente.ClienteGuiaPagamento;
import gsan.cadastro.cliente.ClienteGuiaPagamentoHistorico;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteImovelEconomia;
import gsan.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.ClienteVirtual;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.cliente.FoneTipo;
import gsan.cadastro.cliente.OrgaoExpedidorRg;
import gsan.cadastro.cliente.PessoaSexo;
import gsan.cadastro.cliente.Profissao;
import gsan.cadastro.cliente.RamoAtividade;
import gsan.cadastro.dadocensitario.FonteDadosCensitario;
import gsan.cadastro.dadocensitario.IbgeSetorCensitario;
import gsan.cadastro.dadocensitario.IbgeSetorCensitarioDado;
import gsan.cadastro.dadocensitario.LocalidadeDadosCensitario;
import gsan.cadastro.dadocensitario.MunicipioDadosCensitario;
import gsan.cadastro.descricaogenerica.DescricaoGenerica;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.EmpresaCobranca;
import gsan.cadastro.empresa.EmpresaCobrancaFaixa;
import gsan.cadastro.empresa.EmpresaContratoCobranca;
import gsan.cadastro.endereco.Cep;
import gsan.cadastro.endereco.CepTipo;
import gsan.cadastro.endereco.EnderecoReferencia;
import gsan.cadastro.endereco.EnderecoTipo;
import gsan.cadastro.endereco.Logradouro;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.endereco.LogradouroCep;
import gsan.cadastro.endereco.LogradouroTipo;
import gsan.cadastro.endereco.LogradouroTitulo;
import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.funcionario.FuncionarioCargo;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.BairroArea;
import gsan.cadastro.geografico.Microrregiao;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.geografico.MunicipioFeriado;
import gsan.cadastro.geografico.Regiao;
import gsan.cadastro.geografico.RegiaoDesenvolvimento;
import gsan.cadastro.geografico.UnidadeFederacao;
import gsan.cadastro.imovel.AreaConstruidaFaixa;
import gsan.cadastro.imovel.CadastroOcorrencia;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.CategoriaTipo;
import gsan.cadastro.imovel.Despejo;
import gsan.cadastro.imovel.EloAnormalidade;
import gsan.cadastro.imovel.EntidadeBeneficente;
import gsan.cadastro.imovel.FonteAbastecimento;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelAtualizacaoCadastral;
import gsan.cadastro.imovel.ImovelCadastroOcorrencia;
import gsan.cadastro.imovel.ImovelCobrancaSituacao;
import gsan.cadastro.imovel.ImovelContaEnvio;
import gsan.cadastro.imovel.ImovelDoacao;
import gsan.cadastro.imovel.ImovelEconomia;
import gsan.cadastro.imovel.ImovelEloAnormalidade;
import gsan.cadastro.imovel.ImovelEnderecoAnterior;
import gsan.cadastro.imovel.ImovelInscricaoAlterada;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.imovel.ImovelProgramaEspecial;
import gsan.cadastro.imovel.ImovelRamoAtividade;
import gsan.cadastro.imovel.ImovelSituacao;
import gsan.cadastro.imovel.ImovelSituacaoTipo;
import gsan.cadastro.imovel.ImovelSubcategoria;
import gsan.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gsan.cadastro.imovel.ImovelSuprimido;
import gsan.cadastro.imovel.ImovelTipoCobertura;
import gsan.cadastro.imovel.ImovelTipoConstrucao;
import gsan.cadastro.imovel.ImovelTipoHabitacao;
import gsan.cadastro.imovel.ImovelTipoPropriedade;
import gsan.cadastro.imovel.ItemMovimentoProgramaEspecial;
import gsan.cadastro.imovel.MovimentoProgramaEspecial;
import gsan.cadastro.imovel.PavimentoCalcada;
import gsan.cadastro.imovel.PavimentoRua;
import gsan.cadastro.imovel.PiscinaVolumeFaixa;
import gsan.cadastro.imovel.PocoTipo;
import gsan.cadastro.imovel.ReservatorioVolumeFaixa;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cadastro.imovel.VwImovelPrincipalCategoria;
import gsan.cadastro.localidade.AreaTipo;
import gsan.cadastro.localidade.CondicaoAbastecimentoAgua;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.GrauDificuldadeExecucao;
import gsan.cadastro.localidade.GrauIntermitencia;
import gsan.cadastro.localidade.GrauRiscoSegurancaFisica;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.LocalidadeClasse;
import gsan.cadastro.localidade.LocalidadePorte;
import gsan.cadastro.localidade.NivelPressao;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.QuadraFace;
import gsan.cadastro.localidade.QuadraPerfil;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.localidade.Zeis;
import gsan.cadastro.projeto.Projeto;
import gsan.cadastro.sistemaparametro.NacionalFeriado;
import gsan.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.tarifasocial.RendaTipo;
import gsan.cadastro.tarifasocial.TarifaSocialCarta;
import gsan.cadastro.tarifasocial.TarifaSocialCartaDebito;
import gsan.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gsan.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gsan.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gsan.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gsan.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gsan.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacionalMunicipio;
import gsan.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gsan.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gsan.cadastro.unidade.UnidadeTipo;
import gsan.cobranca.*;
import gsan.cobranca.contratoparcelamento.ContratoParcelamento;
import gsan.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gsan.cobranca.contratoparcelamento.ContratoParcelamentoItem;
import gsan.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gsan.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gsan.cobranca.contratoparcelamento.PrestacaoItemContratoParcelamento;
import gsan.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gsan.cobranca.contratoparcelamento.TipoRelacao;
import gsan.cobranca.parcelamento.ParcDesctoInativVista;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gsan.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gsan.cobranca.parcelamento.ParcelamentoFaixaValor;
import gsan.cobranca.parcelamento.ParcelamentoItem;
import gsan.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gsan.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gsan.cobranca.parcelamento.ParcelamentoPerfil;
import gsan.cobranca.parcelamento.ParcelamentoPerfilDebitos;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua;
import gsan.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento;
import gsan.cobranca.parcelamento.ParcelamentoSituacao;
import gsan.cobranca.parcelamento.ParcelamentoTipo;
import gsan.faturamento.ConsumoFaixaCategoria;
import gsan.faturamento.ConsumoFaixaLigacao;
import gsan.faturamento.ConsumoMinimoParametro;
import gsan.faturamento.ContaRevisaoFaixaValor;
import gsan.faturamento.DocumentoNaoEntregue;
import gsan.faturamento.ExtratoQuitacao;
import gsan.faturamento.ExtratoQuitacaoItem;
import gsan.faturamento.FaturamentoAtivCronRota;
import gsan.faturamento.FaturamentoAtividade;
import gsan.faturamento.FaturamentoAtividadeCronograma;
import gsan.faturamento.FaturamentoContabilParametros;
import gsan.faturamento.FaturamentoDados;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FaturamentoGrupoCanceladoHistorico;
import gsan.faturamento.FaturamentoGrupoCronogramaMensal;
import gsan.faturamento.FaturamentoImediatoAjuste;
import gsan.faturamento.FaturamentoSituacaoComando;
import gsan.faturamento.FaturamentoSituacaoHistorico;
import gsan.faturamento.FaturamentoSituacaoMotivo;
import gsan.faturamento.FaturamentoSituacaoTipo;
import gsan.faturamento.FaturamentoTipo;
import gsan.faturamento.GuiaPagamentoGeral;
import gsan.faturamento.HistogramaAguaEconomia;
import gsan.faturamento.HistogramaAguaEconomiaSemQuadra;
import gsan.faturamento.HistogramaAguaLigacao;
import gsan.faturamento.HistogramaAguaLigacaoSemQuadra;
import gsan.faturamento.HistogramaEsgotoEconomia;
import gsan.faturamento.HistogramaEsgotoEconomiaSemQuadra;
import gsan.faturamento.HistogramaEsgotoLigacao;
import gsan.faturamento.HistogramaEsgotoLigacaoSemQuadra;
import gsan.faturamento.ImpostoTipo;
import gsan.faturamento.ImpostoTipoAliquota;
import gsan.faturamento.MotivoInterferenciaTipo;
import gsan.faturamento.MovimentoContaCategoriaConsumoFaixa;
import gsan.faturamento.MovimentoContaImpostoDeduzido;
import gsan.faturamento.MovimentoContaPrefaturada;
import gsan.faturamento.MovimentoContaPrefaturadaCategoria;
import gsan.faturamento.Prescricao;
import gsan.faturamento.QualidadeAgua;
import gsan.faturamento.QualidadeAguaPadrao;
import gsan.faturamento.ResumoFaturamentoSimulacao;
import gsan.faturamento.ResumoFaturamentoSimulacaoCredito;
import gsan.faturamento.ResumoFaturamentoSimulacaoDebito;
import gsan.faturamento.ResumoFaturamentoSituacaoEspecial;
import gsan.faturamento.ResumoFaturamentoSituacaoEspecialDetalhe;
import gsan.faturamento.TarifaTipoCalculo;
import gsan.faturamento.VencimentoAlternativo;
import gsan.faturamento.autoinfracao.AutoInfracaoSituacao;
import gsan.faturamento.autoinfracao.AutosInfracao;
import gsan.faturamento.autoinfracao.AutosInfracaoDebitoACobrar;
import gsan.faturamento.consumotarifa.ConsumoTarifa;
import gsan.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gsan.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gsan.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaCategoria;
import gsan.faturamento.conta.ContaCategoriaConsumoFaixa;
import gsan.faturamento.conta.ContaCategoriaConsumoFaixaHistorico;
import gsan.faturamento.conta.ContaCategoriaHistorico;
import gsan.faturamento.conta.ContaComunicado;
import gsan.faturamento.conta.ContaComunicadoFaturamentoGrupo;
import gsan.faturamento.conta.ContaComunicadoQuadra;
import gsan.faturamento.conta.ContaComunicadoRota;
import gsan.faturamento.conta.ContaComunicadoSetor;
import gsan.faturamento.conta.ContaGeral;
import gsan.faturamento.conta.ContaHistorico;
import gsan.faturamento.conta.ContaImpostosDeduzidos;
import gsan.faturamento.conta.ContaImpostosDeduzidosHistorico;
import gsan.faturamento.conta.ContaImpressao;
import gsan.faturamento.conta.ContaMensagem;
import gsan.faturamento.conta.ContaMotivoCancelamento;
import gsan.faturamento.conta.ContaMotivoInclusao;
import gsan.faturamento.conta.ContaMotivoRetificacao;
import gsan.faturamento.conta.ContaMotivoRetificacaoColuna;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.faturamento.conta.ContaTipo;
import gsan.faturamento.conta.Fatura;
import gsan.faturamento.conta.FaturaItem;
import gsan.faturamento.conta.FaturaItemHistorico;
import gsan.faturamento.conta.MotivoNaoEntregaDocumento;
import gsan.faturamento.conta.Refaturamento;
import gsan.faturamento.contratodemanda.ContratoDemandaFaixaConsumo;
import gsan.faturamento.contratodemanda.ContratoDemandaImovel;
import gsan.faturamento.contratodemanda.ContratoDemandaMotivoEncerramento;
import gsan.faturamento.contratodemanda.ContratoDemandaSituacao;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.credito.CreditoARealizarCategoria;
import gsan.faturamento.credito.CreditoARealizarCategoriaHistorico;
import gsan.faturamento.credito.CreditoARealizarGeral;
import gsan.faturamento.credito.CreditoARealizarHistorico;
import gsan.faturamento.credito.CreditoOrigem;
import gsan.faturamento.credito.CreditoRealizado;
import gsan.faturamento.credito.CreditoRealizadoCategoria;
import gsan.faturamento.credito.CreditoRealizadoCategoriaHistorico;
import gsan.faturamento.credito.CreditoRealizadoHistorico;
import gsan.faturamento.credito.CreditoTipo;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.faturamento.debito.DebitoACobrarCategoria;
import gsan.faturamento.debito.DebitoACobrarCategoriaHistorico;
import gsan.faturamento.debito.DebitoACobrarGeral;
import gsan.faturamento.debito.DebitoACobrarHistorico;
import gsan.faturamento.debito.DebitoCobrado;
import gsan.faturamento.debito.DebitoCobradoCategoria;
import gsan.faturamento.debito.DebitoCobradoCategoriaHistorico;
import gsan.faturamento.debito.DebitoCobradoHistorico;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.faturamento.debito.DebitoFaixaValore;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.DebitoTipoVigencia;
import gsan.financeiro.ContaAReceberContabil;
import gsan.financeiro.ContaContabil;
import gsan.financeiro.DevedoresDuvidososContabilParametro;
import gsan.financeiro.DocumentosAReceberFaixaResumo;
import gsan.financeiro.DocumentosAReceberResumo;
import gsan.financeiro.FaixaDocumentosAReceber;
import gsan.financeiro.FinanciamentoTipo;
import gsan.financeiro.LancamentoResumo;
import gsan.financeiro.LancamentoResumoConta;
import gsan.financeiro.LancamentoResumoContaHistorico;
import gsan.financeiro.LancamentoResumoValorTipo;
import gsan.financeiro.ParametrosDevedoresDuvidosos;
import gsan.financeiro.ParametrosDevedoresDuvidososItem;
import gsan.financeiro.ParametrosPerdasOrgaoPublico;
import gsan.financeiro.ParametrosPerdasSocietarias;
import gsan.financeiro.PerdasTipo;
import gsan.financeiro.ResumoDevedoresDuvidosos;
import gsan.financeiro.ResumoFaturamento;
import gsan.financeiro.ResumoReceita;
import gsan.financeiro.ValorConsumidoNaoFaturadoParametro;
import gsan.financeiro.ValorVolumesConsumidosNaoFaturado;
import gsan.financeiro.lancamento.LancamentoContabil;
import gsan.financeiro.lancamento.LancamentoContabilItem;
import gsan.financeiro.lancamento.LancamentoItem;
import gsan.financeiro.lancamento.LancamentoItemContabil;
import gsan.financeiro.lancamento.LancamentoOrigem;
import gsan.financeiro.lancamento.LancamentoTipo;
import gsan.financeiro.lancamento.LancamentoTipoItem;
import gsan.gerencial.arrecadacao.GArrecadacaoForma;
import gsan.gerencial.arrecadacao.GArrecadador;
import gsan.gerencial.arrecadacao.GDevolucaoSituacao;
import gsan.gerencial.arrecadacao.UnResumoArrecadacao;
import gsan.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto;
import gsan.gerencial.arrecadacao.UnResumoArrecadacaoCredito;
import gsan.gerencial.arrecadacao.UnResumoArrecadacaoOutro;
import gsan.gerencial.arrecadacao.UnResumoArrecadacaoPorAno;
import gsan.gerencial.arrecadacao.pagamento.GEpocaPagamento;
import gsan.gerencial.arrecadacao.pagamento.GPagamentoSituacao;
import gsan.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gsan.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gsan.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gsan.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gsan.gerencial.atendimentopublico.registroatendimento.GAtendimentoMotivoEncerramento;
import gsan.gerencial.atendimentopublico.registroatendimento.GMeioSolicitacao;
import gsan.gerencial.atendimentopublico.registroatendimento.GSolicitacaoTipo;
import gsan.gerencial.atendimentopublico.registroatendimento.GSolicitacaoTipoEspecificacao;
import gsan.gerencial.atendimentopublico.registroatendimento.UnResumoRegistroAtendimento;
import gsan.gerencial.atendimentopublico.registroatendimento.UnResumoRegistroAtendimentoPorAno;
import gsan.gerencial.cadastro.GEmpresa;
import gsan.gerencial.cadastro.Indicador;
import gsan.gerencial.cadastro.RgResumoLigacaoEconomia;
import gsan.gerencial.cadastro.UnResumoConsumoAgua;
import gsan.gerencial.cadastro.UnResumoIndicadorLigacaoEconomia;
import gsan.gerencial.cadastro.UnResumoLigacaoEconomia;
import gsan.gerencial.cadastro.cliente.GClienteTipo;
import gsan.gerencial.cadastro.cliente.GEsferaPoder;
import gsan.gerencial.cadastro.geografico.GBairro;
import gsan.gerencial.cadastro.geografico.GMicrorregiao;
import gsan.gerencial.cadastro.geografico.GMunicipio;
import gsan.gerencial.cadastro.geografico.GRegiao;
import gsan.gerencial.cadastro.imovel.GCategoria;
import gsan.gerencial.cadastro.imovel.GImovelPerfil;
import gsan.gerencial.cadastro.imovel.GSubcategoria;
import gsan.gerencial.cadastro.localidade.GGerenciaRegional;
import gsan.gerencial.cadastro.localidade.GLocalidade;
import gsan.gerencial.cadastro.localidade.GLocalidadePorte;
import gsan.gerencial.cadastro.localidade.GQuadra;
import gsan.gerencial.cadastro.localidade.GSetorComercial;
import gsan.gerencial.cadastro.localidade.GUnidadeNegocio;
import gsan.gerencial.cadastro.unidade.GUnidadeOrganizacional;
import gsan.gerencial.cobranca.FaixaValor;
import gsan.gerencial.cobranca.GDocumentoTipo;
import gsan.gerencial.cobranca.UnResumoIndicadoresCobranca;
import gsan.gerencial.cobranca.UnResumoParcelamento;
import gsan.gerencial.cobranca.UnResumoParcelamentoPorAno;
import gsan.gerencial.cobranca.UnResumoPendencia;
import gsan.gerencial.cobranca.UnResumoPendenciaSemQuadra;
import gsan.gerencial.faturamento.GConsumoTarifa;
import gsan.gerencial.faturamento.GFaturamentoGrupo;
import gsan.gerencial.faturamento.GImpostoTipo;
import gsan.gerencial.faturamento.UnResumoFaturamento;
import gsan.gerencial.faturamento.UnResumoIndicadoresFaturamento;
import gsan.gerencial.faturamento.UnResumoRefaturamento;
import gsan.gerencial.faturamento.credito.GCreditoOrigem;
import gsan.gerencial.faturamento.credito.GCreditoTipo;
import gsan.gerencial.faturamento.debito.GDebitoTipo;
import gsan.gerencial.financeiro.GFinanciamentoTipo;
import gsan.gerencial.financeiro.lancamento.GLancamentoItem;
import gsan.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import gsan.gerencial.micromedicao.GRota;
import gsan.gerencial.micromedicao.UnResumoColetaEsgoto;
import gsan.gerencial.micromedicao.UnResumoHidrometro;
import gsan.gerencial.micromedicao.UnResumoIndicadorDesempenhoMicromedicao;
import gsan.gerencial.micromedicao.UnResumoIndicadorDesempenhoMicromedicaoRef2010;
import gsan.gerencial.micromedicao.UnResumoInstalacaoHidrometro;
import gsan.gerencial.micromedicao.UnResumoInstalacaoHidrometroPorAno;
import gsan.gerencial.micromedicao.UnResumoLeituraAnormalidade;
import gsan.gerencial.micromedicao.UnResumoMeta;
import gsan.gerencial.micromedicao.UnResumoMetasAcumulado;
import gsan.gerencial.micromedicao.consumo.GConsumoTipo;
import gsan.gerencial.micromedicao.hidrometro.GHidrometroCapacidade;
import gsan.gerencial.micromedicao.hidrometro.GHidrometroClasseMetrologica;
import gsan.gerencial.micromedicao.hidrometro.GHidrometroDiametro;
import gsan.gerencial.micromedicao.hidrometro.GHidrometroLocalArmazenagem;
import gsan.gerencial.micromedicao.hidrometro.GHidrometroMarca;
import gsan.gerencial.micromedicao.hidrometro.GHidrometroMotivoBaixa;
import gsan.gerencial.micromedicao.hidrometro.GHidrometroSituacao;
import gsan.gerencial.micromedicao.hidrometro.GHidrometroTipo;
import gsan.gerencial.micromedicao.leitura.GLeituraSituacao;
import gsan.gerencial.micromedicao.medicao.GMedicaoTipo;
import gsan.gerencial.operacional.GDistritoOperacional;
import gsan.integracao.ServicoTerceiroAcompanhamentoServico;
import gsan.interceptor.Interceptador;
import gsan.micromedicao.ArquivoTextoRoteiroEmpresa;
import gsan.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gsan.micromedicao.ConsumoMinimoArea;
import gsan.micromedicao.ContratoEmpresaAditivo;
import gsan.micromedicao.ContratoEmpresaServico;
import gsan.micromedicao.ImovelTestesMedicaoConsumo;
import gsan.micromedicao.ItemContratoServicoTipo;
import gsan.micromedicao.ItemServico;
import gsan.micromedicao.ItemServicoContrato;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.MovimentoRoteiroEmpresa;
import gsan.micromedicao.MovimentoRoteiroEmpresaFoto;
import gsan.micromedicao.RateioTipo;
import gsan.micromedicao.ReleituraMobile;
import gsan.micromedicao.ResumoAnormalidadeLeitura;
import gsan.micromedicao.Rota;
import gsan.micromedicao.RotaAtualizacaoSeq;
import gsan.micromedicao.RoteiroEmpresa;
import gsan.micromedicao.ServicoTipoCelular;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.micromedicao.TelemetriaLog;
import gsan.micromedicao.TelemetriaMov;
import gsan.micromedicao.TelemetriaMovReg;
import gsan.micromedicao.TelemetriaRetMot;
import gsan.micromedicao.consumo.ConsumoAnormalidade;
import gsan.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gsan.micromedicao.consumo.ConsumoHistorico;
import gsan.micromedicao.consumo.ConsumoHistoricoAnterior;
import gsan.micromedicao.consumo.ConsumoTipo;
import gsan.micromedicao.consumo.LigacaoTipo;
import gsan.micromedicao.consumo.ResumoAnormalidadeConsumo;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroCapacidade;
import gsan.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gsan.micromedicao.hidrometro.HidrometroClassePressao;
import gsan.micromedicao.hidrometro.HidrometroDiametro;
import gsan.micromedicao.hidrometro.HidrometroFatorCorrecao;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gsan.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.HidrometroMarca;
import gsan.micromedicao.hidrometro.HidrometroMotivoBaixa;
import gsan.micromedicao.hidrometro.HidrometroMotivoMovimentacao;
import gsan.micromedicao.hidrometro.HidrometroMovimentacao;
import gsan.micromedicao.hidrometro.HidrometroMovimentado;
import gsan.micromedicao.hidrometro.HidrometroProtecao;
import gsan.micromedicao.hidrometro.HidrometroRelojoaria;
import gsan.micromedicao.hidrometro.HidrometroSituacao;
import gsan.micromedicao.hidrometro.HidrometroTipo;
import gsan.micromedicao.hidrometro.RetornoControleHidrometro;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gsan.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gsan.micromedicao.leitura.LeituraFaixaFalsa;
import gsan.micromedicao.leitura.LeituraFiscalizacao;
import gsan.micromedicao.leitura.LeituraSituacao;
import gsan.micromedicao.leitura.LeituraTipo;
import gsan.micromedicao.medicao.MedicaoHistorico;
import gsan.micromedicao.medicao.MedicaoHistoricoAnterior;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.operacional.Bacia;
import gsan.operacional.DistritoOperacional;
import gsan.operacional.DivisaoEsgoto;
import gsan.operacional.FonteCaptacao;
import gsan.operacional.ProducaoAgua;
import gsan.operacional.SetorAbastecimento;
import gsan.operacional.SetorFonteCaptacao;
import gsan.operacional.SistemaAbastecimento;
import gsan.operacional.SistemaEsgoto;
import gsan.operacional.SistemaEsgotoTratamentoTipo;
import gsan.operacional.TipoCaptacao;
import gsan.operacional.ZonaAbastecimento;
import gsan.operacional.ZonaPressao;
import gsan.operacional.abastecimento.AbastecimentoProgramacao;
import gsan.operacional.abastecimento.ManutencaoProgramacao;
import gsan.seguranca.Atributo;
import gsan.seguranca.AtributoGrupo;
import gsan.seguranca.ConsultarReceitaFederal;
import gsan.seguranca.FuncionalidadeAtributo;
import gsan.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gsan.seguranca.acesso.Funcionalidade;
import gsan.seguranca.acesso.FuncionalidadeCategoria;
import gsan.seguranca.acesso.FuncionalidadeDependencia;
import gsan.seguranca.acesso.Grupo;
import gsan.seguranca.acesso.GrupoAcesso;
import gsan.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gsan.seguranca.acesso.GrupoPermissaoEspecial;
import gsan.seguranca.acesso.Modulo;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.OperacaoOrdemExibicao;
import gsan.seguranca.acesso.OperacaoTabela;
import gsan.seguranca.acesso.OperacaoTipo;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.SolicitacaoAcesso;
import gsan.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gsan.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAbrangencia;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAlteracao;
import gsan.seguranca.acesso.usuario.UsuarioBanco;
import gsan.seguranca.acesso.usuario.UsuarioFavorito;
import gsan.seguranca.acesso.usuario.UsuarioGrupo;
import gsan.seguranca.acesso.usuario.UsuarioGrupoRestricao;
import gsan.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gsan.seguranca.acesso.usuario.UsuarioSenhaHistorico;
import gsan.seguranca.acesso.usuario.UsuarioSituacao;
import gsan.seguranca.acesso.usuario.UsuarioTipo;
import gsan.seguranca.parametrosistema.ParametroSistema;
import gsan.seguranca.parametrosistema.ParametroTipo;
import gsan.seguranca.transacao.AlteracaoTipo;
import gsan.seguranca.transacao.SgbdTabela;
import gsan.seguranca.transacao.SgbdTabelaColuna;
import gsan.seguranca.transacao.Tabela;
import gsan.seguranca.transacao.TabelaAtualizacaoCadastral;
import gsan.seguranca.transacao.TabelaAtualizacaoCadastralSituacao;
import gsan.seguranca.transacao.TabelaColuna;
import gsan.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gsan.seguranca.transacao.TabelaLinhaAlteracao;
import gsan.seguranca.transacao.TabelaLinhaColunaAlteracao;

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
 * Classe responsável pela instanciação do Hibernate e serviços específicos da
 * tecnologia
 * 
 * @author rodrigo
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private static SessionFactory sessionFactoryGerencial;
	
	private static SessionFactory sessionFactoryIntegracaoSAM;

	private static SessionFactory sessionFactoryIntegracaoIFS;
	
	private static Configuration configuration;
	
	private static Configuration configurationUser;

	private static Configuration configurationGerencial;
	
	private static Configuration configurationIFS;
		
	private static HashMap<Integer, Long> tempoSession = new HashMap<Integer, Long>();
	
	public static Logger log;
	
	static { 
	      log = Logger.getLogger("GSAN_ENTIDADES_CONSULTAS");

	      log.debug(";ClasseChamadaN2;MetodoChamadaN2;ClasseChamadaN1;MetodoChamadaN1;NomeEntidade;QtdEntidadesConsultadas;TempoConsulta;OutrasEntidadesConsultadas");
	}

	public static void inicializarSessionFactoryIFS() {
		configurationIFS = new Configuration();
		configurationIFS.setProperty("hibernate.connection.datasource","java:/OracleIFSDS");
	
		sessionFactoryIntegracaoIFS = configurationIFS.buildSessionFactory();
	}
	
	public static void inicializarSessionFactory() {

		try {

			//-------------------Configuração do servidor Gerencial------------------//
			configurationGerencial = new Configuration();
			configuration = new Configuration();
			
			
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

			//-------------------Configuração do servidor Gerencial------------------//	
		

			configuration
			// **********************************************/
					// CLASSES DO PACOTE gsan.atendimentopublico //
					// ********************************************//
					// gsan.atendimentopublico.ligacaoagua
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
							
					// gsan.atendimentopublico.ligacaoesgoto
					.addClass(LigacaoEsgoto.class).addClass(
							LigacaoEsgotoDiametro.class).addClass(
							LigacaoEsgotoMaterial.class).addClass(
							LigacaoEsgotoPerfil.class).addClass(
							LigacaoEsgotoSituacao.class)
					// gsan.atendimentopublico.registroatendimento
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
				   
					// gsan.atendimentopublico.ordemservico
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
					// CLASSES DO PACOTE gsan.cadastro //
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
					// gsan.cadastro.cliente
					.addClass(CpfTipo.class).addClass(Cliente.class).addClass(ClienteEndereco.class)
					.addClass(ImovelCadastroOcorrencia.class).addClass(
							ImovelEloAnormalidade.class).addClass(
							ClienteFone.class).addClass(ClienteImovel.class)
							.addClass(
							ClienteRelacaoTipo.class).addClass(
							ClienteImovelEconomia.class).addClass(
							ClienteImovelFimRelacaoMotivo.class).addClass(
							ClienteTipo.class).addClass(FoneTipo.class)
					.addClass(ClienteConta.class).addClass(
							ClienteContaHistorico.class).addClass(
							OrgaoExpedidorRg.class).addClass(PessoaSexo.class)
					.addClass(Profissao.class).addClass(RamoAtividade.class)
					.addClass(EsferaPoder.class).addClass(
							ClienteGuiaPagamento.class).addClass(
							ClienteGuiaPagamentoHistorico.class).addClass(
							SituacaoAtualizacaoCadastral.class)
							.addClass(ClienteAtualizacaoCadastral.class)
							.addClass(ClienteFoneAtualizacaoCadastral.class)
					// gsan.cadastro.dadocensitario
					.addClass(LocalidadeDadosCensitario.class).addClass(
							MunicipioDadosCensitario.class).addClass(
							IbgeSetorCensitarioDado.class).addClass(
							FonteDadosCensitario.class).addClass(
							IbgeSetorCensitario.class)
					// gsan.cadastro.empresa
					.addClass(Empresa.class)
					.addClass(EmpresaCobranca.class)
					.addClass(EmpresaCobrancaFaixa.class)
					// gsan.cadastro.endereco
					.addClass(LogradouroCep.class).addClass(Cep.class)
					.addClass(CepTipo.class).addClass(EnderecoReferencia.class)
					.addClass(EnderecoTipo.class).addClass(Logradouro.class)
					.addClass(LogradouroBairro.class).addClass(
							LogradouroTipo.class).addClass(
							LogradouroTitulo.class)
					// gsan.cadastro.funcionario
					.addClass(Funcionario.class).addClass(FuncionarioCargo.class)
					// gsan.cadastro.geografico
					.addClass(Bairro.class).addClass(Microrregiao.class)
					.addClass(Municipio.class).addClass(MunicipioFeriado.class)
					.addClass(Regiao.class).addClass(
							RegiaoDesenvolvimento.class).addClass(
							UnidadeFederacao.class).addClass(BairroArea.class)
					// gsan.cadastro.imovel
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
					// gsan.cadastro.localidade
					.addClass(GerenciaRegional.class)
					.addClass(Localidade.class).addClass(LocalidadePorte.class)
					.addClass(LocalidadeClasse.class).addClass(Quadra.class)
					.addClass(QuadraPerfil.class)
					.addClass(SetorComercial.class).addClass(Zeis.class)
					.addClass(AreaTipo.class).addClass(UnidadeNegocio.class)
					.addClass(QuadraFace.class)
					// gsan.cadastro.sistemaparametro
					.addClass(NacionalFeriado.class).addClass(
							SistemaParametro.class).addClass(
							SistemaAlteracaoHistorico.class)
					// gsan.cadastro.tarifasocial
					.addClass(RendaTipo.class).addClass(
							TarifaSocialCartaoTipo.class).addClass(
							TarifaSocialExclusaoMotivo.class).addClass(
							TarifaSocialDadoEconomia.class).addClass(
							TarifaSocialRevisaoMotivo.class)
					// gsan.cadastro.unidade
					.addClass(UnidadeOrganizacional.class).addClass(
							UnidadeTipo.class)
					.addClass(ArquivoTextoAtualizacaoCadastral.class)
					
					.addClass(EmpresaContratoCadastro.class)
					.addClass(EmpresaContratoCadastroAtributo.class)
					.addClass(UnidadeOrganizacionalMunicipio.class)
					// gsan.cadastro.atualizacaocadastralsimplificado
					.addClass(AtualizacaoCadastralSimplificado.class)
					.addClass(AtualizacaoCadastralSimplificadoCritica.class)
					.addClass(AtualizacaoCadastralSimplificadoBinario.class)
					.addClass(AtualizacaoCadastralSimplificadoCriticaTipo.class)
					.addClass(AtualizacaoCadastralSimplificadoLinha.class)
					// gsan.cadastro.projeto
					.addClass(Projeto.class)
					.addClass(EmpresaContratoCobranca.class)
					// gsan.cadastro.descricaogenerica
					.addClass(DescricaoGenerica.class)			
					.addClass(MensagemRetornoReceitaFederal.class)
					.addClass(ClienteVirtual.class)
					.addClass(MotivoRetiradaCobranca.class)					
					.addClass(ParametroTabelaAtualizacaoCadastro.class)
					
					// *************************************//
					// CLASSES DO PACOTE gsan.atualizacaocadastral //
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
					
					// *************************************//
					// CLASSES DO PACOTE gsan.cobranca //
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
							// CLASSES DO PACOTE gsan.cobranca.contratoparcelamento //
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
							// FIM CLASSES DO PACOTE gsan.cobranca.contratoparcelamento //
							// *************************************//

//							.addClass(CobrancaBoletimMedicao.class)
//							.addClass(CobrancaBoletimDesconto.class)
//							.addClass(CobrancaBoletimExecutado.class)
//							.addClass(CobrancaBoletimSucesso.class)


					// *************************************//
					// CLASSES DO PACOTE gsan.faturamento //
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

					// gsan.faturamento.conta ContaMensagem
					.addClass(ContaCategoriaConsumoFaixa.class).addClass(
							Conta.class).addClass(ContaCategoria.class)
					.addClass(MotivoNaoEntregaDocumento.class).addClass(
							Refaturamento.class).addClass(Fatura.class)
					.addClass(FaturaItem.class).addClass(ContaHistorico.class)
					.addClass(ContaImpostosDeduzidos.class).addClass(
							ContaMotivoCancelamento.class).addClass(
							ContaMotivoInclusao.class).addClass(
							ContaMotivoRetificacao.class).addClass(
							ContaMotivoRevisao.class)
					.addClass(ContaGeral.class).addClass(ContaImpressao.class)
					.addClass(ContaCategoriaConsumoFaixaHistorico.class)
					.addClass(ContaCategoriaHistorico.class).addClass(
							ContaImpostosDeduzidosHistorico.class).addClass(
							ContaTipo.class).addClass(
									ContaMotivoRetificacaoColuna.class)
					// gsan.faturamento.debito
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

					// gsan.faturamento.credito
					.addClass(CreditoRealizado.class).addClass(
							CreditoARealizar.class).addClass(
							CreditoARealizarCategoria.class).addClass(
							CreditoRealizadoHistorico.class).addClass(
							CreditoRealizadoCategoria.class).addClass(
							CreditoTipo.class).addClass(
							CreditoARealizarHistorico.class).addClass(
							CreditoOrigem.class).addClass(
							CreditoARealizarGeral.class)

					// gsan.faturamento.consumotarifa
					.addClass(ConsumoTarifa.class).addClass(
							ConsumoTarifaVigencia.class).addClass(
							ConsumoTarifaCategoria.class).addClass(
							ConsumoTarifaFaixa.class)
							
					// gsan.faturamento.debito
					.addClass(DebitoFaixaValore.class)
					
					// gsan.faturamento.autoinfracao
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
					// CLASSES DO PACOTE gsan.micromedicao //
					// *************************************//
					.addClass(Rota.class).addClass(RateioTipo.class).addClass(
							ImovelTestesMedicaoConsumo.class)
					.addClass(ItemContratoServicoTipo.class)		
					// gsan.micromedicao.hidrometro
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
							.addClass(TelemetriaMov.class)
							.addClass(TelemetriaMovReg.class)
							.addClass(TelemetriaRetMot.class)
							.addClass(ContratoEmpresaAditivo.class)
							.addClass(HidrometroFatorCorrecao.class)
							.addClass(HidrometroClassePressao.class)
							.addClass(MovimentoRoteiroEmpresaFoto.class)

					// gsan.micromedicao.leitura
					.addClass(LeituraTipo.class)
					.addClass(LeituraSituacao.class).addClass(
							LeituraFaixaFalsa.class).addClass(
							LeituraAnormalidadeLeitura.class).addClass(
							LeituraAnormalidade.class).addClass(
							LeituraFiscalizacao.class).addClass(
							LeituraAnormalidadeConsumo.class)
					// gsan.micromedicao.medicao //
					.addClass(MedicaoHistorico.class).addClass(
							MedicaoTipo.class)
					// gsan.micromedicao.consumo //
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
					// CLASSES DO PACOTE gsan.financeiro //
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
					// CLASSES DO PACOTE gsan.arrecadacao //
					// ************************************//
					// gsan.arrecadacao.banco

					.addClass(ResumoArrecadacao.class).addClass(Banco.class)
					.addClass(Agencia.class)
					// gsan.arrecadacao.pagamento
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
					// gsan.arrecadacao.debito
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
					// *************************************//
					// CLASSES DO PACOTE gsan.operacional //
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
					// CLASSES DO PACOTE gsan.seguranca //
					// ************************************//
					// gsan.seguranca.acesso
					.addClass(AlteracaoTipo.class).addClass(UsuarioTipo.class)
					.addClass(TabelaLinhaAlteracao.class).addClass(
							TabelaLinhaColunaAlteracao.class).addClass(
							TabelaColuna.class).addClass(Tabela.class)
					.addClass(UsuarioAcao.class)
					.addClass(UsuarioFavorito.class)
					.addClass(GrupoAcesso.class)
					.addClass(UsuarioSenhaHistorico.class)
					//gsan.seguranca.parametrosistema
					.addClass(ParametroSistema.class)
					.addClass(ParametroTipo.class)
					// gsan.seguranca.transacao
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
					// CLASSES DO PACOTE gsan.atendimentopublico.portal //
					// ************************************//
					
					.addClass(QuestionarioSatisfacaoCliente.class)
					.addClass(AcessoLojaVirtual.class)
					
					
					// ************************************//
					// FIM DAS CLASSES DO PACOTE gsan.atendimentopublico.portal //
					// ************************************//
					
					.addClass(ServicoTerceiroAcompanhamentoServico.class)
					
					
					// ***************************************************//
					// CLASSES DO PACOTE gsan.faturamento.contratodemanda //
					// ***************************************************//
					.addClass(ContratoDemandaImovel.class)
					.addClass(ContratoDemandaMotivoEncerramento.class)
					.addClass(ContratoDemandaSituacao.class)
					.addClass(ContratoDemandaFaixaConsumo.class)
					
					;
							
							
			configuration.setInterceptor(Interceptador.getInstancia());
			sessionFactory = configuration.buildSessionFactory();
			
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException(
					"Hibernate - Erro ao criar a SessionFactory");
		}finally{
			try {
				if (getDialect().toUpperCase().contains("ORACLE")){
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
	 * Fecha a session
	 * 
	 * @param session
	 *            Descrição do parâmetro
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

			
			
			String nomeEntidade = ((String) iterator.next());

			 
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
	 *            Descrição do parâmetro
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
	 * Método que obtém o tamanho da propriedade da classe
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
	 * Método que obtém o nome da coluna no banco da propriedade passada Caso
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
	 * Método que obtém o nome da tabela da classe passada
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
	 * Retorna a que classe está mapeada a tabela passada 
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
				//É setado Postgres como Default
				configurationGerencial.setProperty("hibernate.connection.datasource","java:/PostgresGerencialDS");
				configurationGerencial.setProperty("hibernate.connection.release_mode", "after_transaction");
				configurationGerencial.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
				
				configuration.setProperty("hibernate.connection.datasource","java:/PostgresDS");
				configuration.setProperty("hibernate.connection.release_mode", "after_transaction");
				configuration.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
				
			}
		} catch (Exception e) {

			e.printStackTrace();
			
			//É setado Postgres como Default
			configurationGerencial.setProperty("hibernate.connection.datasource","java:/PostgresGerencialDS");
			configurationGerencial.setProperty("hibernate.connection.release_mode", "after_transaction");
			configurationGerencial.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
			
			configuration.setProperty("hibernate.connection.datasource","java:/PostgresDS");
			configuration.setProperty("hibernate.connection.release_mode", "after_transaction");
			configuration.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
		}
	}
	
	/**
	 * [RM6365] - 21/11/2011 - Bruno Barros - Gravação das alterações no banco de dados por usuário
	 * [UC1252] - Alterar Usuário Logado no Banco de Dados
	 * 
	 * [FS-0002] - Trocar o usuário da base
	 * 
	 * @author Thúlio Araújo
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
	 * [RM6365] - 21/11/2011 - Bruno Barros - Gravação das alterações no banco de dados por usuário
	 * [UC1252] - Alterar Usuário Logado no Banco de Dados
	 * 
	 * [FS-0002] - Trocar o usuário da base
	 * 
	 * @author Thúlio Araújo
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
	 * [RM 4201] Bloquear versão batch para IP pré cadastrado
	 * 
	 * Método que verifica se o ip do servidor possui
	 * autorização para rodar batch
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
