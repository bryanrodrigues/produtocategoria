package com.br.produtocategoria.controller;

import com.br.produtocategoria.exception.RecursoNaoEncontradoException;
import com.br.produtocategoria.models.dto.CategoriaDTO;
import com.br.produtocategoria.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias",
     description = "Operações relacionadas a categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria com o ID especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada."),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada.")
    })
    public ResponseEntity<CategoriaDTO> getById(@PathVariable Long id) throws RecursoNaoEncontradoException {
        CategoriaDTO categoria = categoriaService.getById(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    @Operation(summary = "Busca todas as categorias", description = "Retorna todas as categorias.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada."),
    })
    public ResponseEntity<List<CategoriaDTO>> getAll() {
        List<CategoriaDTO> categorias = categoriaService.getAll();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    @Operation(summary = "Cadastro de categoria", description = "Cadastra uma categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria Salva."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
    })
    public ResponseEntity<CategoriaDTO> save(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        CategoriaDTO categoria = categoriaService.save(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização de categoria", description = "Atualiza uma categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria Atualizada."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
    })
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoria = categoriaService.update(id, categoriaDTO);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria excluída."),
    })
    @Operation(summary = "Deleta a categoria", description = "Deleta uma categoria")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
