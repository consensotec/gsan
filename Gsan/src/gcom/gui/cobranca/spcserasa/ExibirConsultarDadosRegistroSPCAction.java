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
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegRetMot;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorMovimentoRegRetMot;
import gcom.util.ConstantesSistema;
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
 * Permite a consulta dos dados de um registro no formato padr�o SPC.
 * [UC0683] - Consultar Dados do Registro SPC
 * 
 * @author Yara Taciane de Souza
 * @date 23/01/2008
 */
public class ExibirConsultarDadosRegistroSPCAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {		
		
		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		ConsultarDadosRegistroActionForm form = (ConsultarDadosRegistroActionForm) actionForm;

		NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) sessao.getAttribute("negativadorMovimentoReg");
			
		form.setIdNegativadorMovimentoReg(negativadorMovimentoReg.getId().toString());		
		
		String indicCorrecao = "-1";
		
		if(negativadorMovimentoReg.getIndicadorCorrecao()!= null){
			if(negativadorMovimentoReg.getIndicadorCorrecao().equals(ConstantesSistema.CORRIGIDO)||negativadorMovimentoReg.getIndicadorCorrecao().equals(ConstantesSistema.NAO_CORRIGIDO) ){
				indicCorrecao = negativadorMovimentoReg.getIndicadorCorrecao().toString();
			}
		}
		
		form.setIndicadorCorrecao(indicCorrecao);
		
		NegativadorRegistroTipo negativadorRegistroTipo= negativadorMovimentoReg.getNegativadorRegistroTipo();
		
		if(negativadorRegistroTipo != null){
			
			if(negativadorRegistroTipo.getId().equals(NegativadorRegistroTipo.ID_SPC_HEADER)){
				retorno = exibirDadosRegistroTipoHeader(negativadorMovimentoReg,form,fachada,sessao,httpServletRequest,actionMapping);
			}else if(negativadorRegistroTipo.getId().equals(NegativadorRegistroTipo.ID_SPC_DETALHE_CONSUMIDOR)){
				retorno = exibirDadosRegistroTipoDetalheConsumidor(negativadorMovimentoReg,form,fachada,sessao,httpServletRequest,actionMapping);
			}else if(negativadorRegistroTipo.getId().equals(NegativadorRegistroTipo.ID_SPC_DETALHE_SPC)){
				retorno = exibirDadosRegistroTipoDetalheSPC(negativadorMovimentoReg,form,fachada,sessao,httpServletRequest,actionMapping);
			}else if(negativadorRegistroTipo.getId().equals(NegativadorRegistroTipo.ID_SPC_TRAILLER)){
				retorno = exibirDadosRegistroTipoTrailler(negativadorMovimentoReg,form,fachada,sessao,httpServletRequest,actionMapping);
			}else{
				throw new ActionServletException("atencao.codigo_tipo_registro_inexistente");
			}
		}
		
		   FiltroNegativadorMovimentoRegRetMot filtroNegativadorMovimentoRegRetMot = new FiltroNegativadorMovimentoRegRetMot();
		   filtroNegativadorMovimentoRegRetMot
					.adicionarParametro(new ParametroSimples(
							FiltroNegativadorMovimentoRegRetMot.NEGATIVADOR_MOVIMENTO_REG_ID,
							negativadorMovimentoReg.getId()));
		   filtroNegativadorMovimentoRegRetMot.adicionarCaminhoParaCarregamentoEntidade("negativadorRetornoMotivo");

		   
			Collection<NegativadorMovimentoRegRetMot> collNegativadorMovimentoRegRetMot = fachada
			.pesquisar(filtroNegativadorMovimentoRegRetMot,
					NegativadorMovimentoRegRetMot.class.getName());
		     
