package br.com.arangel.vendas.online.useCase;

import br.com.arangel.vendas.online.domain.Cliente;
import br.com.arangel.vendas.online.exception.EntityNotFoundException;
import br.com.arangel.vendas.online.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscaCliente {

    private IClienteRepository repository;

    @Autowired
    public BuscaCliente(IClienteRepository repository) {
        this.repository = repository;
    }

    public Page<Cliente> buscar(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Cliente buscarPorId(String id) throws EntityNotFoundException {
        return this.repository.findById(id).orElseThrow(()-> new EntityNotFoundException(Cliente.class, "id não existe"));
    }

    public Boolean isCadastrado(String id) {
        Optional<Cliente> cliente = this.repository.findById(id);
        return cliente.isPresent();
    }

    public Cliente buscaPorCpf(Long cpf) throws EntityNotFoundException {
        return this.repository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException(Cliente.class, "Cpf invalido"));
    }

}
