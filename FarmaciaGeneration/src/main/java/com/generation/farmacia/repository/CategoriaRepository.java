package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Categoria;

@Repository // anotação que indica ao spring que a classe se trata de uma classe de repositório
public interface CategoriaRepository extends JpaRepository<Categoria, Long> { // tipo de interface a se estar trabalhando
	public List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao); // métodos que usam de conceitos para realizar consultas de auto construção
	// neste caso, foi a busca pelo título, não sendo ele exato, contendo as informações sem levar em conta maiúscula e minúscula
}

