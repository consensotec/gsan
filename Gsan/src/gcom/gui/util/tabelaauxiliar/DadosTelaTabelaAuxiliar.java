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
package gcom.gui.util.tabelaauxiliar;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.dadocensitario.FonteDadosCensitario;
import gcom.cadastro.dadocensitario.IbgeSetorCensitario;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.imovel.EloAnormalidade;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelTipoCobertura;
import gcom.cadastro.imovel.ImovelTipoConstrucao;
import gcom.cadastro.imovel.ImovelTipoHabitacao;
import gcom.cadastro.imovel.ImovelTipoPropriedade;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.localidade.LocalidadeClasse;
import gcom.cadastro.localidade.LocalidadePorte;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.gui.Funcionalidade;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.operacional.SistemaEsgotoTratamentoTipo;
import gcom.operacional.TipoCaptacao;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.util.HibernateUtil;
import gcom.util.SistemaException;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.TabelaAuxiliarAbstrata;
import gcom.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.util.HashMap;

/**
 * Title: GCOM
 * Description: Sistema de Gest�o Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * @author not attributable
 * @version 1.0
 */

public class DadosTelaTabelaAuxiliar {
	private String titulo;

	private TabelaAuxiliar tabela;

	private String funcionalidadeTabelaAux;
	private String nomeParametroFuncionalidade;

	private static HashMap<String,DadosTelaTabelaAuxiliar> telas = new HashMap<String,DadosTelaTabelaAuxiliar>();
	private static HashMap<String,String[]> configuracaoParametrosTelas = new HashMap<String,String[]>();

	/**
	 * Este m�todo busca um map de funcionalidades cadastradas e cria uma
	 * inst�ncia da funcionalidade para ser usada na Tabela Auxiliar
	 * 
	 * @param nome
	 * @return
	 */
	public static DadosTelaTabelaAuxiliar obterDadosTelaTabelaAuxiliar(
			String nome) {

		// Verifica se a funcionalidade desejada j� foi instanciada e j� est� no
		// cache
		if (!telas.containsKey(nome)) {

			String[] configuracaoTela = configuracaoParametrosTelas.get(nome);

			try {

				// Cria a inst�ncia do objeto DadosTelaTabelaAuxiliar
				DadosTelaTabelaAuxiliar dadosTela = new DadosTelaTabelaAuxiliar(
						configuracaoTela[1], (TabelaAuxiliar) Class.forName(
								configuracaoTela[0]).newInstance(),
						configuracaoTela[2], nome);

				// Coloca a inst�ncia criada no map que representa o cache com
				// as inst�ncia j� criadas
				telas.put(nome, dadosTela);

				return dadosTela;
			} catch (ClassNotFoundException ex) {
				throw new SistemaException();
			} catch (IllegalAccessException ex) {
				throw new SistemaException();
			} catch (InstantiationException ex) {
				throw new SistemaException();
			}
		}
		// Se o a funcionalidade j� estiver no cache, ent�o ela � retornada
		// sem a necessidade de passar pelo m�todo
		return telas.get(nome);
	}

