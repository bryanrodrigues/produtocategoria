package com.br.produtocategoria.models.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "DTO para representar um produto.")
public class ProdutoDTO {

    @Schema(description = "ID do produto.", example = "111")
    private Long id;

    @Schema(description = "Nome do produto.", example = "Coca Cola 210ml")
    @NotBlank(message = "O Campo nome é Obrigatório.")
    @Size(min = 3, max = 100)
    private String nome;

    @Schema(description = "Descição do produto.", example = "Refrigerante com acçibar sabor cola")
    @NotBlank(message = "O Campo descrição é Obrigatório.")
    @Size(min = 3, max = 100)
    private String descricao;

    @Schema(description = "Valor do produto.", example = "4.95")
    @Min(value = 0)
    @NotNull(message = "O Campo valor é Obrigatório.")
    private BigDecimal valor;

    @Schema(description = "Categoria do produto")
    @NotNull(message = "A categoria é Obrigatória.")
    private CategoriaDTO categoria;

}
