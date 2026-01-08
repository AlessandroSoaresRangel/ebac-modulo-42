package br.com.arangel.vendas.online.usecase;

import br.com.arangel.vendas.online.domain.Produto;
import br.com.arangel.vendas.online.exception.EntityNotFoundException;
import br.com.arangel.vendas.online.repository.IProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroProduto {

    private IProdutoRepository repository;

    @Autowired
    public CadastroProduto(IProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto cadastrar(@Valid Produto produto) {

        produto.setStatus(Produto.Status.ATIVO);
        return this.repository.insert(produto);
    }

    public Produto atualizar(@Valid Produto produto) {
        return this.repository.save(produto);
    }

    public void remover(String id) {
        Produto produto = this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Produto.class, "id", id));

        produto.setStatus(Produto.Status.INATIVO);
        this.repository.save(produto);
    }
}
