package com.ecommerce.project.exceptions;

import com.ecommerce.project.payload.APIResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String ,String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        Map<String ,String> response=new HashMap<>();    //------to store errors like   email->Invalid email
        e.getBindingResult()         //---------contains all validation errors
                .getAllErrors()       //---------gives a list of them
                .forEach(err->{
                    String fieldName=((FieldError)err).getField();         //-------which field failed(email,name)
                    String message=err.getDefaultMessage();                //-------validation message(Invalid email)

                    response.put(fieldName,message);                       //--------Adding to Map
                });
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST); //-------JSON error response

                                                                                           //------HTTP status 400
        //This code captures validation errors and converts them
        // into a clean JSON response with field-wise messages.


    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> myResourceNotFoundException(ResourceNotFoundException e)
    {
          //String message =e.getMessage();
        String message= e.getMessage();
        APIResponse apiResponse=new APIResponse(message,false);
          return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> myAPIException(APIException e)
    {
        String message =e.getMessage();
        APIResponse apiResponse=new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

}