//		   
//			Map resultado = controlarPaginacao(httpServletRequest, retorno,
//					filtroNegativadorMovimentoRegRetMot, NegativadorMovimentoRegRetMot.class.getName());			
//			
//			Collection<NegativadorMovimentoRegRetMot> collNegativadorMovimentoRegRetMot = (Collection) resultado.get("colecaoRetorno");

				
			
			sessao.setAttribute("collNegativadorMovimentoRegRetMot", collNegativadorMovimentoRegRetMot);
			
			
		return retorno;
	}
	
	
	
	
	private ActionForward exibirDadosRegistroTipoHeader(
			NegativadorMovimentoReg negativadorMovimentoReg,
			ConsultarDadosRegistroActionForm form,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest,
			ActionMapping actionMapping ) {

		   ActionForward  retorno = actionMapping.findForward("consultarDadosRegistroSPCHeader");


		   Negativador negativador = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();
		   NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();
		   
		   if(negativador != null && !negativador.equals("")){
			   form.setNegativador(negativador.getCliente().getNome());
		   }else{
			   form.setNegativador(""); 
		   }
		   
		   Short codigoMovimento = negativadorMovimento.getCodigoMovimento();
		   if(codigoMovimento != null && !codigoMovimento.equals("")){
			   form.setTipoMovimento(codigoMovimento.toString());
		   }else{
			   form.setTipoMovimento(""); 
		   }
		  
		   String tipoRegistroCodigo=negativadorMovimentoReg.getNegativadorRegistroTipo().getCodigoRegistro();
		     if(tipoRegistroCodigo != null && !tipoRegistroCodigo.equals("")){
			   form.setTipoRegistroCodigo(tipoRegistroCodigo);
		   }else{
			   form.setTipoRegistroCodigo(""); 
		   }
		     
		   String tipoRegistroDescricao=negativadorMovimentoReg.getNegativadorRegistroTipo().getDescricaoRegistroTipo() ;
		   if(tipoRegistroDescricao != null && !tipoRegistroDescricao.equals("")){
			   form.setTipoRegistroDescricao(tipoRegistroDescricao);
		   }else{
			   form.setTipoRegistroDescricao(""); 
		   }
		   

		     
		     
			//Conte�do Registro
		   String conteudoRegistro = negativadorMovimentoReg.getConteudoRegistro();	
		   conteudoRegistro =Util.completaString(conteudoRegistro, 340);
		   
		   if(conteudoRegistro!= null && !conteudoRegistro.equals("")){
			   // Opera��o
			   String operacao=conteudoRegistro.substring(2, 9);
			   if(operacao != null && !operacao.equals("")){
				   form.setOperacao(operacao);
			   }else{
				   form.setOperacao(""); 
			   }
			   
			   Date dataMovimento = Util.converteStringParaDate(conteudoRegistro.substring(10,18));
			   if(dataMovimento != null && !dataMovimento.equals("")){
				   form.setDataMovimento(Util.formatarDataSemBarraDDMMAAAA(dataMovimento));
			   }else{
				   form.setDataMovimento(""); 
			   } 
			   
			   //sequencial Remessa
			   String sequencialRemessa=conteudoRegistro.substring(17, 25);
			   if(sequencialRemessa!= null && !sequencialRemessa.equals("")){
				   form.setSequencialRemessa(sequencialRemessa);
			   }else{
				   form.setSequencialRemessa(""); 
			   }
			   
			   //entidade
			   String entidade=conteudoRegistro.substring(25, 30);
			   if(entidade!= null && !entidade.equals("")){
				   form.setEntidade(entidade);
			   }else{
				   form.setEntidade(""); 
			   }
			   
			   //associado
			   String associado=conteudoRegistro.substring(30, 38);
			   if(associado!= null && !associado.equals("")){
				   form.setAssociado(associado);
			   }else{
				   form.setAssociado(""); 
			   }
			   
			   //dataMovimentoArquivo
			   String dataMovimentoArquivo=conteudoRegistro.substring(38, 46);
			   if(dataMovimentoArquivo!= null && !dataMovimentoArquivo.equals("")){
				   form.setDataMovimentoArquivo(dataMovimentoArquivo);
			   }else{
				   form.setDataMovimentoArquivo(""); 
			   }
			   
			   //unidadeNegocio
			   String unidadeNegocio=conteudoRegistro.substring(317, 322);
			   if(unidadeNegocio!= null && !unidadeNegocio.equals("")){
				   form.setUnidadeNegocio(unidadeNegocio);
			   }else{
				   form.setUnidadeNegocio(""); 
			   }
			   
			   //numeroVersao
			   String numeroVersao=conteudoRegistro.substring(323, 324);
			   if(numeroVersao!= null && !numeroVersao.equals("")){
				   form.setNumeroVersao(numeroVersao);
			   }else{
				   form.setNumeroVersao(""); 
			   }
			   
			   //codigoRetorno
			   String codigoRetorno=conteudoRegistro.substring(324, 334);
			   if(codigoRetorno!= null && !codigoRetorno.equals("")){
				   form.setCodigoRetorno(codigoRetorno);
			   }else{
				   form.setCodigoRetorno(""); 
			   }
			   
			   //sequencialRegistro
			   String sequencialRegistro=conteudoRegistro.substring(334, 340);
			   if(sequencialRegistro!= null && !sequencialRegistro.equals("")){
				   form.setSequencialRegistro(sequencialRegistro);
			   }else{
				   form.setSequencialRegistro(""); 
			   }
			   
			 
			   Short indicadorAceito=negativadorMovimentoReg.getIndicadorAceito();
			   if(indicadorAceito != null && !indicadorAceito.equals("")){
				   
				   if(indicadorAceito.equals(ConstantesSistema.ACEITO)){
					   form.setIndicadorAceitacao("SIM"); 
				   }else{
					   form.setIndicadorAceitacao("N�O");   
				   }
				   
			   }else{
				   form.setIndicadorAceitacao(""); 
			   }
			   
			   
			    
		   }
		   

			
			return retorno;
	}
	
	/**
	 * --------------------------------------------------------------------------------------------------------
	 */
	
	private ActionForward exibirDadosRegistroTipoDetalheConsumidor(
			NegativadorMovimentoReg negativadorMovimentoReg,
			ConsultarDadosRegistroActionForm form,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest,
			ActionMapping actionMapping ) {

		   ActionForward  retorno = actionMapping.findForward("consultarDadosRegistroSPCDetalheConsumidor");
		
		   Negativador negativador = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();
		   NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();
		   
		   if(negativador != null && !negativador.equals("")){
			   form.setNegativador(negativador.getCliente().getNome());
		   }else{
			   form.setNegativador(""); 
		   }
		   
		   Short codigoMovimento = negativadorMovimento.getCodigoMovimento();
		   if(codigoMovimento != null && !codigoMovimento.equals("")){
			   form.setTipoMovimento(codigoMovimento.toString());
		   }else{
			   form.setTipoMovimento(""); 
		   }
		  
   
		   String tipoRegistroCodigo=negativadorMovimentoReg.getNegativadorRegistroTipo().getCodigoRegistro();
		     if(tipoRegistroCodigo != null && !tipoRegistroCodigo.equals("")){
			   form.setTipoRegistroCodigo(tipoRegistroCodigo);
		   }else{
			   form.setTipoRegistroCodigo(""); 
		   }
		     
		   String tipoRegistroDescricao=negativadorMovimentoReg.getNegativadorRegistroTipo().getDescricaoRegistroTipo() ;
		   if(tipoRegistroDescricao != null && !tipoRegistroDescricao.equals("")){
			   form.setTipoRegistroDescricao(tipoRegistroDescricao);
		   }else{
			   form.setTipoRegistroDescricao(""); 
		   }
		   
		   //Conte�do Registro
		   String conteudoRegistro = negativadorMovimentoReg.getConteudoRegistro();		
		   conteudoRegistro =Util.completaString(conteudoRegistro, 340);
		   
		   if(conteudoRegistro!= null && !conteudoRegistro.equals("")){
			   
			   //D1.02
			   String pracaConcessao=conteudoRegistro.substring(2, 10);
			   if(pracaConcessao != null && !pracaConcessao.equals("")){
				   form.setPracaConcessao(pracaConcessao);
			   }else{
				   form.setPracaConcessao(""); 
			   }
			   
			   //D1.03
			   String nomeRazao=conteudoRegistro.substring(10, 55);
			   if(nomeRazao != null && !nomeRazao.equals("")){
				   form.setNomeRazao(nomeRazao);
			   }else{
				   form.setNomeRazao(""); 
			   }
			   
			   //D1.04
			   String tipoDocumentoCodigo=conteudoRegistro.substring(55, 56);
			   if(tipoDocumentoCodigo != null && !tipoDocumentoCodigo.equals("")){
				   form.setTipoDocumentoCodigo(tipoDocumentoCodigo);
				   if(tipoDocumentoCodigo.equals("1")){					   
					   form.setTipoDocumentoDescricao("CNPJ");
				   }else{
					   form.setTipoDocumentoDescricao("CPF"); 
				   }				  
			   }else{
				   form.setTipoDocumentoCodigo("");
				   form.setTipoDocumentoDescricao(""); 
			   }			   
			  

			   //D1.05
			   String cpfCnpj=conteudoRegistro.substring(56, 71);
			   if(cpfCnpj != null && !cpfCnpj.equals("")){
				   form.setCpfCnpj(cpfCnpj);
			   }else{
				   form.setCpfCnpj(""); 
			   } 
			   
			   //D1.06
			   String rg=conteudoRegistro.substring(71, 91);
			   if(rg != null && !rg.equals("")){
				   form.setRg(rg);
			   }else{
				   form.setRg(""); 
			   } 
			   
			   //D1.07			  
			   Date dataNascimento = Util.converteStringParaDate(conteudoRegistro.substring(91, 99));
			   if(dataNascimento != null && !dataNascimento.equals("")){
				   form.setDataNascimento(Util.formatarDataSemBarraDDMMAAAA(dataNascimento));
			   }else{
				   form.setDataNascimento(""); 
			   } 
			   
			   //D1.08
			   String filiacao=conteudoRegistro.substring(99,144);
			   if(filiacao != null && !filiacao.equals("")){
				   form.setFiliacao(filiacao);
			   }else{
				   form.setRg(""); 
			   } 
			   
			   //D1.09
			   String endereco=conteudoRegistro.substring(144,195);
			   if(endereco != null && !endereco.equals("")){
				   form.setEndereco(endereco);
			   }else{
				   form.setEndereco(""); 
			   }
			   
			   //D1.10
			   String numero=conteudoRegistro.substring(196,199);
			   if(numero != null && !numero.equals("")){
				   form.setNumero(numero);
			   }else{
				   form.setNumero(""); 
			   } 			   
			   //D1.11
			   String complemento=conteudoRegistro.substring(199,229);
			   if(complemento != null && !complemento.equals("")){
				   form.setComplemento(complemento);
			   }else{
				   form.setComplemento(""); 
			   } 
			   //D1.12
			   String bairro=conteudoRegistro.substring(229,254);
			   if(bairro != null && !bairro.equals("")){
				   form.setBairro(bairro);
			   }else{
				   form.setBairro(""); 
			   }			   
			   //D1.13
			   String cep=conteudoRegistro.substring(254,262);
			   if(cep != null && !cep.equals("")){
				   form.setCep(cep);
			   }else{
				   form.setCep(""); 
			   }
			   //D1.14
			   String cidade=conteudoRegistro.substring(262,292);
			   if(cidade != null && !cidade.equals("")){
				   form.setCidade(cidade);
			   }else{
				   form.setCidade(""); 
			   }
			   //D1.15
			   String uf=conteudoRegistro.substring(292,294);
			   if(uf != null && !uf.equals("")){
				   form.setUf(uf);
			   }else{
				   form.setUf(""); 
			   }
			   //D1.16
			   String foneDDD=conteudoRegistro.substring(294,296);
			   if(foneDDD != null && !foneDDD.equals("")){
				   form.setFoneDDD(foneDDD);
			   }else{
				   form.setFoneDDD(""); 
			   }
			   
			   //D1.18
			   String foneNumero=conteudoRegistro.substring(294,296);
			   if(foneNumero != null && !foneNumero.equals("")){
				   form.setFoneNumero(foneNumero);
			   }else{
				   form.setFoneNumero(""); 
			   }
			   //D1.19
			   String codigoRetorno=conteudoRegistro.substring(324,334);
			   if(codigoRetorno != null && !codigoRetorno.equals("")){
				   form.setCodigoRetorno(codigoRetorno);
			   }else{
				   form.setCodigoRetorno(""); 
			   }
			   //D1.20
			   String sequencialRegistro=conteudoRegistro.substring(334,340);
			   if(sequencialRegistro != null && !sequencialRegistro.equals("")){
				   form.setSequencialRegistro(sequencialRegistro);
			   }else{
				   form.setSequencialRegistro(""); 
			   }
			   
			   //D1.19
			   Short indicadorAceito=negativadorMovimentoReg.getIndicadorAceito();
			   if(indicadorAceito != null && !indicadorAceito.equals("")){
				   
				   if(indicadorAceito.equals(ConstantesSistema.ACEITO)){
					   form.setIndicadorAceitacao("SIM"); 
				   }else{
					   form.setIndicadorAceitacao("N�O");   
				   }
				   
			   }else{
				   form.setIndicadorAceitacao(""); 
			   }
				   
		   }
			

		
			return retorno;
	}
	
	private ActionForward exibirDadosRegistroTipoDetalheSPC(
			NegativadorMovimentoReg negativadorMovimentoReg,
			ConsultarDadosRegistroActionForm form,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest,
			ActionMapping actionMapping ) {

		   ActionForward  retorno = actionMapping.findForward("consultarDadosRegistroSPCDetalheSPC");
		
		   Negativador negativador = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();
		   NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();
		   
		   if(negativador != null && !negativador.equals("")){
			   form.setNegativador(negativador.getCliente().getNome());
		   }else{
			   form.setNegativador(""); 
		   }
		   
		   Short codigoMovimento = negativadorMovimento.getCodigoMovimento();
		   if(codigoMovimento != null && !codigoMovimento.equals("")){
			   form.setTipoMovimento(codigoMovimento.toString());
		   }else{
			   form.setTipoMovimento(""); 
		   }
		  
   
		   String tipoRegistroCodigo=negativadorMovimentoReg.getNegativadorRegistroTipo().getCodigoRegistro();
		     if(tipoRegistroCodigo != null && !tipoRegistroCodigo.equals("")){
			   form.setTipoRegistroCodigo(tipoRegistroCodigo);
		   }else{
			   form.setTipoRegistroCodigo(""); 
		   }
		 	     
		     
			//Conte�do Registro
		   String conteudoRegistro = negativadorMovimentoReg.getConteudoRegistro();	
		   conteudoRegistro =Util.completaString(conteudoRegistro, 340);
	   
		   if(conteudoRegistro!= null && !conteudoRegistro.equals("")){
			   
			   //D1.02
			   String tipoDocumentoCodigo=conteudoRegistro.substring(2, 3);
			   if(tipoDocumentoCodigo != null && !tipoDocumentoCodigo.equals("")){
				   form.setTipoDocumentoCodigo(tipoDocumentoCodigo);
				   if(tipoDocumentoCodigo.equals("1")){					   
					   form.setTipoDocumentoDescricao("CNPJ");
				   }else{
					   form.setTipoDocumentoDescricao("CPF"); 
				   }				  
			   }else{
				   form.setTipoDocumentoCodigo("");
				   form.setTipoDocumentoDescricao(""); 
			   }	
			   
			   //D1.02
			   String cpfCnpj=conteudoRegistro.substring(3,18);
			   if(cpfCnpj != null && !cpfCnpj.equals("")){
				   form.setCpfCnpj(cpfCnpj);
			   }else{
				   form.setCpfCnpj(""); 
			   } 
			   
			   //D1.02 � ISSO?
			   String codigoOperacao=conteudoRegistro.substring(18,19);
			   if(codigoOperacao != null && !codigoOperacao.equals("")){
				   form.setCodigoOperacao(codigoOperacao);
				   if(codigoOperacao.equals("I")){
					 form.setOperacao("INCLUS�O");  
				   }else{
					   form.setOperacao("EXCLUS�O");   
				   }
			   }else{
				   form.setCodigoOperacao(""); 
			   } 
			   
               //D1.05
			   String compradorFiadorAvalista=conteudoRegistro.substring(19,20);
			   if(compradorFiadorAvalista != null && !compradorFiadorAvalista.equals("")){
				   form.setCompradorFiadorAvalista(compradorFiadorAvalista);
			   }else{
				   form.setCompradorFiadorAvalista(""); 
			   } 
			   
			   // D1.06			  
			   Date dataVencimentoDebito = Util.converteStringParaDate(conteudoRegistro.substring(20, 28));
			   if(dataVencimentoDebito != null && !dataVencimentoDebito.equals("")){
				   form.setDataVencimentoDebito(Util.formatarDataSemBarraDDMMAAAA(dataVencimentoDebito));
			   }else{
				   form.setDataVencimentoDebito(""); 
			   } 
			   
			   // D1.06			  
			   Date dataRegistro = Util.converteStringParaDate(conteudoRegistro.substring(28, 36));
			   if(dataRegistro != null && !dataRegistro.equals("")){
				   form.setDataRegistro(Util.formatarDataSemBarraDDMMAAAA(dataRegistro));
			   }else{
				   form.setDataRegistro(""); 
			   } 
			   
			   // D1.08			  
			   Date valorDebito = Util.converteStringParaDate(conteudoRegistro.substring(36, 49));
			   if(valorDebito != null && !valorDebito.equals("")){
				   form.setValorDebito(Util.formatarDataSemBarraDDMMAAAA(valorDebito));
			   }else{
				   form.setValorDebito(""); 
			   } 
			   
			   //D1.09
			   String contrato=conteudoRegistro.substring(49,79);
			   if(contrato != null && !contrato.equals("")){
				   form.setContrato(contrato);
			   }else{
				   form.setContrato(""); 
			   } 
			   
			   //D1.10
			   String associado=conteudoRegistro.substring(79,87);
			   if(associado != null && !associado.equals("")){
				   form.setAssociado(associado);
			   }else{
				   form.setAssociado(""); 
			   }  
			   
			   //D1.11
			   String naturezaInclusao=conteudoRegistro.substring(87,89);
			   if(naturezaInclusao != null && !naturezaInclusao.equals("")){
				   form.setNaturezaInclusao(naturezaInclusao);
			   }else{
				   form.setNaturezaInclusao(""); 
			   }  
			   
			   //D1.12
			   String motivoExclusao=conteudoRegistro.substring(89,92);
			   if(motivoExclusao != null && !motivoExclusao.equals("")){
				   form.setMotivoExclusao(motivoExclusao);
			   }else{
				   form.setMotivoExclusao(""); 
			   }  
			   
			   //D1.14
			   String codigoRetorno=conteudoRegistro.substring(324,334);
			   if(codigoRetorno != null && !codigoRetorno.equals("")){
				   form.setCodigoRetorno(codigoRetorno);
			   }else{
				   form.setCodigoRetorno(""); 
			   }
			   
			   //D1.15
			   String sequencialRegistro=conteudoRegistro.substring(334,340);
			   if(sequencialRegistro != null && !sequencialRegistro.equals("")){
				   form.setSequencialRegistro(sequencialRegistro);
			   }else{
				   form.setSequencialRegistro(""); 
			   }  			   
		
			   Short indicadorAceito=negativadorMovimentoReg.getIndicadorAceito();
			   if(indicadorAceito != null && !indicadorAceito.equals("")){
				   
				   if(indicadorAceito.equals(ConstantesSistema.ACEITO)){
					   form.setIndicadorAceitacao("SIM"); 
				   }else{
					   form.setIndicadorAceitacao("N�O");   
				   }
				   
			   }else{
				   form.setIndicadorAceitacao(""); 
			   }
		   }
				
			return retorno;
	}
	
	private ActionForward exibirDadosRegistroTipoTrailler(
			NegativadorMovimentoReg negativadorMovimentoReg,
			ConsultarDadosRegistroActionForm form,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest,
			ActionMapping actionMapping ) {

		   ActionForward  retorno = actionMapping.findForward("consultarDadosRegistroSPCTrailler");

			
		   Negativador negativador = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();
		   NegativadorMovimento negativadorMovimento = negativadorMovimentoReg.getNegativadorMovimento();
		   
		   if(negativador != null && !negativador.equals("")){
			   form.setNegativador(negativador.getCliente().getNome());
		   }else{
			   form.setNegativador(""); 
		   }
		   
		   Short codigoMovimento = negativadorMovimento.getCodigoMovimento();
		   if(codigoMovimento != null && !codigoMovimento.equals("")){
			   form.setTipoMovimento(codigoMovimento.toString());
		   }else{
			   form.setTipoMovimento(""); 
		   }
		  
   
		   String tipoRegistroCodigo=negativadorMovimentoReg.getNegativadorRegistroTipo().getCodigoRegistro();
		     if(tipoRegistroCodigo != null && !tipoRegistroCodigo.equals("")){
			   form.setTipoRegistroCodigo(tipoRegistroCodigo);
		   }else{
			   form.setTipoRegistroCodigo(""); 
		   }
		     
		   String tipoRegistroDescricao=negativadorMovimentoReg.getNegativadorRegistroTipo().getDescricaoRegistroTipo() ;
		   if(tipoRegistroDescricao != null && !tipoRegistroDescricao.equals("")){
			   form.setTipoRegistroDescricao(tipoRegistroDescricao);
		   }else{
			   form.setTipoRegistroDescricao(""); 
		   }
		 	     
		     
			//Conte�do Registro
		   String conteudoRegistro = negativadorMovimentoReg.getConteudoRegistro();	
		   conteudoRegistro =Util.completaString(conteudoRegistro, 340);
	   
		   if(conteudoRegistro!= null && !conteudoRegistro.equals("")){   
			   
			   //T.02
			   String totalRegistros=conteudoRegistro.substring(2,8);
			   if(totalRegistros != null && !totalRegistros.equals("")){
				   form.setTotalRegistros(totalRegistros);
			   }else{
				   form.setTotalRegistros(""); 
			   }
			   
			   //T.04
			   String codigoRetorno=conteudoRegistro.substring(324,334);
			   if(codigoRetorno != null && !codigoRetorno.equals("")){
				   form.setCodigoRetorno(codigoRetorno);
			   }else{
				   form.setCodigoRetorno(""); 
			   }
			   
			   //T.05
			   String sequencialRegistro=conteudoRegistro.substring(334,340);
			   if(sequencialRegistro != null && !sequencialRegistro.equals("")){
				   form.setSequencialRegistro(sequencialRegistro);
			   }else{
				   form.setSequencialRegistro(""); 
			   }
			   
			   
			   Short indicadorAceito=negativadorMovimentoReg.getIndicadorAceito();
			   if(indicadorAceito != null && !indicadorAceito.equals("")){
				   
				   if(indicadorAceito.equals(ConstantesSistema.ACEITO)){
					   form.setIndicadorAceitacao("SIM"); 
				   }else{
					   form.setIndicadorAceitacao("N�O");   
				   }
				   
			   }else{
				   form.setIndicadorAceitacao(""); 
			   }
			   
			   
		   }   
		
			return retorno;
	}
	
}
