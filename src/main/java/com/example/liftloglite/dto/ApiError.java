package com.example.liftloglite.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Schema(description = "Standard API error response")
public class ApiError {

    @Schema(
            description = "HTTP status code",
            example = "400"
    )
    private int status;

    @Schema(
            description = "Short error message",
            example = "Validation failed"
    )
    private String message;

    @Schema(
            description = "Request path where the error occurred",
            example = "/api/workouts"
    )
    private String path;

    @Schema(
            description = "Timestamp when the error occurred",
            example = "2025-01-01T10:15:30Z"
    )
    private Instant timestamp;

    @Schema(
            description = "List of validation or business errors",
            example = "[\"startedAt must not be null\"]"
    )
    private List<String> errors;

    public ApiError(int status, String message, String path, Instant timestamp, List<String> errors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
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

    public List<String> getErrors() {
        return errors;
    }
}
