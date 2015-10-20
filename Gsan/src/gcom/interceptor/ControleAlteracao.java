package gcom.interceptor;

import java.lang.annotation.*;


/**
 * Este anota��o ser� usada para definir os atributos e classes que ser�o
 * registradas nas opera��es efetuadas. 
 * @author Francisco do Nascimento
 *
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface ControleAlteracao {
	// o valor guardado se refere a um campo de filtro que deveria ser adicionado
	// para carregamento, a fim de se estar carregado o valor do atributo controlado.
	String value() default "";
	// valor utilizado para definir conjuntos de atributos por funcionalidade
	// Ex.: Manter Imovel: codigo, nomeproprietario 
	//		Inserir imovel: c�digo, nomeproprietario, dataNascimento 
	int[] funcionalidade() default {0};
	
	int idTabelaColuna() default 0;
}
