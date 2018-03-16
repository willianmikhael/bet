package br.com.caelum.vraptor.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.UsuarioDao;
import br.com.caelum.vraptor.model.Usuario;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class LoginController {

	private final UsuarioDao dao;
	private final Validator validator;
	private final Result result;

	@Inject
	public LoginController(UsuarioDao dao, Validator validator, Result result) {
		this.dao = dao;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public LoginController() { //para uso do CDI
		this(null,null,null);
	}
	
	@Get
	public void formulario() {
		
	}
	
	@Post
	public void autentica(Usuario usuario) {
		if(!dao.existe(usuario)) {
			validator.add(new I18nMessage("login", "login.invalido"));
			validator.onErrorUsePageOf(this).formulario();
		}
		result.redirectTo(ProdutoController.class).lista();
	}
	
}
