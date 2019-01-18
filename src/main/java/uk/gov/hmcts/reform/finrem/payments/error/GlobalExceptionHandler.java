package uk.gov.hmcts.reform.finrem.payments.error;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException exception) {
        log.warn(exception.getMessage(), exception);

        return ResponseEntity.status(exception.status()).body(exception.getMessage());
    }
}
