package br.com.codigolivre.projetoapi.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.codigolivre.projetoapi.domain.model.Cliente;
import br.com.codigolivre.projetoapi.domain.repository.ClienteRepository;
import br.com.codigolivre.projetoapi.domain.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public List<Cliente> findAll() {
		List<Cliente> clientes = new ArrayList<>();
		clienteRepository.findAll().forEach(clientes::add);
		return clientes;
	}

	public Cliente findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", tipo:  " + Cliente.class.getName()));
	}

	public List<Cliente> findByNome(String nome) {
		Optional<List<Cliente>> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
		return clientes.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! nome: " + nome + ", tipo: " + Cliente.class.getName()));
	}

	public Cliente create(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}

	public Cliente update(Cliente obj) {
		obj.getId();
		return clienteRepository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		clienteRepository.deleteById(id);
	}

}
