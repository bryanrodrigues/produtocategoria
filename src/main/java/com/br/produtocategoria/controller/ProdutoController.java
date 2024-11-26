package com.br.produtocategoria.controller;

import com.br.produtocategoria.models.dto.ProdutoDTO;
import com.br.produtocategoria.models.entity.Produto;
import com.br.produtocategoria.service.ProdutoService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos",
        description = "Operações relacionadas a produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto com o ID especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.getById(id);
        return ResponseEntity.ok(produto);
    }


    @GetMapping
    @Operation(summary = "Buscar todos os produtos", description = "Retorna todos os produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados."),
    })
    public ResponseEntity<List<ProdutoDTO>> getAll() {
        List<ProdutoDTO> produtos = produtoService.getAll();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    @Operation(summary = "Cadastro de Produto", description = "Cadastra um Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto Salvo."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
    })
    public ResponseEntity<ProdutoDTO> save(@RequestBody @Valid ProdutoDTO produtoDTO) {
        ProdutoDTO dto = produtoService.save(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização de produto", description = "Atualiza um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto Atualizado."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
    })
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO dto = produtoService.update(id, produtoDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o produto", description = "Deleta um produto")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}