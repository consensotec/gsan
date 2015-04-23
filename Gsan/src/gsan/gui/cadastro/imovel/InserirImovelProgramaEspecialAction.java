package gsan.gui.cadastro.imovel;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.FiltroImovelProgramaEspecial;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelProgramaEspecial;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC0973] Inserir Imóvel em Programa Especial
 * 
 * @author Hugo Amorim
 * @date 17/12/2009
 */
public class InserirImovelProgramaEspecialAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		// Obter Parametros do sistema	
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		InserirImovelProgramaEspecialActionForm form = (InserirImovelProgramaEspecialActionForm) actionForm;
		Integer idImovelInserido = null;
		
		if(form.getDescricaoDocumentos()!=null
				&& form.getDescricaoDocumentos().length()>200){
			throw new ActionServletException("atencao.execedeu_limit_observacao","Observação","200");
		}
		
		ImovelProgramaEspecial imovelProgramaEspecial = new ImovelProgramaEspecial();
		
		Imovel imovel = null;
		
		if(form.getMatriculaImovel()!=null && !form.getMatriculaImovel().equals("")){
			FiltroImovel filtroImovel = new FiltroImovel();
			
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, form.getMatriculaImovel()));
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.FATURAMENTO_GRUPO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
			
			Collection<Imovel> colecaoImovel = 
				fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
		
			imovelProgramaEspecial.setImovel(imovel);
		}
		
		/*
		 * @author Jonathan Marcos
		 * @date 13/02/2014
		 * [OBSERVACOES]
		 * 		- [FS0005] VERIFICA INDICADOR PROGRAMA ESPECIAL
		 * 		  VERIFICA SE O CAMPO cltp_icprogramaespecial 
		 * 		  DA TABELA CLIENTE_TIPO E IGUAL A 1	
		 * 
		 */
		if(fachada.verificarClienteTipoProgramaEspecial(imovel.getId()).compareTo(ConstantesSistema.SIM)==0){
			
			if(form.getDescricaoDocumentos()!=null 
					&& !form.getDescricaoDocumentos().equals("")){
				imovelProgramaEspecial.setDescricaoDocumentos(form.getDescricaoDocumentos());
			}
			
			if(form.getNumeroBolsaFamilia()!=null 
					&& !form.getNumeroBolsaFamilia().equals("")){
				imovelProgramaEspecial.setNumeroBolsaFamilia(form.getNumeroBolsaFamilia());
			}
			
			if(form.getDataApresentacaoDocumentos()!=null 
					&& !form.getDataApresentacaoDocumentos().equals("")){
				Date dataApresentacao = Util.converteStringParaDate(form.getDataApresentacaoDocumentos());			
				imovelProgramaEspecial.setDataApresentacaoDocumentos(dataApresentacao);
			}
			
		// CASO CONTRARIO cltp_icprogramaespecial IGUAL A 2
		}else{
			
			/**
			 * @author Arthur Carvalho
			 * @date 02/08/2011
			 *  Restricao para inserir o imovel em programa especial, caso o cliente usuario tenha CPF
			 */
			Cliente cliente = fachada.pesquisarClienteUsuarioImovel(imovel.getId());
			if ( cliente != null && ( cliente.getCpf() == null || cliente.getCpf().equals("")) ) {
				throw new ActionServletException("atencao.cliente_usuario_nao_possui_cpf");
			}
			
			if(form.getDescricaoDocumentos()!=null 
					&& !form.getDescricaoDocumentos().equals("")){
				imovelProgramaEspecial.setDescricaoDocumentos(form.getDescricaoDocumentos());
			}
			
			if(form.getNumeroBolsaFamilia()!=null 
					&& !form.getNumeroBolsaFamilia().equals("")){
				imovelProgramaEspecial.setNumeroBolsaFamilia(form.getNumeroBolsaFamilia());
			}
			
			if(form.getDataApresentacaoDocumentos()!=null 
					&& !form.getDataApresentacaoDocumentos().equals("")){
				Date dataApresentacao = Util.converteStringParaDate(form.getDataApresentacaoDocumentos());			
				imovelProgramaEspecial.setDataApresentacaoDocumentos(dataApresentacao);
			}	
			
			if(sistemaParametro.getClienteResponsavelProgramaEspecial()==null){
				
				throw new ActionServletException("atencao.nao.existe.cliente.reposponsavel.cadastrado");
				
			}
			if(sistemaParametro.getPerfilProgramaEspecial()==null){
				
				throw new ActionServletException("atencao.nao.existe.perfil.programa.cadastrado");
				
			}	
				
			Date dataAtual = new Date();
			
			if(Util.compararData(imovelProgramaEspecial.getDataApresentacaoDocumentos(), dataAtual)==1){
				throw new ActionServletException("atencao.data.apresentacao.documentos.invalida");
			}
			
			// [FS0005] Verificar existência do imóvel no programa especial	
			FiltroImovelProgramaEspecial filtroImovelProgramaEspecial =  new FiltroImovelProgramaEspecial();
			
			filtroImovelProgramaEspecial.adicionarParametro(new ParametroSimples(
					FiltroImovelProgramaEspecial.IMOVEL_ID, imovelProgramaEspecial.getImovel().getId()));
			filtroImovelProgramaEspecial.adicionarParametro(new ParametroNulo(
					FiltroImovelProgramaEspecial.DATA_SUSPENSAO));
			
			Collection<ImovelProgramaEspecial> colecaoImovelProgramaEspecial = 
				fachada.pesquisar(filtroImovelProgramaEspecial, ImovelProgramaEspecial.class.getName());
			
			if(colecaoImovelProgramaEspecial!=null && !colecaoImovelProgramaEspecial.isEmpty()){
				throw new ActionServletException("atencao.imovel.ja.cadastrado.no.programa");
			}
			
			/**
			 * @author Thúlio Araújo
			 * @since 14/10/2011
			 * 
			 * Verificar se o imovel tem situacao especial de cobranca (cbsp_id <> null DA TABELA IMOVEL ) , caso tenha informar a mensagem:
			 * "Imovel está em situação especial de cobrança, não é possível colocar no programa especial."
			 */
			boolean cobrancaSituacaoImovel = fachada.confirmarImovelTemSituacaoEspecialCobranca(Integer.valueOf(form.getMatriculaImovel()));
			
			if (cobrancaSituacaoImovel){
				throw new ActionServletException("atencao.imovel.situacao_especial_cobranca");
			}
			
			// [FS0006] Verificar se o imóvel tem débito automático
			if(imovelProgramaEspecial.getImovel()
					.getIndicadorDebitoConta().compareTo(ConstantesSistema.SIM)==0){
				throw new ActionServletException("atencao.imovel_debito_automatico");
			}
			
			// [FS0004] Valida dados do imóvel no programa especial
			
			if (fachada.verificarExistenciaParcelamentoImovel(imovelProgramaEspecial.getImovel())){
				throw new ActionServletException("atencao.imovel.existe.parcelamento");
			}
			
			fachada.validarDadosInserirImovelProgramaEspecial(imovelProgramaEspecial);
		}
		
		idImovelInserido = 
				fachada.inserirImovelEmProgramaEspecial(imovelProgramaEspecial, usuarioLogado);
		
		montarPaginaSucesso(
				httpServletRequest,
				"Matrícula do imóvel "
				+ imovelProgramaEspecial.getImovel().getId() 
				+ " inserida com sucesso no programa especial.",
				"Inserir outro imóvel no programa.",
				"exibirInserirImovelProgramaEspecialAction.do?menu=sim",
				"exibirAtualizarImovelProgramaEspecialAction.do?imovelProgramaEspecialID="
						+ idImovelInserido,
				"Manter Imóvel inserido no Programa Especial.");
			
		
		return retorno;
	}

}
