package br.com.caelum.vraptor.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.annotation.Public;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.controller.LoginController;
import br.com.caelum.vraptor.controller.UsuarioLogado;

@Intercepts
public class AutorizadorInterceptor {
	
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	private final ControllerMethod controllerMethod;

	@Inject
	private AutorizadorInterceptor(Result result, UsuarioLogado usuarioLogado, ControllerMethod controllerMethod) {
		this.result = result;
		this.usuarioLogado = usuarioLogado;
		this.controllerMethod = controllerMethod;
	}

	@Deprecated
	public AutorizadorInterceptor() {
		this(null, null, null); //para uso do CDI
	}
	
	//Definindo se vamos interceptar o metodo ou não obs: para deixar livre a tela de login
	@Accepts
	public boolean accepts() {
	    return !controllerMethod.containsAnnotation(Public.class); //Os Métodos que não tiverem a anotação public serão interceptados
	}
	
	//Código de interceptação
	@AroundCall
	public void intercepta(SimpleInterceptorStack stack) {
		
		//antes de executar
        if (usuarioLogado.getUsuario() == null) {
            result.redirectTo(LoginController.class).formulario();
            return;
        }
		
		stack.next(); //executa o código normalmente
		//depois de executar
		
	}
}
