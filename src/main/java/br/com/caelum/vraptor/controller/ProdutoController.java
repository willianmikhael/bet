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

@Controller
public class ProdutoController {

	//Mapeamento Default /nomeController/nomeMetodo

	
	private final Result result; //usado para redirecionar usuario e adicionar parametros para view

	@Inject
	public ProdutoController(Result result) {
	    this.result = result;
	}
	
	public ProdutoController() { //necessario criar um construtor sem argumentos
		this(null);
	}


	@Get("/")
	public void inicio() {
		
	}
	
	@Get
	public void sobre() { //exemplo /produto/sobre
		
	}
	
	@Get
	public void lista() {
		EntityManager em = JPAUtil.criaEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		
		result.include("produtoList", dao.lista()); //passando lista para view
	}
	
	@Get
	public void formulario() {
		
	}
	
	@Post
	public void adiciona(Produto produto) {
		EntityManager em = JPAUtil.criaEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		produtoDao.adiciona(produto);
		em.getTransaction().commit();
		
		result.include("mensagem","Produto adicionado com sucesso!");
		result.redirectTo(this).lista(); // encaminha para Lista
	}
	
	@Delete
	public void remove(Produto produto) {
		EntityManager em = JPAUtil.criaEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		produtoDao.remove(produto);
		em.getTransaction().commit();
	}
}
