package com.br.produtocategoria.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "DTO para representar uma categoria.")
public class CategoriaDTO {

    @Schema(description = "ID da categoria.", example = "111")
    private Long id;

    @Schema(description = "Nome da categoria.", example = "Bebida sem alcool")
    @NotBlank(message = "O Campo nome é Obrigatório.")
    @Size(min = 3, max = 100)
    private String nome;

}
