package com.omidmohebbise.springthroughput;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

@ControllerAdvice
public class GlobalTimeoutExceptionHandler {

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<String> handleAsyncTimeout() {
        // If you prefer JSON, swap this to a small DTO + produces=application/json.
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                .body("Request timed out (global async timeout). Please try again.");
    }
}
