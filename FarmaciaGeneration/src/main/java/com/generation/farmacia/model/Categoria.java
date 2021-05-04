package com.generation.farmacia.model;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

//inserção de anotações: anotações servem como parâmetros que colocamos em cimade classes ou propriedades que definem certos tipos de comportamentos para elas

@Entity // anotação indica classe que será entidade do jpa hebernate
@Table(name = "tb_categoria") // anotação que diz que a entidade virará uma tabela dentro do banco de dados e o nome da tabela será de "categoria"
public class Categoria { //classe
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY ) //esse ID irá se comportar na base de dados como um valor gerado. Atributo virará primary key no banco de dados
	private long id; //ID da postagem
	
	@NotNull // não poderá vir título vazio na postagem
	@Size(max = 250) // quantidade de caractere que o cliente conseguirá enviar como título. Valores mínimos e máximos na anotação
	private String descricao;
	
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) //1 para-muitos; mapeando o atributo "tema","Cascade".Este Cascadetype mostra que se algo for alterado e apagado, todas as postagens sofrerão alterações. Assim como se deletar, as postagens serão apagadas
	@JsonIgnoreProperties("categoria") // ignora as propriedades lógicas especificadas na serialização e desserialização JSON. É anotado no nível da classe.
	private List<Produto> produto;

	//metodos de acesso gets e sets
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}

