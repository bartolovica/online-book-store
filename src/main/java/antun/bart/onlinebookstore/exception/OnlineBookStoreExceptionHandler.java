package antun.bart.onlinebookstore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@EnableWebMvc
@ControllerAdvice
public class OnlineBookStoreExceptionHandler {

    Logger LOG = LoggerFactory.getLogger(OnlineBookStoreExceptionHandler.class);

    @ExceptionHandler(value
            = {IllegalArgumentException.class})
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
        LOG.warn("IllegalArgumentException: {}", e.getMessage());
        return ResponseEntity.status(BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(value
            = {BookNotFoundException.class})
    public ResponseEntity handleBookNotFoundException(BookNotFoundException e) {
        LOG.warn("IllegalArgumentException: {}", e.getMessage());
        return ResponseEntity.status(NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(value
            = {CustomerNotFoundException.class})
    public ResponseEntity handleCustomerNotFoundException(CustomerNotFoundException e) {
        LOG.warn("IllegalArgumentException: {}", e.getMessage());
        return ResponseEntity.status(NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List<String>> processUnmergeException(final
                                                                MethodArgumentNotValidException ex) {

        List<String> list = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }
}
