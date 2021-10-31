package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotCorrectDataException extends RuntimeException{

    public NotCorrectDataException(String message){
        super(message);
        log.error("Data is not correct");

    }

}
