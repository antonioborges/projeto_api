package br.com.codigolivre.projetoapi.domain.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.codigolivre.projetoapi.domain.model.Produto;
import br.com.codigolivre.projetoapi.domain.services.ProdutoServeice;

@RestController
@RequestMapping(value = "/produtos")
public class PodutoController {

	private ProdutoServeice produtoServeice;

	public PodutoController(ProdutoServeice produtoServeice) {
		this.produtoServeice = produtoServeice;
	}

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produtos = produtoServeice.findAll();
		return ResponseEntity.ok().body(produtos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Produto obj = produtoServeice.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "nome/{nome}")
	public ResponseEntity<List<Produto>> findByNome(@PathVariable String nome) {
		List<Produto> produtos = produtoServeice.findByNome(nome);
		return ResponseEntity.ok().body(produtos);
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Produto obj) {
		obj = produtoServeice.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Produto obj) {
		obj.setId(id);
		obj = produtoServeice.update(obj);
		return ResponseEntity.noContent().build();
	}

	
}
