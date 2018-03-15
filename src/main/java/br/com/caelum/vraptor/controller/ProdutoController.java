package br.com.caelum.vraptor.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.util.JPAUtil;
import br.com.caelum.vraptor.view.Results;

@Controller
public class ProdutoController {

	//Mapeamento Default /nomeController/nomeMetodo

	
	private final Result result; //usado para redirecionar usuario e adicionar parametros para view
	private final ProdutoDao dao;

	@Inject
	public ProdutoController(Result result, ProdutoDao dao) {
	    this.result = result;
	    this.dao = dao;
	}
	
	public ProdutoController() { //necessario criar um construtor sem argumentos
		this(null,null);
	}


	@Get("/")
	public void inicio() {
		
	}
	
	@Get
	public void sobre() { //exemplo /produto/sobre
		
	}
	
	@Get
	public void lista() {
		result.include("produtoList", dao.lista()); //passando lista para view
	}
	
	@Get
	public void listaEmXML() { //retornando a lista em xml
		result.use(Results.xml()).from(dao.lista()).serialize();
	}
	
	@Get
	public void listaEmJson() { //retornando a lista em xml
		result.use(Results.json()).from(dao.lista()).serialize();
		
		//result.use(Results.xml()).from(dao.lista()).exclude("quantidade").serialize(); // Devolve o Json sem o campo quantidade
	}
	
	@Get
	public void formulario() {
		
	}
	
	@Post
	public void adiciona(Produto produto) {
		
		dao.adiciona(produto);
	
		result.include("mensagem","Produto adicionado com sucesso!");
		result.redirectTo(this).lista(); // encaminha para Lista
	}
	
	@Get
	public void remove(Produto produto) {
		dao.remove(produto);
		
		result.include("mensagem", "Produto foi apagado com sucesso!");
		result.redirectTo(this).lista();
	}
}
