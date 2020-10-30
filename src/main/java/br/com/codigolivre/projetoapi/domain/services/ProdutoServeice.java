package br.com.codigolivre.projetoapi.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.codigolivre.projetoapi.domain.model.Produto;
import br.com.codigolivre.projetoapi.domain.repository.ProdutoRepository;
import br.com.codigolivre.projetoapi.domain.services.exception.ObjectNotFoundException;

@Service
public class ProdutoServeice {

	private ProdutoRepository produtoRepository;

	public ProdutoServeice(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<Produto> findAll() {
		List<Produto> produtos = new ArrayList<>();
		produtoRepository.findAll().forEach(produtos::add);
		return produtos;
	}

	public Produto findById(Long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " tipo: " + Produto.class.getName()));
	}

	public List<Produto> findByNome(String nome) {
		Optional<List<Produto>> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
		return produtos.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado  nome: " + nome + " tipo: " + Produto.class.getName()));
	}

	public Produto create(Produto obj) {
		obj.setId(null);
		return produtoRepository.save(obj);
	}

	public Produto update(Produto obj) {
		obj.getId();
		return produtoRepository.save(obj);
	}
	
	

}
