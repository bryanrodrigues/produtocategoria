package com.br.produtocategoria.models.mapper;


import com.br.produtocategoria.models.dto.ProdutoDTO;
import com.br.produtocategoria.models.entity.Categoria;
import com.br.produtocategoria.models.entity.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public Produto toEntity(ProdutoDTO dto) {
        return modelMapper.map(dto, Produto.class);
    }

    public ProdutoDTO toDto(Produto entity) {
        return modelMapper.map(entity, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toDto(List<Produto> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Produto> toEntity(List<ProdutoDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(Produto entity, ProdutoDTO dto) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        Categoria categoria = new Categoria();
        categoria.setId(dto.getCategoria().getId());
        categoria.setNome(dto.getCategoria().getNome());
        entity.setCategoria(categoria);
    }

}
