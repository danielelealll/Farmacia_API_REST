package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> { //extendendo  comandos
	public List<Produto> findAllByNomeContainingIgnoreCase(String nome); // pegando de acordo com a digitação do cliente, ele vai pegar tudo que contém a palavra (ignorando maiúsuculo e minúsculo) e ele vai utilizar como parâmetro o atributo de descrição

}