	static {

		//Arrumar
		configuracaoParametrosTelas.put("perfilImovel", new String[] {
				ImovelPerfil.class.getName(), "Perfil do Im�vel",
				Funcionalidade.TELA_IMOVEL_PERFIL });
		
		//Arrumar
		configuracaoParametrosTelas.put("ibgeSetorCensitario", new String[] {
				IbgeSetorCensitario.class.getName(), "IBGE Setor Censit�rio",
				Funcionalidade.TELA_IBGE_SETOR_CENSITARIO });	
		
		//Arrumar
		configuracaoParametrosTelas.put("tipoCorte", new String[] {
				CorteTipo.class.getName(), "Tipo de Corte",
				Funcionalidade.TELA_CORTE_TIPO });
		
		//Arrumar
		configuracaoParametrosTelas.put("tipoSupressao", new String[] {
				SupressaoTipo.class.getName(), "Tipo de Supressao",
				Funcionalidade.TELA_SUPRESSAO_TIPO });
		
		//Arrumar
		configuracaoParametrosTelas.put("tipoConsumo", new String[] {
				ConsumoTipo.class.getName(), "Tipo de Consumo",
				Funcionalidade.TELA_CONSUMO_TIPO });
		
		configuracaoParametrosTelas.put("localidadeClasse", 
			new String[] {
				LocalidadeClasse.class.getName(), 
				"Localidade Classe",
				Funcionalidade.TELA_LOCALIDADE_CLASSE });

		configuracaoParametrosTelas.put("localidadePorte", 
			new String[] {
				LocalidadePorte.class.getName(), 
				"Localidade Porte",
				Funcionalidade.TELA_LOCALIDADE_PORTE });

		configuracaoParametrosTelas.put("fonteDadosCensitario",
			new String[] { 
				FonteDadosCensitario.class.getName(),
				"Fonte Dados Censit�rios",
				Funcionalidade.TELA_SETOR_CENSITARIO });

		configuracaoParametrosTelas.put("cepTipo", 
			new String[] {
				CepTipo.class.getName(), 
				"Tipo de CEP",
				Funcionalidade.TELA_CEP_TIPO });

		configuracaoParametrosTelas.put("tipoEndereco", 
			new String[] {
				EnderecoTipo.class.getName(), 
				"Tipo do Endere�o",
				Funcionalidade.TELA_ENDERECO_TIPO });

		configuracaoParametrosTelas.put("pessoaSexo", 
			new String[] {
				PessoaSexo.class.getName(), 
				"Pessoa do Sexo",
				Funcionalidade.TELA_PESSOA_SEXO });

		configuracaoParametrosTelas.put("tipoRenda", 
			new String[] {
				RendaTipo.class.getName(), 
				"Tipo da Renda",
				Funcionalidade.TELA_RENDA_TIPO });

		configuracaoParametrosTelas.put("acaoUsuario", 
			new String[] {
				UsuarioAcao.class.getName(), 
				"A��o do Usu�rio",
				Funcionalidade.TELA_USUARIO_ACAO });

		configuracaoParametrosTelas.put("materialLigacaoEsgoto", 
			new String[] {
				LigacaoEsgotoMaterial.class.getName(),
				"Material de Liga��o do Esgoto",
				Funcionalidade.TELA_LIGACAO_ESGOTO_MATERIAL });

		configuracaoParametrosTelas.put("diametroLigacaoEsgoto", 
			new String[] {
				LigacaoEsgotoDiametro.class.getName(),
				"Diametro de Liga��o do Esgoto",
				Funcionalidade.TELA_LIGACAO_ESGOTO_DIAMETRO });

		configuracaoParametrosTelas.put("diametroLigacaoAgua", 
			new String[] {
				LigacaoAguaDiametro.class.getName(),
				"Diametro de Liga��o da �gua",
				Funcionalidade.TELA_LIGACAO_AGUA_DIAMETRO });

		configuracaoParametrosTelas.put("tipoOrdemEmissaoCobranca",
			new String[] { 
				EmissaoOrdemCobrancaTipo.class.getName(),
				"Tipo da Ordem de Emiss�o de Cobranca",
				Funcionalidade.TELA_EMISSAO_ORDEM_COBRANCA_TIPO });

		configuracaoParametrosTelas.put("perfilLigacaoAgua", 
			new String[] {
				LigacaoAguaPerfil.class.getName(), 
				"Perfil de Liga��o da �gua",
				Funcionalidade.TELA_LIGACAO_AGUA_PERFIL });

		configuracaoParametrosTelas.put("poco", 
			new String[] {
				PocoTipo.class.getName(), 
				"Po�o", 
				Funcionalidade.TELA_POCO });

		configuracaoParametrosTelas.put("eloAnormalidade", 
			new String[] {
				EloAnormalidade.class.getName(), 
				"Elo Anormalidade",
				Funcionalidade.TELA_ELO_ANORMALIDADE });
		
		configuracaoParametrosTelas.put("imovelTipoHabitacao", 
			new String[] {
				ImovelTipoHabitacao.class.getName(), 
				"Tipo de Habita��o",
				Funcionalidade.TELA_TIPO_HABITACAO });

		configuracaoParametrosTelas.put("imovelTipoPropriedade", 
			new String[] {
				ImovelTipoPropriedade.class.getName(), 
				"Tipo de Propriedade",
				Funcionalidade.TELA_TIPO_PROPRIEDADE });
		
		configuracaoParametrosTelas.put("imovelTipoConstrucao", 
			new String[] {
				ImovelTipoConstrucao.class.getName(), 
				"Tipo de Constru��o",
				Funcionalidade.TELA_TIPO_CONSTRUCAO });
		
		configuracaoParametrosTelas.put("imovelTipoCobertura", 
			new String[] {
				ImovelTipoCobertura.class.getName(), 
				"Tipo de Cobertura",
				Funcionalidade.TELA_TIPO_COBERTURA });
		
		configuracaoParametrosTelas.put("hidrometroRelojoaria", 
			new String[] {
				HidrometroRelojoaria.class.getName(), 
				"Hidrometro Relojoaria",
				Funcionalidade.TELA_HIDROMETRO_RELOJOARIA });

		configuracaoParametrosTelas.put("ligacaoEsgotoDestinoDejetos", 
			new String[] {
				LigacaoEsgotoDestinoDejetos.class.getName(), 
				"Liga��o de Esgoto destino de dejetos",
				Funcionalidade.TELA_LIGACAO_ESGOTO_DESTINO_DEJETOS });
		
		configuracaoParametrosTelas.put("ligacaoEsgotoEsgotamento", 
			new String[] {
				LigacaoEsgotoEsgotamento.class.getName(), 
				"Liga��o de Esgoto Esgotamento",
				Funcionalidade.TELA_LIGACAO_ESGOTO_ESGOTAMENTO });
		
		configuracaoParametrosTelas.put("ligacaoEsgotoCaixaInspecao", 
			new String[] {
				LigacaoEsgotoCaixaInspecao.class.getName(), 
				"Liga��o de Esgoto Caixa de Inspe��o",
				Funcionalidade.TELA_LIGACAO_ESGOTO_CAIXA_INSPECAO });
		
		configuracaoParametrosTelas.put("ligacaoEsgotoDestinoAguasPluviais", 
			new String[] {
				LigacaoEsgotoDestinoAguasPluviais.class.getName(), 
				"Liga��o de Esgoto Destino das �guas Pluviais",
				Funcionalidade.TELA_LIGACAO_ESGOTO_DESTINO_AGUAS_PLUVIAIS });
		
		configuracaoParametrosTelas.put("regiaoIntegracao", 
			new String[] {
				RegiaoDesenvolvimento.class.getName(), 
				"Regi�o de Integra��o",
				Funcionalidade.TELA_REGIAO_INTEGRACAO});
		
		
		configuracaoParametrosTelas.put("sistemaEsgotoTratamentoTipo", 
			new String[] {
				SistemaEsgotoTratamentoTipo.class.getName(), 
				"Tipo de Tratamento do Sistema de Esgoto",
				Funcionalidade.TELA_ESGOTO_TRATAMENTO_TIPO});
		
		configuracaoParametrosTelas.put("tipoCaptacao", 
				new String[] {
					TipoCaptacao.class.getName(), 
					"Tipo de Capta��o",
					Funcionalidade.TELA_TIPO_CAPTACAO });
		
		configuracaoParametrosTelas.put("contaMotivoRetificacao", 
				new String[] {
				ContaMotivoRetificacao.class.getName(), 
					"Motivo de Retifica��o da Conta",
					Funcionalidade.TELA_CONTA_MOTIVO_RETIFICACAO });
		
	
	}

