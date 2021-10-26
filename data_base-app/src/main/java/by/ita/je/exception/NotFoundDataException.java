package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundDataException extends RuntimeException {

    public NotFoundDataException(String message){
        super(message);
        log.error("Data is not found");
    }

}
