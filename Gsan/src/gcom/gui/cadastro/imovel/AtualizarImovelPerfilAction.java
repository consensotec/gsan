package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelPerfilCapacidadeHidrometro;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfilCapacidadeHidrometro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1032]	MANTER IMOVEL PERFIL
 * 
 * @author Wallace Thierre
 * @date 04/10/2010
 * 
 */

public class AtualizarImovelPerfilAction extends GcomAction{
	

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");	

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		AtualizarImovelPerfilActionForm form =
			(AtualizarImovelPerfilActionForm) actionForm;
		
		FiltroImovelPerfilCapacidadeHidrometro filtroImovelPerfilCapacidadeHidrometro;
		ImovelPerfilCapacidadeHidrometro imovelPerfilCapacidadeHidrometro;
		HidrometroCapacidade hidrometroCapacidade;
		Collection<ImovelPerfilCapacidadeHidrometro> colecaoImovelPerfilCapacidadeHidrometroRemover;
		FiltroPermissaoEspecial filtroPermissaoEspecial;
		PermissaoEspecial permissaoEspecial;
		
		ImovelPerfil imovelPerfil = (ImovelPerfil)
			sessao.getAttribute("atualizarImovelPerfil");
		
		String[] hidrometroCapacidades = null;
		
		if(form.getHidrometroCapacidadesHidden()!=null && form.getHidrometroCapacidadesHidden().length()!=0)
		{
			hidrometroCapacidades = form.getHidrometroCapacidadesHidden().split(",");
		}
		
		form.setHidrometroCapacidades(hidrometroCapacidades);
		
		

		if(validarMesmaDescricao(form.getDescricao(),Integer.valueOf(form.getId()))
				&& validarSelecaoIndicadores(form)
				&& verificarCapacidadeSelecionada(form))
		{
			//Atualiza a entidade com os valores do formulário		
			imovelPerfil.setDescricao(form.getDescricao());
			imovelPerfil.setIndicadorUso(form.getIndicadorUso());
			imovelPerfil.setIndicadorGeracaoAutomatica(form.getIndicadorGeracaoAutomatica());
			imovelPerfil.setIndicadorInserirManterPerfil(form.getIndicadorInserirManterPerfil());
			imovelPerfil.setIndicadorGerarDadosLeitura(form.getIndicadorGerarDadosLeitura());
			imovelPerfil.setIndicadorBloquearRetificacao(form.getIndicadorBloquearRetificacao());
			imovelPerfil.setIndicadorGrandeConsumidor(form.getIndicadorGrandeConsumidor());
			imovelPerfil.setIndicadorBloqueaDadosSocial(form.getIndicadorBloquearDadosSocial());
			imovelPerfil.setIndicadorGeraDebitoSegundaViaConta(form.getIndicadorGeraDebitoSegundaViaConta());
			imovelPerfil.setIndicadorAcrescimoImpontualidade(form.getIndicadorAcrescimosImpontualidade());
			imovelPerfil.setIndicadorNegativacaoDoCliente(form.getIndicadorNegativacaoDoCliente());
			imovelPerfil.setIndicadorCorporativo(form.getIndicadorCorporativo());
			imovelPerfil.setIndicadorPerfilTelemedido(form.getIndicadorPerfilTelemedido());
			
			if(Util.isInteger(form.getConsumoMinimo()) && 
					(imovelPerfil.getIndicadorCorporativo().equals(ConstantesSistema.SIM) 
							|| imovelPerfil.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM)))
			{
				imovelPerfil.setConsumoMinimo(Integer.valueOf(form.getConsumoMinimo()));
			}
			else
			{
				imovelPerfil.setConsumoMinimo(null);
			}
			
			if(form.getIdPermissaoEspecial()!=null && !form.getIdPermissaoEspecial().equals(""))
			{
				filtroPermissaoEspecial = new FiltroPermissaoEspecial();
				filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(FiltroPermissaoEspecial.ID, 
						Integer.valueOf(form.getIdPermissaoEspecial())));
				
