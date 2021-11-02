package by.ita.je.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public String handlerException(Model model, Exception exception){
        String message;
        int statusNumber=Integer.valueOf(exception.getMessage().substring(0,3).strip());
        if(statusNumber==404){
            message="No data found";
        }
        else if(statusNumber>=500){
            message="Server-side error";
        }
        else message="Incorrect request";
        model.addAttribute("number", statusNumber);
        model.addAttribute("message", message);
        return "error";
    }
}

