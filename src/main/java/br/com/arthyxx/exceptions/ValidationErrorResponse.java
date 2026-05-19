package br.com.arthyxx.exceptions;

import java.util.Date;
import java.util.List;

public record ValidationErrorResponse(
        Date timestamp,
        String message,
        List<String> errors,
        String details
) {
}
