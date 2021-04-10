package accela.coding.consoleapp.CustomException;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class InvalidUserInputException {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> personIdnvalidException(
            ItemNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
