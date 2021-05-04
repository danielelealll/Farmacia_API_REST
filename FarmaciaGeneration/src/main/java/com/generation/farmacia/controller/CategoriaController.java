package com.generation.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

@RestController // informa para o spring que esta classe se trata de um controle
@RequestMapping("/categoria") // por qual url esta classe será acessada
@CrossOrigin(origins = "*", allowedHeaders = "*") // a classe aceitará requisições de qualquer origem
public class CategoriaController { // classe
	
	@Autowired // responsabilidade de instanciamento de interface para a anotação spring. Será acessado a partir do controller
	private CategoriaRepository repository; // serviço 
	
	@GetMapping() // mostrando que sempre que vir uma requisição externa e se o método for get, ele vai disparar o metodo get mapping
	public ResponseEntity<List<Categoria>> GetAllCategoria(){  //tipo de método find all, pegar todos os dados
		return ResponseEntity.ok(repository.findAll()); ////find all, encontrar todos os dados
	}
	
	// para uma busca por ID;
	
	@GetMapping("/{id}") // método http enviado para a nossa api
	public ResponseEntity<Categoria> findByIdCategoria(@PathVariable long id){ // método pegar por id //Pathvariabe: caminho  da variável da url
		return repository.findById(id).map(Resp -> ResponseEntity.ok(Resp)) // esse método pode devolver tanto postagem, quanto um not found  caso o objeto não exista ou exista um erro na requisição
				//  caso capture resposta positiva, devolver como recurso na requisição
				.orElse(ResponseEntity.notFound().build()); // caso n tiver dados 
		//assim que for feita alguma requisição do tipo get em "/categoria", e se passar um atributo, no caso o id, vai ser acessado este método que irá capturar qual é a variável que estamos recebendo dentro do @pathvariable, assim, retornando a interface injetada com @autowired
	}
	
	@GetMapping("/Descricao/{descricao}") // subcaminho e atributo, para não dar ambiguidade de caminho com o acima
	public ResponseEntity<List<Categoria>> findByDescricaoCategoria(@PathVariable String descricao){ // método pegar por descricao
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao)); // find all, encontrar todos os dados referentes a descricao e não discernir por letra maiúscula ou minúscula
	}
	
	@PostMapping ////Anotação para mapear POST solicitações de HTTP em métodos de tratamento específicos.
	public ResponseEntity<Categoria> post(@RequestBody Categoria categoria){ //objeto grande, com todos os atributos inseridos a ele
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria)); // end point de postagem
	}
	
	@PutMapping //Anotação para mapear PUT solicitações de HTTP em métodos de tratamento específicos.
	public ResponseEntity<Categoria> put(@RequestBody Categoria categoria){ //objeto grande, com todos os atributos inseridos a ele
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria)); // end point de postagem
	}
	
	@DeleteMapping("/{id}") //anotação mapeia solicitações HTTP DELETE para métodos de tratamento específicos.
	public void delete(@PathVariable long id) { // retornar status ok, não retorna nada
		 repository.deleteById(id); // pedindo no repositório o delete pelo ID.
	}
}
	
