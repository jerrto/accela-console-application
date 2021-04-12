package accela.coding.consoleapp.CustomException;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHelper {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);
    @ExceptionHandler(InvalidUserInputException.class)
    public ResponseEntity<Object> handleInvalidUserInputException( InvalidUserInputException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex);
        logger.error("");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

