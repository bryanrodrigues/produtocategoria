package com.br.produtocategoria.service;

import com.br.produtocategoria.exception.NegocioException;
import com.br.produtocategoria.exception.RecursoNaoEncontradoException;
import com.br.produtocategoria.models.dto.CategoriaDTO;
import com.br.produtocategoria.models.dto.ProdutoDTO;
import com.br.produtocategoria.models.entity.Categoria;
import com.br.produtocategoria.models.entity.Produto;
import com.br.produtocategoria.models.mapper.ProdutoMapper;
import com.br.produtocategoria.repository.CategoriaRepository;
import com.br.produtocategoria.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final CategoriaRepository categoriaRepository;

    public ProdutoDTO getById(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado."));

        return produtoMapper.toDto(produto);
    }

    public List<ProdutoDTO> getAll() {
        return produtoMapper.toDto(produtoRepository.findAll());
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        this.validarCategoria(produtoDTO);
        Produto produtoEntity = produtoMapper.toEntity(produtoDTO);

        return produtoMapper.toDto(produtoRepository.save(produtoEntity));
    }

    public ProdutoDTO update(Long id, ProdutoDTO produtoDTO) {
        this.validarCategoria(produtoDTO);
        Produto produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado."));

        this.produtoMapper.updateEntityFromDto(produtoEntity, produtoDTO);

        return produtoMapper.toDto(produtoRepository.save(produtoEntity));
    }

    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado."));

        produtoRepository.delete(produto);
    }

    private void validarCategoria(ProdutoDTO produtoDTO) {

        if(Objects.nonNull(produtoDTO.getCategoria()) && Objects.nonNull(produtoDTO.getCategoria().getId())) {
            Categoria categoria = this.categoriaRepository.findById(produtoDTO.getCategoria().getId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria com ID "
                            + produtoDTO.getCategoria().getId() + " não encontrada."));
            produtoDTO.getCategoria().setNome(categoria.getNome());
            produtoDTO.getCategoria().setId(categoria.getId());

        }

        if(Objects.nonNull(produtoDTO.getCategoria()) && Objects.isNull(produtoDTO.getCategoria().getId())) {
            throw new NegocioException("O id da categoria deve ser preenchido.");
        }

    }

}