	/**
	 * Construtor da classe DadosTelaTabelaAuxiliar
	 * 
	 * @param titulo
	 *            Descri��o do par�metro
	 * @param tabela
	 *            Descri��o do par�metro
	 * @param funcionalidadeTabelaAuxManter
	 *            Descri��o do par�metro
	 */
	protected DadosTelaTabelaAuxiliar(String titulo, TabelaAuxiliar tabela,
			String funcionalidadeTabelaAux, String nomeParametroFuncionalidade) {
		this.titulo = titulo;
		this.tabela = tabela;
		this.funcionalidadeTabelaAux = funcionalidadeTabelaAux;
		this.nomeParametroFuncionalidade = nomeParametroFuncionalidade;
		System.out.println("inicializando objeto");
	}

	public DadosTelaTabelaAuxiliar(String titulo2,
			TabelaAuxiliarFaixaReal tabela2, String funcionalidadeTabelaAux2,
			String nomeParametroFuncionalidade2) {
	}

	public DadosTelaTabelaAuxiliar(String nomeParametroFuncionalidade2,
			TabelaAuxiliarAbstrata abstrata,
			String nomeParametroFuncionalidade3,
			String nomeParametroFuncionalidade4) {
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param dados
	 *            Descri��o do par�metro
	 */
	protected static void adicionarDadosTela(DadosTelaTabelaAuxiliar dados) {
		String nomeCompletoClasse = dados.getTabelaAuxiliar().getClass()
				.getName();
		String nomeClasse = nomeCompletoClasse.substring(nomeCompletoClasse
				.lastIndexOf(".") + 1, nomeCompletoClasse.length());

		telas.put(nomeClasse.toLowerCase(), dados);
	}

	/**
	 * Retorna o valor de dadosTela
	 * 
	 * @param nome
	 *            Descri��o do par�metro
	 * @return O valor de dadosTela
	 */
	public static DadosTelaTabelaAuxiliar getDadosTela(String nome) {
		return telas.get(nome.toLowerCase());
	}

	/**
	 * Retorna o valor de titulo
	 * 
	 * @return O valor de titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Retorna o valor de tabelaAuxiliar
	 * 
	 * @return O valor de tabelaAuxiliar
	 */
	public TabelaAuxiliar getTabelaAuxiliar() {
		return tabela;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxManter() {
		return Funcionalidade.TABELA_AUXILIAR_MANTER + funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxFiltrar() {
		return Funcionalidade.TABELA_AUXILIAR_FILTRAR
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxInserir() {
		return Funcionalidade.TABELA_AUXILIAR_INSERIR + funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaIndicadorManter() {
		return Funcionalidade.TABELA_AUXILIAR_INDICADOR_MANTER
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaIndicadorFiltrar() {
		return Funcionalidade.TABELA_AUXILIAR_INDICADOR_FILTRAR
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaIndicadorInserir() {
		return Funcionalidade.TABELA_AUXILIAR_INDICADOR_INSERIR
				+ funcionalidadeTabelaAux;
	}

	/**
	 * Retorna o valor de tamanhoMaximoCampo
	 * 
	 * @return O valor de tamanhoMaximoCampo
	 */
	public int getTamanhoMaximoDescricao() {
		return HibernateUtil.getColumnSize(tabela.getClass(), "descricao");
	}

	public String getNomeParametroFuncionalidade() {
		return nomeParametroFuncionalidade;
	}

	public void setNomeParametroFuncionalidade(
			String nomeParametroFuncionalidade) {
		this.nomeParametroFuncionalidade = nomeParametroFuncionalidade;
	}

}
