package br.com.arangel.vendas.online.controller;

import br.com.arangel.vendas.online.domain.Produto;
import br.com.arangel.vendas.online.usecase.BuscaProduto;
import br.com.arangel.vendas.online.usecase.CadastroProduto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    private BuscaProduto buscaProduto;
    private CadastroProduto cadastroProduto;


    @Autowired
    public ProdutoResource(BuscaProduto buscaProduto, CadastroProduto cadastroProduto) {
        this.buscaProduto = buscaProduto;
        this.cadastroProduto = cadastroProduto;
    }

    @GetMapping
    @Operation(summary = "Busca uma lista paginada de produtos")
    public ResponseEntity<Page<Produto>> buscar(Pageable pageable) {
        return ResponseEntity.ok(this.buscaProduto.buscar(pageable));
    }
}
