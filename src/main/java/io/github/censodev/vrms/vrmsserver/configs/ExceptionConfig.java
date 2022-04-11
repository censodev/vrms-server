package io.github.censodev.vrms.vrmsserver.configs;

import io.github.censodev.vrms.vrmsserver.data.models.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionConfig extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ResponseStatusException.class)
    protected ResponseEntity<Res<Object>> handleResStatusException(ResponseStatusException ex, WebRequest request) {
        log.info(ex.getMessage());
        return ResponseEntity
                .status(ex.getStatus())
                .body(new Res<>(null, ex.getReason()));
    }
}
