package com.br.produtocategoria.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto_id")

    @SequenceGenerator(name = "seq_produto_id", sequenceName = "seq_produto_id", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "valor", nullable = false, precision
            = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
