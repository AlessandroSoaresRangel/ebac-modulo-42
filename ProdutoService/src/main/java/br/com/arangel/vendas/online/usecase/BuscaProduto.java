package br.com.arangel.vendas.online.usecase;

import br.com.arangel.vendas.online.domain.Produto;
import br.com.arangel.vendas.online.exception.EntityNotFoundException;
import br.com.arangel.vendas.online.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscaProduto {

    private IProdutoRepository repository;

    @Autowired
    public BuscaProduto(IProdutoRepository repository) {
        this.repository = repository;
    }

    public Page<Produto> buscar(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

//    public Page<Produto> buscarPorStatus(Pageable pageable, Produto.Status status) {
//        return this.repository.FindAllByStatus(pageable, status);
//    }

    public Produto buscarPorCodigo(String codigo) {
        return this.repository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException(Produto.class, "codigo", codigo));
    }
}
