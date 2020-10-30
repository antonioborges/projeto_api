package br.com.codigolivre.projetoapi.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.codigolivre.projetoapi.domain.model.Categoria;
import br.com.codigolivre.projetoapi.domain.repository.CategoriaRepository;
import br.com.codigolivre.projetoapi.domain.services.exception.DataIntegrityException;
import br.com.codigolivre.projetoapi.domain.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public List<Categoria> findAll() {
		List<Categoria> categorias = new ArrayList<>();
		categoriaRepository.findAll().forEach(categorias::add);
		return categorias;
	}

	public Categoria findById(Long id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " + Categoria.class.getName()));
	}

	public List<Categoria> findByNome(String nome) {
		Optional<List<Categoria>> categorias = categoriaRepository.findByNomeContainingIgnoreCase(nome);
		return categorias.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado nome: " + nome + " tipo: " + Categoria.class.getName()));
	}

	public Categoria create(Categoria obj) {
		obj.setId(null);// se for null é criado uma nova categoria se não é inserido uma categoria.
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return categoriaRepository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possível excluir uma categoria que possui produtos");
		}
	}

}
