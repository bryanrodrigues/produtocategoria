package com.br.produtocategoria.exception;
import com.br.produtocategoria.models.dto.ApiResponseErrorDTO;
import com.br.produtocategoria.models.dto.NegocioExceptionDTO;
import com.br.produtocategoria.models.dto.RecursoNaoEncontradoExceptionDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<RecursoNaoEncontradoExceptionDTO>
    handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, HttpServletRequest request) {
        RecursoNaoEncontradoExceptionDTO recursoNaoEncontradoExceptionDTO = new RecursoNaoEncontradoExceptionDTO();
        recursoNaoEncontradoExceptionDTO.setMensagem(ex.getMessage());
        recursoNaoEncontradoExceptionDTO.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(recursoNaoEncontradoExceptionDTO    );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ApiResponseErrorDTO apiResponseError = new ApiResponseErrorDTO();
        apiResponseError.setMensagem("Erro de validação");
        apiResponseError.setPath(request.getRequestURI());
        apiResponseError.setStatus(HttpStatus.BAD_REQUEST.value());

        List<String> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        apiResponseError.setErros(erros);

        return ResponseEntity.badRequest().body(apiResponseError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponseErrorDTO>
    handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        ApiResponseErrorDTO apiError = new ApiResponseErrorDTO();
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setPath(request.getRequestURI());

        if (ex.getName().equals("id")) {
            apiError.setMensagem("Valor inválido para o ID.");
            String errorDetail = String.format("O valor '%s' não é um ID válido.", ex.getValue());
            apiError.setErros(List.of(errorDetail));
        } else {
            apiError.setMensagem("Tipo de parâmetro inválido.");
            String errorDetail = String.format("Parâmetro '%s' com valor '%s' não pode ser convertido para %s.",
                    ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
            apiError.setErros(List.of(errorDetail));
        }

        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<NegocioExceptionDTO>
    handleNegocioException(NegocioException ex, HttpServletRequest request) {
        NegocioExceptionDTO negocioException = new NegocioExceptionDTO();
        negocioException.setErro(ex.getMessage());
        negocioException.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(negocioException    );
    }

}