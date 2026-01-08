package br.com.arangel.vendas.online.usecase;


import br.com.arangel.vendas.online.domain.Venda;
import br.com.arangel.vendas.online.exception.EntityNotFoundException;
import br.com.arangel.vendas.online.repository.IVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscaVenda {

    private IVendaRepository repository;

    @Autowired
    public BuscaVenda(IVendaRepository repository) {
        this.repository = repository;
    }

    public Page<Venda> buscar(Pageable pageable) {
        return this.repository.findAll(pageable);
    }


    public Venda buscarPorCodigo(String codigo) {
        return this.repository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException(Venda.class, "codigo", codigo));
    }
}
