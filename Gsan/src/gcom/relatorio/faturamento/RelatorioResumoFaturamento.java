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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ResumoFaturamentoRelatorioHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioResumoFaturamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoFaturamento (Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO);
	}


	private Collection<RelatorioResumoFaturamentoBean> inicializarBeanRelatorio(Collection listaCamposConsultaRelatorio) {

		Iterator iterator = listaCamposConsultaRelatorio.iterator();

		Collection<RelatorioResumoFaturamentoBean> retorno = new ArrayList();

		while (iterator.hasNext()) {
			ResumoFaturamentoRelatorioHelper resumoFaturamentoRelatorioHelper = (ResumoFaturamentoRelatorioHelper) iterator.next();
			
			RelatorioResumoFaturamentoBean relatorioResumoFaturamentoBean = 
				new RelatorioResumoFaturamentoBean(
						resumoFaturamentoRelatorioHelper.getValorItemFaturamento(),
						resumoFaturamentoRelatorioHelper.getDescricaoTipoLancamento(),
						resumoFaturamentoRelatorioHelper.getDescricaoItemLancamento(),
						resumoFaturamentoRelatorioHelper.getDescricaoItemContabil(),
						resumoFaturamentoRelatorioHelper.getIndicadorImpressao(),
						resumoFaturamentoRelatorioHelper.getIndicadorTotal(),
						resumoFaturamentoRelatorioHelper.getLancamentoTipo(),
						resumoFaturamentoRelatorioHelper.getLancamentoTipoSuperior(),
						false,
						resumoFaturamentoRelatorioHelper.getDescricaoGerencia(),
						resumoFaturamentoRelatorioHelper.getGerencia(),
						resumoFaturamentoRelatorioHelper.getDescricaoLocalidade(),
						resumoFaturamentoRelatorioHelper.getLocalidade(),
						resumoFaturamentoRelatorioHelper.getDescricaoMunicipio(),
						resumoFaturamentoRelatorioHelper.getMunicipio(),
						resumoFaturamentoRelatorioHelper.getDescLancamentoTipoSuperior(),
						resumoFaturamentoRelatorioHelper.getDescricaoUnidadeNegocio(),
						resumoFaturamentoRelatorioHelper.getUnidadeNegocio(),
						resumoFaturamentoRelatorioHelper.getCodigoCentroCusto() );

			retorno.add(relatorioResumoFaturamentoBean);
		}		
		return retorno;
	}

	
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");
		Integer codigoLocalidade = (Integer) getParametro("localidade");
		Integer codigoMunicipio = (Integer) getParametro("municipio");
		Integer codigoGerencia = (Integer) getParametro("gerenciaRegional");
		Integer unidadeNegocio = (Integer) getParametro("unidadeNegocio");
		Collection<Integer> idsPerfilImovel = (Collection<Integer>) getParametro("idsPerfilImovel");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String opcaoRelatorio = (String) getParametro("opcaoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection dadosRelatorio = fachada.consultarResumoFaturamentoRelatorio(opcaoTotalizacao, mesAno,
						codigoGerencia, codigoLocalidade, codigoMunicipio, unidadeNegocio, idsPerfilImovel, opcaoRelatorio);

		Collection<RelatorioResumoFaturamentoBean> colecaoBean = this.inicializarBeanRelatorio(dadosRelatorio);

		Integer lancamentoTipoAnterior = null;
		Integer lancamentoTipoAnteriorSuperior = null;
		
		for (Iterator<RelatorioResumoFaturamentoBean> iter = colecaoBean.iterator(); iter.hasNext();) {
			RelatorioResumoFaturamentoBean bean = iter.next();

			// Se o tipo de lan�amento for subordinado a outro tipo de
			// lan�amento
			if (!bean.getLancamentoTipo().equals(bean.getLancamentoTipoSuperior())) {

				// Recua a descri��o neste caso
				bean.setDescricaoTipoLancamento("    "	+ bean.getDescricaoTipoLancamento());

				bean.setDescricaoItemLancamento("    " 	+ bean.getDescricaoItemLancamento());

				if (bean.getDescricaoItemContabil() != null){
					bean.setDescricaoItemContabil("    "  + bean.getDescricaoItemContabil());	
				}
				
				if (lancamentoTipoAnterior != null 
						&& !lancamentoTipoAnterior.equals(bean.getLancamentoTipoSuperior())
						&& !lancamentoTipoAnteriorSuperior.equals(bean.getLancamentoTipoSuperior())){
					String descricaoLancamentoTipoSuperior = fachada.obterDescricaoLancamentoTipo(bean.getLancamentoTipoSuperior());
					bean.setDescLancamentoTipoSuperior(descricaoLancamentoTipoSuperior);
				}
				
			}
			
			lancamentoTipoAnterior = bean.getLancamentoTipo();
			lancamentoTipoAnteriorSuperior = bean.getLancamentoTipoSuperior();
			
			// Se o indicador impress�o n�o estiver setado ent�o a linha de
			// detalhe n�o aparecer� no relat�rio
			if (bean.getIndicadorImpressao() == null || !bean.getIndicadorImpressao().toString().equals("1")) {
				bean.setDescricaoTipoLancamento("");
				//iter.remove();
				continue;
			}
			
		}

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		String opcaoTotalizacaoDesc = "";
		if (opcaoTotalizacao.equalsIgnoreCase("estado")) {
			opcaoTotalizacaoDesc = "Estado";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			opcaoTotalizacaoDesc = "Estado por Ger�ncia Regional";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade")) {
			opcaoTotalizacaoDesc = "Estado por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio")) {
			opcaoTotalizacaoDesc = "Estado por Munic�pio";
		} else if (opcaoTotalizacao.equalsIgnoreCase("gerenciaRegional")) {
			opcaoTotalizacaoDesc = "Ger�ncia Regional";
		} else if (opcaoTotalizacao
				.equalsIgnoreCase("gerenciaRegionalLocalidade")) {
			opcaoTotalizacaoDesc = "Ger�ncia Regional por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("localidade")) {
			opcaoTotalizacaoDesc = "Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("municipio")) {
			opcaoTotalizacaoDesc = "Munic�pio";
		} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
			opcaoTotalizacaoDesc = "Estado por Unidade de Neg�cio";
		} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
			opcaoTotalizacaoDesc = "Unidade de Neg�cio";
		}

		parametros.put("opcaoTotalizacaoDesc", opcaoTotalizacaoDesc);

		String mesAnoString = "" + mesAno;
		if (mesAnoString.length() == 5) {
			mesAnoString = "0" + mesAnoString;
		}
		mesAnoString = mesAnoString.substring(0, 2) + "/" + mesAnoString.substring(2, 6);

		parametros.put("mesAnoArrecadacao", mesAnoString);
		
		String indicadorControleGrandeCliente = fachada.obterValorParametro(ParametroSistema.INDICADOR_CONTROLE_GRANDE_CLIENTE);
		
		if (indicadorControleGrandeCliente != null && indicadorControleGrandeCliente.trim().equals("" + ConstantesSistema.SIM)) {
			String perfisSelecionados = "";
			
			if (idsPerfilImovel != null && !idsPerfilImovel.isEmpty()) {
				int i = 0;
				
				for (Integer idPerfilImovel : idsPerfilImovel) {
					FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
					filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, idPerfilImovel));
					
					Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
					
					ImovelPerfil imovelPerfil = colecaoImovelPerfil.iterator().next(); 
					
					if (i == 0) {
						perfisSelecionados += imovelPerfil.getDescricao();
					} else if (idsPerfilImovel.size() > 5 && i == 4) {
						perfisSelecionados += " E OUTROS";
						break;
					} else if (i <= 4) {
						perfisSelecionados += ", " + imovelPerfil.getDescricao();
					}
					
					i++;
				}
			} else {
				perfisSelecionados = "TODOS";
			}
			
			parametros.put("perfisSelecionados", perfisSelecionados);
		}

		parametros.put("tipoFormatoRelatorio", "");

		if (opcaoTotalizacao.equalsIgnoreCase("unidadeNegocio") || opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio")) {
			parametros.put("agrupaPorUnidadeNegocio", "1");
		}else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			parametros.put("agrupaPorGerencia", "1");
		}else if (opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio") || opcaoTotalizacao.equalsIgnoreCase("municipio")) {
			parametros.put("agrupaPorMunicipio", "1");
		}else {
			parametros.put("agrupaPorGerencia", "0");
		}
		
		if (opcaoRelatorio != null && opcaoRelatorio.equals("2")) {
			parametros.put("opcaoRelatorio", "Op��o de Relat�rio:Resumido");
		} else {
			parametros.put("opcaoRelatorio", "");
		}
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("tipoFormatoRelatorio", "R0173");

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO, parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RESUMO_FATURAMENTO,idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Fachada fachada = Fachada.getInstancia();
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");
		Integer idLocalidade = (Integer) getParametro("localidade");
		Integer idGerencia = (Integer) getParametro("gerenciaRegional");
		Integer idMunicipio = (Integer) getParametro("municipio");
		
		Integer totalRegistrosRel;
		totalRegistrosRel = fachada.consultarQtdeRegistrosResumoFaturamentoRelatorio(mesAno, idLocalidade, 
					idMunicipio, idGerencia, opcaoTotalizacao);
		return totalRegistrosRel.intValue();
	}


	public void agendarTarefaBatch() {
	}
}