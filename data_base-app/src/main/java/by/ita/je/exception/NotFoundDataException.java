package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundDataException extends RuntimeException {

    public NotFoundDataException(String message){
        super(message);
        log.error("Data is not found");
    }
}
