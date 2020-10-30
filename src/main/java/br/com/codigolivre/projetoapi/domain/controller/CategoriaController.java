package br.com.codigolivre.projetoapi.domain.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.codigolivre.projetoapi.domain.model.Categoria;
import br.com.codigolivre.projetoapi.domain.repository.CategoriaRepository;
import br.com.codigolivre.projetoapi.domain.services.CategoriaService;

@RestController // recebe requisiçoes http e enviar respostas para a aplicação cliente .
@RequestMapping(value = "/categorias")
public class CategoriaController {

	private CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService, CategoriaRepository categoriaRepository) {
		this.categoriaService = categoriaService;
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categorias = categoriaService.findAll();
		return ResponseEntity.ok().body(categorias);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria obj = categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "nome/{nome}")
	public ResponseEntity<List<Categoria>> findByNome(@PathVariable String nome) {
		List<Categoria> categorias = categoriaService.findByNome(nome);
		return ResponseEntity.ok().body(categorias);
	}

	// Criar uma nova categoria (POST)
	// A @RequestBody faz o Json ser convertido para o objeto java automaticamente.
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Categoria obj) {
		obj = categoriaService.create(obj);
		// chamada que pega a URI do novo recurso que foi inserido.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		// build() cria a entidade resposta sem o corpo.
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> upadate(@PathVariable Long id, @RequestBody Categoria obj) {
		obj.setId(id);
		obj = categoriaService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
