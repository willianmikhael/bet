package br.com.caelum.vraptor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Produto {

	@GeneratedValue @Id
	private Long id;

	@NotEmpty(message="{produto.nome.vazio}") @Size(min = 3, message="{produto.nome.quantidademinima}") // validation string nao pode ser vazia e o minimo de caracteres é 3
	private String nome;
	
	@Min(value = 0, message="{produto.valor.negativo}")
	private Double valor;
	
	@Min(value = 0, message="{produto.quantidade.negativa}") //validations o valor minimo para o campo é 0 e a mensagem esta no arquivo src/main/resources/messages.properties
	private Integer quantidade;

	public Produto() {
	}
	
	public Produto(String nome, Double valor, Integer quantidade) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}