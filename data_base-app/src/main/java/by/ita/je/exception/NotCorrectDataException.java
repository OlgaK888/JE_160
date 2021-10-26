package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotCorrectDataException extends RuntimeException{

    public NotCorrectDataException(String message){
        super(message);
        log.error("Data is not correct");

    }

}
