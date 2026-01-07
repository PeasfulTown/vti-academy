package xyz.peasfultown.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import xyz.peasfultown.dtos.ApiErrorDTO;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> entityNotFoundExceptionHandler(HttpServletRequest req,
                                                                              EntityNotFoundException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("message", exception.getMessage());
        body.put("exception", exception.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> resourceNotFoundExceptionHandler(HandlerMethod method,
                                                                                HttpServletRequest req,
                                                                                ResourceNotFoundException exception) {
        ApiErrorDTO body = new ApiErrorDTO(
                HttpStatus.NOT_FOUND.value(),
                exception.getClass().getName(),
                exception.getMessage(),
                req.getRequestURI(),
                Instant.now(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }
}
