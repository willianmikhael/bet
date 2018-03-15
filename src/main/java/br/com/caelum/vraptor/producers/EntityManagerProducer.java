package br.com.caelum.vraptor.producers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.util.JPAUtil;

public class EntityManagerProducer {

	@Produces //Sempre que a dependencia for criar um Entitymanager irá chamar esse método por causa do produces como é o caso de ProdutoDao
	@RequestScoped // diz que o tempo de vida dele é até o fim da request (por exemplo se na mesma requisição pro chamado varias vezes o entity maneger a injeção de depencia retornará o mesmo) outros exemplos são: @SessionScoped e @ApplicationScoped
	public EntityManager criaEM() {
		return JPAUtil.criaEntityManager();
	}
	
	//Quando a injeção de depencias não usar mais o EntityManager ele irá fechar liberando recurso
	public void finaliza(@Disposes EntityManager manager) {
	      manager.close();
	}
}
