package com.br.produtocategoria.exception;

public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super(mensagem);
    }
}
