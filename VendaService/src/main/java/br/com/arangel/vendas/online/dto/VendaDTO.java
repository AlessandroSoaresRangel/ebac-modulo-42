package br.com.arangel.vendas.online.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class VendaDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String codigo;

    @NotNull
    private String clienteId;

    @NotNull
    private Instant dataVenda;
}