				permissaoEspecial = (PermissaoEspecial) Util.retonarObjetoDeColecao(getFachada().
						pesquisar(filtroPermissaoEspecial, PermissaoEspecial.class.getName()));
				
				imovelPerfil.setPermissaoEspecial(permissaoEspecial);
			}
			else
			{
				imovelPerfil.setPermissaoEspecial(null);
			}
			
			imovelPerfil.setUltimaAlteracao(new Date());
		
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_IMOVEL_PERFIL_ATUALIZAR, imovelPerfil.getId(),
					imovelPerfil.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
			registradorOperacao.registrarOperacao(imovelPerfil);
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			
			fachada.atualizar(imovelPerfil);
			
			filtroImovelPerfilCapacidadeHidrometro = new FiltroImovelPerfilCapacidadeHidrometro();
			filtroImovelPerfilCapacidadeHidrometro.adicionarParametro(
					new ParametroSimples(FiltroImovelPerfilCapacidadeHidrometro.IMOVEL_PERFIL_ID, imovelPerfil.getId()));
			
			colecaoImovelPerfilCapacidadeHidrometroRemover = getFachada().pesquisar(filtroImovelPerfilCapacidadeHidrometro, 
					ImovelPerfilCapacidadeHidrometro.class.getName());
				
			if(colecaoImovelPerfilCapacidadeHidrometroRemover!=null && colecaoImovelPerfilCapacidadeHidrometroRemover.size()!=0)
			{
				for(ImovelPerfilCapacidadeHidrometro imovelPerfilCHRemover : colecaoImovelPerfilCapacidadeHidrometroRemover)
				{
					getFachada().remover(imovelPerfilCHRemover);
				}
			}
			
			if(form.getIndicadorCorporativo().equals(ConstantesSistema.SIM)
					|| form.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM))
			{
				if(form.getHidrometroCapacidades()!=null 
						&& form.getHidrometroCapacidades().length!=0)
				{
					for(String idHidrometroCapacidade : form.getHidrometroCapacidades())
					{
						imovelPerfilCapacidadeHidrometro = new ImovelPerfilCapacidadeHidrometro();
						imovelPerfilCapacidadeHidrometro.setImovelPerfil(imovelPerfil);
						
						hidrometroCapacidade = new HidrometroCapacidade();
						hidrometroCapacidade.setId(Integer.valueOf(idHidrometroCapacidade));
						imovelPerfilCapacidadeHidrometro.setHidrometroCapacidade(hidrometroCapacidade);
						
						imovelPerfilCapacidadeHidrometro.setIndicadorUso(ConstantesSistema.SIM);
						imovelPerfilCapacidadeHidrometro.setUltimaAlteracao(new Date());
						
						getFachada().inserir(imovelPerfilCapacidadeHidrometro);
					}
				}
			}
			
			montarPaginaSucesso(httpServletRequest, "Imovel Perfil "
					+form.getDescricao() + " atualizado com sucesso.",
					"Realizar outra Manutenção Imovel Perfil",
					"exibirFiltrarImovelPerfilAction.do?menu=sim");
		}

		return retorno;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * validar se existe uma descrição<br>
	 * igual na base de dados
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param descricao
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarMesmaDescricao(String descricao,Integer idImovelPerfil){
		
		ImovelPerfil imovelPerfil;
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.DESCRICAO, descricao));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil==null || colecaoImovelPerfil.size()!=0)
		{
			imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(colecaoImovelPerfil);
			
			if(!imovelPerfil.getId().equals(idImovelPerfil))
			{
				throw new ActionServletException("atencao.descricao_existente", null,
						descricao);
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * [FS0004]-Verificar seleção de indicadores
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param inserirImovelPerfilActionForm
	 * @return boolean
	 */
	private boolean validarSelecaoIndicadores(AtualizarImovelPerfilActionForm atualizarImovelPerfilActionForm){
		
		boolean validado = false;
		
		if(validarIndicadorClienteCorporativoGrandeConsumidor(
				atualizarImovelPerfilActionForm.getIndicadorCorporativo(), 
				atualizarImovelPerfilActionForm.getIndicadorGrandeConsumidor()))
		{
			if(atualizarImovelPerfilActionForm.getIndicadorCorporativo().equals(ConstantesSistema.SIM))
			{
				validarClienteCorporativoIndicadorTelemedido(Integer.valueOf(atualizarImovelPerfilActionForm.getId()),
						atualizarImovelPerfilActionForm.getIndicadorPerfilTelemedido());
				
				if(Util.isInteger(atualizarImovelPerfilActionForm.getConsumoMinimo()))
				{
					validarPerfilCorporativoConsumoMinimo(Integer.valueOf(atualizarImovelPerfilActionForm.getConsumoMinimo()));
				}
			}
			
			if(atualizarImovelPerfilActionForm.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM))
			{
				validarGrandeConsumidorIndicadorTelemedido(Integer.valueOf(atualizarImovelPerfilActionForm.getId()),atualizarImovelPerfilActionForm.getIndicadorPerfilTelemedido());
				
				if(Util.isInteger(atualizarImovelPerfilActionForm.getConsumoMinimo()))
				{
					validarPerfilGrandeConsumidorConsumoMinimo(Integer.valueOf(atualizarImovelPerfilActionForm.getConsumoMinimo()));
				}
			}
			
			validado = true;
		}
		
		return validado;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * validar os indicador<br>
	 * corporativo e grande consumidor
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param indicadorClienteCorporativo
	 * @param indicadorGrandeConsumidor
	 * @return
	 */
	private boolean validarIndicadorClienteCorporativoGrandeConsumidor(Short indicadorClienteCorporativo,Short indicadorGrandeConsumidor){
		
		if(indicadorClienteCorporativo.equals(ConstantesSistema.SIM) 
				&& indicadorGrandeConsumidor.equals(ConstantesSistema.SIM))
		{
			throw new ActionServletException("atencao.indicador.cliente.corporativo.igual.indicador.grande.consumidor");
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * validar relação dos indicadores<br> 
	 * cliente corporativo e telemedido
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param indicadorTelemedido
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarClienteCorporativoIndicadorTelemedido(Integer idImovelPerdil,Short indicadorTelemedido){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_CORPORATIVO, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_PERFIL_TELEMEDIDO,indicadorTelemedido));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovelPerfil.ID, idImovelPerdil));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil!=null && colecaoImovelPerfil.size()!=0)
		{
			throw new ActionServletException("atencao.perfil.corporativo.mesmo.indicador.telemedido");
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * [FS0005]-Verificar Consumo Mínimo
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param consumoMinimo
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarPerfilCorporativoConsumoMinimo(Integer consumoMinimo){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_GRANDE_CONSUMIDOR, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().
				pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil!=null && colecaoImovelPerfil.size()!=0)
		{
			for(ImovelPerfil imovelPerfil : colecaoImovelPerfil)
			{
				if(imovelPerfil!=null && (imovelPerfil.getConsumoMinimo()!=null && imovelPerfil.getConsumoMinimo().intValue()>=consumoMinimo.intValue()))
				{
					throw new ActionServletException("atencao.consumo.minimo.corporativo.maior.grande.consumidor");
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * [FS0005]-Verificar Consumo Mínimo
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param consumoMinimo
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarPerfilGrandeConsumidorConsumoMinimo(Integer consumoMinimo){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_CORPORATIVO, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().
				pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil!=null && colecaoImovelPerfil.size()!=0)
		{
			for(ImovelPerfil imovelPerfil : colecaoImovelPerfil)
			{
				if(imovelPerfil!=null && (imovelPerfil.getConsumoMinimo()!=null && imovelPerfil.getConsumoMinimo().intValue()<=consumoMinimo.intValue()))
				{
					throw new ActionServletException("atencao.consumo.minimo.corporativo.maior.grande.consumidor");
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * validar relação dos indicadores<br> 
	 * grande cliente e telemedido
	 * 
	 * @author Jonathan Marcos
	 * @since 03/06/2015
	 * @param indicadorTelemedido
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validarGrandeConsumidorIndicadorTelemedido(Integer idImovelPerdil,Short indicadorTelemedido){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_GRANDE_CONSUMIDOR, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_PERFIL_TELEMEDIDO,indicadorTelemedido));
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		filtroImovelPerfil.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovelPerfil.ID, idImovelPerdil));
		
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(colecaoImovelPerfil!=null && colecaoImovelPerfil.size()!=0)
		{
			throw new ActionServletException("atencao.perfil.grande.cliente.mesmo.indicador.telemedido");
		}
		
		return true;
	}
	
	/**
	 * 
	 * Método responsável por<br>
	 * [FS0006]-Verificar Capacidade Selecionada
	 * 
	 * @author Jonathan Marcos
	 * @since 04/06/2015
	 * @param inserirImovelPerfilActionForm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean verificarCapacidadeSelecionada(AtualizarImovelPerfilActionForm atualizarImovelPerfilActionForm){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		FiltroImovelPerfilCapacidadeHidrometro filtroImovelPerfilCapacidadeHidrometro = null;
		Collection<ImovelPerfil> colecaoImovelPefil = null;
		Collection<ImovelPerfilCapacidadeHidrometro> colecaoImovelPerfilCapacidadeHidrometro = null;
		ImovelPerfilCapacidadeHidrometro imovelPerfilCapacidadeHidrometro = null;
		
		
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		
		if(atualizarImovelPerfilActionForm.getHidrometroCapacidades()!=null)
		{
			filtroImovelPerfil.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovelPerfil.ID, Integer.valueOf(atualizarImovelPerfilActionForm.getId())));
			
			if(atualizarImovelPerfilActionForm.getIndicadorCorporativo().equals(ConstantesSistema.SIM))
			{
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_GRANDE_CONSUMIDOR, ConstantesSistema.SIM));
			}
			else if(atualizarImovelPerfilActionForm.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM))
			{
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_CORPORATIVO, ConstantesSistema.SIM));
			}
			
			if(atualizarImovelPerfilActionForm.getIndicadorCorporativo().equals(ConstantesSistema.SIM)
					|| atualizarImovelPerfilActionForm.getIndicadorGrandeConsumidor().equals(ConstantesSistema.SIM))
			{
				colecaoImovelPefil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
				
				if(colecaoImovelPefil!=null && colecaoImovelPefil.size()!=0)
				{
					for(ImovelPerfil perfil : colecaoImovelPefil)
					{
						for(String idHidrometroCapacidade : atualizarImovelPerfilActionForm.getHidrometroCapacidades())
						{
							filtroImovelPerfilCapacidadeHidrometro = new FiltroImovelPerfilCapacidadeHidrometro();
							filtroImovelPerfilCapacidadeHidrometro.adicionarParametro(new ParametroSimples(
									FiltroImovelPerfilCapacidadeHidrometro.IMOVEL_PERFIL_ID, perfil.getId()));
							filtroImovelPerfilCapacidadeHidrometro.adicionarParametro(new ParametroSimples(
									FiltroImovelPerfilCapacidadeHidrometro.HIDROMETRO_CAPACIDADE_ID, Integer.valueOf(idHidrometroCapacidade)));
							filtroImovelPerfilCapacidadeHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
							colecaoImovelPerfilCapacidadeHidrometro = getFachada().pesquisar(filtroImovelPerfilCapacidadeHidrometro, ImovelPerfilCapacidadeHidrometro.class.getName());
							
							if(colecaoImovelPerfilCapacidadeHidrometro!=null && colecaoImovelPerfilCapacidadeHidrometro.size()!=0)
							{
								imovelPerfilCapacidadeHidrometro = (ImovelPerfilCapacidadeHidrometro) 
										Util.retonarObjetoDeColecao(colecaoImovelPerfilCapacidadeHidrometro);
								
								atualizarImovelPerfilActionForm.setHidrometroCapacidades(null);
								
								throw new ActionServletException("atencao.capacidade.associada.corpocativo.grande.cliente", null,
										imovelPerfilCapacidadeHidrometro.getHidrometroCapacidade().getDescricao());
							}
						}
					}
				}
			}
		}
		
		return true;
	}

}
