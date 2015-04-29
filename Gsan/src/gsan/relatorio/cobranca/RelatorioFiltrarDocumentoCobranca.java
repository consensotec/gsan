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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gsan.batch.Relatorio;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaDocumentoItem;
import gsan.cobranca.DocumentoEmissaoForma;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.cobranca.FiltroCobrancaDocumentoItem;
import gsan.cobranca.FiltroDocumentoEmissaoForma;
import gsan.cobranca.FiltroMotivoNaoEntregaDocumento;
import gsan.cobranca.bean.FiltrarDocumentoCobrancaHelper;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.MotivoNaoEntregaDocumento;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

/**
 * Descri��o da classe
 * Classe respons�vel pelo processamento dos
 * parametros informados e consequente 
 * montagem dos registros exibidos posteriormente
 * pelo relat�rio
 * 
 * @author Anderson Italo
 * @date 17/08/2009
 */
public class RelatorioFiltrarDocumentoCobranca extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioFiltrarDocumentoCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FILTRAR_DOCUMENTO_COBRANCA);
	}
	
	@Deprecated
	public RelatorioFiltrarDocumentoCobranca(){
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		 
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		FiltrarDocumentoCobrancaHelper filtrarDocumentoCobranca = (FiltrarDocumentoCobrancaHelper) getParametro("filtroCobrancaDocumento");
		filtrarDocumentoCobranca.setNumeroPagina(-1);
		
		//TRECHO PARA RECUPERA��O DOS PARAMETROS
		
		//matricula do imovel
		String matriculaImovel = "";
		if (filtrarDocumentoCobranca.getIdImovel() != null && filtrarDocumentoCobranca.getIdImovel() > 0  ) {
			matriculaImovel = filtrarDocumentoCobranca.getIdImovel().toString();
		}
		
		//gerencia regional
		String gerenciaRegional = "";
		if (filtrarDocumentoCobranca.getIdGerenciaRegional() != null 
				&& filtrarDocumentoCobranca.getIdGerenciaRegional() > 0){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(
					new ParametroSimples(FiltroGerenciaRegional.ID, filtrarDocumentoCobranca.getIdGerenciaRegional()));
			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			GerenciaRegional gerencia = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
			gerenciaRegional = gerencia.getNomeAbreviado() + "-" + gerencia.getNome();
		}
		
		//unidade de negocio
		String unidadeNegocio = "";
		if (filtrarDocumentoCobranca.getIdUnidadeNegocio() != null 
				&& filtrarDocumentoCobranca.getIdUnidadeNegocio() > 0){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(
					new ParametroSimples(FiltroUnidadeNegocio.ID, filtrarDocumentoCobranca.getIdUnidadeNegocio()));
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			UnidadeNegocio unidade = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			unidadeNegocio = unidade.getNomeAbreviado() + "-" + unidade.getNome();
		}
		
		//inscri��o inicial
		//localidade inicial
		String localidadeInicial = "";
		if (filtrarDocumentoCobranca.getIdLocalidadeInicial() != null 
				&& filtrarDocumentoCobranca.getIdLocalidadeInicial() > 0){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID, filtrarDocumentoCobranca.getIdLocalidadeInicial()));
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			localidadeInicial = localidade.getId().toString() + " - " + localidade.getDescricao();
		}
		
		//setor comercial inicial
		String setorComercialInicial = "";
		if (filtrarDocumentoCobranca.getIdSetorComercialInicial() != null){
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, filtrarDocumentoCobranca.getIdSetorComercialInicial()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, filtrarDocumentoCobranca.getIdLocalidadeInicial()));
			Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercialConsulta = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			setorComercialInicial = String.valueOf(setorComercialConsulta.getCodigo());
		}
		
		//setor quadra inicial
		String quadraInicial = "";
		if (filtrarDocumentoCobranca.getIdQuadraInicial() != null){
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, filtrarDocumentoCobranca.getIdQuadraInicial()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, filtrarDocumentoCobranca.getIdSetorComercialInicial()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, filtrarDocumentoCobranca.getIdLocalidadeInicial()));
			Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			Quadra quadraConsulta = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
			
			quadraInicial = String.valueOf(quadraConsulta.getNumeroQuadra());
		}
		
		//inscri��o final
		//localidade final
		String localidadeFinal = "";
		if (filtrarDocumentoCobranca.getIdLocalidadeFinal() != null 
				&& filtrarDocumentoCobranca.getIdLocalidadeFinal() > 0){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID, filtrarDocumentoCobranca.getIdLocalidadeFinal()));
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			localidadeFinal = localidade.getId().toString() + " - " + localidade.getDescricao();
		}
		
		//setor comercial final
		String setorComercialFinal = "";
		if (filtrarDocumentoCobranca.getIdSetorComercialFinal() != null){
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, filtrarDocumentoCobranca.getIdSetorComercialFinal()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, filtrarDocumentoCobranca.getIdLocalidadeFinal()));
			Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercialConsulta = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			setorComercialFinal = String.valueOf(setorComercialConsulta.getCodigo());
		}else if (filtrarDocumentoCobranca.getIdSetorComercialFinal() == null && filtrarDocumentoCobranca.getIdSetorComercialInicial() != null){
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, filtrarDocumentoCobranca.getIdSetorComercialInicial()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, filtrarDocumentoCobranca.getIdLocalidadeInicial()));
			Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			SetorComercial setorComercialConsulta = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			setorComercialFinal = String.valueOf(setorComercialConsulta.getCodigo());
		}
		
		//setor quadra final
		String quadraFinal = "";
		if (filtrarDocumentoCobranca.getIdQuadraFinal() != null){
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, filtrarDocumentoCobranca.getIdQuadraFinal()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, filtrarDocumentoCobranca.getIdSetorComercialFinal()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, filtrarDocumentoCobranca.getIdLocalidadeFinal()));
			Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			Quadra quadraConsulta = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
			
			quadraFinal = String.valueOf(quadraConsulta.getNumeroQuadra());
		}else if(filtrarDocumentoCobranca.getIdQuadraFinal() == null && filtrarDocumentoCobranca.getIdQuadraInicial() != null){
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, filtrarDocumentoCobranca.getIdQuadraInicial()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, filtrarDocumentoCobranca.getIdSetorComercialInicial()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, filtrarDocumentoCobranca.getIdLocalidadeInicial()));
			Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			Quadra quadraConsulta = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
			
			quadraFinal = String.valueOf(quadraConsulta.getNumeroQuadra());
		}
		
		//forma de emiss�o do documento
		String formaEmissao = "";
		if (filtrarDocumentoCobranca.getIdsDocumentoEmissaoForma() != null &&
				filtrarDocumentoCobranca.getIdsDocumentoEmissaoForma().length > 0){
			Integer[] idsFormaEmissao = filtrarDocumentoCobranca.getIdsDocumentoEmissaoForma();
			FiltroDocumentoEmissaoForma filtroDocumentoEmissaoForma = new FiltroDocumentoEmissaoForma();
			
			for(int i=0; i< idsFormaEmissao.length; i++ ){
			    	
				if(!idsFormaEmissao[i].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			      
				    if(idsFormaEmissao.length == 1){
				    	
				    	filtroDocumentoEmissaoForma.adicionarParametro(new ParametroSimples(
				    	FiltroDocumentoEmissaoForma.ID, idsFormaEmissao[i]));
				    	
				    }
				    else if( i == 0 ){
				    	
				    	filtroDocumentoEmissaoForma.adicionarParametro(new ParametroSimples(
				    			FiltroDocumentoEmissaoForma.ID, idsFormaEmissao[i], ParametroSimples.CONECTOR_OR, idsFormaEmissao.length));
				    	 
				    }
				    else if( i  == (idsFormaEmissao.length - 1) ){
				    	
				    	filtroDocumentoEmissaoForma.adicionarParametro(new ParametroSimples(
				    			FiltroDocumentoEmissaoForma.ID, idsFormaEmissao[i]));
				    
				    }
				    else{
				    	filtroDocumentoEmissaoForma.adicionarParametro(new ParametroSimples(
				    			FiltroDocumentoEmissaoForma.ID, idsFormaEmissao[i], ParametroSimples.CONECTOR_OR));
				    }
				}
			}
			Collection colecaoDocumentoEmissaoForma = fachada.pesquisar(filtroDocumentoEmissaoForma, DocumentoEmissaoForma.class.getName());
			
			for (Iterator iter = colecaoDocumentoEmissaoForma.iterator(); iter
					.hasNext();) {
				DocumentoEmissaoForma documentoEmissaoForma = (DocumentoEmissaoForma) iter.next();
				
				formaEmissao+= documentoEmissaoForma.getDescricaoDocumentoEmissaoForma() + " / ";
			}
			//remove a barra do final
			formaEmissao = formaEmissao.substring(0, formaEmissao.length() - 3);
		}
		
		//a��o de cobranca
		String acaoCobranca = "";
		if (filtrarDocumentoCobranca.getIdsCobrancaAcao()!= null && 
				filtrarDocumentoCobranca.getIdsCobrancaAcao().length > 0){
			Integer[] idsCobrancaAcao = filtrarDocumentoCobranca.getIdsCobrancaAcao();
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			
			for(int i=0; i< idsCobrancaAcao.length; i++ ){
				
				if(!idsCobrancaAcao[i].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			      
				    if(idsCobrancaAcao.length == 1){
				    	
				    	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				    	FiltroCobrancaAcao.ID, idsCobrancaAcao[i]));
				    	
				    }
				    else if( i == 0 ){
				    	
				    	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				    			FiltroCobrancaAcao.ID, idsCobrancaAcao[i], ParametroSimples.CONECTOR_OR, idsCobrancaAcao.length));
				    	 
				    }
				    else if( i  == (idsCobrancaAcao.length - 1) ){
				    	
				    	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				    			FiltroCobrancaAcao.ID, idsCobrancaAcao[i]));
				    
				    }
				    else{
				    	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				    			FiltroCobrancaAcao.ID, idsCobrancaAcao[i], ParametroSimples.CONECTOR_OR));
				    }
				}
			}
			Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
			
			for (Iterator iter = colecaoCobrancaAcao.iterator(); iter
					.hasNext();) {
				CobrancaAcao cobrancaAcao = (CobrancaAcao) iter.next();
				
				acaoCobranca += cobrancaAcao.getDescricaoCobrancaAcao() + " / ";
			}
			//remove a barra do final
			acaoCobranca = acaoCobranca.substring(0, acaoCobranca.length() - 3);
		}
		
		//ciclo
		String ciclo = "";
		if (filtrarDocumentoCobranca.getCiclo() != null && !filtrarDocumentoCobranca.getCiclo().equals("")){
			ciclo = filtrarDocumentoCobranca.getCiclo(); 
		}
		
		//periodo de emissao
		//data emissao inicial
		String dataEmissaoInicial = "";
		if (filtrarDocumentoCobranca.getDataEmissaoInicial() != null && !filtrarDocumentoCobranca.getDataEmissaoInicial().equals("")){
			dataEmissaoInicial = filtrarDocumentoCobranca.getDataEmissaoInicial(); 
		}
		
		//data emissao final
		String dataEmissaoFinal = "";
		if (filtrarDocumentoCobranca.getDataEmissaoFinal() != null && !filtrarDocumentoCobranca.getDataEmissaoFinal().equals("")){
			dataEmissaoFinal = filtrarDocumentoCobranca.getDataEmissaoFinal(); 
		}
		
		String periodoEmissao = "";
		if (!dataEmissaoInicial.equals("") && !dataEmissaoFinal.equals("")){
			periodoEmissao = dataEmissaoInicial +  " a " + dataEmissaoFinal;
		}
		
		//intervalo valor documento
		//valor do documento inicial
		String valorDocumentoInicial = "";
		if (filtrarDocumentoCobranca.getValorDocumentoInicial() != null && !filtrarDocumentoCobranca.getValorDocumentoInicial().equals("")){
			valorDocumentoInicial = filtrarDocumentoCobranca.getValorDocumentoInicial().toEngineeringString(); 
		}
		
		//valor do documento final
		String valorDocumentoFinal = "";
		if (filtrarDocumentoCobranca.getValorDocumentoFinal() != null && !filtrarDocumentoCobranca.getValorDocumentoFinal().equals("")){
			valorDocumentoFinal = filtrarDocumentoCobranca.getValorDocumentoFinal().toEngineeringString(); 
		}
		
		String intervaloValorDocumento = "";
		if (!valorDocumentoInicial.equals("") && !valorDocumentoFinal.equals("")){
			intervaloValorDocumento = valorDocumentoInicial + " a " + valorDocumentoFinal;
		}
		
		//motivo n�o entrega documento
		String motivoNaoEntrega = "";
		if (filtrarDocumentoCobranca.getIdsMotivoNaoEntrega() != null
				&& filtrarDocumentoCobranca.getIdsMotivoNaoEntrega().length > 0){
					
			Integer[] idsMotivoNaoEntregaDocumento = filtrarDocumentoCobranca.getIdsMotivoNaoEntrega();
			FiltroMotivoNaoEntregaDocumento filtroMotivoNaoEntrega = new FiltroMotivoNaoEntregaDocumento();
			
			for(int i=0; i< idsMotivoNaoEntregaDocumento.length; i++ ){
				
				if(!idsMotivoNaoEntregaDocumento[i].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			      
				    if(idsMotivoNaoEntregaDocumento.length == 1){
				    	
				    	filtroMotivoNaoEntrega.adicionarParametro(new ParametroSimples(
				    	FiltroMotivoNaoEntregaDocumento.ID, idsMotivoNaoEntregaDocumento[i]));
				    	
				    }
				    else if( i == 0 ){
				    	
				    	filtroMotivoNaoEntrega.adicionarParametro(new ParametroSimples(
				    			FiltroMotivoNaoEntregaDocumento.ID, idsMotivoNaoEntregaDocumento[i], ParametroSimples.CONECTOR_OR, idsMotivoNaoEntregaDocumento.length));
				    	 
				    }
				    else if( i  == (idsMotivoNaoEntregaDocumento.length - 1) ){
				    	
				    	filtroMotivoNaoEntrega.adicionarParametro(new ParametroSimples(
				    			FiltroMotivoNaoEntregaDocumento.ID, idsMotivoNaoEntregaDocumento[i]));
				    
				    }
				    else{
				    	filtroMotivoNaoEntrega.adicionarParametro(new ParametroSimples(
				    			FiltroMotivoNaoEntregaDocumento.ID, idsMotivoNaoEntregaDocumento[i], ParametroSimples.CONECTOR_OR));
				    }
				}
			}
			Collection colecaoMotivoNaoEntrega = fachada.pesquisar(filtroMotivoNaoEntrega, MotivoNaoEntregaDocumento.class.getName());
			
			for (Iterator iter = colecaoMotivoNaoEntrega.iterator(); iter
					.hasNext();) {
				MotivoNaoEntregaDocumento motivoNaoEntregaDocumento = (MotivoNaoEntregaDocumento) iter.next();
				
				motivoNaoEntrega += motivoNaoEntregaDocumento.getAbreviado() + " / ";
			}
			//remove a barra do final
			motivoNaoEntrega = motivoNaoEntrega.substring(0, motivoNaoEntrega.length() - 3);
		}
		
		//perfil do imovel
		String perfilImovel = "";
		if (filtrarDocumentoCobranca.getIdsImovelPerfil() != null
				&& filtrarDocumentoCobranca.getIdsImovelPerfil().length > 0){
					
			Integer[] idsImovelPerfil = filtrarDocumentoCobranca.getIdsImovelPerfil();
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			
			for(int i=0; i< idsImovelPerfil.length; i++ ){
			    
				
				if(!idsImovelPerfil[i].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			      
				    if(idsImovelPerfil.length == 1){
				    	
				    	filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				    	FiltroImovelPerfil.ID, idsImovelPerfil[i]));
				    	
				    }
				    else if( i == 0 ){
				    	
				    	filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				    			FiltroImovelPerfil.ID, idsImovelPerfil[i], ParametroSimples.CONECTOR_OR, idsImovelPerfil.length));
				    	 
				    }
				    else if( i  == (idsImovelPerfil.length - 1) ){
				    	
				    	filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				    			FiltroImovelPerfil.ID, idsImovelPerfil[i]));
				    
				    }
				    else{
				    	filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				    			FiltroImovelPerfil.ID, idsImovelPerfil[i], ParametroSimples.CONECTOR_OR));
				    }
				}
			}
			Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			
			for (Iterator iter = colecaoImovelPerfil.iterator(); iter
					.hasNext();) {
				ImovelPerfil imovelPerfil = (ImovelPerfil) iter.next();
				
				perfilImovel += imovelPerfil.getDescricao() + " / ";
			}
			//remove a barra do final
			perfilImovel = perfilImovel.substring(0, perfilImovel.length() - 3);
		}
		
		//categoria do imovel
		String categoriaImovel = "";
		if (filtrarDocumentoCobranca.getIdsImovelCategoria() != null
				&& filtrarDocumentoCobranca.getIdsImovelCategoria().length > 0){
					
			Integer[] idsCategoria = filtrarDocumentoCobranca.getIdsImovelCategoria();
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			
			for(int i=0; i< idsCategoria.length; i++ ){
			    
				if(!idsCategoria[i].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			      
				    if(idsCategoria.length == 1){
				    	
				    	filtroCategoria.adicionarParametro(new ParametroSimples(
				    	FiltroCategoria.CODIGO, idsCategoria[i]));
				    	
				    }
				    else if( i == 0 ){
				    	
				    	filtroCategoria.adicionarParametro(new ParametroSimples(
				    			FiltroCategoria.CODIGO, idsCategoria[i], ParametroSimples.CONECTOR_OR, idsCategoria.length));
				    	 
				    }
				    else if( i  == (idsCategoria.length - 1) ){
				    	
				    	filtroCategoria.adicionarParametro(new ParametroSimples(
				    			FiltroCategoria.CODIGO, idsCategoria[i]));
				    
				    }
				    else{
				    	filtroCategoria.adicionarParametro(new ParametroSimples(
				    			FiltroCategoria.CODIGO, idsCategoria[i], ParametroSimples.CONECTOR_OR));
				    }
				}
			}
			Collection colecaoImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			
			for (Iterator iter = colecaoImovelCategoria.iterator(); iter
					.hasNext();) {
				Categoria imovelCategoria = (Categoria) iter.next();
				
				categoriaImovel += imovelCategoria.getDescricao() + " / ";
			}
			//remove a barra do final
			categoriaImovel = categoriaImovel.substring(0, categoriaImovel.length() - 3);
		}
		
		//empresa
		String empresaNome = "";
		if (filtrarDocumentoCobranca.getIdsEmpresa() != null
				&& filtrarDocumentoCobranca.getIdsEmpresa().length > 0){
					
			Integer[] idsEmpresas = filtrarDocumentoCobranca.getIdsEmpresa();
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			
			for(int i=0; i< idsEmpresas.length; i++ ){
			     
				if(!idsEmpresas[i].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
			      
				    if(idsEmpresas.length == 1){
				    	
				    	filtroEmpresa.adicionarParametro(new ParametroSimples(
				    	FiltroEmpresa.ID, idsEmpresas[i]));
				    	
				    }
				    else if( i == 0 ){
				    	
				    	filtroEmpresa.adicionarParametro(new ParametroSimples(
				    			FiltroEmpresa.ID, idsEmpresas[i], ParametroSimples.CONECTOR_OR, idsEmpresas.length));
				    	 
				    }
				    else if( i  == (idsEmpresas.length - 1) ){
				    	
				    	filtroEmpresa.adicionarParametro(new ParametroSimples(
				    			FiltroEmpresa.ID, idsEmpresas[i]));
				    
				    }
				    else{
				    	filtroEmpresa.adicionarParametro(new ParametroSimples(
				    			FiltroEmpresa.ID, idsEmpresas[i], ParametroSimples.CONECTOR_OR));
				    }
				}
			}
			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			for (Iterator iter = colecaoEmpresa.iterator(); iter
					.hasNext();) {
				Empresa empresa = (Empresa) iter.next();
				
				empresaNome += empresa.getDescricaoAbreviada() + " / ";
			}
			//remove a barra do final
			empresaNome = empresaNome.substring(0, empresaNome.length() - 3);
		}
		
		//seta os pametros
		parametros.put("matriculaImovel", matriculaImovel);
		parametros.put("gerenciaRegional", gerenciaRegional);
		parametros.put("unidadeNegocio", unidadeNegocio);
		parametros.put("localidadeInicial", localidadeInicial);
		parametros.put("localidadeFinal", localidadeFinal);
		parametros.put("setorComercialInicial", setorComercialInicial);
		parametros.put("setorComercialFinal", setorComercialFinal);
		parametros.put("quadraInicial", quadraInicial);
		parametros.put("quadraFinal", quadraFinal);
		parametros.put("formaEmissao", formaEmissao);
		parametros.put("acaoCobranca", acaoCobranca);
		parametros.put("ciclo", ciclo);
		parametros.put("periodoEmissao", periodoEmissao);
		parametros.put("intervaloValorDocumento", intervaloValorDocumento);
		parametros.put("motivoNaoEntregaDocumento", motivoNaoEntrega);
		parametros.put("imovelPerfil", perfilImovel);
		parametros.put("categoria", categoriaImovel);
		parametros.put("empresa", empresaNome);
		
		Integer tipoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		List<RelatorioFiltrarDocumentoCobrancaBean> beans = new ArrayList<RelatorioFiltrarDocumentoCobrancaBean>();
		List documentosEncontrados = fachada.filtrarCobrancaDocumento(filtrarDocumentoCobranca);
		Object obj = null;
		Object[] dados = null;
		FiltroCobrancaDocumentoItem filtroCobrancaDocumentoItem = null;
		Collection colecaoCobrancaDocumentoItem = null;
		
		//seta os dados dos beans
		if (documentosEncontrados != null){
			for (int i = 0; i < documentosEncontrados.size(); i++) {
				obj = documentosEncontrados.get(i);
				
				if (obj instanceof Object[]) {
					dados = (Object[]) obj;
					
					RelatorioFiltrarDocumentoCobrancaBean bean = new RelatorioFiltrarDocumentoCobrancaBean();
					
					//instancia imovel a ser usado posteriormente
					Imovel imovel = new Imovel();
					imovel.setId(new Integer(dados[1].toString()));
					
					//dados para inscricao formatada
					Localidade localidade = new Localidade();
					localidade.setId(new Integer(dados[2].toString()));
					SetorComercial setor = new SetorComercial();
					setor.setCodigo(new Integer(dados[3].toString()));
					Quadra quadra = new Quadra();
					quadra.setNumeroQuadra(new Integer(dados[4].toString()));
					imovel.setLocalidade(localidade);
					imovel.setSetorComercial(setor);
					imovel.setQuadra(quadra);
					imovel.setLote(new Short(dados[5].toString()));
					imovel.setSubLote(new Short(dados[6].toString()));
					
					//obter endereco
					String endereco="";
					endereco = fachada.pesquisarEndereco(imovel.getId());
					
					bean.setMatriculaImovel(imovel.getId().toString());
					bean.setInscricaoImovel(imovel.getInscricaoFormatada());
					bean.setEndereco(endereco);
					bean.setUsuario(dados[7].toString());
					bean.setSituacaoLigacaoAgua(dados[8].toString());
					bean.setSituacaoLigacaoEsgoto(dados[9].toString());
					bean.setPerfilImovel(dados[10].toString());
					
					
					if (dados[11] != null){
						bean.setCategoria(dados[11].toString());
					}
					
					//pesquisar dados da ordem de servi�o
					Object[] dadosOrdemServico = fachada.pesquisarDadosOrdemServicoDocumentoCobranca(new Integer(dados[0].toString()));
					if(dadosOrdemServico != null){
						if(dadosOrdemServico[0] != null){
							bean.setOrdemServico((Integer)dadosOrdemServico[0]);
						}
					}
					
					bean.setEmissao(Util.converteStringInvertidaSemBarraParaDate(dados[12].toString().substring(0,10).replace("-","")));
					bean.setValor(new BigDecimal(dados[13].toString()));
					
					//pesquisar dados de cobrancaDocumentoItem
					filtroCobrancaDocumentoItem = new FiltroCobrancaDocumentoItem();
					filtroCobrancaDocumentoItem
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaDocumentoItem.COBRANCA_DOCUMENTO_ID,
							new Integer(dados[0].toString())));
					colecaoCobrancaDocumentoItem = fachada.pesquisar(
							filtroCobrancaDocumentoItem, CobrancaDocumentoItem.class.getName());
					
					if (colecaoCobrancaDocumentoItem == null
							|| colecaoCobrancaDocumentoItem.isEmpty()) {
						bean.setQuatidadeItens(0);
					} else {
						bean.setQuatidadeItens(colecaoCobrancaDocumentoItem
										.size());
					}
					
					if (dados[14] != null){
						bean.setSituacaoAcao(dados[14].toString());
					}
					
					if (dados[15] != null){
						bean.setSituacaoDebito(dados[15].toString());
					}
					
					beans.add(bean);
				}
			}
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_FILTRAR_DOCUMENTO_COBRANCA,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_FILTRAR_DOCUMENTO_COBRANCA, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException(	"Erro ao gravar relat�rio no sistema",
										e);
		}
		// ------------------------------------
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada
				.getInstancia()
				.filtrarCobrancaDocumentoCount((FiltrarDocumentoCobrancaHelper) getParametro("filtroCobrancaDocumento"));

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFiltrarDocumentoCobranca", this);
	}

}
