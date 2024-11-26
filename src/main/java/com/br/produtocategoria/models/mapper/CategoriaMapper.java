package com.br.produtocategoria.models.mapper;

import com.br.produtocategoria.models.dto.CategoriaDTO;
import com.br.produtocategoria.models.entity.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public Categoria toEntity(CategoriaDTO dto) {
        return modelMapper.map(dto, Categoria.class);
    }

    public CategoriaDTO toDto(Categoria entity) {
        return modelMapper.map(entity, CategoriaDTO.class);
    }

    public List<CategoriaDTO> toDto(List<Categoria> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Categoria> toEntity(List<CategoriaDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(Categoria entity, CategoriaDTO dto) {
        entity.setNome(dto.getNome());
    }
}