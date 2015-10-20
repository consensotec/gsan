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

package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.ImovelSubcategoriaAtualizacaoCadastralDM;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC1446] Gerar Relatorio de Consulta aos Dados do Imovel no Ambiente Pre-GSAN
 * 
 * @author Anderson Cabral
 * @author Bruno Sá Barreto
 * 
 * @date 20/03/2013
 */
public class RelatorioConsultaImovelPreGsan extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioConsultaImovelPreGsan(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSULTA_IMOVEL_PRE_GSAN);
	}

	public RelatorioConsultaImovelPreGsan(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		byte[] retorno = null;
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		ConsultarDadosImovelAmbienteVirtualPopupActionForm form = (ConsultarDadosImovelAmbienteVirtualPopupActionForm) getParametro("form");
		ArrayList<ImovelSubcategoriaAtualizacaoCadastralDM> colecaoSubCategoria = (ArrayList<ImovelSubcategoriaAtualizacaoCadastralDM>) getParametro("colecaoSubCategoria");
		ArrayList<CadastroOcorrencia> colecaoCadastroOcorrencia = (ArrayList<CadastroOcorrencia>) getParametro("colecaoCadastroOcorrencia");
		
		Map<String, Object> parametros = montarMapParametros(form);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1446");
		int tipoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		JRBeanCollectionDataSource bds = new JRBeanCollectionDataSource(colecaoSubCategoria);
		JRBeanCollectionDataSource bds2 = new JRBeanCollectionDataSource(colecaoCadastroOcorrencia);

		parametros.put("categorias", bds);
		parametros.put("ocorrenciasCadastro", bds2);
		
		Object obj = new Object();
		ArrayList<Object> array = new ArrayList<Object>();
		array.add(obj);
		RelatorioDataSource ds = new RelatorioDataSource(array);			
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTA_IMOVEL_PRE_GSAN, parametros, ds, tipoRelatorio);
		
		return retorno;
	}
	
	/***
	 * Monta um Map com os dados do imovel
	 * 
	 * @author Anderson Cabral
	 * @since 20/03/2013
	 * 
	 ****/
	private Map<String, Object> montarMapParametros(ConsultarDadosImovelAmbienteVirtualPopupActionForm form){
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("matricula", form.getMatricula());
		parametros.put("cadastrador", form.getCadastrador());
		
		//Localidade
		parametros.put("idLocalidade", form.getIdLocalidade());
		parametros.put("nomeLocalidade", form.getLocalidade());
		parametros.put("codigoSetorComercial", form.getSetorComercial());
		parametros.put("nomeSetorComercial", form.getDescricaoSetorComercial());
		parametros.put("numeroQuadra", form.getQuadra());
		parametros.put("numeroLote", form.getLote());
		parametros.put("numeroSublote", form.getSubLote());
		parametros.put("coordenadaX", form.getCoordenadaX());
		parametros.put("coordenadaY", form.getCoordenadaY());
		//Endereco
		parametros.put("tipoLogradouro", form.getTipoLogradouro());
		parametros.put("municipio", form.getMunicipio());
		
		if(form.getTituloLogradouro() != null){
			parametros.put("tituloLogradouro", form.getTituloLogradouro());
		}else{
			parametros.put("tituloLogradouro", "");
		}
		
		parametros.put("nomeLogradouro", form.getDescricaoLogradouro());
		parametros.put("referencia", form.getDescricaoReferencia());
		parametros.put("numeroImovel", form.getNumeroImovel());
		parametros.put("complementoEndereco", form.getComplementoLogradouro());
		parametros.put("bairro", form.getBairro());
		parametros.put("cep", form.getCep());
		//Cliente
		parametros.put("tipoCliente", form.getTipoCliente());
		parametros.put("cpfCnpj", form.getCpfcnpj());
		parametros.put("nomeCliente", form.getNomeCliente());
		parametros.put("orgaoExpedidor", form.getOrgaoExpedidor());
		parametros.put("sexo", form.getSexo());	
		parametros.put("telefones", form.getTelefones());
		//Imovel
		parametros.put("idPerfil", form.getIdImovelPerfil());
		parametros.put("descricaoPerfil", form.getImovelPerfil());
//		parametros.put("analisarTarifaSocial", 2);
		parametros.put("numeroMedidorEnergia", form.getNumeroMedidorEnergia());
		parametros.put("numeroMoradores", form.getNumeroMoradores());
		parametros.put("idPavimentoRua", form.getIdPavimentoRua());
		parametros.put("desPavimentoRua", form.getPavimentoRua());
		parametros.put("idPavimentoCalcada", form.getIdPavimentoCalcada());
		parametros.put("desPavimentoCalcada", form.getPavimentoCalcada());
		parametros.put("idFonteAbastecimento", form.getIdFonteAbastecimento());
		parametros.put("desFonteAbastecimento", form.getFonteAbastecimento());
		parametros.put("categoria", form.getCategoria());
		parametros.put("subCategoria", form.getSubCategoria());
		parametros.put("qtdEconomias", form.getQuantidadeEconomias());
		
		//Dados de Ligacao
		parametros.put("idSituacaoAgua", form.getIdSituacaoLigacaoAgua());
		parametros.put("desSituacaoAgua", form.getSituacaoLigacaoAgua());
		parametros.put("idSituacaoEsgoto", form.getIdSituacaoLigacaoEsgoto());
		parametros.put("desSituacaoEsgoto", form.getSituacaoLigacaoEsgoto());
		parametros.put("numeroHidrometro", form.getNumeroHidrometro());
		parametros.put("tipoMedicao", form.getMedicaoTipo());
		
		parametros.put("desLocalInstalacao", form.getLocalInstalacao());
		parametros.put("idLocalInstalacao", form.getIdLocalInstalacao());
		parametros.put("idTipoProtecao", form.getIdTipoProtecao());
		parametros.put("desTipoProtecao", form.getTipoProtecao());
		parametros.put("leitura", form.getLeitura());
		parametros.put("observacao", form.getObservacao());
		parametros.put("ocorrenciaHidrometro", form.getDescricaoOcorrenciaHidrometro());
		parametros.put("dataInstalacao", form.getDataInstalacaoHidrometroFormatada());
	
		if(form.getCliente()!=null){
	
			parametros.put("rg", form.getCliente().getRg());
			
			if(form.getCliente().getUnidadeFederacao()!=null){
				parametros.put("uf", form.getCliente().getUnidadeFederacao().getDescricao());
			}
			
			if(form.getCliente().getDataEmissaoRg()!=null){
				parametros.put("dataEmissao",Util.formatarData(form.getCliente().getDataEmissaoRg()));
			}
			
			if(form.getCliente().getDataEmissaoRg()!=null){			
				parametros.put("dataNascimento", Util.formatarData(form.getCliente().getDataNascimento()));
			}
			
			parametros.put("nomeMae", form.getCliente().getNomeMae());
			
			if(ConstantesSistema.SIM.equals(form.getCliente().getIndicadorResponsavel())){
				parametros.put("icClienteResponsavel", "SIM");
			}else{
				parametros.put("icClienteResponsavel", "NÃO");
			}
			
			if(ConstantesSistema.SIM.equals(form.getCliente().getIndicadorProprietario())){
				parametros.put("icClienteProprietario", "SIM");
			}else{
				parametros.put("icClienteProprietario", "NÃO");
			}
			
			if(ConstantesSistema.SIM.equals(form.getCliente().getIndicadorDocumentacao())){
				parametros.put("icApresentouDocumentacao", "SIM");
			}else{
				parametros.put("icApresentouDocumentacao", "NÃO");
			}
		}
		
		parametros.put("descricaoOcorrencia", form.getDescricaoOcorrencia());
		
		parametros.put("dataVisita", form.getDataVisita());
		
		return parametros;
	} 

	@Override
	public void agendarTarefaBatch() {
		
	}
}
