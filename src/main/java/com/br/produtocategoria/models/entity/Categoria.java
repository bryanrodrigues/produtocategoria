package com.br.produtocategoria.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_id_seq")
    @SequenceGenerator(name = "categoria_id_seq", sequenceName = "categoria_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

}
