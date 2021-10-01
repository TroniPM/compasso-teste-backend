/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmateus.compasso.util;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Paulo Mateus
 */
public class ErrorMessage implements Serializable {

    private int status_code;
    private String message;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatusCode(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ErrorMessage notValidData() {
        ErrorMessage error = new ErrorMessage();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Dados inválidos.");

        return error;
    }

    public static ErrorMessage notFoundData() {
        ErrorMessage error = new ErrorMessage();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage("Dados não encontrados.");

        return error;
    }
}
