package br.com.arangel.vendas.online.useCase;

import br.com.arangel.vendas.online.domain.Cliente;
import br.com.arangel.vendas.online.repository.IClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCliente {
    private IClienteRepository repository;

    @Autowired
    public CadastroCliente(IClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente cadastrar(@Valid Cliente cliente) {
        return this.repository.insert(cliente);
    }

    public Cliente atualizar(@Valid Cliente cliente) {
        return this.repository.save(cliente);
    }

    public void remover(String id) {
        this.repository.deleteById(id);
    }
}
