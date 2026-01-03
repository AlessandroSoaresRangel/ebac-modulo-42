package br.com.arangel.vendas.online.resource;

import br.com.arangel.vendas.online.domain.Cliente;
import br.com.arangel.vendas.online.useCase.BuscaCliente;
import br.com.arangel.vendas.online.useCase.CadastroCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente")
public class ClienteResource {

    private BuscaCliente buscaCliente;
    private CadastroCliente cadastroCliente;

    @Autowired
    public ClienteResource (BuscaCliente buscaCliente, CadastroCliente cadastroCliente) {
        this.buscaCliente = buscaCliente;
        this.cadastroCliente = cadastroCliente;
    }

    @GetMapping
    @Operation(summary = "Busca uma lista paginada de clientes")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de cliente"),
            @ApiResponse(responseCode = "400", description = "Requisição errada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exeção",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<Page<Cliente>> buscar(Pageable pageable) {
        return ResponseEntity.ok(this.buscaCliente.buscar(pageable));
    }

    @GetMapping(value="/{id}")
    @Operation(summary = "Busca Cliente por id")
    public ResponseEntity<Cliente> buscaPorId(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(this.buscaCliente.buscarPorId(id));
    }

    @GetMapping(value = "/isCadastrado/{id}")
    public ResponseEntity<Boolean> isCadastrado(@PathVariable(value = "id", required = true) String id){
        return ResponseEntity.ok(this.buscaCliente.isCadastrado(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(this.cadastroCliente.cadastrar(cliente));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Cliente> buscaPorCpf(@PathVariable(value = "cpf", required = true) Long cpf) {
        return ResponseEntity.ok(this.buscaCliente.buscaPorCpf(cpf));
    }

    @PutMapping
    @Operation(summary = "Atualiza um cliente")
    public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(this.cadastroCliente.atualizar(cliente));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um cliente")
    public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
        this.cadastroCliente.remover(id);
        return ResponseEntity.ok("Removido com sucesso");
    }


}
