package gsan.relatorio.cadastro.imovel;

import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioManterImovelPerfil extends TarefaRelatorio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  RelatorioManterImovelPerfil(Usuario usuario){
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_IMOVEL_PERFIL);
	}
	
	@Deprecated
	public RelatorioManterImovelPerfil() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {	
		
		// ------------------------------------
	//	Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroImovelPerfil filtroImovelPerfil = (FiltroImovelPerfil) getParametro("filtroImovelPerfil");
		ImovelPerfil imovelPerfilParametros = (ImovelPerfil) getParametro("imovelPerfilParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterImovelPerfilBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroImovelPerfil.setConsultaSemLimites(true);

		Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
				ImovelPerfil.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoImovelPerfil != null && !colecaoImovelPerfil.isEmpty()) {

			// la�o para criar a cole��o de par�metros da analise
			for (ImovelPerfil imovelPerfil : colecaoImovelPerfil) {

				relatorioBean = new RelatorioManterImovelPerfilBean(
						// C�digo
						imovelPerfil.getId().toString(),					 
						
						// Descri��o
						imovelPerfil.getDescricao());						

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
				
			}
			
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (imovelPerfilParametros.getId() != null) {
			parametros.put("id",
					imovelPerfilParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}

		if ( imovelPerfilParametros.getDescricao() != null &&
				!imovelPerfilParametros.getDescricao().equals("") ) {
			
			parametros.put("descricao", imovelPerfilParametros.getDescricao());
		} else {
			parametros.put("descricao", "" );
		}
		
		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_IMOVEL_PERFIL, parametros,
				ds, tipoFormatoRelatorio);
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterImovelPerfil", this);
	}

}
