package com.br.produtocategoria.service;

import com.br.produtocategoria.exception.RecursoNaoEncontradoException;
import com.br.produtocategoria.models.dto.CategoriaDTO;
import com.br.produtocategoria.models.entity.Categoria;
import com.br.produtocategoria.models.mapper.CategoriaMapper;
import com.br.produtocategoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaDTO getById(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria com ID " + id + " não encontrada."));

        return categoriaMapper.toDto(categoria);
    }

    public List<CategoriaDTO> getAll() {
        return categoriaMapper.toDto(categoriaRepository.findAll());
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {

        Categoria categoriaEntity = categoriaMapper.toEntity(categoriaDTO);

        return categoriaMapper.toDto(categoriaRepository.save(categoriaEntity));
    }

    public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria com ID " + id + " não encontrada."));

        this.categoriaMapper.updateEntityFromDto(categoriaEntity, categoriaDTO);

        return this.categoriaMapper.toDto(categoriaRepository.save(categoriaEntity));
    }

    public void delete(Long id) {
        categoriaRepository.delete(categoriaMapper.toEntity(this.getById(id)));
    }

}
