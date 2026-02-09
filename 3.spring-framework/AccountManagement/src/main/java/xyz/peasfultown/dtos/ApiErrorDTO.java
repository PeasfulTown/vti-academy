package xyz.peasfultown.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.List;

public class ApiErrorDTO {
    private int status;
    private String error;
    private String message;
    private String path;
    private Instant timestamp;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private List<String> validationErrors;

    public ApiErrorDTO(int status, String error, String message, String path, Instant timestamp, List<String> validationErrors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
        this.validationErrors = validationErrors;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}
