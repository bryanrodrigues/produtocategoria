package com.br.produtocategoria.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponseErrorDTO {
    private String mensagem;
    private int status;
    private String path;
    private List<String> erros;

}
